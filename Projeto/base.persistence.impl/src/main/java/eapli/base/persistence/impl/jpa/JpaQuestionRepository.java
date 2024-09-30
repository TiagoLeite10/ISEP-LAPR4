package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.coursemanagement.domain.CourseCode;
import eapli.base.questionmanagement.domain.Question;
import eapli.base.questionmanagement.repositories.QuestionRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;

public class JpaQuestionRepository extends JpaAutoTxRepository<Question, String, String>
        implements QuestionRepository {

    public JpaQuestionRepository(final TransactionalContext autoTx) {
        super(autoTx, "text");
    }

    public JpaQuestionRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "text");
    }

    @Override
    public Integer countTypeQuestion(CourseCode courseCode, String typeQuestion) {
        final TypedQuery<Long> query = entityManager().createQuery(
                "SELECT COUNT(q) FROM Course c JOIN c.questions q WHERE c.courseCode = :code " +
                        "AND q.type = :typeQuestion ", Long.class);

        query.setParameter("code", courseCode);
        query.setParameter("typeQuestion", typeQuestion);

        return query.getSingleResult().intValue();
    }

    @Override
    public Iterable<Question> generateRandomQuestions(CourseCode courseCode, String typeOfQuestion, int numOfQuestions) {
        final TypedQuery<Question> query = entityManager().createQuery(
                "SELECT q FROM Course c JOIN c.questions q WHERE c.courseCode = :code " +
                        "AND q.type = :typeQuestion ORDER BY RAND()", Question.class);

        query.setParameter("code", courseCode);
        query.setParameter("typeQuestion", typeOfQuestion);
        query.setMaxResults(numOfQuestions);

        return query.getResultList();
    }
}
