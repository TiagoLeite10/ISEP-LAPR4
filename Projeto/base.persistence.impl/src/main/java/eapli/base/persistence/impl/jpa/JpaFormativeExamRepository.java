package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.coursemanagement.domain.CourseCode;
import eapli.base.coursemanagement.domain.CourseStatus;
import eapli.base.exammanagement.domain.ExamTitle;
import eapli.base.formativeexammanagement.domain.FormativeExam;
import eapli.base.formativeexammanagement.repositories.FormativeExamRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;
import java.util.Calendar;

public class JpaFormativeExamRepository  extends JpaAutoTxRepository<FormativeExam, ExamTitle, ExamTitle>
        implements FormativeExamRepository {

    public JpaFormativeExamRepository(final TransactionalContext autoTx) {
        super(autoTx, "title");
    }

    public JpaFormativeExamRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "title");
    }

    @Override
    public Iterable<FormativeExam> listAllAvailableFormativeExamsOfACourse(CourseCode courseCode) {
        final TypedQuery<FormativeExam> query = entityManager().createQuery(
                "SELECT fe FROM Course c JOIN c.formativeExams fe WHERE c.courseCode = :courseCode " +
                        "AND c.courseStatus = :statusCourse " +
                        "AND :actualDate BETWEEN fe.dateTime.openingDateTime AND fe.dateTime.closingDateTime",
                FormativeExam.class);
        query.setParameter("courseCode", courseCode);
        query.setParameter("statusCourse", CourseStatus.IN_PROGRESS);
        query.setParameter("actualDate", Calendar.getInstance());

        return query.getResultList();
    }
}
