package eapli.base.formativeexammanagement.application;

import eapli.base.clientusermanagement.domain.Student;
import eapli.base.clientusermanagement.repositories.StudentRepository;
import eapli.base.coursemanagement.domain.Course;
import eapli.base.coursemanagement.repositories.CourseRepository;
import eapli.base.exammanagement.domain.Exam;
import eapli.base.formativeexammanagement.application.FormativeExamService;
import eapli.base.formativeexammanagement.domain.FormativeExam;
import eapli.base.formativeexammanagement.repositories.FormativeExamRepository;
import eapli.base.questionmanagement.repositories.QuestionRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.validations.Preconditions;

import java.util.Optional;

/**
 * Controlador para fazer um exame formativo automático.
 */
@UseCaseController
public class TakeAutomaticFormativeExamController {

    private final AuthorizationService authz;
    private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;
    private final FormativeExamRepository formativeExamRepo;
    private final FormativeExamService formativeExamService;

    public TakeAutomaticFormativeExamController(final AuthorizationService authz, final StudentRepository studentRepo,
                                                final CourseRepository courseRepo,
                                                final FormativeExamRepository formativeExamRepo,
                                                final QuestionRepository questionRepo) {
        // dependency injection - to make this object more testable we don't create the
        // infrastructure objects to avoid coupling to the implementation. This way, the controller
        // can be used in different scenarios with different implementations of the repository. for
        // instance, unit testing.
        this.authz = authz;
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
        this.formativeExamRepo = formativeExamRepo;

        // dependency injection - only the external plugable dependencies are injected.
        formativeExamService = new FormativeExamService(authz, questionRepo);
    }

    private Optional<Student> currentUserStudent() {
        return authz.session().flatMap(s -> studentRepo.findByUsername(s.authenticatedUser().username()));
    }

    public Iterable<Course> findAllStudentApprovedCoursesInProgressWithActualFormativeExams() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.STUDENT);

        final Student theStudent = currentUserStudent().orElseThrow(IllegalStateException::new);
        return courseRepo.findAllStudentApprovedCoursesInProgressWithActualFormativeExams(theStudent);
    }

    public Iterable<FormativeExam> listAllAvailableFormativeExamsOfTheCourse(Course theCourse) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.STUDENT);

        Preconditions.noneNull(theCourse);

        return this.formativeExamRepo.listAllAvailableFormativeExamsOfACourse(theCourse.identity());
    }

    public Exam transformFormativeExamDataInExam(Course theCourse, FormativeExam theFormativeExam) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.STUDENT);

        Preconditions.noneNull(theFormativeExam);

        return this.formativeExamService.transformFormativeExamDataInExam(theCourse.identity(), theFormativeExam);
    }

    public String getExamQuestionsString(Exam theExam) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.STUDENT);
        Preconditions.noneNull(theExam);
        return theExam.examQuestionsToString();
    }

    public String getExamFeedback(Exam theExam) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.STUDENT);
        Preconditions.noneNull(theExam);
        return theExam.feedback();
    }

    public float parseFormativeExamAnswers(String filePath, Exam theExam) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.STUDENT);

        Preconditions.noneNull(filePath, theExam);
        Preconditions.nonEmpty(filePath);

        return this.formativeExamService.parseAndHandleFormativeExamGrade(filePath, theExam);
    }
}
