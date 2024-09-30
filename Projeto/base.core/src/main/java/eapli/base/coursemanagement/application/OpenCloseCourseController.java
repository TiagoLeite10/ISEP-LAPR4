package eapli.base.coursemanagement.application;

import eapli.base.coursemanagement.domain.Course;
import eapli.base.coursemanagement.repositories.CourseRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

/**
 * Controlador para abrir/fechar um curso
 */
@UseCaseController
public class OpenCloseCourseController {

    private final AuthorizationService authz;
    private final CourseRepository courseRepository;
    private final ListCourseService listCourseService;

    public OpenCloseCourseController(final AuthorizationService authz, final CourseRepository repo) {
        // dependency injection - to make this object more testable we don't create the
        // infrastructure objects to avoid coupling to the implementation. This way, the controller
        // can be used in different scenarios with different implementations of the repository. for
        // instance, unit testing.
        this.authz = authz;
        this.courseRepository = repo;

        // dependency injection - only the external plugable dependencies are injected.
        listCourseService = new ListCourseService(authz, repo);
    }

    public Iterable<Course> listCloseCourses() {
        return listCourseService.allCloseCourses();
    }

    public Iterable<Course> listInProgressCourses() {
        return listCourseService.allInProgressCourses();
    }

    public Course openCloseCourse(Course theCourse) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN);
        if (theCourse == null) {
            throw new IllegalArgumentException();
        }

        theCourse.nextState();
        return courseRepository.save(theCourse);
    }

}
