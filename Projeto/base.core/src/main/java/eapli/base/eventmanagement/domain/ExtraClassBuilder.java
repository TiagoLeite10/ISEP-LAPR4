package eapli.base.eventmanagement.domain;

import eapli.base.clientusermanagement.domain.Student;
import eapli.base.clientusermanagement.domain.Teacher;
import eapli.framework.domain.model.DomainFactory;

import java.util.*;

public class ExtraClassBuilder implements DomainFactory<ExtraClass> {

    private EventDuration eventDuration;

    private EventDate eventDate;

    private Teacher teacher;

    private Set<Student> students = new HashSet<>();

    public ExtraClassBuilder with(final Calendar eventDate, final Integer eventDuration, final Teacher teacher, final Set<Student> students) {
        this.withEventDate(eventDate);
        this.withEventDuration(eventDuration);
        this.withTeacher(teacher);
        this.withStudents(students);
        return this;
    }

    public ExtraClassBuilder withEventDate(final Calendar eventDate) {
        this.eventDate = new EventDate(eventDate);
        return this;
    }

    public ExtraClassBuilder withEventDuration(final Integer eventDuration) {
        this.eventDuration = new EventDuration(eventDuration);
        return this;
    }

    public ExtraClassBuilder withTeacher(final Teacher teacher) {
        this.teacher = teacher;
        return this;
    }

    public ExtraClassBuilder withStudents(final Set<Student> students) {
        this.students = students;
        return this;
    }

    @Override
    public ExtraClass build() {
        // since the factory knows that all the parts are needed it could throw
        // an exception. however, we will leave that to the constructor
        return new ExtraClass(eventDate, eventDuration, teacher, students);
    }
}
