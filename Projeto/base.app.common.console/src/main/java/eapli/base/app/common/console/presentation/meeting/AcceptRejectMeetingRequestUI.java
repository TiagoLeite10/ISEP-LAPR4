package eapli.base.app.common.console.presentation.meeting;

import eapli.base.app.common.console.Helpers;
import eapli.base.app.common.console.presentation.board.CreateBoardUI;
import eapli.base.eventmanagement.application.AcceptRejectMeetingRequestController;
import eapli.base.eventmanagement.domain.ParticipationMeeting;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class AcceptRejectMeetingRequestUI extends AbstractUI {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateBoardUI.class);

    private final AcceptRejectMeetingRequestController theController = new AcceptRejectMeetingRequestController(
            AuthzRegistry.authorizationService(), PersistenceContext.repositories().participationsMeetings());

    @Override
    protected boolean doShow() {

        Iterable<Object[]> pendentMeetingRequestList = theController.listUserPendentMeetingRequest();
        if (!pendentMeetingRequestList.iterator().hasNext()) {
            System.out.println("There is no pendent requests for meetings!");
            return false;
        }

        String header = String.format("#  %-30s %-30s %-30s %-30s", "TITLE", "DATE", "DURATION", "INVITED BY");
        System.out.println("Choose the request that you want to accept/refuse:");
        final SelectWidget<Object[]> pendentMeetingRequestSelector = new SelectWidget<>(header,
                pendentMeetingRequestList, new RequestedParticipationMeetingPrinter());
        pendentMeetingRequestSelector.show();
        final Object[] choosenInvite = pendentMeetingRequestSelector.selectedElement();

        if (choosenInvite == null) {
            System.out.println("Operation cancelled!");
            return false;
        }

        List<String> options = new ArrayList<>();
        options.add("Accept");
        options.add("Reject");
        SelectWidget<String> optionsSelector = new SelectWidget<>("You want to accept or reject the meeting?", options);
        optionsSelector.show();
        int choosenOption = optionsSelector.selectedOption();
        boolean yesOrNo = false;

        if (choosenOption != 1 && choosenOption != 2) {
            System.out.println("Operation cancelled!");
            return false;
        }

        if (choosenOption == 1)
            yesOrNo = true;

        String operationText = yesOrNo ? "accept" : "refuse";
        System.out.println("Are you sure you want to " + operationText + " the following meeting?");
        System.out.println(header);
        System.out.print("   ");
        new RequestedParticipationMeetingPrinter().visit(choosenInvite);
        System.out.println();
        boolean confirm = Helpers.askForConfirmation();

        if (confirm) {
            try {
                ParticipationMeeting theParticipationMeeting = (ParticipationMeeting) choosenInvite[0];
                theController.acceptMeeting(theParticipationMeeting, yesOrNo);
                System.out.println("The meeting was successfully " + operationText + "!");
            } catch (ClassCastException ex) {
                System.out.println("An error occurred! Please contact the administrator of the system!");
            } catch (IllegalArgumentException | IllegalStateException ex) {
                System.out.printf(ex.getMessage());
            } catch (@SuppressWarnings("unused") final ConcurrencyException ex) {
                System.out.println("WARNING: It is not possible to accept/reject the meeting because it was changed by another user.");
            } catch (final IntegrityViolationException ex) {
                LOGGER.error("Error performing the operation", ex);
                System.out.println("Unfortunatelly there was an unexpected error in the application. " +
                        "Please try again and if the problem persists, contact your system admnistrator.");
            }
        } else {
            System.out.println("Operation cancelled!");
            return false;
        }

        return true;
    }

    @Override
    public String headline() {
        return "Accept/Decline meeting request";
    }
}
