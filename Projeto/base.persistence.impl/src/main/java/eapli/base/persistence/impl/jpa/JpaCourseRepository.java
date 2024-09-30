package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.clientusermanagement.domain.Student;
import eapli.base.clientusermanagement.domain.Teacher;
import eapli.base.coursemanagement.domain.*;
import eapli.base.coursemanagement.repositories.CourseRepository;
import eapli.base.enrollmentmanagement.domain.EnrollmentStatus;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class JpaCourseRepository extends JpaAutoTxRepository<Course, CourseCode, CourseCode>
        implements CourseRepository {

    public JpaCourseRepository(final TransactionalContext autoTx) {
        super(autoTx, "courseCode");
    }

    public JpaCourseRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "courseCode");
    }

    @Override
    public Iterable<Course> findCloseCourses() {
        final TypedQuery<Course> query = entityManager().createQuery(
                "SELECT c FROM Course c WHERE c.courseStatus = :status", Course.class);
        query.setParameter("status", CourseStatus.CLOSE);

        return query.getResultList();
    }

    @Override
    public Iterable<Course> findOpenCourses() {
        final TypedQuery<Course> query = entityManager().createQuery(
                "SELECT c FROM Course c WHERE c.courseStatus = :status", Course.class);
        query.setParameter("status", CourseStatus.OPEN);

        return query.getResultList();
    }

    @Override
    public Iterable<Course> findEnrollCourses() {
        final TypedQuery<Course> query = entityManager().createQuery(
                "SELECT c FROM Course c WHERE c.courseStatus = :status", Course.class);
        query.setParameter("status", CourseStatus.ENROLL);

        return query.getResultList();
    }

    @Override
    public Iterable<Course> findAllEnrollAndInProgressCourses() {
        final TypedQuery<Course> query = entityManager().createQuery(
                "SELECT c FROM Course c WHERE c.courseStatus = :status1 OR c.courseStatus = :status2", Course.class);
        query.setParameter("status1", CourseStatus.ENROLL);
        query.setParameter("status2", CourseStatus.IN_PROGRESS);

        return query.getResultList();
    }

    @Override
    public Iterable<Course> findInProgressCourses() {
        final TypedQuery<Course> query = entityManager().createQuery(
                "SELECT c FROM Course c WHERE c.courseStatus = :status", Course.class);
        query.setParameter("status", CourseStatus.IN_PROGRESS);

        return query.getResultList();
    }

    @Override
    public Iterable<Course> findAllStudentCourses(Student student) {
        final TypedQuery<Course> query = entityManager().createQuery(
                "SELECT c FROM Course c JOIN c.enrollments e WHERE e.student = :student",
                Course.class);
        query.setParameter("student", student);

        return query.getResultList();
    }

    @Override
    public Iterable<Course> findAllApprovedStudentCoursesInProgress(Student student) {
        final TypedQuery<Course> query = entityManager().createQuery(
                "SELECT c FROM Course c JOIN c.enrollments e WHERE e.student = :student " +
                        "AND e.enrollmentStatus = :statusEnrollment AND c.courseStatus = :statusCourse",
                Course.class);
        query.setParameter("student", student);
        query.setParameter("statusEnrollment", EnrollmentStatus.APPROVED);
        query.setParameter("statusCourse", CourseStatus.IN_PROGRESS);

        return query.getResultList();
    }

    @Override
    public Iterable<Course> findAllStudentApprovedCoursesInProgressWithActualFormativeExams(Student student) {
        final TypedQuery<Course> query = entityManager().createQuery(
                "SELECT DISTINCT c FROM Course c JOIN c.formativeExams fe JOIN c.enrollments e WHERE e.student = :student " +
                        "AND e.enrollmentStatus = :statusEnrollment AND c.courseStatus = :statusCourse " +
                        "AND :actualDate BETWEEN fe.dateTime.openingDateTime AND fe.dateTime.closingDateTime " +
                        "GROUP BY fe.id HAVING COUNT(fe) > 0",
                Course.class);
        query.setParameter("student", student);
        query.setParameter("statusEnrollment", EnrollmentStatus.APPROVED);
        query.setParameter("statusCourse", CourseStatus.IN_PROGRESS);
        query.setParameter("actualDate", Calendar.getInstance());

        return query.getResultList();
    }

    @Override
    public Iterable<Course> findAllCoursesNotEnrolled(Student student) {
        final TypedQuery<Course> query = entityManager().createQuery(
                "SELECT c FROM Course c WHERE c.courseStatus = :status AND c NOT IN (SELECT c1 FROM Course c1 " +
                        "JOIN c1.enrollments e WHERE e.student = :student)",
                Course.class);
        query.setParameter("status", CourseStatus.ENROLL);
        query.setParameter("student", student);

        return query.getResultList();
    }

    @Override
    public Iterable<Course> findAllTeacherCourses(Teacher teacher) {
        final TypedQuery<Course> query = entityManager().createQuery(
                "SELECT c FROM Course c JOIN c.teachers t WHERE t = :teacher OR c.responsibleTeacher = :teacher", Course.class);
        query.setParameter("teacher", teacher);

        return query.getResultList();
    }

    @Override
    public Iterable<Course> findAllTeacherCoursesInProgress(Teacher teacher) {
        final TypedQuery<Course> query = entityManager().createQuery(
                "SELECT c FROM Course c JOIN c.teachers t WHERE c.courseStatus = :status AND " +
                        "t = :teacher OR c.responsibleTeacher = :teacher", Course.class);
        query.setParameter("status", CourseStatus.IN_PROGRESS);
        query.setParameter("teacher", teacher);

        return query.getResultList();
    }

    @Override
    public Iterable<Course> findAllTeacherCoursesNotClosed(Teacher teacher) {
        final TypedQuery<Course> query = entityManager().createQuery(
                "SELECT c FROM Course c JOIN c.teachers t WHERE c.courseStatus != :status AND " +
                        "t = :teacher OR c.responsibleTeacher = :teacher", Course.class);
        query.setParameter("status", CourseStatus.CLOSED);
        query.setParameter("teacher", teacher);

        return query.getResultList();
    }

    @Override
    public Iterable<Course> findAllCoursesWithoutResponsible() {
        final TypedQuery<Course> query = entityManager().createQuery(
                "SELECT c FROM Course c WHERE c.responsibleTeacher = NULL", Course.class);

        return query.getResultList();
    }

    @Override
    public Iterable<Course> findAllCoursesNotClosed() {
        final TypedQuery<Course> query = entityManager().createQuery(
                "SELECT c FROM Course c WHERE c.courseStatus != :status", Course.class);
        query.setParameter("status", CourseStatus.CLOSED);

        return query.getResultList();
    }

    @Override
    public Iterable<Course> findAllCoursesInProgress(SystemUser user) {
        final TypedQuery<Course> query = entityManager().createQuery(
                "SELECT c FROM Course c WHERE c.courseStatus = :statusCourse AND " +
                        "(c.courseCode IN (SELECT c1.courseCode FROM Course c1 JOIN c1.teachers t WHERE t.systemUser = :user OR c1.responsibleTeacher.systemUser = :user) " +
                        "OR c.courseCode IN (SELECT c2.courseCode FROM Course c2 JOIN c2.enrollments e WHERE e.student.systemUser = :user AND e.enrollmentStatus = :statusEnrollment))",
                Course.class);
        query.setParameter("statusCourse", CourseStatus.IN_PROGRESS);
        query.setParameter("user", user);
        query.setParameter("statusEnrollment", EnrollmentStatus.APPROVED);

        return query.getResultList();
    }

    @Override
    public Iterable<Course> saveAll(Iterable<Course> courses) {
        List<Course> ret = new ArrayList<>();

        for (Course course : courses) {
            ret.add(this.save(course));
        }

        return ret;
    }
}
