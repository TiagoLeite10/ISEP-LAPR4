package eapli.base.app.common.console.presentation.meeting;

import eapli.base.Helper;
import eapli.base.app.common.console.presentation.authz.SystemUserPrinter;
import eapli.base.eventmanagement.application.CreateMeetingController;
import eapli.base.eventmanagement.domain.Meeting;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ListWidget;
import eapli.framework.presentation.console.SelectWidget;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class CreateMeetingUI extends AbstractUI {

    private final CreateMeetingController theController = new CreateMeetingController(AuthzRegistry.authorizationService(),
            PersistenceContext.repositories().meetings(), PersistenceContext.repositories().courses());

    @Override
    protected boolean doShow() {

        try {
            final String title = Console.readLine("Insert a title for the meeting:");
            final Calendar date = Console.readCalendar("Date (dd-MM-yyyy HH:mm)", "dd-MM-yyyy HH:mm");
            final int duration = Console.readInteger("Duration (min)");
            Meeting meeting = theController.addMeeting(title, date, duration);

            System.out.println("Meeting successfully created!");

            Iterable<SystemUser> allUsers = theController.listAllUsers();
            SystemUser actualUser = AuthzRegistry.authorizationService().session().orElseThrow(IllegalStateException::new).authenticatedUser();
            Helper.removeElementFromList(allUsers, actualUser);

            boolean hasAllUsers = allUsers.iterator().hasNext();

            if (!hasAllUsers) {
                System.out.println("There are no users in the system!");
            } else {
                System.out.println("List of possible participants in the meeting: ");

                String header = String.format("#  %-20s %-30s %-30s %-15s", "USERNAME", "FIRST NAME", "LAST NAME", "TYPE");


                Set<SystemUser> users = new HashSet<>();
                boolean flag = true;

                while (flag && hasAllUsers) {
                    System.out.println("Select a participant for meeting: ");
                    final SelectWidget<SystemUser> selectorUser = new SelectWidget<>(header, allUsers, new SystemUserPrinter());
                    selectorUser.show();
                    final SystemUser user = selectorUser.selectedElement();

                    if (user != null) {
                        users.add(user);
                        Helper.removeElementFromList(allUsers, user);
                        hasAllUsers = allUsers.iterator().hasNext();
                    } else {
                        flag = false;
                    }
                }

                theController.inviteUsers(meeting, users);

                System.out.println("Participant in the meeting: ");
                final ListWidget<SystemUser> selectorUser = new ListWidget<>(header, users, new SystemUserPrinter());

                selectorUser.show();

                System.out.println("Users successfully added to meeting");

            }
        } catch (@SuppressWarnings("unused") final IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
            System.out.println("You tried to enter a meeting which already exists in the database!");
        }

        return true;
    }

    @Override
    public String headline() {
        return "Schedule a Meeting";
    }
}

