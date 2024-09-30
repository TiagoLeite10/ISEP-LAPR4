package eapli.base.eventmanagement.repositories;

import eapli.base.eventmanagement.domain.ParticipationMeeting;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;

import java.util.Calendar;

public interface ParticipationMeetingRepository extends DomainRepository<Long, ParticipationMeeting> {
    Iterable<Object[]> findPendentMeetingRequestOfUserByLimitDate(Username username, Calendar today);
}
