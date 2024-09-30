package eapli.base.classmanagement.aplication;

import eapli.base.classmanagement.domain.RecurringClass;
import eapli.base.classmanagement.domain.RecurringClassBuilder;
import eapli.base.classmanagement.repositories.RecurringClassRepository;
import eapli.base.clientusermanagement.domain.Teacher;
import eapli.base.clientusermanagement.repositories.TeacherRepository;
import eapli.base.coursemanagement.application.ListCourseService;
import eapli.base.coursemanagement.domain.Course;
import eapli.base.coursemanagement.repositories.CourseRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

import java.util.Calendar;
import java.util.Optional;

/**
 * Controlador para atualizar o horário de uma aula recorrente.
 */
@UseCaseController
public class UpdateClassScheduleController {

    private final AuthorizationService authz;

    private final TeacherRepository teacherRepository;

    private final CourseRepository courseRepository;

    private final ListCourseService listCourseService;

    private final ListRecurringClassService listRecurringClassService;

    public UpdateClassScheduleController(final AuthorizationService authz, final TeacherRepository teacherRepository,
                                         final CourseRepository courseRepository, final RecurringClassRepository recurringClassRepository) {
        // dependency injection - to make this object more testable we don't create the
        // infrastructure objects to avoid coupling to the implementation. This way, the controller
        // can be used in different scenarios with different implementations of the repository. for
        // instance, unit testing.
        this.authz = authz;
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;

        // dependency injection - only the external plugable dependencies are injected.
        listCourseService = new ListCourseService(authz, courseRepository);
        listRecurringClassService = new ListRecurringClassService(authz, recurringClassRepository);
    }

    private Optional<Teacher> currentUserTeacher() {
        return authz.session().flatMap(s -> teacherRepository.findByUsername(s.authenticatedUser().username()));
    }

    public Iterable<Course> listAllTeacherCoursesNotClosed() {
        final Teacher teacher = currentUserTeacher().orElseThrow(IllegalStateException::new);
        return listCourseService.allTeacherCoursesNotClosed(teacher);
    }

    public Iterable<RecurringClass> listAllTeacherRecurringClass() {
        final Teacher teacher = currentUserTeacher().orElseThrow(IllegalStateException::new);
        return listRecurringClassService.allTeacherRecurringClass(teacher);
    }

    public Course addClassInstanceToRecurringClass(final Course course, final RecurringClass recurringClass, final Calendar newDateTime) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER);
        course.checkCourseAvailability(newDateTime, recurringClass);
        recurringClass.addClassInstance(newDateTime);

        return courseRepository.save(course);
    }
}
