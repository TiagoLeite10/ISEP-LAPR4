package eapli.base.eventmanagement.domain;

import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Representa a classe abstrata Event.
 */
@MappedSuperclass
public abstract class Event {

    @Column(nullable = false)
    private EventDuration eventDuration;

    @Column(nullable = false)
    private EventDate eventDate;

    protected Event(final EventDuration eventDuration, final EventDate eventDate) {
        Preconditions.noneNull(eventDuration, eventDate, "Event must not contain null or empty attributes!");

        this.eventDuration = eventDuration;
        this.eventDate = eventDate;
    }

    protected Event() {
        // for ORM
    }

    public EventDuration eventDuration() {
        return eventDuration;
    }

    public EventDate eventDate() {
        return this.eventDate;
    }

    public Calendar eventStartDate() {
        return this.eventDate.eventDate();
    }

    public Calendar eventEndDate() {
        Calendar eventEndDate = (Calendar) eventStartDate().clone();
        eventEndDate.add(Calendar.MINUTE, eventDuration.duration());

        return eventEndDate;
    }

    public boolean eventDateLessThan(Calendar otherCalendar) {
        return this.eventDate.compareEventDateCalendar(otherCalendar) < 0;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Event)) {
            return false;
        }

        final Event that = (Event) other;
        if (this == that) {
            return true;
        }

        return eventDate.equals(that.eventDate) && eventDuration.equals(that.eventDuration);
    }
}
