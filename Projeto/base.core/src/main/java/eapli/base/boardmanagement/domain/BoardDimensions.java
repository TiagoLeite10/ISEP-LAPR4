package eapli.base.boardmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Representa as dimensões do quadro.
 */
@Embeddable
@EqualsAndHashCode
public class BoardDimensions implements ValueObject {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private final int numLines;

    @Column(nullable = false)
    private final int numColumns;

    protected BoardDimensions(final int numLines, final int numColumns) {
        Preconditions.ensure(numLines > 0, "The number of lines should be greater than 0!");
        Preconditions.ensure(numColumns > 0, "The number of columns should be greater than 0!");

        this.numLines = numLines;
        this.numColumns = numColumns;
    }

    protected BoardDimensions() {
        this.numLines = 0;
        this.numColumns = 0;
    }

    public static BoardDimensions valueOf(final int numLines, final int numColumns) {
        return new BoardDimensions(numLines, numColumns);
    }

    public int numLines() {
        return numLines;
    }

    public int numColumns() {
        return numColumns;
    }
}
