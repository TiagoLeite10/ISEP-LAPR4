package eapli.base.coursemanagement.application;

import eapli.base.clientusermanagement.domain.Teacher;
import eapli.base.clientusermanagement.repositories.TeacherRepository;
import eapli.base.coursemanagement.domain.Course;
import eapli.base.coursemanagement.repositories.CourseRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

/**
 * Controlador para atribuir um professor a um curso.
 */
@UseCaseController
public class SetCourseTeacherController {
    private final AuthorizationService authz;
    private final CourseRepository courseRepository;
    private final ListCourseService listCourseService;
    private final ListTeacherService listTeacherService;

    public SetCourseTeacherController(final AuthorizationService authz, final CourseRepository courseRepo,
                                      TeacherRepository teacherRepo) {
        // dependency injection - to make this object more testable we don't create the
        // infrastructure objects to avoid coupling to the implementation. This way, the controller
        // can be used in different scenarios with different implementations of the repository. for
        // instance, unit testing.
        this.authz = authz;
        this.courseRepository = courseRepo;

        // dependency injection - only the external plugable dependencies are injected.
        listCourseService = new ListCourseService(authz, courseRepository);
        listTeacherService = new ListTeacherService(authz, teacherRepo);
    }

    public Iterable<Course> allCoursesNotClosed() {
        return listCourseService.allCoursesNotClosed();
    }

    public Iterable<Teacher> allTeachersWhoDoNotTeachThisCourse(Course theCourse) {
        return listTeacherService.allTeachersWhoDoNotTeachThisCourse(theCourse);
    }

    public Course setCourseTeacher(Course theCourse, Teacher theTeacher) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN);
        if (theCourse == null || theTeacher == null) {
            throw new IllegalArgumentException();
        }

        theCourse.addTeacherToCourse(theTeacher);

        return courseRepository.save(theCourse);
    }
}
