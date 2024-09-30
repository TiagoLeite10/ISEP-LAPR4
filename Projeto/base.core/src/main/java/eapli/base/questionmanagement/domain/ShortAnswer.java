package eapli.base.questionmanagement.domain;

import javax.persistence.*;

/**
 * Representa a entidade ShortAnswer.
 */
@Entity
@DiscriminatorValue("Short Answer")
public class ShortAnswer extends Question {

    public ShortAnswer(final String text, final QuestionScore score, final Answer answer) {
        super(text, score, answer);
    }

    protected ShortAnswer() {
        // for ORM only
    }

    @Override
    public String typeQuestionToString () {
        return "Short Answer\n";
    }

    @Override
    public void merge(Question question) {
        if (!(question instanceof ShortAnswer)) {
            throw new IllegalArgumentException("Cannot merge with a different type");
        }

        super.merge(question);
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof ShortAnswer))
            return false;

        final ShortAnswer that = (ShortAnswer) other;
        if (this == that)
            return true;

        return super.sameAs(other);
    }
}
