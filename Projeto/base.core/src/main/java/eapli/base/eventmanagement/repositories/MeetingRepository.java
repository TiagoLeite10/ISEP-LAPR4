package eapli.base.eventmanagement.repositories;

import eapli.base.eventmanagement.domain.Meeting;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;

public interface MeetingRepository extends DomainRepository<Long, Meeting> {

    Iterable<Meeting> findAllMeetingsToBeHeld(final Username user);

    Iterable<Meeting> findAllMeetings(final Username user);
}
