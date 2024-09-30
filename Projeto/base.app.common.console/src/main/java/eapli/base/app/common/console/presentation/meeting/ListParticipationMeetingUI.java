package eapli.base.app.common.console.presentation.meeting;

import eapli.base.eventmanagement.application.ListParticipationMeetingController;
import eapli.base.eventmanagement.domain.Meeting;
import eapli.base.eventmanagement.domain.ParticipationMeeting;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ListWidget;
import eapli.framework.presentation.console.SelectWidget;

public class ListParticipationMeetingUI extends AbstractUI {

    private final ListParticipationMeetingController theController = new ListParticipationMeetingController(AuthzRegistry.authorizationService(),
            PersistenceContext.repositories().meetings());


    @Override
    protected boolean doShow() {
        Iterable<Meeting> allMeetings = this.theController.listAllMeetings();

        boolean hasMeetings = allMeetings.iterator().hasNext();

        if (!hasMeetings) {
            System.out.println("You do not participate in any meeting!");
        } else {
            System.out.println("Meetings list: ");
            String header = String.format("#  %-30s %-30s %-30s %-30s %-10s", "TITLE", "DATE", "DURATION", "ACCEPTED PARTICIPANTS", "CANCELED");

            final SelectWidget<Meeting> selectorMeeting = new SelectWidget<>(header, allMeetings, new MeetingPrinter());
            selectorMeeting.show();
            final Meeting meeting = selectorMeeting.selectedElement();

            if (meeting != null) {
                Iterable<ParticipationMeeting> allParticipants = this.theController.listAllParticipants(meeting);

                boolean hasAllParticipants = allParticipants.iterator().hasNext();

                if (!hasAllParticipants) {
                    System.out.println("The meeting does not contain participants!");

                } else{
                    System.out.println("Participants list: ");
                    header = String.format("#  %-30s %-30s", "USERNAME", "STATUS");

                    final ListWidget<ParticipationMeeting> listParticipationMeeting = new ListWidget<>(header, allParticipants, new ParticipationMeetingPrinter());
                    listParticipationMeeting.show();
                    }
            }
        }

        return false;
    }

    @Override
    public String headline() {
        return "List participants";
    }
}
