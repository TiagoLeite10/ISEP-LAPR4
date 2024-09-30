package eapli.base.classmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Representa a hora inicial da aula recorrente.
 */
@Embeddable
@EqualsAndHashCode
public class ClassStartTime implements ValueObject, Comparable<ClassStartTime> {

    private static final long serialVersionUID = 1L;

    @Temporal(TemporalType.TIME)
    @Column(nullable = false)
    private Date startTime;

    protected ClassStartTime(final Calendar startTime) {
        setStartTime(startTime);
    }

    protected ClassStartTime() {
        // for ORM
    }

    private void setStartTime(final Calendar newStartTime) {
        if (!classStartTimeMinimumRequirements(newStartTime)) {
            throw new IllegalArgumentException("The class start time is invalid!");
        }

        newStartTime.set(Calendar.SECOND, 0);
        newStartTime.set(Calendar.MILLISECOND, 0);
        this.startTime = newStartTime.getTime();
    }

    private static boolean classStartTimeMinimumRequirements(final Calendar startTime) {
        return startTime != null;
    }

    public int hours() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startTime);

        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public int minutes() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startTime);

        return calendar.get(Calendar.MINUTE);
    }

    public static ClassStartTime valueOf(final Calendar startTime) {
        return new ClassStartTime(startTime);
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(startTime.getTime());
    }

    @Override
    public int compareTo(ClassStartTime other) {
        return startTime.compareTo(other.startTime);
    }
}
