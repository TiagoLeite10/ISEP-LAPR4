package eapli.base.coursemanagement.repositories;

import eapli.base.clientusermanagement.domain.Student;
import eapli.base.clientusermanagement.domain.Teacher;
import eapli.base.coursemanagement.domain.Course;
import eapli.base.coursemanagement.domain.CourseCode;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;

public interface CourseRepository extends DomainRepository<CourseCode, Course> {

    Iterable<Course> findCloseCourses();

    Iterable<Course> findOpenCourses();

    Iterable<Course> findEnrollCourses();

    Iterable<Course> findAllEnrollAndInProgressCourses();

    Iterable<Course> findInProgressCourses();

    Iterable<Course> findAllStudentCourses(Student student);

    Iterable<Course> findAllApprovedStudentCoursesInProgress(Student student);

    Iterable<Course> findAllStudentApprovedCoursesInProgressWithActualFormativeExams(Student student);

    Iterable<Course> findAllCoursesNotEnrolled(Student student);

    Iterable<Course> findAllTeacherCourses(Teacher teacher);

    Iterable<Course> findAllTeacherCoursesInProgress(Teacher teacher);

    Iterable<Course> findAllTeacherCoursesNotClosed(Teacher teacher);

    Iterable<Course> findAllCoursesWithoutResponsible();

    Iterable<Course> findAllCoursesNotClosed();

    Iterable<Course> findAllCoursesInProgress(SystemUser user);

    Iterable<Course> saveAll(Iterable<Course> courses);

}
