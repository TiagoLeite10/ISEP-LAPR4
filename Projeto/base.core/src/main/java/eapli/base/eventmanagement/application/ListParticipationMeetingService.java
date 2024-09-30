package eapli.base.eventmanagement.application;

import eapli.base.eventmanagement.repositories.ParticipationMeetingRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.Username;

import java.util.Calendar;

public class ListParticipationMeetingService {

    private final AuthorizationService authz;

    private final ParticipationMeetingRepository participationMeetingRepository;

    public ListParticipationMeetingService(final AuthorizationService authz, final ParticipationMeetingRepository participationMeetingRepository) {
        // dependency injection - to make this object more testable we don't create the
        // infrastructure objects to avoid coupling to the implementation. This way, the controller
        // can be used in different scenarios with different implementations of the repository. for
        // instance, unit testing.
        this.authz = authz;
        this.participationMeetingRepository = participationMeetingRepository;
    }

    public Iterable<Object[]> listUserPendentMeetingRequest(Username username) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.TEACHER, BaseRoles.STUDENT);
        return participationMeetingRepository.findPendentMeetingRequestOfUserByLimitDate(username, Calendar.getInstance());
    }

}
