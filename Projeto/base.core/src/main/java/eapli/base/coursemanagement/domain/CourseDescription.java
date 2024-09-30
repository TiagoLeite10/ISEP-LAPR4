package eapli.base.coursemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;

/**
 * Representa a descrição de um curso.
 */
@Embeddable
public class CourseDescription implements ValueObject {
    private final int DESCRIPTION_MAX_SIZE = 250;

    private static final long serialVersionUID = 1L;

    private final String courseDescription;

    protected CourseDescription(String courseDescription) {
        if (courseDescription == null)
            throw new IllegalArgumentException("Course description should not be null!");

        Preconditions.ensure(courseDescription.length() <= DESCRIPTION_MAX_SIZE, "The course description " +
                "cannot contain more than " + DESCRIPTION_MAX_SIZE + " characters!");

        this.courseDescription = courseDescription;
    }

    /**
     * Construtor de caso especial para valores desconhecidos e ORM.
     */
    protected CourseDescription() {
        courseDescription = "";
    }

    public static CourseDescription valueOf(final String courseDescription) {
        return new CourseDescription(courseDescription);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CourseDescription)) {
            return false;
        }

        final CourseDescription that = (CourseDescription) o;
        return this.courseDescription.equals(that.courseDescription);
    }

    @Override
    public String toString() {
        return this.courseDescription;
    }
}
