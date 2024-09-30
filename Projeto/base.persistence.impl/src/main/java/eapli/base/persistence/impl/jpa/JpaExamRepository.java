package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.clientusermanagement.domain.Student;
import eapli.base.coursemanagement.domain.Course;
import eapli.base.coursemanagement.domain.CourseCode;
import eapli.base.coursemanagement.domain.CourseStatus;
import eapli.base.enrollmentmanagement.domain.EnrollmentStatus;
import eapli.base.exammanagement.domain.Exam;
import eapli.base.exammanagement.domain.ExamTitle;
import eapli.base.exammanagement.repositories.ExamRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import java.util.Calendar;

/**
 * @author Gabriel Gonçalves (1191296)
 */
public class JpaExamRepository extends JpaAutoTxRepository<Exam, ExamTitle, ExamTitle>
        implements ExamRepository {

    public JpaExamRepository(final TransactionalContext autoTx) {
        super(autoTx, "title");
    }

    public JpaExamRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "title");
    }

    @Override
    public Iterable<Exam> findAllCourseExams(CourseCode courseCode) {
        final TypedQuery<Exam> query = entityManager().createQuery(
                "SELECT ex FROM Course c JOIN c.exams ex WHERE c.courseCode = :code", Exam.class);
        query.setParameter("code", courseCode);

        return query.getResultList();
    }

    @Override
    public Iterable<Exam> findAllFutureExams(Username user) {
        final TypedQuery<Exam> query = entityManager().createQuery(
                "SELECT ex FROM Course c JOIN c.enrollments en JOIN c.exams ex WHERE en.student.systemUser.username = :name " +
                        "AND ex.dateTime.openingDateTime >= :actualTime",
                Exam.class);

        query.setParameter("name", user);
        query.setParameter("actualTime", Calendar.getInstance(), TemporalType.TIMESTAMP);

        return query.getResultList();
    }

    @Override
    public Iterable<Course> findAllApprovedStudentCoursesInProgressWithExams(Student student) {
        final TypedQuery<Course> query = entityManager().createQuery(
                "SELECT DISTINCT c FROM Course c JOIN c.exams ex INNER JOIN c.enrollments e ON e.student = :student " +
                        "WHERE e.enrollmentStatus = :statusEnrollment AND c.courseStatus = :statusCourse " +
                        "AND :actualDate BETWEEN ex.dateTime.openingDateTime AND ex.dateTime.closingDateTime " +
                        "AND ex NOT IN(SELECT te_ex FROM TakenExam te JOIN te.exam te_ex WHERE te_ex.id = ex.id AND :student = te.student)" +
                        "GROUP BY ex.id HAVING COUNT(ex) > 0",
                Course.class);
        query.setParameter("student", student);
        query.setParameter("statusEnrollment", EnrollmentStatus.APPROVED);
        query.setParameter("statusCourse", CourseStatus.IN_PROGRESS);
        query.setParameter("actualDate", Calendar.getInstance());

        return query.getResultList();
    }

    @Override
    public Iterable<Exam> findAllCourseExamsInATimeInterval(CourseCode courseCode, Student student) {
        final TypedQuery<Exam> query = entityManager().createQuery(
                "SELECT ex FROM Course c JOIN c.exams ex WHERE c.courseCode = :code " +
                "AND :actualDate BETWEEN ex.dateTime.openingDateTime AND ex.dateTime.closingDateTime " +
                "AND ex NOT IN(SELECT te_ex FROM TakenExam te JOIN te.exam te_ex WHERE te_ex.id = ex.id AND :student = te.student)", Exam.class);
        query.setParameter("code", courseCode);
        query.setParameter("actualDate", Calendar.getInstance());
        query.setParameter("student", student);

        return query.getResultList();
    }
}
