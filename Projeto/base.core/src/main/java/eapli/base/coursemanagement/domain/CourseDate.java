package eapli.base.coursemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;

/**
 * Representa a data de início e fim do curso.
 */
@Embeddable
public class CourseDate implements ValueObject {

    private static final long serialVersionUID = 1L;

    @Temporal(TemporalType.DATE)
    private final Calendar startDate;

    @Temporal(TemporalType.DATE)
    private final Calendar endDate;

    protected CourseDate(Calendar sDate) {
        if (sDate == null)
            sDate = Calendar.getInstance();

        this.checkStartDate(sDate);
        this.startDate = sDate;
        this.endDate = null;
    }

    protected CourseDate(Calendar sDate, Calendar eDate) {
        if (sDate == null)
            sDate = Calendar.getInstance();

        if (eDate == null)
            eDate = Calendar.getInstance();

        this.checkStartDate(sDate);
        this.checkBothDates(sDate, eDate);
        this.startDate = sDate;
        this.endDate = eDate;
    }

    protected CourseDate() {
        this.startDate = null;
        this.endDate = null;
    }

    public static CourseDate valueOf(final Calendar sDate, final Calendar eDate) {
        return new CourseDate(sDate, eDate);
    }

    private void checkStartDate(Calendar startDate) {
        boolean precondition = startDate.compareTo(Calendar.getInstance()) >= 0;
        Preconditions.ensure(precondition, "The course start date must be equals or greater than today!");
    }

    private void checkBothDates(Calendar startDate, Calendar endDate) {
        boolean precondition = startDate.compareTo(endDate) < 0;
        Preconditions.ensure(precondition, "The course start date must be less than the end date!");
    }

    public Calendar cloneStartDate() {
        return (Calendar) startDate.clone();
    }

    @Override
    public String toString() {
        return "Start date: " + startDate.toString() + "\nEnd date: " + endDate.toString();
    }

}
