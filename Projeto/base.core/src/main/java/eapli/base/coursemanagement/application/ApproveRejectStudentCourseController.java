package eapli.base.coursemanagement.application;

import eapli.base.coursemanagement.domain.Course;
import eapli.base.coursemanagement.domain.CourseCode;
import eapli.base.enrollmentmanagement.application.ListEnrollmentService;
import eapli.base.enrollmentmanagement.domain.Enrollment;
import eapli.base.coursemanagement.repositories.CourseRepository;
import eapli.base.enrollmentmanagement.repositories.EnrollmentRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

/**
 * Controlador para aprovar/rejeitar alunos numa disciplina.
 */
@UseCaseController
public class ApproveRejectStudentCourseController {

    private final AuthorizationService authz;

    private final CourseRepository courseRepository;

    private final ListCourseService listCourseService;

    private final ListEnrollmentService listEnrollmentService;

    public ApproveRejectStudentCourseController(final AuthorizationService authz, final CourseRepository courseRepository, final EnrollmentRepository enrollmentRepository) {
        // dependency injection - to make this object more testable we don't create the
        // infrastructure objects to avoid coupling to the implementation. This way, the controller
        // can be used in different scenarios with different implementations of the repository. for
        // instance, unit testing.
        this.authz = authz;
        this.courseRepository = courseRepository;

        // dependency injection - only the external plugable dependencies are injected.
        listCourseService = new ListCourseService(authz, courseRepository);
        listEnrollmentService = new ListEnrollmentService(authz, enrollmentRepository);
    }

    public Iterable<Course> listAllEnrollCourses() {
        return listCourseService.allEnrollCourses();
    }

    public Iterable<Enrollment> listAllPendingEnrollments(final CourseCode courseCode) {
        return listEnrollmentService.allPendingEnrollments(courseCode);
    }

    public Course approvedEnrollment(final Course course, final Enrollment enrollment) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN);

        course.approveCourseEnrollment(enrollment);

        return courseRepository.save(course);
    }

    public Course rejectEnrollment(final Course course, final Enrollment enrollment) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN);

        course.rejectCourseEnrollment(enrollment);

        return courseRepository.save(course);
    }
}
