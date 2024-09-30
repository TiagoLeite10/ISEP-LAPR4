package eapli.base.formativeexammanagement.application;

import eapli.base.clientusermanagement.domain.Teacher;
import eapli.base.clientusermanagement.repositories.TeacherRepository;
import eapli.base.coursemanagement.domain.Course;
import eapli.base.coursemanagement.repositories.CourseRepository;
import eapli.base.formativeexammanagement.domain.FormativeExam;
import eapli.base.formativeexammanagement.lprog.FormativeExamMain;
import eapli.base.questionmanagement.repositories.QuestionRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

import java.util.Optional;

/**
 * Controlador para criar/atualizar um exame formativo automático.
 */
@UseCaseController
public class CreateAutomaticFormativeExamsController {

    private final AuthorizationService authz;

    private final TeacherRepository teacherRepo;

    private final CourseRepository courseRepo;

    private final FormativeExamService formativeExamService;

    public CreateAutomaticFormativeExamsController(final AuthorizationService authz, final TeacherRepository teacherRepo,
                                                   final CourseRepository courseRepo, final QuestionRepository questionRepo) {
        this.authz = authz;
        this.teacherRepo = teacherRepo;
        this.courseRepo = courseRepo;

        this.formativeExamService = new FormativeExamService(authz, questionRepo);
    }

    private Optional<Teacher> currentUserTeacher() {
        return authz.session().flatMap(s -> teacherRepo.findByUsername(s.authenticatedUser().username()));
    }

    public Iterable<Course> findAllTeacherCoursesInProgress() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER);

        final Teacher theTeacher = currentUserTeacher().orElseThrow(IllegalStateException::new);
        return courseRepo.findAllTeacherCoursesInProgress(theTeacher);
    }

    public FormativeExam parseFormativeExam(final Course theCourse, final String pathToFile) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER);

        FormativeExam theFormativeExam = FormativeExamMain.parseWithVisitor(pathToFile);
        formativeExamService.checkFormativeExamNumberOfTypeQuestions(theCourse, theFormativeExam);

        return theFormativeExam;
    }

    public Course createFormativeExam(final Course theCourse, final FormativeExam theFormativeExam) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER);

        theCourse.addFormativeExam(theFormativeExam);

        return courseRepo.save(theCourse);
    }
}
