package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.eventmanagement.domain.Meeting;
import eapli.base.eventmanagement.domain.ParticipationMeetingStatus;
import eapli.base.eventmanagement.repositories.MeetingRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import java.util.Calendar;

public class JpaMeetingRepository extends JpaAutoTxRepository<Meeting, Long, Long>
        implements MeetingRepository {

    public JpaMeetingRepository(final TransactionalContext autoTx) {
        super(autoTx, "meetingId");
    }

    public JpaMeetingRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "meetingId");
    }

    @Override
    public Iterable<Meeting> findAllMeetingsToBeHeld(Username user) {
        final TypedQuery<Meeting> query = entityManager().createQuery(
                "SELECT m FROM Meeting m WHERE m.ownerUser.username = :name AND m.canceled = FALSE AND m.eventDate.dateTime >= :actualDateTime",
                Meeting.class);

        query.setParameter("name", user);
        query.setParameter("actualDateTime", Calendar.getInstance(), TemporalType.TIMESTAMP);

        return query.getResultList();
    }

    @Override
    public Iterable<Meeting> findAllMeetings(Username user) {
        final TypedQuery<Meeting> query = entityManager().createQuery(
                "SELECT m FROM Meeting m WHERE m.ownerUser.username = :name OR m IN " +
                        "(SELECT m1 FROM Meeting m1 INNER JOIN m1.participationMeetings p WHERE p.user.username = :name " +
                        "AND p.participationMeetingStatus = :status)",
                Meeting.class);

        query.setParameter("name", user);
        query.setParameter("status", ParticipationMeetingStatus.ACCEPTED);

        return query.getResultList();
    }
}

