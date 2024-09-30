package eapli.base.persistence.impl.inmemory;

import eapli.base.clientusermanagement.domain.Student;
import eapli.base.clientusermanagement.domain.Teacher;
import eapli.base.coursemanagement.domain.Course;
import eapli.base.coursemanagement.domain.CourseCode;
import eapli.base.coursemanagement.domain.CourseStatus;
import eapli.base.coursemanagement.repositories.CourseRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryCourseRepository extends InMemoryDomainRepository<Course, CourseCode> implements CourseRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<Course> findCloseCourses() {
        return match(course -> course.status().equals(CourseStatus.CLOSE));
    }

    @Override
    public Iterable<Course> findOpenCourses() {
        return match(course -> course.status().equals(CourseStatus.OPEN));
    }

    @Override
    public Iterable<Course> findEnrollCourses() {
        return match(course -> course.status().equals(CourseStatus.ENROLL));
    }

    @Override
    public Iterable<Course> findAllEnrollAndInProgressCourses() {
        return match(course -> course.status().equals(CourseStatus.ENROLL) || course.status().equals(CourseStatus.IN_PROGRESS));
    }

    @Override
    public Iterable<Course> findInProgressCourses() {
        return match(course -> course.status().equals(CourseStatus.IN_PROGRESS));
    }

    @Override
    public Iterable<Course> findAllStudentCourses(Student student) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public Iterable<Course> findAllApprovedStudentCoursesInProgress(Student student) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public Iterable<Course> findAllStudentApprovedCoursesInProgressWithActualFormativeExams(Student student) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public Iterable<Course> findAllCoursesNotEnrolled(Student student) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public Iterable<Course> findAllTeacherCourses(Teacher teacher) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public Iterable<Course> findAllTeacherCoursesInProgress(Teacher teacher) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public Iterable<Course> findAllTeacherCoursesNotClosed(Teacher teacher) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    public Iterable<Course> findAllCoursesWithoutResponsible() {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public Iterable<Course> findAllCoursesNotClosed() {
        return match(course -> !course.status().equals(CourseStatus.CLOSED));
    }

    @Override
    public Iterable<Course> findAllCoursesInProgress(SystemUser user) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public Iterable<Course> saveAll(Iterable<Course> courses) {
        List<Course> ret = new ArrayList<>();
        forEach(course -> ret.add(save(course)));
        return ret;
    }

}
