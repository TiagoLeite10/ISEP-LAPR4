package eapli.base.classmanagement.domain;

import eapli.base.clientusermanagement.domain.Teacher;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.*;

/**
 * Representa a entidade RecurringClass.
 */
@Entity
public class RecurringClass implements AggregateRoot<ClassTitle> {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ClassTitle classTitle;

    @Column(nullable = false)
    private ClassStartTime classStartTime;

    @Column(nullable = false)
    private ClassDuration classDuration;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClassDayOfTheWeek classDayOfTheWeek;

    @ManyToOne
    private Teacher teacher;

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<ClassInstance> classInstances = new HashSet<>();

    public RecurringClass(final ClassTitle classTitle, final ClassStartTime classStartTime, final ClassDuration classDuration,
                          final ClassDayOfTheWeek classDayOfTheWeek, final Teacher teacher) {
        Preconditions.noneNull(classTitle, classStartTime, classDuration, classDayOfTheWeek, teacher, "Recurring Class must not contain null or empty attributes!");

        this.classTitle = classTitle;
        this.classStartTime = classStartTime;
        this.classDuration = classDuration;
        this.classDayOfTheWeek = classDayOfTheWeek;
        this.teacher = teacher;
    }

    protected RecurringClass() {
        // for ORM only
    }

    public ClassStartTime startTime() {
        return this.classStartTime;
    }

    public ClassDayOfTheWeek dayOfWeek() {
        return this.classDayOfTheWeek;
    }

    public ClassDuration duration() {
        return this.classDuration;
    }

    public Set<ClassInstance> classInstances() {
        return Collections.unmodifiableSet(classInstances);
    }

    public boolean addClassInstance(Calendar newDateTime) {
        ClassInstance classInstance = new ClassInstance(new ClassInstanceDate(newDateTime, nextClassDate()));
        return classInstances.add(classInstance);
    }

    public Calendar nextClassDate() {
        Calendar date = Calendar.getInstance();
        date.set(Calendar.HOUR_OF_DAY, classStartTime.hours());
        date.set(Calendar.MINUTE, classStartTime.minutes());

        while (date.get(Calendar.DAY_OF_WEEK) != classDayOfTheWeek.ordinal() + 1) {
            date.add(Calendar.DAY_OF_MONTH, 1);
        }

        return date;
    }

    public Calendar startDateTimeNextClass() {
        return this.nextClassDate();
    }

    public Calendar endDateTimeNextClass() {
        Calendar endTimeNextClass = nextClassDate();
        endTimeNextClass.add(Calendar.MINUTE, classDuration.duration());

        return endTimeNextClass;
    }

    public boolean hasPendingClassInstances() {
        boolean hasPendingClassInstances = false;
        Calendar todayCalendar = Calendar.getInstance();

        Iterator<ClassInstance> classInstanceIterator = this.classInstances.iterator();
        while (classInstanceIterator.hasNext() && !hasPendingClassInstances) {

            ClassInstance classInstance = classInstanceIterator.next();
            if (classInstance.classWillHappenBeforeCalendar(todayCalendar)) {
                hasPendingClassInstances = true;
            }

        }

        return hasPendingClassInstances;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof RecurringClass))
            return false;

        final RecurringClass that = (RecurringClass) other;
        if (this == that)
            return true;

        return identity().equals(that.identity()) && classStartTime.equals(that.classStartTime)
                && classDuration.equals(that.classDuration) && classDayOfTheWeek.equals(that.classDayOfTheWeek)
                && teacher.equals(that.teacher);
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
    public ClassTitle identity() {
        return this.classTitle;
    }
}
