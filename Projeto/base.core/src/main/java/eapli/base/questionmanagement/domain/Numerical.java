package eapli.base.questionmanagement.domain;

import javax.persistence.*;

/**
 * Representa a entidade Numerical.
 */
@Entity
@DiscriminatorValue("Numerical")
public class Numerical extends Question {

    private Float error;

    public Numerical(final String text, final QuestionScore score, final Answer answer, final Float error) {
        super(text, score, answer);

        setNumericalError(error);
    }

    protected Numerical() {
        // for ORM only
    }

    private void setNumericalError(final Float newError) {
        if (!numericalErrorMeetsMinimumRequirements(newError)) {
            throw new IllegalArgumentException("The numerical error is invalid");
        }
        this.error = newError;
    }

    private static boolean numericalErrorMeetsMinimumRequirements(final Float error) {
        return error != null && error >= 0;
    }

    @Override
    public String typeQuestionToString () {
        return "Numerical\n";
    }

    @Override
    public boolean isCorrectAnswer(String answer) {
        float answerGiven = 0f;
        float correctAnswer = 0f;
        try {
            answerGiven = Float.parseFloat(answer);
            correctAnswer = Float.parseFloat(correctAnswer());
        } catch (NumberFormatException e) {
            return false;
        }

        return (correctAnswer - error <= answerGiven && answerGiven <= correctAnswer + error);
    }

    @Override
    public void merge(Question question) {
        if (!(question instanceof Numerical)) {
            throw new IllegalArgumentException("Cannot merge with a different type");
        }

        super.merge(question);

        final Numerical that = (Numerical) question;
        this.error = that.error;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Numerical))
            return false;

        final Numerical that = (Numerical) other;
        if (this == that)
            return true;

        return super.sameAs(other) && error.equals(that.error);
    }
}
