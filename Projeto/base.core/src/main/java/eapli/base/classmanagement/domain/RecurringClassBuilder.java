package eapli.base.classmanagement.domain;

import eapli.base.clientusermanagement.domain.Teacher;
import eapli.framework.domain.model.DomainFactory;

import java.util.Calendar;

public class RecurringClassBuilder implements DomainFactory<RecurringClass> {

    private ClassTitle classTitle;

    private ClassStartTime classStartTime;

    private ClassDuration classDuration;

    private ClassDayOfTheWeek classDayOfTheWeek;

    private Teacher teacher;

    public RecurringClassBuilder with(final String classTitle, final Calendar classStartTime, final Integer classDuration,
                                      final ClassDayOfTheWeek classDayOfTheWeek, final Teacher teacher) {
        this.withClassTitle(classTitle);
        this.withClassStartTime(classStartTime);
        this.withClassDuration(classDuration);
        this.withClassDayOfTheWeek(classDayOfTheWeek);
        this.withTeacher(teacher);
        return this;
    }

    public RecurringClassBuilder withClassTitle(final String classTitle) {
        this.classTitle = new ClassTitle(classTitle);
        return this;
    }

    public RecurringClassBuilder withClassStartTime(final Calendar classStartTime) {
        this.classStartTime = new ClassStartTime(classStartTime);
        return this;
    }

    public RecurringClassBuilder withClassDuration(final Integer classDuration) {
        this.classDuration = new ClassDuration(classDuration);
        return this;
    }

    public RecurringClassBuilder withClassDayOfTheWeek(final ClassDayOfTheWeek dayOfWeek) {
        this.classDayOfTheWeek = dayOfWeek;
        return this;
    }

    public RecurringClassBuilder withTeacher(final Teacher teacher) {
        this.teacher = teacher;
        return this;
    }

    @Override
    public RecurringClass build() {
        // since the factory knows that all the parts are needed it could throw
        // an exception. however, we will leave that to the constructor
        return new RecurringClass(classTitle, classStartTime, classDuration, classDayOfTheWeek, teacher);
    }
}
