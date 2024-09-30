package eapli.base.questionmanagement.domain;

import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Representa a entidade MultipleChoice.
 */
@Entity
@DiscriminatorValue("Multiple Choice")
public class MultipleChoice extends Question {

    @ElementCollection
    private Map<String, String> options = new HashMap<>();

    public MultipleChoice(final String text, final QuestionScore score, final Answer answer, final Map<String, String> options) {
        super(text, score, answer);

        Preconditions.noneNull(options, "Options cannot be null");
        this.options = options;
    }

    protected MultipleChoice() {
        // for ORM only
    }

    @Override
    public String text() {
        StringBuilder options = new StringBuilder();

        for (String key : this.options.keySet()) {
            options.append(key).append(") ").append(this.options.get(key)).append("\n");
        }

        return super.text() + "\n" + options;
    }

    @Override
    public String typeQuestionToString () {
        return "Multiple Choice\n";
    }

    @Override
    public void merge(Question question) {
        if (!(question instanceof MultipleChoice)) {
            throw new IllegalArgumentException("Cannot merge with a different type");
        }

        super.merge(question);

        final MultipleChoice that = (MultipleChoice) question;
        this.options = that.options;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof MultipleChoice))
            return false;

        final MultipleChoice that = (MultipleChoice) other;
        if (this == that)
            return true;

        return super.sameAs(other) && options.equals(that.options);
    }
}
