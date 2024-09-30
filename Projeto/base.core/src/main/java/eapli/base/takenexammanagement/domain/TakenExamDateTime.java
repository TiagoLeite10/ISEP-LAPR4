package eapli.base.takenexammanagement.domain;

import eapli.framework.domain.model.ValueObject;
import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Representa a data e o tempo em que o exame foi realizado.
 */
@Embeddable
@EqualsAndHashCode
public class TakenExamDateTime implements ValueObject {

    private static final long serialVersionUID = 1L;

    @Temporal(TemporalType.TIMESTAMP)
    private final Calendar takenExamDateTime;

    protected TakenExamDateTime() {
        this.takenExamDateTime = Calendar.getInstance();
    }

    public static TakenExamDateTime valueOf() {
        return new TakenExamDateTime();
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return format.format(takenExamDateTime.getTime());
    }
}
