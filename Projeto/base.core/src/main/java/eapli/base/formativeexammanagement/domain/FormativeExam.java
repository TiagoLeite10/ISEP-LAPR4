package eapli.base.formativeexammanagement.domain;

import eapli.base.exammanagement.domain.*;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * Representa a entidade FormativeExam.
 */
@Entity
public class FormativeExam implements AggregateRoot<ExamTitle> {

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
    private List<FormativeExamSection> formativeExamSections = new ArrayList<>();

    public FormativeExam(final ExamTitle title, final ExamDateTime dateTime, final Header header) {
        Preconditions.noneNull(title, dateTime, header, "Formative Exam must not contain null or empty attributes");

        this.title = title;
        this.dateTime = dateTime;
        this.header = header;
    }

    protected FormativeExam() {
        // for ORM only
    }

    public boolean validAnswerFormativeExamDate(Calendar date) {
        return this.dateTime.closingDateIsGreaterThan(date);
    }

    public boolean inProgressOrFinish() {
        return this.dateTime.inProgress() || this.dateTime.finish();
    }

    public void addFormativeExamSection(final FormativeExamSection formativeExamSection) {
        if (formativeExamSection == null) {
            throw new IllegalArgumentException("Formative exam section cannot be null");
        }

        formativeExamSections.add(formativeExamSection);
    }

    public List<FormativeExamSection> formativeExamSections() {
        return Collections.unmodifiableList(formativeExamSections);
    }

    public void merge(FormativeExam formativeExam) {
        if (formativeExam == null) {
            throw new IllegalArgumentException("Cannot merge with a null formative exam");
        }

        this.dateTime = formativeExam.dateTime;
        this.header = formativeExam.header;

        this.formativeExamSections.clear();
        this.formativeExamSections.addAll(formativeExam.formativeExamSections);
    }

    public Exam transformInExamObject() {
        Calendar nowPlus1Hour = Calendar.getInstance();
        nowPlus1Hour.add(Calendar.HOUR, 1);

        Calendar nowPlus2Hour = Calendar.getInstance();
        nowPlus2Hour.add(Calendar.HOUR, 2);

        return new ExamBuilder().withTitle(this.title.toString()).withDateTime(nowPlus1Hour, nowPlus2Hour)
                .withHeader(this.header).buildWithoutCheckScore();
    }

    public String description() {
        return this.header.descriptionString();
    }

    public String dateString() {
        return this.dateTime.toString();
    }

    @Override
    public boolean sameAs(final Object other) {
        if (!(other instanceof FormativeExam)) {
            return false;
        }

        final FormativeExam that = (FormativeExam) other;
        if (this == that) {
            return true;
        }

        boolean flag = true;
        if (formativeExamSections.size() != that.formativeExamSections.size()) {
            flag = false;
        } else {
            for (int i = 0; i < formativeExamSections.size(); i++) {
                if (!formativeExamSections.get(i).sameAs(that.formativeExamSections.get(i))) {
                    flag = false;
                    break;
                }
            }
        }

        return title.equals(that.title) && dateTime.equals(that.dateTime) && header.sameAs(that.header)
                && flag;
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

        for (FormativeExamSection formativeExamSection : formativeExamSections) {
            toString.append(formativeExamSection.toString());
        }

        return toString.toString();
    }
}
