package eapli.base.eventmanagement.application;

import eapli.base.clientusermanagement.application.ListStudentService;
import eapli.base.clientusermanagement.domain.Student;
import eapli.base.clientusermanagement.domain.Teacher;
import eapli.base.clientusermanagement.repositories.StudentRepository;
import eapli.base.clientusermanagement.repositories.TeacherRepository;
import eapli.base.coursemanagement.application.ListCourseService;
import eapli.base.coursemanagement.domain.Course;
import eapli.base.coursemanagement.domain.CourseCode;
import eapli.base.coursemanagement.repositories.CourseRepository;
import eapli.base.eventmanagement.domain.ExtraClassBuilder;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

import java.util.Calendar;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

/**
 * Controlador para criar uma aula extra.
 */
@UseCaseController
public class CreateExtraClassController {

    private final AuthorizationService authz;

    private final TeacherRepository teacherRepository;

    private final CourseRepository courseRepository;

    private final ListCourseService listCourseService;

    private final ListStudentService listStudentService;

    public CreateExtraClassController(final AuthorizationService authz, final TeacherRepository teacherRepository,
                                      final CourseRepository courseRepository, final StudentRepository studentRepository) {
        // dependency injection - to make this object more testable we don't create the
        // infrastructure objects to avoid coupling to the implementation. This way, the controller
        // can be used in different scenarios with different implementations of the repository. for
        // instance, unit testing.
        this.authz = authz;
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;

        // dependency injection - only the external plugable dependencies are injected.
        listCourseService = new ListCourseService(authz, courseRepository);
        listStudentService = new ListStudentService(authz, studentRepository);
    }

    private Optional<Teacher> currentUserTeacher() {
        return authz.session().flatMap(s -> teacherRepository.findByUsername(s.authenticatedUser().username()));
    }

    public Iterable<Course> listAllTeacherCoursesInProgress() {
        final Teacher teacher = currentUserTeacher().orElseThrow(IllegalStateException::new);
        return listCourseService.allTeacherCoursesInProgress(teacher);
    }

    public Iterable<Student> listAllApprovedEnrollmentsStudents(final CourseCode courseCode) {
        return listStudentService.allApprovedEnrollmentsStudents(courseCode);
    }

    public void checkStudentsAvailability(final Calendar startDate, final Calendar endDate, Set<Student> students) {
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            Iterable<Course> courses = listCourseService.allApprovedStudentCoursesInProgress(student);
            boolean removeStudent = true;

            for (Course course : courses) {
                if (course.checkStudentsAvailability(startDate, endDate, student)) {
                    removeStudent = false;
                    break;
                }
            }

            if (removeStudent) {
                iterator.remove();
            }
        }
    }

    public Course addExtraClassToCourse(final Course course, final Calendar date, final int duration, final Set<Student> students) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER);
        final Teacher teacher = currentUserTeacher().orElseThrow(IllegalStateException::new);
        final var extraClass = new ExtraClassBuilder().with(date, duration, teacher, students).build();

        course.addExtraClass(extraClass);

        return courseRepository.save(course);
    }
}
