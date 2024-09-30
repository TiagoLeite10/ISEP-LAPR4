package eapli.base.app.common.console.presentation.meeting;

import eapli.base.eventmanagement.domain.Meeting;
import eapli.framework.visitor.Visitor;

public class MeetingPrinter implements Visitor<Meeting> {

    @Override
    public void visit(Meeting visitee) {
        System.out.printf("%-30s %-30s %-30s %-30s %-10s", visitee.title(), visitee.eventDate(),
                visitee.eventDuration(), visitee.numberAcceptedParticipants(), visitee.canceled() ? "Yes" : "No");
    }
}

