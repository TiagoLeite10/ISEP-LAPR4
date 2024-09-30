package eapli.base.eventmanagement.application;

import eapli.base.eventmanagement.domain.Meeting;
import eapli.base.eventmanagement.repositories.MeetingRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

/**
 * Controlador para cancelar uma reunião.
 */
@UseCaseController
public class CancelMeetingController {

    private final AuthorizationService authz;

    private final MeetingRepository meetingRepository;

    public CancelMeetingController(final AuthorizationService authz, final MeetingRepository meetingRepository) {
        // dependency injection - only the external plugable dependencies are injected.
        this.authz = authz;
        this.meetingRepository = meetingRepository;
    }

    private SystemUser currentUser() {
        return authz.session().orElseThrow(IllegalStateException::new).authenticatedUser();
    }

    public Iterable<Meeting> listAllMeetingsToBeHeld() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.TEACHER, BaseRoles.STUDENT);

        return meetingRepository.findAllMeetingsToBeHeld(currentUser().identity());
    }

    public Meeting cancelMeeting(final Meeting meeting) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.TEACHER, BaseRoles.STUDENT);

        meeting.cancelMeeting();

        return meetingRepository.save(meeting);
    }
}
