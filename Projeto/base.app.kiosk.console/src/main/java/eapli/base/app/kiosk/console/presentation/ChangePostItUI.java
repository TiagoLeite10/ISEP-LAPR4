package eapli.base.app.kiosk.console.presentation;

import eapli.base.app.common.console.Helpers;
import eapli.base.app.kiosk.console.application.ChangePostItController;
import eapli.base.boardmanagement.domain.Board;
import eapli.base.boardmanagement.domain.BoardElementColumn;
import eapli.base.boardmanagement.domain.BoardElementLine;
import eapli.base.postitmanagement.domain.PostIt;
import eapli.base.postitmanagement.exception.ImageException;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * UI para mudar um post-it num quadro (board).
 */
public class ChangePostItUI extends AbstractUI {

    private static final Logger LOGGER = LogManager.getLogger(ChangePostItUI.class);

    private final ChangePostItController theController = new ChangePostItController();

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
                        int option = selectChangeType();

                        if (option == 0) {
                            System.out.println("It was not possible to change the post-it because the type of change intended was not indicated!");
                            return false;
                        }

                        String content = "";
                        Integer line = 0;
                        Integer column = 0;

                        switch (option) {
                            case 1:
                                content = Console.readLine("Insert the new content for the post-it: ");

                                System.out.println();
                                System.out.println("** The post-it on a board data you want to change, please confirm it **");
                                System.out.println("** Post-It **");
                                System.out.println("New content: " + content);
                                System.out.println();

                                break;

                            case 2:
                                line = readLinePosition(theController.listAllBoardElementLines(theBoard));
                                column = readColumnPosition(theController.listAllBoardElementColumns(theBoard));

                                if (line == null || column == null) {
                                    System.out.println("It was not possible to change the post-it position because not all attributes were indicated!");
                                    return false;
                                }

                                System.out.println();
                                System.out.println("** The post-it data you want to change, please confirm it **");
                                System.out.println("** Post-It **");
                                System.out.println("New position (line): " + line);
                                System.out.println("New position (column): " + column);
                                System.out.println();

                                break;
                        }

                        boolean confirm = Helpers.askForConfirmation();
                        if (confirm) {
                            try {
                                switch (option) {
                                    case 1:
                                        theController.changeContentPostIt(theBoard, thePostIt, content);
                                        break;
                                    case 2:
                                        theController.changePositionPostIt(theBoard, thePostIt, line, column);
                                        break;
                                }

                                System.out.println("The post-it was successfully changed!");

                            } catch (final ImageException e) {
                                System.out.println(e.getMessage());
                            } catch (@SuppressWarnings("unused") final ConcurrencyException ex) {
                                System.out.println("WARNING: It is not possible to change the post-it because it was changed by another user.");
                            } catch (final IntegrityViolationException | IOException ex) {
                                LOGGER.error("Error performing the operation", ex);
                                System.out.println("Unfortunatelly there was an unexpected error in the application. " +
                                        "Please try again and if the problem persists, contact your system admnistrator.");
                            }

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
        return "Change a post-it on a board";
    }

    public static int selectChangeType() {
        List<String> options = new ArrayList<>();
        options.add("Content");
        options.add("Position");

        final SelectWidget<String> optionSelector = new SelectWidget<>(
                "What do you want to change in the post-it?", options);
        optionSelector.show();

        return optionSelector.selectedOption();
    }

    public Integer readLinePosition(final Iterable<BoardElementLine> boardElementLines) {
        final SelectWidget<BoardElementLine> selector = new SelectWidget<>("Select the new position (line)", boardElementLines);
        selector.show();
        final BoardElementLine theBoardElementLine = selector.selectedElement();

        if (theBoardElementLine != null) {
            return theBoardElementLine.line();
        } else {
            return null;
        }
    }

    public Integer readColumnPosition(final Iterable<BoardElementColumn> boardElementColumns) {
        final SelectWidget<BoardElementColumn> selector = new SelectWidget<>("Select the new position (column)", boardElementColumns);
        selector.show();
        final BoardElementColumn theBoardElementColumn = selector.selectedElement();

        if (theBoardElementColumn != null) {
            return theBoardElementColumn.column();
        } else {
            return null;
        }
    }
}
