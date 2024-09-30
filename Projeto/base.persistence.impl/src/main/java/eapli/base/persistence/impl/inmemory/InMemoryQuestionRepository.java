package eapli.base.persistence.impl.inmemory;

import eapli.base.coursemanagement.domain.CourseCode;
import eapli.base.questionmanagement.domain.Question;
import eapli.base.questionmanagement.repositories.QuestionRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryQuestionRepository extends InMemoryDomainRepository<Question, String>
        implements QuestionRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Integer countTypeQuestion(CourseCode courseCode, String typeQuestion) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public Iterable<Question> generateRandomQuestions(CourseCode courseCode, String typeOfQuestion, int numOfQuestions) {
        throw new UnsupportedOperationException("not implemented yet");
    }
}
