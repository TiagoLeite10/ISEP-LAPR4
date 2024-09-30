package eapli.base.persistence.impl.inmemory;

import eapli.base.eventmanagement.domain.ParticipationMeeting;
import eapli.base.eventmanagement.repositories.ParticipationMeetingRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.Calendar;

public class InMemoryParticipationMeetingRepository extends InMemoryDomainRepository<ParticipationMeeting, Long>
        implements ParticipationMeetingRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<Object[]> findPendentMeetingRequestOfUserByLimitDate(Username username, Calendar today) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
