package eapli.base.enrollmentmanagement.application;

import eapli.base.clientusermanagement.domain.MecanographicNumber;
import eapli.base.clientusermanagement.repositories.StudentRepository;
import eapli.base.coursemanagement.domain.Course;
import eapli.base.coursemanagement.domain.CourseCode;
import eapli.base.coursemanagement.repositories.CourseRepository;
import eapli.base.enrollmentmanagement.domain.BulkEnrollmentFileCSV;
import eapli.base.enrollmentmanagement.exception.ErrorInFileException;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import org.springframework.data.util.Pair;

import java.util.List;

/**
 * Controlador para inscrever em massa alunos nos cursos.
 */
@UseCaseController
public class BulkEnrollmentController {

    private final AuthorizationService authz;
    private final CourseRepository courseRepository;
    private final EnrollmentService enrollmentService;

    public BulkEnrollmentController(final AuthorizationService authz, final CourseRepository courseRepo,
                                    final StudentRepository studentRepo) {
        // dependency injection - to make this object more testable we don't create the
        // infrastructure objects to avoid coupling to the implementation. This way, the controller
        // can be used in different scenarios with different implementations of the repository. for
        // instance, unit testing.
        this.authz = authz;
        this.courseRepository = courseRepo;

        // dependency injection - only the external plugable dependencies are injected.
        enrollmentService = new EnrollmentService(authz, studentRepo, courseRepository);
    }

    public List<Pair<MecanographicNumber, CourseCode>> getFileData(String filePath) {
        return new BulkEnrollmentFileCSV().readFile(filePath);
    }

    public Iterable<Course> bulkStudentEnrollments(List<Pair<MecanographicNumber, CourseCode>> fileData) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN);

        Iterable<Course> courses = enrollmentService.bulkEnrollment(fileData);

        if (!courses.iterator().hasNext())
            throw new ErrorInFileException("There is no corret information on the file!");

        return courseRepository.saveAll(courses);
    }
}
