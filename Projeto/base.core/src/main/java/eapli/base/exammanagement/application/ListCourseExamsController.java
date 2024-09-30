package eapli.base.exammanagement.application;

import eapli.base.clientusermanagement.domain.Teacher;
import eapli.base.clientusermanagement.repositories.TeacherRepository;
import eapli.base.coursemanagement.application.ListCourseService;
import eapli.base.coursemanagement.domain.Course;
import eapli.base.coursemanagement.domain.CourseCode;
import eapli.base.coursemanagement.repositories.CourseRepository;
import eapli.base.exammanagement.domain.Exam;
import eapli.base.exammanagement.repositories.ExamRepository;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

import java.util.Optional;

/**
 * Controlador para listar todos os exames de um curso.
 */
@UseCaseController
public class ListCourseExamsController {

    private final AuthorizationService authz;

    private final TeacherRepository teacherRepository;

    private final ListCourseService listCourseService;

    private final ListExamService listExamService;

    public ListCourseExamsController(final AuthorizationService authz, final TeacherRepository teacherRepository,
                                     final CourseRepository courseRepository, final ExamRepository examRepository) {
        this.authz = authz;
        this.teacherRepository = teacherRepository;

        // dependency injection - only the external plugable dependencies are injected.
        listCourseService = new ListCourseService(authz, courseRepository);
        listExamService = new ListExamService(authz, examRepository);
    }


    private Optional<Teacher> currentUserTeacher() {
        return authz.session().flatMap(s -> teacherRepository.findByUsername(s.authenticatedUser().username()));
    }

    public Iterable<Course> listAllTeacherCourses() {
        final Teacher teacher = currentUserTeacher().orElseThrow(IllegalStateException::new);
        return listCourseService.allTeacherCourses(teacher);
    }

    public Iterable<Exam> listAllCourseExams(final CourseCode courseCode) {
        return listExamService.allCourseExams(courseCode);
    }
}
