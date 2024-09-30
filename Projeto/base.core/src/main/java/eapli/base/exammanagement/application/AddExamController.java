package eapli.base.exammanagement.application;

import eapli.base.clientusermanagement.domain.Teacher;
import eapli.base.clientusermanagement.repositories.TeacherRepository;
import eapli.base.coursemanagement.domain.Course;
import eapli.base.coursemanagement.repositories.CourseRepository;
import eapli.base.exammanagement.domain.Exam;
import eapli.base.exammanagement.lprog.ExamMain;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

import java.util.Optional;

/**
 * Controlador para adicionar/atualizar um exame.
 */
@UseCaseController
public class AddExamController {

    private final AuthorizationService authz;

    private final TeacherRepository teacherRepository;

    private final CourseRepository courseRepository;

    public AddExamController(final AuthorizationService authz, final TeacherRepository teacherRepository,
                             final CourseRepository courseRepository) {
        this.authz = authz;
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }

    private Optional<Teacher> currentUserTeacher() {
        return authz.session().flatMap(s -> teacherRepository.findByUsername(s.authenticatedUser().username()));
    }

    public Iterable<Course> listAllTeacherCoursesInProgress() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER);

        final Teacher teacher = currentUserTeacher().orElseThrow(IllegalStateException::new);
        return courseRepository.findAllTeacherCoursesInProgress(teacher);
    }

    public Exam getExam(final String filePath) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER);

        return ExamMain.parseWithVisitor(filePath);
    }

    public Course addExam(final Course course, final Exam exam) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER);

        course.addExam(exam);

        return courseRepository.save(course);
    }
}
