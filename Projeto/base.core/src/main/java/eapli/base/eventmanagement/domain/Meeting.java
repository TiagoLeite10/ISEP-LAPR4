package eapli.base.eventmanagement.domain;

import eapli.base.eventmanagement.exception.MeetingNotHeldException;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.infrastructure.authz.domain.model.Name;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.strings.util.StringPredicates;
import eapli.framework.validations.Preconditions;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Representa a entidade Meeting.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Meeting extends Event implements AggregateRoot<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long meetingId;

    @Column
    private String title;

    @Column
    private boolean canceled;

    @ManyToOne()
    private SystemUser ownerUser;

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<ParticipationMeeting> participationMeetings = new HashSet<>();

    public Meeting(String title, EventDate eventDate, EventDuration eventDuration, SystemUser ownerUser) {
        super(eventDuration, eventDate);

        if (StringPredicates.isNullOrEmpty(title))
            throw new IllegalArgumentException("The meeting title should neither be null nor empty!");

        Preconditions.noneNull(ownerUser, "Meeting must not contain null or empty attributes!");

        this.title = title;
        this.canceled = false;
        this.ownerUser = ownerUser;
    }

    protected Meeting() {
        super();
        // for ORM
    }

    public boolean addMeetingInvitation(SystemUser user) {
        return participationMeetings.add(new ParticipationMeeting(user));
    }

    public void addMeetingInvitationList(Set<SystemUser> users) {
        for (SystemUser u : users) {
            addMeetingInvitation(u);
        }
    }

    public void invitedMeetingInvitation(SystemUser user) {
        for (ParticipationMeeting p : participationMeetings) {
            if (p.user().equals(user)) {
                p.invitedParticipation();
            }
        }
    }

    public void cancelMeetingInvitation(SystemUser user) {
        for (ParticipationMeeting p : participationMeetings) {
            if (p.user().equals(user)) {
                p.canceledParticipation();
            }
        }
    }

    public void acceptedMeetingInvitation(SystemUser user) {
        for (ParticipationMeeting p : participationMeetings) {
            if (p.user().equals(user)) {
                p.acceptedParticipation();
            }
        }
    }

    public void refusedMeetingInvitation(SystemUser user) {
        for (ParticipationMeeting p : participationMeetings) {
            if (p.user().equals(user)) {
                p.refusedParticipation();
            }
        }
    }

    public ParticipationMeeting meetingInvitation(SystemUser user) {
        for (ParticipationMeeting p : participationMeetings) {
            if (p.user().equals(user)) {
                return p;
            }
        }
        return null;
    }

    public int numberAcceptedParticipants() {
        int count = 0;

        for (ParticipationMeeting p : participationMeetings) {
            if (p.isAccepted())
                count++;
        }

        return count;
    }

    public boolean canceled() {
        return this.canceled;
    }

    public void cancelMeeting() {
        if (eventStartDate().after(Calendar.getInstance())) {
            this.canceled = true;
        } else {
            throw new MeetingNotHeldException();
        }
    }

    public Iterable<ParticipationMeeting> allParticipants() {
        return Collections.unmodifiableSet(this.participationMeetings);
    }

    public String title() {
        return this.title;
    }

    public Name createdBy() {
        return this.ownerUser.name();
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Meeting))
            return false;

        final Meeting that = (Meeting) other;
        if (this == that)
            return true;

        return super.equals(other) && ownerUser.equals(that.ownerUser)
                && participationMeetings.equals(that.participationMeetings);
    }

    @Override
    public boolean equals(final Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public Long identity() {
        return meetingId;
    }
}
