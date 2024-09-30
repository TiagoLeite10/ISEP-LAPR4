package eapli.base.coursemanagement.application;

import eapli.base.clientusermanagement.domain.Student;
import eapli.base.clientusermanagement.domain.Teacher;
import eapli.base.coursemanagement.domain.Course;
import eapli.base.coursemanagement.repositories.CourseRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

/**
 * Um serviço de aplicação para evitar a duplicação de código.
 */
@ApplicationService
public class ListCourseService {

    private final AuthorizationService authz;
    private final CourseRepository courseRepository;

    public ListCourseService(final AuthorizationService authz, final CourseRepository courseRepository) {
        // dependency injection - to make this object more testable we don't create the
        // infrastructure objects to avoid coupling to the implementation. This way, the controller
        // can be used in different scenarios with different implementations of the repository. for
        // instance, unit testing.
        this.authz = authz;
        this.courseRepository = courseRepository;
    }

    public Iterable<Course> allCloseCourses() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN);
        return courseRepository.findCloseCourses();
    }

    public Iterable<Course> allOpenCourses() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN);
        return courseRepository.findOpenCourses();
    }

    public Iterable<Course> allEnrollCourses() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.STUDENT, BaseRoles.POWER_USER, BaseRoles.ADMIN);
        return courseRepository.findEnrollCourses();
    }

    public Iterable<Course> allEnrollAndInProgressCourses() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN);
        return courseRepository.findAllEnrollAndInProgressCourses();
    }

    public Iterable<Course> allInProgressCourses() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN);
        return courseRepository.findInProgressCourses();
    }

    public Iterable<Course> allCourses() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN);
        return courseRepository.findAll();
    }

    public Iterable<Course> allStudentCourses(final Student student) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.STUDENT);
        return courseRepository.findAllStudentCourses(student);
    }

    public Iterable<Course> allApprovedStudentCoursesInProgress(final Student student) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER);
        return courseRepository.findAllApprovedStudentCoursesInProgress(student);
    }

    public Iterable<Course> allCoursesNotEnrolled(final Student student) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.STUDENT);
        return courseRepository.findAllCoursesNotEnrolled(student);
    }

    public Iterable<Course> allTeacherCourses(final Teacher teacher) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER);
        return courseRepository.findAllTeacherCourses(teacher);
    }

    public Iterable<Course> allTeacherCoursesInProgress(final Teacher teacher) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER);
        return courseRepository.findAllTeacherCoursesInProgress(teacher);
    }

    public Iterable<Course> allCoursesWithoutResponsible() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN);
        return courseRepository.findAllCoursesWithoutResponsible();
    }

    public Iterable<Course> allCoursesNotClosed() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN);
        return courseRepository.findAllCoursesNotClosed();
    }

    public Iterable<Course> allTeacherCoursesNotClosed(final Teacher teacher) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER);
        return courseRepository.findAllTeacherCoursesNotClosed(teacher);
    }

    public Iterable<Course> allCoursesInProgress(final SystemUser user) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.TEACHER, BaseRoles.STUDENT);
        return courseRepository.findAllCoursesInProgress(user);
    }
}
