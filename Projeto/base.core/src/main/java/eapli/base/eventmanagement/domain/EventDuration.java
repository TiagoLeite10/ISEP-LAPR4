package eapli.base.eventmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Representa a duração do evento.
 */
@Embeddable
@EqualsAndHashCode
public class EventDuration implements ValueObject, Comparable<EventDuration> {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private Integer duration;

    protected EventDuration(final Integer duration) {
        setEventDate(duration);
    }

    protected EventDuration() {
        // for ORM
    }

    private void setEventDate(final Integer newDuration) {
        if (!eventDurationMinimumRequirements(newDuration)) {
            throw new IllegalArgumentException("The event duration is invalid");
        }
        this.duration = newDuration;
    }

    private static boolean eventDurationMinimumRequirements(final Integer duration) {
        return duration != null && duration > 0;
    }

    public Integer duration() {
        return this.duration;
    }

    public static EventDuration valueOf(final Integer duration) {
        return new EventDuration(duration);
    }

    @Override
    public String toString() {
        return Integer.toString(this.duration);
    }

    @Override
    public int compareTo(final EventDuration other) {
        return Integer.compare(duration, other.duration);
    }
}
