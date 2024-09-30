package eapli.base.app.kiosk.console.presentation;

import eapli.base.Helper;
import eapli.base.app.common.console.Helpers;
import eapli.base.app.common.console.presentation.authz.SystemUserPrinter;
import eapli.base.app.kiosk.console.application.ShareBoardController;
import eapli.base.boardmanagement.domain.AccessPermission;
import eapli.base.boardmanagement.domain.Board;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ListWidget;
import eapli.framework.presentation.console.SelectWidget;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.util.Pair;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * UI para compartilhar uma board.
 */
public class ShareBoardUI extends AbstractUI {

    private final ShareBoardController theController = new ShareBoardController();

    private static final Logger LOGGER = LogManager.getLogger(ShareBoardUI.class);

    @Override
    protected boolean doShow() {
        Iterable<Board> allBoardsOwned;
        try {
            allBoardsOwned = this.theController.listAllBoardsOwned();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }

        boolean hasBoard = allBoardsOwned.iterator().hasNext();

        if (!hasBoard) {
            System.out.println("You are not the owner of any boards!");
            return false;
        } else {
            System.out.println("Boards list: ");
            String boardHeader = String.format("#  %-30s %-30s %-30s", "TITLE", "CREATION DATE", "CREATED BY");

            final SelectWidget<Board> selectorBoard = new SelectWidget<>(boardHeader, allBoardsOwned, new BoardPrinter());
            selectorBoard.show();
            final Board theBoard = selectorBoard.selectedElement();

            if (theBoard != null) {
                Iterable<SystemUser> allUsers;

                try {
                    allUsers = this.theController.listAllUsersAvailableToShare(theBoard);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    return false;
                }

                boolean hasUser = allUsers.iterator().hasNext();

                if (!hasUser) {
                    System.out.println("There is no available user to add!");
                    return false;
                } else {
                    System.out.println("List of possible users available to add: ");

                    String userHeader = String.format("#  %-20s %-30s %-30s %-15s", "USERNAME", "FIRST NAME", "LAST NAME", "TYPE");

                    Set<Pair<SystemUser, AccessPermission>> users = new HashSet<>();
                    boolean flag = true;

                    while (flag && hasUser) {
                        System.out.println("Select one of the displayed users: ");
                        final SelectWidget<SystemUser> selectorUser = new SelectWidget<>(userHeader, allUsers, new SystemUserPrinter());
                        selectorUser.show();
                        final SystemUser user = selectorUser.selectedElement();

                        if (user != null) {
                            AccessPermission permission = selectPermission();
                            if (permission != null) {
                                users.add(Pair.of(user, permission));

                                Helper.removeElementFromList(allUsers, user);
                                hasUser = allUsers.iterator().hasNext();
                            }
                        } else {
                            flag = false;
                        }
                    }

                    System.out.println("List the invited users: ");
                    String header = String.format("#  %-50s %-30s", "NAME", "PERMISSION TYPE");
                    final ListWidget<Pair<SystemUser, AccessPermission>> usersListWidget = new ListWidget<>(header, users, new MemberPrinter());

                    boolean hasSelectedUsers = users.iterator().hasNext();

                    if (!hasSelectedUsers) {
                        System.out.println("There are no selected users!");
                        return false;
                    } else {
                        usersListWidget.show();

                        boolean confirm = Helpers.askForConfirmation();
                        try {
                            if (confirm) {
                                theController.shareBoard(theBoard, users);

                                System.out.println("The board has successefuly shared!");
                            }
                        } catch (@SuppressWarnings("unused") final ConcurrencyException ex) {
                            System.out.println("WARNING: It is not possible to share board because it was changed by another user.");
                        } catch (final IntegrityViolationException | IOException ex) {
                            LOGGER.error("Error performing the operation", ex);
                            System.out.println("Unfortunatelly there was an unexpected error in the application. " +
                                    "Please try again and if the problem persists, contact your system admnistrator.");
                        }
                    }
                }
            } else {
                System.out.println("Operation cancelled!");
            }
        }

        return false;
    }

    @Override
    public String headline() {
        return "Share a board";
    }

    public static AccessPermission selectPermission() {
        List<AccessPermission> options = Arrays.asList(AccessPermission.values());

        final SelectWidget<AccessPermission> optionSelector = new SelectWidget<>(
                "What permission do you want to give the user?", options);
        optionSelector.show();

        return optionSelector.selectedElement();
    }

}
