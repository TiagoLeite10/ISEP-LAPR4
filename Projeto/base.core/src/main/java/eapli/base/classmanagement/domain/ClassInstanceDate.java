package eapli.base.classmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Representa a data e hora da aula instanciada.
 */
@Embeddable
@EqualsAndHashCode
public class ClassInstanceDate implements ValueObject {

    private static final long serialVersionUID = 1L;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Calendar newDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Calendar previousDateTime;

    protected ClassInstanceDate(final Calendar newDateTime, final Calendar previousDateTime) {
        Preconditions.noneNull(newDateTime, previousDateTime, "Class instance date must not contain null or empty attributes!");

        setInstanceDate(newDateTime, previousDateTime);
    }

    protected ClassInstanceDate() {
        // for ORM
    }

    private void setInstanceDate(final Calendar newDateTime, final Calendar previousDateTime) {
        if (!instanceDateMinimumRequirements(newDateTime, previousDateTime)) {
            throw new IllegalArgumentException("The class instance datetime is invalid!");
        }

        newDateTime.set(Calendar.SECOND, 0);
        newDateTime.set(Calendar.MILLISECOND, 0);
        this.newDateTime = newDateTime;

        previousDateTime.set(Calendar.SECOND, 0);
        previousDateTime.set(Calendar.MILLISECOND, 0);
        this.previousDateTime = previousDateTime;
    }

    private static boolean instanceDateMinimumRequirements(final Calendar newDateTime, final Calendar previousDateTime) {
        return newDateTime != null && previousDateTime != null && newDateTime.after(Calendar.getInstance());
    }

    public int compareClassInstanceDateCalendar(Calendar calendar) {
        return this.newDateTime.compareTo(calendar);
    }

    public Calendar newDateTime() {
        return this.newDateTime;
    }

    public static ClassInstanceDate valueOf(final Calendar newDateTime, final Calendar previousDateTime) {
        return new ClassInstanceDate(newDateTime, previousDateTime);
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return format.format(newDateTime.getTime());
    }
}
