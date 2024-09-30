package eapli.base.formativeexammanagement.domain;

import eapli.base.exammanagement.domain.SectionDescription;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;
import org.springframework.data.util.Pair;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Representa a entidade FormativeExamSection.
 */
@Entity
public class FormativeExamSection implements AggregateRoot<Long> {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sectionId;

    @Column
    private SectionDescription description;

    @ElementCollection
    private Map<String, Integer> questionAndRequiredNumber = new HashMap<>();

    public FormativeExamSection(SectionDescription description, Map<String, Integer> questionAndRequiredNumber) {
        Preconditions.noneNull(questionAndRequiredNumber, "Formative exam section must not contain null or empty attributes");

        this.description = description;
        this.questionAndRequiredNumber = questionAndRequiredNumber;
    }

    public FormativeExamSection(SectionDescription description) {
        this.description = description;
    }

    protected FormativeExamSection() {
        // for ORM only
    }

    public void addQuestionAndRequiredNumber(final Pair<String, Integer> questionAndRequiredNumber) {
        if (questionAndRequiredNumber == null) {
            throw new IllegalArgumentException("Number questions cannot be null");
        }

        this.questionAndRequiredNumber.put(questionAndRequiredNumber.getFirst(), questionAndRequiredNumber.getSecond());
    }

    public Map<String, Integer> questionAndRequiredNumbers() {
        return Collections.unmodifiableMap(questionAndRequiredNumber);
    }

    public SectionDescription sectionDescription() {
        return description.clone();
    }

    @Override
    public boolean sameAs(final Object other) {
        if (!(other instanceof FormativeExamSection)) {
            return false;
        }

        final FormativeExamSection that = (FormativeExamSection) other;
        if (this == that) {
            return true;
        }

        return description.equals(that.description) && questionAndRequiredNumber.equals(that.questionAndRequiredNumber);
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
        return this.sectionId;
    }

    @Override
    public String toString() {
        StringBuilder toString = new StringBuilder("-- Section --\n"
                + "Section description: " + this.description + "\n");

        for (String key : questionAndRequiredNumber.keySet()) {
            toString.append("Type question: ").append(key).append(" <-> Number of questions: ")
                    .append(questionAndRequiredNumber.get(key)).append("\n");
        }

        return toString.toString();
    }
}
