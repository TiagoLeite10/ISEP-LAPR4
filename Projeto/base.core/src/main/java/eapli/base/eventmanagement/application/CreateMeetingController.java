package eapli.base.eventmanagement.application;

import eapli.base.coursemanagement.application.ListCourseService;
import eapli.base.coursemanagement.domain.Course;
import eapli.base.coursemanagement.repositories.CourseRepository;
import eapli.base.eventmanagement.domain.Meeting;
import eapli.base.eventmanagement.domain.MeetingBuilder;
import eapli.base.eventmanagement.repositories.MeetingRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.Calendar;
import java.util.Set;

/**
 * Controlador para criar uma reunião.
 */
@UseCaseController
public class CreateMeetingController {

    private final AuthorizationService authz;

    private final UserManagementService userSvc;

    private final ListCourseService listCourseService;

    private final MeetingRepository meetingRepository;

    public CreateMeetingController(final AuthorizationService authz, final MeetingRepository meetingRepository,
                                   final CourseRepository courseRepository) {
        // dependency injection - only the external plugable dependencies are injected.
        this.authz = authz;
        this.userSvc = AuthzRegistry.userService();
        this.meetingRepository = meetingRepository;

        this.listCourseService = new ListCourseService(authz, courseRepository);
    }

    private SystemUser currentUser() {
        return authz.session().orElseThrow(IllegalStateException::new).authenticatedUser();
    }

    public Iterable<SystemUser> listAllUsers() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.TEACHER, BaseRoles.STUDENT);

        return userSvc.allUsers();
    }

    public Meeting addMeeting(final String name, final Calendar date, final int duration) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.TEACHER, BaseRoles.STUDENT);
        final var meeting = new MeetingBuilder().with(name, date, duration, currentUser()).build();

        return meetingRepository.save(meeting);
    }

    public Meeting inviteUsers(final Meeting meeting, final Set<SystemUser> users) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.TEACHER, BaseRoles.STUDENT);

        //Convida todos os utilizadores
        meeting.addMeetingInvitationList(users);

        // Verificar se os utilizadores estão disponíveis para colocar o estado do convite em invited ou cancel
        checkAvailabilityOfParticipants(meeting, users);

        return meetingRepository.save(meeting);
    }

    private void checkAvailabilityOfParticipants(final Meeting meeting, final Set<SystemUser> users) {
        Calendar meetingStartDate = meeting.eventStartDate();
        Calendar meetingEndDate = meeting.eventEndDate();

        for (SystemUser u : users) {
            Iterable<Course> courses;

            if (u.roleTypes().contains(BaseRoles.TEACHER) || u.roleTypes().contains(BaseRoles.STUDENT)) {
                // Colocar estado do convite em enviado
                meeting.invitedMeetingInvitation(u);

                // Para cada user do tipo professor e aluno vou ver em que curso ministra/está inscrito
                courses = listCourseService.allCoursesInProgress(u);
                for (Course c : courses) {
                    // Para cada curso que o aluno está inscrito vou ver a sua disponibilidade
                    if (!c.checkAvailability(meetingStartDate, meetingEndDate)) {
                        // Colocar estado do convite em cancelado
                        meeting.cancelMeetingInvitation(u);
                    }
                }
            }
        }
    }
}
