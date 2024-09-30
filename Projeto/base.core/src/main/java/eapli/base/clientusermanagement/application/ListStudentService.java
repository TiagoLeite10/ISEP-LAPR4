package eapli.base.clientusermanagement.application;

import eapli.base.clientusermanagement.domain.Student;
import eapli.base.clientusermanagement.repositories.StudentRepository;
import eapli.base.coursemanagement.domain.CourseCode;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

public class ListStudentService {

    private final AuthorizationService authz;

    private final StudentRepository studentRepository;

    public ListStudentService(final AuthorizationService authz, final StudentRepository studentRepository) {
        // dependency injection - to make this object more testable we don't create the
        // infrastructure objects to avoid coupling to the implementation. This way, the controller
        // can be used in different scenarios with different implementations of the repository. for
        // instance, unit testing.
        this.authz = authz;
        this.studentRepository = studentRepository;
    }

    public Iterable<Student> allApprovedEnrollmentsStudents(final CourseCode courseCode) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER);
        return studentRepository.findAllApprovedEnrollmentsStudents(courseCode);
    }
}
