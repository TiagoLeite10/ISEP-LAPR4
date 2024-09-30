package eapli.base.boardmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Representa a data e o tempo em que o quadro foi criado.
 */
@Embeddable
@EqualsAndHashCode
public class BoardCreationDateTime implements ValueObject {

    private static final long serialVersionUID = 1L;

    @Temporal(TemporalType.TIMESTAMP)
    private final Calendar boardCreationDateTime;

    protected BoardCreationDateTime() {
        this.boardCreationDateTime = Calendar.getInstance();
    }

    public static BoardCreationDateTime valueOf() {
        return new BoardCreationDateTime();
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return format.format(boardCreationDateTime.getTime());
    }
}
