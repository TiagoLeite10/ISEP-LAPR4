package eapli.base.questionmanagement.repositories;

import eapli.base.coursemanagement.domain.CourseCode;
import eapli.base.questionmanagement.domain.Question;
import eapli.framework.domain.repositories.DomainRepository;

public interface QuestionRepository extends DomainRepository<String, Question> {

    Integer countTypeQuestion(CourseCode courseCode, String typeQuestion);

    Iterable<Question> generateRandomQuestions(CourseCode courseCode, String typeOfQuestion, int numOfQuestions);
}
