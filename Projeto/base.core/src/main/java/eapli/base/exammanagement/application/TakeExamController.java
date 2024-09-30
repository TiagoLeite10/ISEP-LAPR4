package eapli.base.exammanagement.application;

import eapli.base.clientusermanagement.domain.Student;
import eapli.base.clientusermanagement.repositories.StudentRepository;
import eapli.base.coursemanagement.domain.Course;
import eapli.base.coursemanagement.domain.CourseCode;
import eapli.base.exammanagement.domain.Exam;
import eapli.base.exammanagement.repositories.ExamRepository;
import eapli.base.takenexammanagement.domain.TakenExam;
import eapli.base.takenexammanagement.domain.TakenExamBuilder;
import eapli.base.takenexammanagement.lprog.ResolutionMain;
import eapli.base.takenexammanagement.repositories.TakenExamRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

import java.util.Optional;

/**
 * Controlador para fazer um exame.
 */
@UseCaseController
public class TakeExamController {

    private final AuthorizationService authz;

    private final StudentRepository studentRepository;

    private final ExamRepository examRepository;

    private final TakenExamRepository takenExamRepository;

    public TakeExamController(final AuthorizationService authz, final StudentRepository studentRepository, final ExamRepository examRepository, final TakenExamRepository takenExamRepository) {
        this.authz = authz;
        this.studentRepository = studentRepository;
        this.examRepository = examRepository;
        this.takenExamRepository = takenExamRepository;
    }

    private Optional<Student> currentUserStudent() {
        return authz.session().flatMap(s -> studentRepository.findByUsername(s.authenticatedUser().username()));
    }

    public Iterable<Course> listAllStudentCoursesInProgress() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.STUDENT);

        final Student student = currentUserStudent().orElseThrow(IllegalStateException::new);
        return examRepository.findAllApprovedStudentCoursesInProgressWithExams(student);
    }

    public Iterable<Exam> ListAllCourseExamsInATimeInterval(final CourseCode courseCode) {
        final Student student = currentUserStudent().orElseThrow(IllegalStateException::new);
        return examRepository.findAllCourseExamsInATimeInterval(courseCode, student);
    }

    public float getGrade(final String filePath, final Exam exam) {
        return ResolutionMain.parseWithVisitor(filePath, exam);
    }

    public void createTakenExam(final Exam exam, final float grade) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.STUDENT);

        final Student student = currentUserStudent().orElseThrow(IllegalStateException::new);
        final TakenExam takenExam = new TakenExamBuilder().with(student, grade, exam).build();

        takenExamRepository.save(takenExam);
    }

    public String getExamQuestions(final Exam exam) {
        return exam.examQuestionsToString();
    }

    public String getExamFeedback(final Exam exam) {
        return exam.feedback();
    }

}
