package eapli.base.eventmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Representa a data e hora de um evento.
 */
@Embeddable
@EqualsAndHashCode
public class EventDate implements ValueObject, Comparable<EventDate> {

    private static final long serialVersionUID = 1L;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Calendar dateTime;

    protected EventDate(final Calendar dateTime) {
        setEventDate(dateTime);
    }

    protected EventDate() {
        // for ORM
    }

    private void setEventDate(final Calendar newDateTime) {
        if (!eventDateTimeMinimumRequirements(newDateTime)) {
            throw new IllegalArgumentException("The event datetime is invalid");
        }

        newDateTime.set(Calendar.SECOND, 0);
        newDateTime.set(Calendar.MILLISECOND, 0);
        this.dateTime = newDateTime;
    }

    private static boolean eventDateTimeMinimumRequirements(final Calendar dateTime) {
        return dateTime != null && dateTime.after(Calendar.getInstance());
    }

    public Calendar eventDate() {
        return this.dateTime;
    }

    public static EventDate valueOf(final Calendar dateTime) {
        return new EventDate(dateTime);
    }

    public int compareEventDateCalendar(Calendar otherCalendar) {
        return this.dateTime.compareTo(otherCalendar);
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return format.format(dateTime.getTime());
    }

    @Override
    public int compareTo(final EventDate other) {
        return dateTime.compareTo(other.dateTime);
    }
}
