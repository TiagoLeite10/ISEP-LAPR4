package eapli.base.persistence.impl.inmemory;

import eapli.base.eventmanagement.domain.Meeting;
import eapli.base.eventmanagement.repositories.MeetingRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryMeetingRepository extends InMemoryDomainRepository<Meeting, Long>
        implements MeetingRepository {

    @Override
    public Iterable<Meeting> findAllMeetingsToBeHeld(Username user) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public Iterable<Meeting> findAllMeetings(Username user) {
        throw new UnsupportedOperationException("not implemented yet");
    }
}
