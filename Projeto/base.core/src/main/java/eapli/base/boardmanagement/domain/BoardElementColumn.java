package eapli.base.boardmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BoardElementColumn extends BoardElement implements ValueObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private final int column;

    protected BoardElementColumn(String title, int column) {
        super(title);
        Preconditions.ensure(column > 0, "The column should be greater than 0!");
        this.column = column;
    }

    protected BoardElementColumn() {
        this.column = 0;
    }

    public static BoardElementColumn valueOf(final String title, final int column) {
        return new BoardElementColumn(title, column);
    }

    public int column() {
        return column;
    }

    public Long identity() {
        return this.id;
    }

    @Override
    public String toString() {
        return title() + " (" + column + ")";
    }
}
