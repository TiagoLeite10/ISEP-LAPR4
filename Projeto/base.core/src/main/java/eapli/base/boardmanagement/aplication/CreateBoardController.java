package eapli.base.boardmanagement.aplication;

import eapli.base.Application;
import eapli.base.boardmanagement.domain.Board;
import eapli.base.boardmanagement.domain.BoardBuilder;
import eapli.base.boardmanagement.domain.BoardCreationDateTime;
import eapli.base.boardmanagement.domain.BoardDimensions;
import eapli.base.boardmanagement.repositories.BoardRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.springframework.data.util.Pair;

import java.util.List;

/**
 * Controlador para criar um quadro (board).
 */
@UseCaseController
public class CreateBoardController {

    private final AuthorizationService authz;
    private final BoardRepository boardRepository;

    public CreateBoardController(final AuthorizationService authz, final BoardRepository repo) {
        // dependency injection - to make this object more testable we don't create the
        // infrastructure objects to avoid coupling to the implementation. This way, the controller
        // can be used in different scenarios with different implementations of the repository. for
        // instance, unit testing.
        this.authz = authz;
        this.boardRepository = repo;
    }

    private SystemUser currentUser() {
        return authz.session().orElseThrow(IllegalStateException::new).authenticatedUser();
    }

    public boolean validColumnsForABoard(final int numColumns) {
        //return true;
        return Application.settings().maximumNumberOfColumnsInABoard() >= numColumns;
    }

    public boolean validLinesForABoard(final int numLines) {
        //return true;
        return Application.settings().maximumNumberOfLinesInABoard() >= numLines;
    }

    public int getMaximumColumnsForABoard() {
        return Application.settings().maximumNumberOfColumnsInABoard();
    }

    public int getMaximumLinesForABoard() {
        return Application.settings().maximumNumberOfLinesInABoard();
    }

    public Board addBoard(final String title, final int numLines, final int numColumns, final List<Pair<String, Integer>> linesData,
                          final List<Pair<String, Integer>> columnsData) {

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.TEACHER, BaseRoles.STUDENT);

        final var newBoard = new BoardBuilder().entitled(title).creationDateTime(BoardCreationDateTime.valueOf())
                .dimensions(BoardDimensions.valueOf(numLines, numColumns))
                .owner(currentUser())
                .withBoardElementLines(linesData).withBoardElementColumns(columnsData).build();

        return boardRepository.save(newBoard);
    }
}
