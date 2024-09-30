package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.clientusermanagement.domain.Student;
import eapli.base.coursemanagement.domain.CourseCode;
import eapli.base.takenexammanagement.domain.TakenExam;
import eapli.base.takenexammanagement.dto.GradePerStudent;
import eapli.base.takenexammanagement.repositories.TakenExamRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;

public class JpaTakenExamRepository extends JpaAutoTxRepository<TakenExam, Long, Long>
        implements TakenExamRepository {
    public JpaTakenExamRepository(final TransactionalContext autoTx) {
        super(autoTx, "takenExamId");
    }

    public JpaTakenExamRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "takenExamId");
    }

    @Override
    public Iterable<GradePerStudent> findAllGrades(Student student) {
        final TypedQuery<GradePerStudent> query = entityManager().createQuery(
                "SELECT new eapli.base.takenexammanagement.dto.GradePerStudent(te.exam.title, te.dateRealization, te.grade) " +
                        "FROM TakenExam te WHERE te.student = :student", GradePerStudent.class);

        query.setParameter("student", student);

        return query.getResultList();
    }

    @Override
    public Iterable<GradePerStudent> findAllGradesByCourse(CourseCode courseCode) {

        final TypedQuery<GradePerStudent> query = entityManager().createQuery(
                "SELECT new eapli.base.takenexammanagement.dto.GradePerStudent(te.student, te.exam.title, te.dateRealization, te.grade) " +
                        "FROM TakenExam te " +
                        "WHERE te.exam IN (SELECT e FROM Course c JOIN c.exams e WHERE c.courseCode = :courseCode)", GradePerStudent.class);

        query.setParameter("courseCode", courseCode);

        return query.getResultList();
    }
}
