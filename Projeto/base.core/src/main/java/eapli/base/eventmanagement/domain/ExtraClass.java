package eapli.base.eventmanagement.domain;

import eapli.base.clientusermanagement.domain.Student;
import eapli.base.clientusermanagement.domain.Teacher;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Representa a entidade ExtraClass.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ExtraClass extends Event implements AggregateRoot<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long extraClassId;

    @ManyToOne()
    private Teacher teacher;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Student> students = new HashSet<>();

    public ExtraClass(EventDate eventDate, EventDuration eventDuration, Teacher teacher, Set<Student> students) {
        super(eventDuration, eventDate);

        Preconditions.noneNull(teacher, students, "Extra class must not contain null attributes!");
        Preconditions.ensure(!students.isEmpty(), "Extra class must contain students!");

        this.teacher = teacher;
        this.students = students;
    }

    protected ExtraClass() {
        super();
        // for ORM
    }

    public Set<Student> students() {
        return Collections.unmodifiableSet(students);
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof ExtraClass))
            return false;

        final ExtraClass that = (ExtraClass) other;
        if (this == that)
            return true;

        return super.equals(other) && teacher.equals(that.teacher)
                && students.equals(that.students);
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
        return extraClassId;
    }
}
