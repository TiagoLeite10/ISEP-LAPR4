package eapli.base.takenexammanagement.application;

import eapli.base.clientusermanagement.domain.Teacher;
import eapli.base.clientusermanagement.repositories.TeacherRepository;
import eapli.base.coursemanagement.domain.Course;
import eapli.base.coursemanagement.domain.CourseCode;
import eapli.base.coursemanagement.repositories.CourseRepository;
import eapli.base.takenexammanagement.dto.GradePerStudent;
import eapli.base.takenexammanagement.repositories.TakenExamRepository;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

import java.util.Optional;

/**
 * Controlador para listar as notas dos exames de um curso.
 */
@UseCaseController
public class ListGradesByCourseController {

    private final AuthorizationService authz;
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;
    private final TakenExamRepository takenExamRepository;

    public ListGradesByCourseController(final AuthorizationService authz, final TeacherRepository teacherRepository, final CourseRepository courseRepository, TakenExamRepository takenExamRepository) {
        this.authz = authz;
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
        this.takenExamRepository = takenExamRepository;
    }

    private Optional<Teacher> currentUserTeacher() {
        return authz.session().flatMap(s -> teacherRepository.findByUsername(s.authenticatedUser().username()));
    }

    public Iterable<Course> listAllTeacherCourses() {
        final Teacher teacher = currentUserTeacher().orElseThrow(IllegalStateException::new);
        return courseRepository.findAllTeacherCourses(teacher);
    }

    public Iterable<GradePerStudent> listAllGradesByCourse(CourseCode courseCode) {
        return takenExamRepository.findAllGradesByCourse(courseCode);
    }
}
