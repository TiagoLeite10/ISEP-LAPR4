package eapli.base.classmanagement.domain;

import eapli.framework.domain.model.DomainEntities;
import eapli.framework.domain.model.DomainEntity;
import eapli.framework.validations.Preconditions;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Calendar;

/**
 * Representa a entidade ClassInstance.
 */
@Entity
public class ClassInstance implements DomainEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ClassInstanceDate classInstanceDate;

    public ClassInstance(ClassInstanceDate classInstanceDate) {
        Preconditions.noneNull(classInstanceDate, "Class instance must not contain null or empty attributes!");

        this.classInstanceDate = classInstanceDate;
    }

    protected ClassInstance() {
        // for ORM
    }

    public boolean classWillHappenBeforeCalendar(Calendar calendar) {
        return this.classInstanceDate.compareClassInstanceDateCalendar(calendar) < 0;
    }

    public Calendar classInstanceDate() {
        return classInstanceDate.newDateTime();
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof ClassInstance))
            return false;

        final ClassInstance that = (ClassInstance) other;
        if (this == that)
            return true;

        return classInstanceDate.equals(that.classInstanceDate);
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
        return id;
    }
}
