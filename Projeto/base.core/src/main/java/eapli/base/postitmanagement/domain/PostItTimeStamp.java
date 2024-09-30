package eapli.base.postitmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Representa o value object PostItTimeStamp.
 **/
@Embeddable
@EqualsAndHashCode
public class PostItTimeStamp implements ValueObject {

    private static final long serialVersionUID = 1L;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar postItTimeStamp;

    protected PostItTimeStamp() {
        this.postItTimeStamp = Calendar.getInstance();
    }

    public static PostItTimeStamp valueOf() {
        return new PostItTimeStamp();
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return format.format(postItTimeStamp.getTime());
    }
}
