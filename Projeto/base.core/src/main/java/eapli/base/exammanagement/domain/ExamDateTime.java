package eapli.base.exammanagement.domain;

import eapli.framework.domain.model.ValueObject;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Representa a data de abertura e fecho de um exame.
 */
@Embeddable
@EqualsAndHashCode
public class ExamDateTime implements ValueObject {

    private static final long serialVersionUID = 1L;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Calendar openingDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Calendar closingDateTime;

    protected ExamDateTime(final Calendar openingDateTime, final Calendar closingDateTime) {
        setExamDateTime(openingDateTime, closingDateTime);
    }

    protected ExamDateTime() {
        // for ORM
    }

    private void setExamDateTime(final Calendar newOpeningDateTime, final Calendar newClosingDateTime) {
        if (!examDateTimeMinimumRequirements(newOpeningDateTime, newClosingDateTime)) {
            throw new IllegalArgumentException("The exam datetime is invalid");
        }
        this.openingDateTime = newOpeningDateTime;
        this.closingDateTime = newClosingDateTime;
    }

    public boolean inProgress() {
        Calendar currentDate = Calendar.getInstance();

        return currentDate.after(openingDateTime) && currentDate.before(closingDateTime);
    }

    public boolean finish() {
        Calendar currentDate = Calendar.getInstance();

        return currentDate.after(closingDateTime);
    }

    private static boolean examDateTimeMinimumRequirements(final Calendar openingDateTime, final Calendar closingDateTime) {
        return openingDateTime != null && closingDateTime != null && openingDateTime.after(Calendar.getInstance())
                && openingDateTime.before(closingDateTime);
    }

    public static ExamDateTime valueOf(final Calendar openingDateTime, final Calendar closingDateTime) {
        return new ExamDateTime(openingDateTime, closingDateTime);
    }

    public boolean openingDateIsGreaterThan(Calendar other) {
        return this.openingDateTime.compareTo(other) > 0;
    }

    public boolean closingDateIsGreaterThan(Calendar other) {
        return this.closingDateTime.compareTo(other) > 0;
    }

    public ExamDateTime clone() {
        return new ExamDateTime((Calendar) openingDateTime.clone(), (Calendar) closingDateTime.clone());
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return format.format(openingDateTime.getTime()) + " - " + format.format(closingDateTime.getTime());
    }
}
