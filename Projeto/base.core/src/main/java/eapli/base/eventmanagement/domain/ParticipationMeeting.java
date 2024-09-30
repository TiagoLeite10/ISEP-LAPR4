package eapli.base.eventmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.infrastructure.authz.domain.model.Name;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Representa a entidade ParticipationMeeting.
 */
@Entity
public class ParticipationMeeting implements AggregateRoot<Long> {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private SystemUser user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ParticipationMeetingStatus participationMeetingStatus;

    public ParticipationMeeting(SystemUser user) {
        Preconditions.noneNull(user, "Participation Meeting must not contain null user!");

        this.user = user;
        this.participationMeetingStatus = ParticipationMeetingStatus.CREATED;
    }

    protected ParticipationMeeting() {
        // for ORM only
    }

    public SystemUser user() {
        return this.user;
    }

    public ParticipationMeetingStatus status() {
        return this.participationMeetingStatus;
    }

    public boolean isCreated() {
        return participationMeetingStatus.equals(ParticipationMeetingStatus.CREATED);
    }

    public void invitedParticipation() {
        this.participationMeetingStatus = ParticipationMeetingStatus.INVITED;
    }

    public void canceledParticipation() {
        this.participationMeetingStatus = ParticipationMeetingStatus.CANCELED;
    }

    public void acceptedParticipation() {
        this.participationMeetingStatus = ParticipationMeetingStatus.ACCEPTED;
    }

    public void refusedParticipation() {
        this.participationMeetingStatus = ParticipationMeetingStatus.REFUSED;
    }

    public boolean isInvited() {
        return participationMeetingStatus.equals(ParticipationMeetingStatus.INVITED);
    }

    public boolean isAccepted() {
        return participationMeetingStatus.equals(ParticipationMeetingStatus.ACCEPTED);
    }

    public boolean isRefused() {
        return participationMeetingStatus.equals(ParticipationMeetingStatus.REFUSED);
    }

    public boolean isCanceled() {
        return participationMeetingStatus.equals(ParticipationMeetingStatus.CANCELED);
    }

    /**
     * Método para aceitar/recusar um convite para participação numa meeting.
     *
     * @param yesOrNo True se for para aceitar o convite, false se for para recusar.
     */
    public void acceptMeeting(boolean yesOrNo) {
        if (!isInvited())
            throw new IllegalStateException("You cannot alter the state of this invitation!");

        if (yesOrNo)
            this.participationMeetingStatus = ParticipationMeetingStatus.ACCEPTED;
        else
            this.participationMeetingStatus = ParticipationMeetingStatus.REFUSED;
    }

    @Override
    public boolean sameAs(final Object other) {
        if (!(other instanceof ParticipationMeeting)) {
            return false;
        }

        final ParticipationMeeting that = (ParticipationMeeting) other;
        if (this == other) {
            return true;
        }

        return user.equals(that.user);
    }

    @Override
    public boolean equals(final Object o) {
        return sameAs(o);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public Long identity() {
        return this.id;
    }
}
