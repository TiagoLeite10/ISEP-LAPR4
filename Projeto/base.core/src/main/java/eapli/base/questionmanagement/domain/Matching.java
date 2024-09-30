package eapli.base.questionmanagement.domain;

import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.util.*;

/**
 * Representa a entidade Matching.
 */
@Entity
@DiscriminatorValue("Matching")
public class Matching extends Question {

    @ElementCollection
    private Map<String, String> matching1 = new HashMap<>();

    @ElementCollection
    private Map<String, String> matching2 = new HashMap<>();

    @ElementCollection
    private Map<String, String> matchingAssociations = new HashMap<>();

    public Matching(final String text, final QuestionScore score, final Answer answer, final Map<String, String> matching1,
                    final Map<String, String> matching2, final Map<String, String> matchingAssociations) {
        super(text, score, answer);

        Preconditions.noneNull(matching1, matching2, matchingAssociations, "Matching must not contain null or empty attributes");
        this.matching1 = matching1;
        this.matching2 = matching2;
        this.matchingAssociations = matchingAssociations;
    }

    protected Matching() {
        // for ORM only
    }

    @Override
    public String text() {
        StringBuilder pairs = new StringBuilder();

        Iterator<Map.Entry<String, String>> matching1EntrySetIterator = matching1.entrySet().iterator();
        Iterator<Map.Entry<String, String>> matching2EntrySetIterator = matching2.entrySet().iterator();

        while (matching1EntrySetIterator.hasNext() && matching2EntrySetIterator.hasNext()) {
            Map.Entry<String, String> matching1Tmp = matching1EntrySetIterator.next();
            Map.Entry<String, String> matching2Tmp = matching2EntrySetIterator.next();
            pairs.append(matching1Tmp.getKey()).append(" ").append(matching1Tmp.getValue()).append(" - ")
                    .append(matching2Tmp.getKey()).append(" ").append(matching2Tmp.getValue()).append("\n");
        }

        return super.text() + "\n" + pairs;
    }

    @Override
    public String typeQuestionToString() {
        return "Matching\n";
    }

    @Override
    public Float score() {
        return super.score() * matchingAssociations.size();
    }

    @Override
    public void merge(Question question) {
        if (!(question instanceof Matching)) {
            throw new IllegalArgumentException("Cannot merge with a different type");
        }

        super.merge(question);

        final Matching that = (Matching) question;
        this.matching1 = that.matching1;
        this.matching2 = that.matching2;
        this.matchingAssociations = that.matchingAssociations;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Matching))
            return false;

        final Matching that = (Matching) other;
        if (this == that)
            return true;

        return super.sameAs(other) && matching1.equals(that.matching1) && matching2.equals(that.matching2);
    }
}
