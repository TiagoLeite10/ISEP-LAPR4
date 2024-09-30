package eapli.base.infrastructure.bootstrapers;

import eapli.base.boardmanagement.aplication.CreateBoardController;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.validations.Invariants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class BoardBootstrapper implements Action {

    private static final Logger LOGGER = LogManager.getLogger(BoardBootstrapper.class);

    @Override
    public boolean execute() {
        authenticateForBootstrapping("admin", "Password1");
        registerBoard1();
        registerBoard2();

        authenticateForBootstrapping("joaodasilva", "Senha123");
        registerBoard3();
        registerBoard4();

        authenticateForBootstrapping("anapereira", "Passw0rd");
        registerBoard5();
        registerBoard6();

        return true;
    }

    private void register(final String title, final int numLines, final int numColumns, final List<Pair<String, Integer>> linesData,
                          final List<Pair<String, Integer>> columnsData) {
        // dependency injection - when constructing the object one must inject the dependencies to
        // infrastructure objects it needs. this should be handled by a DI/IoC container like Spring
        // Framework
        final CreateBoardController controller = new CreateBoardController(
                AuthzRegistry.authorizationService(), PersistenceContext.repositories().boards());
        try {
            controller.addBoard(title, numLines, numColumns, linesData, columnsData);
        } catch (final IntegrityViolationException | ConcurrencyException ex) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", title);
            LOGGER.trace("Assuming existing record", ex);
        }
    }

    protected void authenticateForBootstrapping(String username, String password) {
        AuthzRegistry.authenticationService().authenticate(username, password);
        Invariants.ensure(AuthzRegistry.authorizationService().hasSession());
    }

    private void registerBoard1() {
        String title = "Relax that Fits";

        List<Pair<String, Integer>> linesData = new ArrayList<>();
        linesData.add(Pair.of("To do", 1));
        linesData.add(Pair.of("Doing", 2));
        linesData.add(Pair.of("Done", 3));

        final List<Pair<String, Integer>> columnsData = new ArrayList<>();
        columnsData.add(Pair.of("High Priority", 1));
        columnsData.add(Pair.of("Medium Priority", 2));
        columnsData.add(Pair.of("Low Priority", 3));

        int numLines = linesData.size();
        int numColumns = columnsData.size();

        register(title, numLines, numColumns, linesData, columnsData);
    }

    private void registerBoard2() {
        String title = "Recipe";

        List<Pair<String, Integer>> linesData = new ArrayList<>();
        linesData.add(Pair.of("Backlog", 1));
        linesData.add(Pair.of("In Progress", 2));
        linesData.add(Pair.of("Testing", 3));
        linesData.add(Pair.of("Completed", 4));

        final List<Pair<String, Integer>> columnsData = new ArrayList<>();
        columnsData.add(Pair.of("High Priority", 1));
        columnsData.add(Pair.of("Medium Priority", 2));
        columnsData.add(Pair.of("Low Priority", 3));
        columnsData.add(Pair.of("Urgent", 4));

        int numLines = linesData.size();
        int numColumns = columnsData.size();

        register(title, numLines, numColumns, linesData, columnsData);
    }

    private void registerBoard3() {
        String title = "My board";

        List<Pair<String, Integer>> linesData = new ArrayList<>();
        linesData.add(Pair.of("To Do", 1));
        linesData.add(Pair.of("In Progress", 2));
        linesData.add(Pair.of("Review", 3));
        linesData.add(Pair.of("Testing", 4));
        linesData.add(Pair.of("Done", 5));

        List<Pair<String, Integer>> columnsData = new ArrayList<>();
        columnsData.add(Pair.of("Urgent", 1));
        columnsData.add(Pair.of("High Priority", 2));
        columnsData.add(Pair.of("Medium Priority", 3));
        columnsData.add(Pair.of("Low Priority", 4));

        int numLines = linesData.size();
        int numColumns = columnsData.size();

        register(title, numLines, numColumns, linesData, columnsData);
    }

    private void registerBoard4() {
        String title = "Group board";

        List<Pair<String, Integer>> linesData = new ArrayList<>();
        linesData.add(Pair.of("Pending", 1));
        linesData.add(Pair.of("In Progress", 2));
        linesData.add(Pair.of("Completed", 3));

        List<Pair<String, Integer>> columnsData = new ArrayList<>();
        columnsData.add(Pair.of("High Priority", 1));
        columnsData.add(Pair.of("Medium Priority", 2));
        columnsData.add(Pair.of("Low Priority", 3));
        columnsData.add(Pair.of("No Priority", 4));

        int numLines = linesData.size();
        int numColumns = columnsData.size();

        register(title, numLines, numColumns, linesData, columnsData);
    }

    private void registerBoard5() {
        String title = "Shopping board";

        List<Pair<String, Integer>> linesData = new ArrayList<>();
        linesData.add(Pair.of("Backlog", 1));
        linesData.add(Pair.of("To do", 2));
        linesData.add(Pair.of("Doing", 3));
        linesData.add(Pair.of("Done", 4));

        List<Pair<String, Integer>> columnsData = new ArrayList<>();
        columnsData.add(Pair.of("Urgent", 1));
        columnsData.add(Pair.of("Normal", 2));

        int numLines = linesData.size();
        int numColumns = columnsData.size();

        register(title, numLines, numColumns, linesData, columnsData);
    }

    private void registerBoard6() {
        String title = "Sales board";

        List<Pair<String, Integer>> linesData = new ArrayList<>();
        linesData.add(Pair.of("Backlog", 1));
        linesData.add(Pair.of("To do", 2));
        linesData.add(Pair.of("In Progress", 3));
        linesData.add(Pair.of("Done", 4));

        List<Pair<String, Integer>> columnsData = new ArrayList<>();
        columnsData.add(Pair.of("Urgent", 1));
        columnsData.add(Pair.of("No Priority", 2));

        int numLines = linesData.size();
        int numColumns = columnsData.size();

        register(title, numLines, numColumns, linesData, columnsData);
    }
}
