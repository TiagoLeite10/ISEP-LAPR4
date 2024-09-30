package eapli.base.enrollmentmanagement.application;

import eapli.base.clientusermanagement.domain.Student;
import eapli.base.clientusermanagement.repositories.StudentRepository;
import eapli.base.coursemanagement.application.ListCourseService;
import eapli.base.coursemanagement.domain.Course;
import eapli.base.enrollmentmanagement.domain.EnrollmentBuilder;
import eapli.base.coursemanagement.repositories.CourseRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

import java.util.Optional;

/**
 * Controlador para inscrever um aluno num curso.
 */
@UseCaseController
public class CreateEnrollmentController {

    private final AuthorizationService authz;

    private final StudentRepository studentRepository;

    private final CourseRepository courseRepository;

    private final ListCourseService svc;


    public CreateEnrollmentController(final AuthorizationService authz, final StudentRepository studentRepository, final CourseRepository courseRepository) {
        // dependency injection - to make this object more testable we don't create the
        // infrastructure objects to avoid coupling to the implementation. This way, the controller
        // can be used in different scenarios with different implementations of the repository. for
        // instance, unit testing.
        this.authz = authz;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.svc = new ListCourseService(authz, courseRepository);

    }

    private Optional<Student> currentUserStudent() {
        return authz.session().flatMap(s -> studentRepository.findByUsername(s.authenticatedUser().username()));
    }

    public Iterable<Course> listAllCoursesNotEnrolled() {
        final Student student = currentUserStudent().orElseThrow(IllegalStateException::new);
        return svc.allCoursesNotEnrolled(student);
    }

    public Course addEnrollment(final Course course) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.STUDENT);
        final Student student = currentUserStudent().orElseThrow(IllegalStateException::new);
        final var newEnrollment = new EnrollmentBuilder().withStudent(student).build();

        course.addEnrollment(newEnrollment);

        return courseRepository.save(course);
    }
}
