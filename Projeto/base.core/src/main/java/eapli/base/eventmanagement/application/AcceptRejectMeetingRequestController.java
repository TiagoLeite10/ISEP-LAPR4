package eapli.base.eventmanagement.application;

import eapli.base.eventmanagement.domain.ParticipationMeeting;
import eapli.base.eventmanagement.repositories.ParticipationMeetingRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.Username;

import java.util.Calendar;

/**
 * Controlador para aceitar/recusar um convite para uma reunião.
 */
@UseCaseController
public class AcceptRejectMeetingRequestController {

    private final AuthorizationService authz;

    private final ParticipationMeetingRepository participationMeetingRepository;

    public AcceptRejectMeetingRequestController(final AuthorizationService authz,
                                                final ParticipationMeetingRepository participationMeetingRepository) {
        // dependency injection - only the external plugable dependencies are injected.
        this.authz = authz;
        this.participationMeetingRepository = participationMeetingRepository;
    }

    private Username currentUser() {
        return authz.session().orElseThrow(IllegalStateException::new).authenticatedUser().identity();
    }

    public Iterable<Object[]> listUserPendentMeetingRequest() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.TEACHER, BaseRoles.STUDENT);

        return participationMeetingRepository.findPendentMeetingRequestOfUserByLimitDate(this.currentUser(), Calendar.getInstance());
    }

    public ParticipationMeeting acceptMeeting(ParticipationMeeting theParticipationMeeting, boolean yesOrNo) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.TEACHER, BaseRoles.STUDENT);

        if (theParticipationMeeting == null)
            throw new IllegalArgumentException("Invalid participation meeting!");

        theParticipationMeeting.acceptMeeting(yesOrNo);

        return participationMeetingRepository.save(theParticipationMeeting);
    }

}
