package eapli.base.questionmanagement.domain;

import javax.persistence.*;

/**
 * Representa a entidade TrueFalse.
 */
@Entity
@DiscriminatorValue("True/False")
public class TrueFalse extends Question {

    public TrueFalse(final String text, final QuestionScore score, final Answer answer) {
        super(text, score, answer);
    }

    protected TrueFalse() {
        // for ORM only
    }

    @Override
    public String typeQuestionToString () {
        return "True/False\n";
    }

    @Override
    public void merge(Question question) {
        if (!(question instanceof TrueFalse)) {
            throw new IllegalArgumentException("Cannot merge with a different type");
        }

        super.merge(question);
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof TrueFalse))
            return false;

        final TrueFalse that = (TrueFalse) other;
        if (this == that)
            return true;

        return super.sameAs(other);
    }
}
