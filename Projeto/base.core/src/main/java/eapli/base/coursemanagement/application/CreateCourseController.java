package eapli.base.coursemanagement.application;

import eapli.base.coursemanagement.domain.Course;
import eapli.base.coursemanagement.domain.CourseBuilder;
import eapli.base.coursemanagement.domain.CourseStatus;
import eapli.base.coursemanagement.repositories.CourseRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

/**
 * Controlador para criar um curso.
 */
@UseCaseController
public class CreateCourseController {

    private final AuthorizationService authz;
    private final CourseRepository courseRepository;

    public CreateCourseController(final AuthorizationService authz, final CourseRepository courseRepository) {
        // dependency injection - to make this object more testable we don't create the
        // infrastructure objects to avoid coupling to the implementation. This way, the controller
        // can be used in different scenarios with different implementations of the repository. for
        // instance, unit testing.
        this.authz = authz;
        this.courseRepository = courseRepository;
    }

    public Course createCourse(final String courseCode, final String courseTitle, final String courseDescription, final int minimumEnrolments, final int maximumEnrolments) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN);

        final var newCourse = new CourseBuilder().with(courseCode, courseTitle, courseDescription, minimumEnrolments, maximumEnrolments).build();

        return courseRepository.save(newCourse);
    }
}
