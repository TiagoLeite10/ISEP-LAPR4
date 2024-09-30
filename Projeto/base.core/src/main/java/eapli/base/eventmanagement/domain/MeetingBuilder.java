package eapli.base.eventmanagement.domain;

import eapli.framework.domain.model.DomainFactory;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.Calendar;

public class MeetingBuilder implements DomainFactory<Meeting> {

    private String title;

    private EventDuration eventDuration;

    private EventDate eventDate;

    private SystemUser ownerUser;

    public MeetingBuilder with(final String title, final Calendar eventDate, final Integer eventDuration, final SystemUser ownerUser) {
        this.withTitle(title);
        this.withEventDate(eventDate);
        this.withEventDuration(eventDuration);
        this.withOwnerUser(ownerUser);
        return this;
    }

    public MeetingBuilder withTitle(final String title) {
        this.title = title;
        return this;
    }

    public MeetingBuilder withEventDate(final Calendar eventDate) {
        this.eventDate = new EventDate(eventDate);
        return this;
    }

    public MeetingBuilder withEventDuration(final Integer eventDuration) {
        this.eventDuration = new EventDuration(eventDuration);
        return this;
    }

    public MeetingBuilder withOwnerUser(final SystemUser ownerUser) {
        this.ownerUser = ownerUser;
        return this;
    }

    @Override
    public Meeting build() {
        // since the factory knows that all the parts are needed it could throw
        // an exception. however, we will leave that to the constructor
        return new Meeting(title, eventDate, eventDuration, ownerUser);
    }
}
