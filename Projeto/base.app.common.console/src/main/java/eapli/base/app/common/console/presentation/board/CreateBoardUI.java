package eapli.base.app.common.console.presentation.board;

import eapli.base.app.common.console.Helpers;
import eapli.base.boardmanagement.aplication.CreateBoardController;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ListWidget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class CreateBoardUI extends AbstractUI {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateBoardUI.class);

    private final CreateBoardController theController = new CreateBoardController(
            AuthzRegistry.authorizationService(), PersistenceContext.repositories().boards());

    @Override
    protected boolean doShow() {

        String title = Console.readLine("Insert the title for the board: ");

        int numColumns = 0;
        boolean validNumColumns = false;
        while (!validNumColumns) {
            numColumns = Console.readInteger("Insert the number of columns you want to have on your board: ");
            if (theController.validColumnsForABoard(numColumns)) {
                validNumColumns = true;
            } else {
                System.out.println("The number of columns is out of limits! The maximum limit is " + theController.getMaximumColumnsForABoard() + "!");
            }
        }

        List<Pair<String, Integer>> columnsData = new ArrayList<>();
        for (int i = 0; i < numColumns; i++) {
            int column = i + 1;
            String columnTitle = Console.readLine("Insert the title for the column number " + column + ": ");
            columnsData.add(Pair.of(columnTitle, column));
        }

        int numLines = 0;
        boolean validNumLines = false;
        while (!validNumLines) {
            numLines = Console.readInteger("Insert the number of lines you want to have on your board: ");
            if (theController.validLinesForABoard(numLines)) {
                validNumLines = true;
            } else {
                System.out.println("The number of lines is out of limits! The maximum limit is " + theController.getMaximumLinesForABoard() + "!");
            }
        }

        List<Pair<String, Integer>> linesData = new ArrayList<>();
        for (int i = 0; i < numLines; i++) {
            int line = i + 1;
            String lineTitle = Console.readLine("Insert the title for the line number " + line + ": ");
            linesData.add(Pair.of(lineTitle, line));
        }

        System.out.println();
        System.out.println("** The board data you want to insert, please confirm it! **");
        System.out.println("Title: " + title);
        System.out.println("Number of lines: " + numLines);
        System.out.println("Number of columns: " + numColumns);
        System.out.println();
        final ListWidget<Pair<String, Integer>> columnsListing = new ListWidget<>("Columns titles " +
                "(format column_title->number_column):", columnsData);
        columnsListing.show();
        final ListWidget<Pair<String, Integer>> linesListing = new ListWidget<>("Lines titles " +
                "(format line_title->number_line):", linesData);
        linesListing.show();

        boolean confirm = Helpers.askForConfirmation();

        if (confirm) {
            try {
                this.theController.addBoard(title, numLines, numColumns, linesData, columnsData);
                System.out.println("The board was successfully created!");
            } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
                System.out.println("You tried to create a board which already exists in the database.");
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Operation cancelled!");
            return false;
        }

        return false;
    }

    @Override
    public String headline() {
        return "Create board";
    }
}
