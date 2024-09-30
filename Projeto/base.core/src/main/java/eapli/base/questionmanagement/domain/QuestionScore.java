package eapli.base.questionmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Representa a pontuação de uma pergunta.
 */
@Embeddable
@EqualsAndHashCode
public class QuestionScore implements ValueObject {

    private static final long serialVersionUID = 1L;

    @Column
    private Float score;

    protected QuestionScore(final Float score) {
        setQuestionScore(score);
    }

    protected QuestionScore() {
        // for ORM
    }

    private void setQuestionScore(final Float newScore) {
        if (!questionScoreMeetsMinimumRequirements(newScore)) {
            throw new IllegalArgumentException("The question score is invalid");
        }
        this.score = newScore;
    }

    private boolean questionScoreMeetsMinimumRequirements(final Float score) {
        return score != null && (score >= 0 && score <= 20);
    }

    public static QuestionScore valueOf(final Float score) {
        return new QuestionScore(score);
    }

    public Float score() {
        return score;
    }

    @Override
    public String toString() {
        return "Score: " + this.score;
    }
}
