package eapli.base.questionmanagement.domain;

import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Representa a entidade SelectMissingWords.
 */
@Entity
@DiscriminatorValue("Select Missing Words")
public class SelectMissingWords extends Question {

    @ElementCollection
    private Set<String> possibleChoices = new HashSet<>();

    public SelectMissingWords(final String text, final QuestionScore score, final Answer answer, final Set<String> possibleChoices) {
        super(text, score, answer);

        Preconditions.noneNull(possibleChoices, "Possible choices cannot be null");
        this.possibleChoices = possibleChoices;
    }

    protected SelectMissingWords() {
        // for ORM only
    }

    @Override
    public String text() {
        StringBuilder choices = new StringBuilder();

        for (String key : this.possibleChoices) {
            choices.append(key).append("\n");
        }

        return super.text() + "\n" + "Choices:\n" + choices;
    }

    @Override
    public String typeQuestionToString() {
        return "Select Missing Words\n";
    }

    @Override
    public void merge(Question question) {
        if (!(question instanceof SelectMissingWords)) {
            throw new IllegalArgumentException("Cannot merge with a different type");
        }

        super.merge(question);

        final SelectMissingWords that = (SelectMissingWords) question;
        this.possibleChoices = that.possibleChoices;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof SelectMissingWords))
            return false;

        final SelectMissingWords that = (SelectMissingWords) other;
        if (this == that)
            return true;

        return super.sameAs(other) && possibleChoices.equals(that.possibleChoices);
    }
}
