package eapli.base.boardmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BoardElementLine extends BoardElement implements ValueObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private final int line;

    protected BoardElementLine(String title, int line) {
        super(title);
        Preconditions.ensure(line > 0, "The line should be greater than 0!");
        this.line = line;
    }

    protected BoardElementLine() {
        this.line = 0;
    }

    public static BoardElementLine valueOf(final String title, final int line) {
        return new BoardElementLine(title, line);
    }

    public int line() {
        return line;
    }

    public Long identity() {
        return this.id;
    }

    @Override
    public String toString() {
        return title() + " (" + line + ")";
    }
}
