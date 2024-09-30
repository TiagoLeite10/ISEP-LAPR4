package eapli.base.app.common.console.presentation.meeting;

import eapli.base.app.common.console.Helpers;
import eapli.base.eventmanagement.application.CancelMeetingController;
import eapli.base.eventmanagement.domain.Meeting;
import eapli.base.eventmanagement.exception.MeetingNotHeldException;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * UI para cancelar uma reunião.
 */
public class CancelMeetingUI extends AbstractUI {

    private static final Logger LOGGER = LogManager.getLogger(CancelMeetingUI.class);

    private final CancelMeetingController theController = new CancelMeetingController(AuthzRegistry.authorizationService(),
            PersistenceContext.repositories().meetings());

    @Override
    protected boolean doShow() {
        Iterable<Meeting> allMeetingsToBeHeld = this.theController.listAllMeetingsToBeHeld();

        boolean hasMeetingsToBeHeld = allMeetingsToBeHeld.iterator().hasNext();

        if (!hasMeetingsToBeHeld) {
            System.out.println("There are no meetings to be held!");
        } else {
            System.out.println("Meetings list: ");
            String header = String.format("#  %-30s %-30s %-30s %-30s", "TITLE", "DATE", "DURATION", "ACCEPTED PARTICIPANTS");

            final SelectWidget<Meeting> selectorMeeting = new SelectWidget<>(header, allMeetingsToBeHeld, new MeetingPrinter());
            selectorMeeting.show();
            final Meeting meeting = selectorMeeting.selectedElement();

            if (meeting != null) {
                System.out.println("Meeting data: ");
                System.out.printf("%-30s %-30s %-30s %-30s %-10s\n", "TITLE", "DATE", "DURATION", "ACCEPTED PARTICIPANTS", "CANCELED");
                new MeetingPrinter().visit(meeting);

                System.out.println();

                boolean confirm = Helpers.askForConfirmation();

                if (confirm) {
                    try {
                        theController.cancelMeeting(meeting);

                        System.out.println("Meeting canceled successfully!");

                    } catch (final MeetingNotHeldException ex) {
                        System.out.println(ex.getMessage());
                    } catch (@SuppressWarnings("unused") final ConcurrencyException ex) {
                        System.out.println("WARNING: It is not possible to cancel the meeting because it was changed by another user.");
                    } catch (final IntegrityViolationException ex) {
                        LOGGER.error("Error performing the operation", ex);
                        System.out.println("Unfortunatelly there was an unexpected error in the application. " +
                                "Please try again and if the problem persists, contact your system admnistrator.");
                    }
                } else {
                    System.out.println("Operation cancelled!");
                }
            }
        }

        return true;
    }

    @Override
    public String headline() {
        return "Cancel a meeting";
    }
}
