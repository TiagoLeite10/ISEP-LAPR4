package eapli.base.coursemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;

/**
 * Representa o título de um curso.
 */
@Embeddable
public class CourseTitle implements ValueObject {
    private final int TITLE_MAX_SIZE = 50;

    private static final long serialVersionUID = 1L;

    private final String courseTitle;

    protected CourseTitle(String courseTitle) {
        if (StringPredicates.isNullOrEmpty(courseTitle))
            throw new IllegalArgumentException("Course title should neither be null nor empty!");

        Preconditions.ensure(courseTitle.length() <= TITLE_MAX_SIZE, "The course title cannot have more than " +
                TITLE_MAX_SIZE + " characters!");
        this.courseTitle = courseTitle;
    }

    /**
     * Construtor de caso especial para valores desconhecidos e ORM.
     */
    protected CourseTitle() {
        courseTitle = "";
    }

    public static CourseTitle valueOf(final String courseTitle) {
        return new CourseTitle(courseTitle);
    }

    @Override
    public String toString() {
        return this.courseTitle;
    }
}
