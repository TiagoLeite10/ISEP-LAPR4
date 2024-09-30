package eapli.base.app.common.console.presentation.meeting;

import eapli.base.eventmanagement.domain.ParticipationMeeting;
import eapli.framework.visitor.Visitor;

public class ParticipationMeetingPrinter implements Visitor<ParticipationMeeting> {

    @Override
    public void visit(ParticipationMeeting visitee) {
        System.out.printf("%-30s %-30s", visitee.user().identity(),
                visitee.status());
    }
}

