package eapli.base.eventmanagement.application;

import eapli.base.eventmanagement.domain.Meeting;
import eapli.base.eventmanagement.domain.ParticipationMeeting;
import eapli.base.eventmanagement.repositories.MeetingRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

/**
 * Controlador para listar os participantes numa reunião.
 */
@UseCaseController
public class ListParticipationMeetingController {
    private final AuthorizationService authz;

    private final MeetingRepository repo;

    public ListParticipationMeetingController(final AuthorizationService authz, final MeetingRepository meetingRepository) {
        // dependency injection - only the external plugable dependencies are injected.
        this.authz = authz;
        this.repo = meetingRepository;
    }

    private SystemUser currentUser() {
        return authz.session().orElseThrow(IllegalStateException::new).authenticatedUser();
    }

    public Iterable<Meeting> listAllMeetings() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.TEACHER, BaseRoles.STUDENT);
        return repo.findAllMeetings(currentUser().identity());
    }

    public Iterable<ParticipationMeeting> listAllParticipants(final Meeting meeting) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.TEACHER, BaseRoles.STUDENT);
        return meeting.allParticipants();
    }
}
