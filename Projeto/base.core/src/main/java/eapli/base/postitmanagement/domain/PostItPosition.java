package eapli.base.postitmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Representa o value object PostItPosition.
 **/
@Embeddable
@EqualsAndHashCode
public class PostItPosition implements ValueObject {

    @Column(nullable = false)
    private final int column;

    @Column(nullable = false)
    private final int line;

    protected PostItPosition(final int line, final int column) {
        Preconditions.ensure(line > 0, "The line number must be greater than 0!");
        Preconditions.ensure(column > 0, "The column number must be greater than 0");

        this.line = line;
        this.column = column;
    }

    protected PostItPosition() {
        this.line = 0;
        this.column = 0;
    }

    public static PostItPosition valueOf(final int line, final int column) {
        return new PostItPosition(line, column);
    }

    public int line() {
        return line;
    }

    public int column() {
        return column;
    }
}
