package eapli.base.questionmanagement.application;

import eapli.base.clientusermanagement.domain.Teacher;
import eapli.base.clientusermanagement.repositories.TeacherRepository;
import eapli.base.coursemanagement.domain.Course;
import eapli.base.coursemanagement.repositories.CourseRepository;
import eapli.base.questionmanagement.domain.Question;
import eapli.base.questionmanagement.lprog.ExamQuestionMain;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

import java.util.Optional;

/**
 * Controlador para adicionar/atualizar questões de exame a um repositório para serem utilizadas nos exames formativos automáticos.
 */
@UseCaseController
public class AddExamQuestionController {

    private final AuthorizationService authz;

    private final TeacherRepository teacherRepo;

    private final CourseRepository courseRepo;

    public AddExamQuestionController(final AuthorizationService authz, final TeacherRepository teacherRepo,
                                     final CourseRepository courseRepo) {
        this.authz = authz;
        this.teacherRepo = teacherRepo;
        this.courseRepo = courseRepo;
    }

    private Optional<Teacher> currentUserTeacher() {
        return authz.session().flatMap(s -> teacherRepo.findByUsername(s.authenticatedUser().username()));
    }

    public Iterable<Course> listAllTeacherCoursesInProgress() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER);

        final Teacher theTeacher = currentUserTeacher().orElseThrow(IllegalStateException::new);
        return courseRepo.findAllTeacherCoursesInProgress(theTeacher);
    }

    public Iterable<Question> parseQuestions(final String pathToFile) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER);

        return ExamQuestionMain.parseWithVisitor(pathToFile);
    }

    public Course addQuestions(final Course theCourse, final Iterable<Question> questions) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER);

        theCourse.addListQuestion(questions);

        return courseRepo.save(theCourse);
    }
}
