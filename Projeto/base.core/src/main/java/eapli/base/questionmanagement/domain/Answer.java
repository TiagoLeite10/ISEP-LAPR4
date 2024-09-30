package eapli.base.questionmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.*;

/**
 * Representa a entidade Answer.
 */
@Entity
public class Answer implements AggregateRoot<Long> {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @Column
    private String correctAnswer;

    @Column
    private String feedback;

    public Answer(final String correctAnswer, final String feedback) {
        if (!StringPredicates.isPhrase(correctAnswer)) {
            throw new IllegalArgumentException("The answer is invalid");
        }

        this.correctAnswer = correctAnswer;
        this.feedback = feedback;
    }

    protected Answer() {
        // for ORM only
    }

    public boolean isCorrectAnswer(String answer) {
        return answer.equalsIgnoreCase(this.correctAnswer);
    }

    public String correctAnswer() {
        return correctAnswer;
    }

    public String feedback() {
        return feedback;
    }

    @Override
    public boolean sameAs(final Object other) {
        if (!(other instanceof Answer)) {
            return false;
        }

        final Answer that = (Answer) other;
        if (this == that) {
            return true;
        }

        return correctAnswer.equals(that.correctAnswer) && feedback.equals(that.feedback);
    }

    @Override
    public boolean equals(final Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public Long identity() {
        return this.answerId;
    }

    @Override
    public String toString() {
        return "Correct answer: " + this.correctAnswer + "\n"
                + "Feedback: " + this.feedback;
    }
}
