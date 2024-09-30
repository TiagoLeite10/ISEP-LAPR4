package eapli.base.exammanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Representa o título de um exame.
 */
@Embeddable
@EqualsAndHashCode
public class ExamTitle implements ValueObject, Comparable<ExamTitle> {
    private static final int TITLE_MAX_SIZE = 50;

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String examTitle;

    protected ExamTitle(final String examTitle) {
        setExamTitle(examTitle);
    }

    protected ExamTitle() {
        // for ORM only
    }

    private void setExamTitle(final String newExamTitle) {
        if (!examTitleMeetsMinimumRequirements(newExamTitle)) {
            throw new IllegalArgumentException("The class title should neither be null nor empty nor have starting blank " +
                    "spaces nem cannot have more than " + TITLE_MAX_SIZE + " characters!");
        }
        this.examTitle = newExamTitle;
    }

    private static boolean examTitleMeetsMinimumRequirements(final String examTitle) {
        return StringPredicates.isPhrase(examTitle) && examTitle.length() <= TITLE_MAX_SIZE;
    }

    public static ExamTitle valueOf(final String examTitle) {
        return new ExamTitle(examTitle);
    }

    public String examTitle() {
        return examTitle;
    }

    @Override
    public String toString() {
        return this.examTitle;
    }

    @Override
    public int compareTo(final ExamTitle other) {
        return examTitle.compareTo(other.examTitle);
    }
}
