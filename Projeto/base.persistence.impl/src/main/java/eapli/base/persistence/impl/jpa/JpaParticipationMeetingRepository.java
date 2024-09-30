package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.eventmanagement.domain.Meeting;
import eapli.base.eventmanagement.domain.ParticipationMeeting;
import eapli.base.eventmanagement.domain.ParticipationMeetingStatus;
import eapli.base.eventmanagement.repositories.ParticipationMeetingRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;
import java.util.Calendar;

public class JpaParticipationMeetingRepository extends JpaAutoTxRepository<ParticipationMeeting, Long, Long>
        implements ParticipationMeetingRepository {

    public JpaParticipationMeetingRepository(final TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    public JpaParticipationMeetingRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "id");
    }

    @Override
    public Iterable<Object[]> findPendentMeetingRequestOfUserByLimitDate(Username username, Calendar date) {
        final TypedQuery<Object[]> query = entityManager().createQuery(
                "SELECT pm, m FROM Meeting m JOIN m.participationMeetings pm WHERE " +
                        "pm.user.id = :username AND pm.participationMeetingStatus = :status " +
                        "AND m.eventDate.dateTime > :date AND m.canceled = FALSE",
                Object[].class);

        query.setParameter("username", username);
        query.setParameter("status", ParticipationMeetingStatus.INVITED);
        query.setParameter("date", date);

        return query.getResultList();
    }
}
