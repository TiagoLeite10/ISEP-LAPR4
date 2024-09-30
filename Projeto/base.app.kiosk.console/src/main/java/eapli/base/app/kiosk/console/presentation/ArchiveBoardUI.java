package eapli.base.app.kiosk.console.presentation;

import eapli.base.app.common.console.Helpers;
import eapli.base.app.kiosk.console.application.ArchiveBoardController;
import eapli.base.boardmanagement.domain.Board;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * UI para arquivar um quadro (board).
 */
public class ArchiveBoardUI extends AbstractUI {

    private static final Logger LOGGER = LogManager.getLogger(ArchiveBoardUI.class);

    private final ArchiveBoardController theController = new ArchiveBoardController();

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
            String header = String.format("#  %-30s %-30s %-30s", "TITLE", "CREATION DATE", "CREATED BY");

            final SelectWidget<Board> selectorBoard = new SelectWidget<>(header, allBoardsOwned, new BoardPrinter());
            selectorBoard.show();
            final Board theBoard = selectorBoard.selectedElement();

            if (theBoard != null) {
                System.out.println("Board data to be archived: ");
                System.out.printf("%-30s %-30s %-30s\n", "TITLE", "CREATION DATE", "CREATED BY");
                new BoardPrinter().visit(theBoard);

                System.out.println();

                boolean confirm = Helpers.askForConfirmation();

                if (confirm) {
                    try {
                        theController.archiveBoard(theBoard);

                        System.out.println("Board archived successfully!");

                    } catch (@SuppressWarnings("unused") final ConcurrencyException ex) {
                        System.out.println("WARNING: It is not possible to archive the board because it was changed by another user.");
                    } catch (final IntegrityViolationException | IOException ex) {
                        LOGGER.error("Error performing the operation", ex);
                        System.out.println("Unfortunatelly there was an unexpected error in the application. " +
                                "Please try again and if the problem persists, contact your system admnistrator.");
                    }

                } else {
                    System.out.println("Operation cancelled!");
                }
            }
        }

        return false;
    }

    @Override
    public String headline() {
        return "Archive a board";
    }
}
