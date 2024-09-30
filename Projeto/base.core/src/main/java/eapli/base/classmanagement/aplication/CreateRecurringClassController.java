package eapli.base.classmanagement.aplication;

import eapli.base.classmanagement.domain.*;
import eapli.base.clientusermanagement.domain.Teacher;
import eapli.base.clientusermanagement.repositories.TeacherRepository;
import eapli.base.coursemanagement.application.ListCourseService;
import eapli.base.coursemanagement.domain.Course;
import eapli.base.coursemanagement.repositories.CourseRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

/**
 * Controlador para criar uma aula recorrente.
 */
@UseCaseController
public class CreateRecurringClassController {

    private final AuthorizationService authz;

    private final TeacherRepository teacherRepository;

    private final CourseRepository courseRepository;

    private final ListCourseService listCourseService;

    public CreateRecurringClassController(final AuthorizationService authz, final TeacherRepository teacherRepository,
                                          final CourseRepository courseRepository) {
        // dependency injection - to make this object more testable we don't create the
        // infrastructure objects to avoid coupling to the implementation. This way, the controller
        // can be used in different scenarios with different implementations of the repository. for
        // instance, unit testing.
        this.authz = authz;
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;

        // dependency injection - only the external plugable dependencies are injected.
        listCourseService = new ListCourseService(authz, courseRepository);
    }

    private Optional<Teacher> currentUserTeacher() {
        return authz.session().flatMap(s -> teacherRepository.findByUsername(s.authenticatedUser().username()));
    }

    public Iterable<Course> listAllTeacherCoursesNotClosed() {
        final Teacher teacher = currentUserTeacher().orElseThrow(IllegalStateException::new);
        return listCourseService.allTeacherCoursesNotClosed(teacher);
    }

    public Course addRecurringClassToCourse(final Course course, final String classTitle, final Calendar classStartTime, final int classDuration,
                                            final ClassDayOfTheWeek classDayOfTheWeek) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER);
        final Teacher teacher = currentUserTeacher().orElseThrow(IllegalStateException::new);
        final var recurringClass = new RecurringClassBuilder().with(classTitle, classStartTime, classDuration, classDayOfTheWeek, teacher).build();

        course.checkCourseAvailability(recurringClass);

        course.addRecurringClass(recurringClass);

        return courseRepository.save(course);
    }
}
