package eapli.base.exammanagement.domain;

import eapli.base.exammanagement.exception.InvalidScoreException;
import eapli.base.questionmanagement.domain.Question;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representa a entidade Exame.
 */
@Entity
public class Exam implements AggregateRoot<ExamTitle> {

    private static final long serialVersionUID = 1L;

    public static final Float MAXIMUM_SCORE = 20F;

    @Version
    private Long version;

    @EmbeddedId
    private ExamTitle title;

    @Column
    private ExamDateTime dateTime;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private Header header;

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ExamSection> sections = new ArrayList<>();

    public Exam(final ExamTitle title, final ExamDateTime dateTime, final Header header) {
        Preconditions.noneNull(title, dateTime, header, "Exam must not contain null or empty attributes");

        this.title = title;
        this.dateTime = dateTime;
        this.header = header;
    }

    protected Exam() {
        // for ORM only
    }

    public boolean inProgressOrFinish() {
        return this.dateTime.inProgress() || this.dateTime.finish();
    }

    public boolean addSection(final ExamSection section) {
        if (section == null) {
            throw new IllegalArgumentException("Section cannot be null");
        }

        return sections.add(section);
    }

    public List<ExamSection> sections() {
        return Collections.unmodifiableList(sections);
    }

    public ExamTitle title() {
        return title;
    }

    public Header header() {
        return header;
    }

    public ExamDateTime dateTime() {
        return dateTime;
    }

    public void merge(Exam exam) {
        if (exam == null) {
            throw new IllegalArgumentException("Cannot merge with a null exam");
        }

        this.dateTime = exam.dateTime;
        this.header = exam.header;

        this.sections.clear();
        this.sections.addAll(exam.sections);
    }

    public void checkScore() {
        Float totalScore = 0F;
        for (ExamSection s : sections) {
            for (Question q : s.questions()) {
                totalScore += q.score();
            }
        }

        if (!totalScore.equals(MAXIMUM_SCORE)) {
            throw new InvalidScoreException();
        }
    }

    public String feedback() {
        StringBuilder examFeedback = new StringBuilder();

        for (ExamSection section : sections) {
            examFeedback.append(section.sectionFeedback());
        }

        return examFeedback.toString();
    }

    public String examQuestionsToString() {
        StringBuilder tmp = new StringBuilder();

        tmp.append("Title: ").append(title.examTitle()).append("\n");

        for (ExamSection examSection : sections) {
            tmp.append(examSection.printExamQuestions()).append("\n");
        }

        return tmp.toString();
    }

    @Override
    public boolean sameAs(final Object other) {
        if (!(other instanceof Exam)) {
            return false;
        }

        final Exam that = (Exam) other;
        if (this == that) {
            return true;
        }

        return title.equals(that.title) && dateTime.equals(that.dateTime) && header.equals(that.header)
                && sections.equals(that.sections);
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
    public ExamTitle identity() {
        return this.title;
    }

    @Override
    public String toString() {
        StringBuilder toString = new StringBuilder("Title: " + this.title + "\n"
                + "Date: " + this.dateTime + "\n"
                + this.header.toString() + "\n");

        for (ExamSection examSection : sections) {
            toString.append(examSection.toString()).append("\n");
        }

        return toString.toString();
    }
}
