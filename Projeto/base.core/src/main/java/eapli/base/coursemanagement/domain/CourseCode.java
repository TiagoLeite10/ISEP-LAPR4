package eapli.base.coursemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;

/**
 * Representa o código de um curso.
 */
@Embeddable
public class CourseCode implements ValueObject, Comparable<CourseCode> {

    private static final long serialVersionUID = 1L;

    private final String courseCode;

    protected CourseCode(String courseCode) {
        if (StringPredicates.isNullOrEmpty(courseCode))
            throw new IllegalArgumentException("Course Code should neither be null nor empty!");

        this.courseCode = courseCode;
    }

    /**
     * Construtor de caso especial para valores desconhecidos e ORM.
     */
    protected CourseCode() {
        courseCode = "";
    }

    public static CourseCode valueOf(final String courseCode) {
        return new CourseCode(courseCode);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CourseCode)) {
            return false;
        }

        final CourseCode that = (CourseCode) o;
        return this.courseCode.equals(that.courseCode);
    }

    @Override
    public int hashCode() {
        return this.courseCode.hashCode();
    }

    @Override
    public String toString() {
        return this.courseCode;
    }

    @Override
    public int compareTo(CourseCode o) {
        return this.courseCode.compareTo(o.courseCode);
    }
}
