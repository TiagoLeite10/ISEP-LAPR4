package eapli.base.app.kiosk.console.presentation;

import eapli.base.app.kiosk.console.application.ViewSharedBoardController;
import eapli.base.boardmanagement.domain.Board;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.io.IOException;

/**
 * UI para visualizar um quadro (board).
 */
public class ViewSharedBoardUI extends AbstractUI {

    private final ViewSharedBoardController theController = new ViewSharedBoardController();

    @Override
    protected boolean doShow() {
        Iterable<Board> allBoards = null;
        try {
            allBoards = this.theController.listAllBoards();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }

        boolean hasBoard = allBoards.iterator().hasNext();

        if (!hasBoard) {
            System.out.println("There are no boards!");
        } else {
            System.out.println("Boards list: ");
            String header = String.format("#  %-30s %-30s %-30s", "TITLE", "CREATION DATE", "CREATED BY");

            final SelectWidget<Board> selectorBoard = new SelectWidget<>(header, allBoards, new BoardPrinter());
            selectorBoard.show();
            final Board theBoard = selectorBoard.selectedElement();

            if (theBoard != null) {
                try {
                    theController.viewBoard(theBoard);
                } catch (IOException e) {
                    System.out.println("Unable to view selected board");
                }

            } else {
                System.out.println("Operation cancelled!");
            }
        }

        return false;
    }

    @Override
    public String headline() {
        return "View a board";
    }
}
