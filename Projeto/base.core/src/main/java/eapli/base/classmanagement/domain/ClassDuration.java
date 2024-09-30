package eapli.base.classmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Representa a duração da aula recorrente.
 */
@Embeddable
@EqualsAndHashCode
public class ClassDuration implements ValueObject, Comparable<ClassDuration> {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private Integer duration;

    protected ClassDuration(final Integer duration) {
        setDuration(duration);
    }

    protected ClassDuration() {
        // for ORM
    }

    private void setDuration(final Integer newDuration) {
        if (!classDurationMinimumRequirements(newDuration)) {
            throw new IllegalArgumentException("The class duration is invalid!");
        }
        this.duration = newDuration;
    }

    private static boolean classDurationMinimumRequirements(final Integer duration) {
        return duration != null && duration > 0;
    }

    public int duration() {
        return this.duration;
    }

    public static ClassDuration valueOf(final Integer duration) {
        return new ClassDuration(duration);
    }

    @Override
    public String toString() {
        return Integer.toString(this.duration);
    }

    @Override
    public int compareTo(ClassDuration other) {
        return Integer.compare(duration, other.duration);
    }
}
