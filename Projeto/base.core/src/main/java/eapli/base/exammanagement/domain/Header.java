package eapli.base.exammanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;

/**
 * Representa a entidade Header.
 */
@Entity
public class Header implements AggregateRoot<Long> {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long headerId;

    @Column
    private HeaderDescription description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HeaderType typeNote;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HeaderType typeFeedback;

    public Header(final HeaderDescription description, final HeaderType typeNote, final HeaderType typeFeedback) {
        Preconditions.noneNull(typeNote, typeFeedback, "Header must not contain null or empty attributes");

        this.description = description;
        this.typeNote = typeNote;
        this.typeFeedback = typeFeedback;
    }

    protected Header() {
        // for ORM only
    }

    public HeaderDescription description() {
        return description;
    }

    public String descriptionString() {
        return this.description.toString();
    }

    @Override
    public boolean sameAs(final Object other) {
        if (!(other instanceof Header)) {
            return false;
        }

        final Header that = (Header) other;
        if (this == that) {
            return true;
        }

        return description.equals(that.description) && typeNote.equals(that.typeNote)
                && typeFeedback.equals(that.typeFeedback);
    }

    @Override
    public boolean equals(final Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public Long identity() {
        return this.headerId;
    }

    @Override
    public String toString() {
        return "Description: " + this.description.toString() + "\n"
                + "Note type: " + typeNote.toString() + "\n"
                + "Feedback type: " + typeFeedback.toString() + "\n";
    }
}
