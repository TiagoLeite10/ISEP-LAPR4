package eapli.base.coursemanagement.application;

import eapli.base.clientusermanagement.domain.Teacher;
import eapli.base.clientusermanagement.repositories.TeacherRepository;
import eapli.base.coursemanagement.domain.Course;
import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

@ApplicationService
public class ListTeacherService {
    private final AuthorizationService authz;
    private final TeacherRepository teacherRepository;

    public ListTeacherService(final AuthorizationService authz, final TeacherRepository teacherRepository) {
        // dependency injection - to make this object more testable we don't create the
        // infrastructure objects to avoid coupling to the implementation. This way, the controller
        // can be used in different scenarios with different implementations of the repository. for
        // instance, unit testing.
        this.authz = authz;
        this.teacherRepository = teacherRepository;
    }

    public Iterable<Teacher> allTeachers() {
        return teacherRepository.findAll();
    }

    public Iterable<Teacher> allTeachersWhoDoNotTeachThisCourse(Course theCourse) {
        return teacherRepository.allTeachersWhoDoNotTeachThisCourse(theCourse.identity());
    }
}
