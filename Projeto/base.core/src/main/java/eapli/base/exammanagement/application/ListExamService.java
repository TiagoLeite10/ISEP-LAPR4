package eapli.base.exammanagement.application;

import eapli.base.coursemanagement.domain.CourseCode;
import eapli.base.exammanagement.domain.Exam;
import eapli.base.exammanagement.repositories.ExamRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.Username;

/**
 * Um serviço de aplicação para evitar a duplicação de código.
 */
@ApplicationService
public class ListExamService {

    private final AuthorizationService authz;
    private final ExamRepository examRepository;

    public ListExamService(final AuthorizationService authz, final ExamRepository examRepository) {
        // dependency injection - to make this object more testable we don't create the
        // infrastructure objects to avoid coupling to the implementation. This way, the controller
        // can be used in different scenarios with different implementations of the repository. for
        // instance, unit testing.
        this.authz = authz;
        this.examRepository = examRepository;
    }

    public Iterable<Exam> allCourseExams(final CourseCode courseCode) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER);
        return examRepository.findAllCourseExams(courseCode);
    }

    public Iterable<Exam> allFutureExams(final Username user) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.STUDENT);
        return examRepository.findAllFutureExams(user);
    }
}
