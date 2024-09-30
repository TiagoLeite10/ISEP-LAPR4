package eapli.base.app.common.console.presentation.meeting;

import eapli.base.eventmanagement.domain.Meeting;
import eapli.base.eventmanagement.domain.ParticipationMeeting;
import eapli.framework.visitor.Visitor;

public class RequestedParticipationMeetingPrinter implements Visitor<Object[]> {
    @Override
    public void visit(Object[] visitee) {
        ParticipationMeeting participationMeeting = (ParticipationMeeting) visitee[0];
        Meeting meeting = (Meeting) visitee[1];

        System.out.printf("%-30s %-30s %-30s %-30s", meeting.title(), meeting.eventDate(), meeting.eventDuration(),
                meeting.createdBy());
    }
}
