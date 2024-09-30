package eapli.base.exammanagement.domain;

import eapli.base.questionmanagement.domain.Question;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.*;

/**
 * Representa a entidade ExamSection.
 */
@Entity
public class ExamSection implements AggregateRoot<Long> {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sectionId;

    @Column
    private SectionDescription description;

    @ManyToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Question> questions = new ArrayList<>();

    public ExamSection(SectionDescription description, List<Question> questions) {
        this.description = description;
        this.questions = questions;
    }

    public ExamSection(final SectionDescription description) {
        this.description = description;
    }

    protected ExamSection() {
        // for ORM only
    }

    public void addQuestion(final Question question) {
        if (question == null) {
            throw new IllegalArgumentException("Question cannot be null");
        }

        questions.add(question);
    }

    public SectionDescription description() {
        return description;
    }

    public List<Question> questions() {
        return Collections.unmodifiableList(questions);
    }

    public String sectionFeedback() {
        StringBuilder sectionFeedback = new StringBuilder();

        sectionFeedback.append("Section description: ").append(description).append("\n");

        for (Question question : this.questions) {
            sectionFeedback.append(question.questionFeedback()).append("\n");
        }

        return sectionFeedback.toString();
    }

    public String printExamQuestions() {
        StringBuilder tmp = new StringBuilder();

        tmp.append("Section description: ").append(this.description).append("\n");

        for (Question question : questions) {
            tmp.append(question.typeText()).append("\n\n");
        }

        return tmp.toString();
    }

    @Override
    public boolean sameAs(final Object other) {
        if (!(other instanceof ExamSection)) {
            return false;
        }

        final ExamSection that = (ExamSection) other;
        if (this == that) {
            return true;
        }

        return description.equals(that.description) && questions.equals(that.questions);
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

        for (Question question : questions) {
            toString.append(question.toString());
        }

        return toString.toString();
    }
}
