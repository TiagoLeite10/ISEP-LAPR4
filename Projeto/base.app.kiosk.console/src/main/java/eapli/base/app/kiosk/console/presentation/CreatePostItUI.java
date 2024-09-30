package eapli.base.app.kiosk.console.presentation;

import eapli.base.app.common.console.Helpers;
import eapli.base.app.kiosk.console.application.CreatePostItController;
import eapli.base.boardmanagement.domain.Board;
import eapli.base.boardmanagement.domain.BoardElementColumn;
import eapli.base.boardmanagement.domain.BoardElementLine;
import eapli.base.postitmanagement.domain.PostItType;
import eapli.base.postitmanagement.exception.ImageException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * UI para criar um post-it num quadro (board).
 */
public class CreatePostItUI extends AbstractUI {

    private final CreatePostItController theController = new CreatePostItController();

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
                PostItType type = readPostItType();

                if (type == null) {
                    System.out.println("Post-it type not indicated!");
                    return false;
                }

                String content = "";
                switch (type) {
                    case TEXT:
                        content = Console.readLine("Insert the content for the post-it: ");
                        break;
                    case IMAGE:
                        content = Console.readLine("Insert the path to the image for the post-it: ");
                        break;
                }

                Integer line = readLinePosition(theController.listAllBoardElementLines(theBoard));
                Integer column = readColumnPosition(theController.listAllBoardElementColumns(theBoard));

                if (line == null || column == null) {
                    System.out.println("It was not possible to create the post-it because not all attributes were indicated!");
                    return false;
                } else {
                    System.out.println();
                    System.out.println("** The post-it on a board data you want to insert, please confirm it **");
                    System.out.println("** Board **");
                    System.out.println("Title: " + theBoard.title());
                    System.out.println("** Post-It **");
                    System.out.println("Content: " + content);
                    System.out.println("Type: " + type);
                    System.out.println("Starting position (line): " + line);
                    System.out.println("starting position (column): " + column);
                    System.out.println();

                    boolean confirm = Helpers.askForConfirmation();

                    if (confirm) {
                        try {
                            theController.createPostIt(theBoard, content, line, column, type);
                            System.out.println("The post-it on a board was successfully created!");
                        } catch (final ImageException e) {
                            System.out.println(e.getMessage());
                        } catch (@SuppressWarnings("unused") final IntegrityViolationException | IOException e) {
                            System.out.println("You tried to create a post-it on a board which already exists in the database!");
                        }
                    } else {
                        System.out.println("Operation cancelled!");
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
        return "Create a post-it on a board";
    }

    public PostItType readPostItType() {
        List<PostItType> postItTypeList = Arrays.asList(PostItType.values());

        final SelectWidget<PostItType> selector = new SelectWidget<>("Select the post-it type", postItTypeList);
        selector.show();

        return selector.selectedElement();
    }

    public Integer readLinePosition(final Iterable<BoardElementLine> boardElementLines) {
        final SelectWidget<BoardElementLine> selector = new SelectWidget<>("Select the starting position (line)", boardElementLines);
        selector.show();
        final BoardElementLine theBoardElementLine = selector.selectedElement();

        if (theBoardElementLine != null) {
            return theBoardElementLine.line();
        } else {
            return null;
        }
    }

    public Integer readColumnPosition(final Iterable<BoardElementColumn> boardElementColumns) {
        final SelectWidget<BoardElementColumn> selector = new SelectWidget<>("Select the starting position (column)", boardElementColumns);
        selector.show();
        final BoardElementColumn theBoardElementColumn = selector.selectedElement();

        if (theBoardElementColumn != null) {
            return theBoardElementColumn.column();
        } else {
            return null;
        }
    }
}
