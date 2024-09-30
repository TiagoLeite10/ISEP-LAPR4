package eapli.base.enrollmentmanagement.application;

import eapli.base.coursemanagement.domain.CourseCode;
import eapli.base.enrollmentmanagement.domain.Enrollment;
import eapli.base.enrollmentmanagement.repositories.EnrollmentRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

/**
 * Um serviço de aplicação para evitar a duplicação de código.
 */
@ApplicationService
public class ListEnrollmentService {

    private final AuthorizationService authz;

    private final EnrollmentRepository enrollmentRepository;

    public ListEnrollmentService(final AuthorizationService authz, final EnrollmentRepository enrollmentRepository) {
        // dependency injection - to make this object more testable we don't create the
        // infrastructure objects to avoid coupling to the implementation. This way, the controller
        // can be used in different scenarios with different implementations of the repository. for
        // instance, unit testing.
        this.authz = authz;
        this.enrollmentRepository = enrollmentRepository;
    }

    public Iterable<Enrollment> allPendingEnrollments(final CourseCode courseCode) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN);
        return enrollmentRepository.findAllPendingEnrollments(courseCode);
    }
}
