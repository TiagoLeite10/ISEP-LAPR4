package eapli.base.coursemanagement.application;

import eapli.base.clientusermanagement.domain.Student;
import eapli.base.clientusermanagement.domain.Teacher;
import eapli.base.clientusermanagement.repositories.StudentRepository;
import eapli.base.clientusermanagement.repositories.TeacherRepository;
import eapli.base.coursemanagement.domain.Course;
import eapli.base.coursemanagement.repositories.CourseRepository;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

import java.util.Optional;

/**
 * Controlador para listar cursos.
 */
@UseCaseController
public class ListCourseController {

    private final AuthorizationService authz;

    private final TeacherRepository teacherRepository;

    private final StudentRepository studentRepository;

    private final ListCourseService svc;

    public ListCourseController(final AuthorizationService authz, final TeacherRepository teacherRepository,
                                final StudentRepository studentRepository, final CourseRepository courseRepository) {
        this.authz = authz;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;

        // dependency injection - only the external plugable dependencies are injected.
        svc = new ListCourseService(authz, courseRepository);
    }

    private Optional<Teacher> currentUserTeacher() {
        return authz.session().flatMap(s -> teacherRepository.findByUsername(s.authenticatedUser().username()));
    }

    private Optional<Student> currentUserStudent() {
        return authz.session().flatMap(s -> studentRepository.findByUsername(s.authenticatedUser().username()));
    }

    public Iterable<Course> listManagerCourses() {
        return svc.allCourses();
    }

    public Iterable<Course> listTeacherCourses() {
        final Teacher teacher = currentUserTeacher().orElseThrow(IllegalStateException::new);
        return svc.allTeacherCourses(teacher);
    }

    public Iterable<Course> listStudentCourses() {
        final Student student = currentUserStudent().orElseThrow(IllegalStateException::new);
        return svc.allStudentCourses(student);
    }
}