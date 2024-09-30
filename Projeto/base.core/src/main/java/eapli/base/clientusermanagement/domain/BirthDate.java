package eapli.base.clientusermanagement.domain;

import eapli.framework.domain.model.ValueObject;
import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Representa a data de nascimento de uma pessoa.
 *
 * @author Gabriel Gonçalves (1191296)
 */
@Embeddable
@EqualsAndHashCode
public class BirthDate implements ValueObject, Comparable<BirthDate> {

    private static final long serialVersionUID = 1L;

    @Temporal(TemporalType.DATE)
    private Calendar birthDate;

    protected BirthDate(final Calendar birthDate) {
        setBirthDate(birthDate);
    }

    protected BirthDate() {
        // for ORM
    }

    private void setBirthDate(final Calendar newBirthDate) {
        if (!birthDateMeetsMinimumRequirements(newBirthDate)) {
            throw new IllegalArgumentException("Birth Date cannot be greater than the current date");
        }

        newBirthDate.set(Calendar.HOUR, 0);
        newBirthDate.set(Calendar.MINUTE, 0);
        newBirthDate.set(Calendar.SECOND, 0);
        newBirthDate.set(Calendar.MILLISECOND, 0);
        this.birthDate = newBirthDate;
    }

    private static boolean birthDateMeetsMinimumRequirements(final Calendar birthDate) {
        return birthDate != null && birthDate.before(Calendar.getInstance());
    }

    public static BirthDate valueOf(final Calendar birthDate) {
        return new BirthDate(birthDate);
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        return format.format(birthDate.getTime());
    }

    @Override
    public int compareTo(final BirthDate other) {
        return birthDate.compareTo(other.birthDate);
    }
}
