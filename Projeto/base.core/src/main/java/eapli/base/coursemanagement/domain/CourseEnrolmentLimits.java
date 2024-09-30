package eapli.base.coursemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;

/**
 * Representa os limites de inscrições.
 */
@Embeddable
public class CourseEnrolmentLimits implements ValueObject {

    private static final long serialVersionUID = 1L;

    private final int minimumEnrolments;

    private final int maximumEnrolments;

    protected CourseEnrolmentLimits(int minimumEnrolments, int maximumEnrolments) {
        Preconditions.ensure(minimumEnrolments > 0, "The minimum number of enrollments in the course must be " +
                "greater than 0!");
        Preconditions.ensure(maximumEnrolments > 0, "The minimum number of enrollments in the course must be " +
                "greater than 0!");
        Preconditions.ensure(minimumEnrolments <= maximumEnrolments, "The minimum number of enrollments in " +
                "the course must be less or equal to the maximum number of enrollments!");

        this.minimumEnrolments = minimumEnrolments;
        this.maximumEnrolments = maximumEnrolments;
    }

    /**
     * Construtor de caso especial para valores desconhecidos e ORM.
     */
    protected CourseEnrolmentLimits() {
        minimumEnrolments = maximumEnrolments = -1;
    }

    public static CourseEnrolmentLimits valueOf(final int minimumEnrolments, final int maximumEnrolments) {
        return new CourseEnrolmentLimits(minimumEnrolments, maximumEnrolments);
    }

    public int minimum() {
        return this.minimumEnrolments;
    }

    public int maximum() {
        return this.maximumEnrolments;
    }

    public boolean exceedsMaximum(int numEnrollments) {
        return numEnrollments > this.maximumEnrolments;
    }

    public boolean validEnrollmentsNumber(int numEnrollments) {
        return numEnrollments >= this.minimumEnrolments && numEnrollments <= this.maximumEnrolments;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CourseEnrolmentLimits)) {
            return false;
        }

        final CourseEnrolmentLimits that = (CourseEnrolmentLimits) o;
        return this.minimumEnrolments == that.minimumEnrolments && this.maximumEnrolments == that.maximumEnrolments;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(minimumEnrolments) - Integer.hashCode(maximumEnrolments);
    }

    @Override
    public String toString() {
        return "Minimum enrollment: " + this.minimumEnrolments + "\nMaximum enrollment: " + this.maximumEnrolments;
    }
}
