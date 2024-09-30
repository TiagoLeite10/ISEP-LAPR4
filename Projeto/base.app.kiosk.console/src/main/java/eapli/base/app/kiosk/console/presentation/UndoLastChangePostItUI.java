package eapli.base.app.kiosk.console.presentation;

import eapli.base.app.common.console.Helpers;
import eapli.base.app.kiosk.console.application.UndoLastChangePostItController;
import eapli.base.boardmanagement.domain.Board;
import eapli.base.postitmanagement.domain.PostIt;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * UI para desfazer a última alteração a um post-it.
 */
public class UndoLastChangePostItUI extends AbstractUI {

    private static final Logger LOGGER = LogManager.getLogger(UndoLastChangePostItUI.class);

    private final UndoLastChangePostItController theController = new UndoLastChangePostItController();

    @Override
    protected boolean doShow() {
        Iterable<Board> allBoardsWithPermissionWrite;
        try {
            allBoardsWithPermissionWrite = this.theController.listAllBoardsWithPermissionWrite();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }

        boolean hasBoardsWithPermissionWrite = allBoardsWithPermissionWrite.iterator().hasNext();

        if (!hasBoardsWithPermissionWrite) {
            System.out.println("You do not own or have write permissions on any boards!");
            return false;
        } else {
            System.out.println("Boards list: ");
            String header = String.format("#  %-30s %-30s %-30s", "TITLE", "CREATION DATE", "CREATED BY");

            final SelectWidget<Board> selectorBoard = new SelectWidget<>(header, allBoardsWithPermissionWrite, new BoardPrinter());
            selectorBoard.show();
            final Board theBoard = selectorBoard.selectedElement();

            if (theBoard != null) {
                Iterable<PostIt> allExistingPostIts;
                try {
                    allExistingPostIts = this.theController.listAllExistingPostIts(theBoard);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    return false;
                }

                boolean hasExistingPostIts = allExistingPostIts.iterator().hasNext();

                if (!hasExistingPostIts) {
                    System.out.println("There is no post-it on the selected board!");
                    return false;
                } else {
                    System.out.println("Post-its list: ");
                    header = String.format("#  %-50s %-20s %-20s %-20s", "CONTENT", "LINE", "COLUMN", "TYPE");

                    final SelectWidget<PostIt> selectorPostIt = new SelectWidget<>(header, allExistingPostIts, new PostItPrinter());
                    selectorPostIt.show();
                    final PostIt thePostIt = selectorPostIt.selectedElement();

                    if (thePostIt != null) {
                        PostIt oldPostIt = this.theController.findOldPostIt(thePostIt);
                        if (oldPostIt != null) {

                            System.out.println("** Post-It **");
                            System.out.printf("%-50s %-20s %-20s %-20s%n", "CONTENT", "LINE", "COLUMN", "TYPE");
                            new PostItPrinter().visit(oldPostIt);
                            System.out.println();

                            boolean confirm = Helpers.askForConfirmation();

                            if (confirm) {
                                try {

                                    theController.removePostIt(thePostIt, theBoard);
                                    System.out.println("The post-it was successfully changed!");

                                } catch (@SuppressWarnings("unused") final ConcurrencyException ex) {
                                    System.out.println("WARNING: It is not possible to change the post-it because it was changed by another user.");
                                } catch (final IntegrityViolationException | IOException ex) {
                                    LOGGER.error("Error performing the operation", ex);
                                    System.out.println("Unfortunatelly there was an unexpected error in the application. " +
                                            "Please try again and if the problem persists, contact your system admnistrator.");
                                }

                                return false;
                            }
                        } else {
                            System.out.println("There are no changes in the chosen post-it!");
                            return false;
                        }
                    }
                }
            }

            System.out.println("Operation cancelled!");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Undo the last change in a post-it";
    }
}
