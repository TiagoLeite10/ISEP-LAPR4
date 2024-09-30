package eapli.base.app.kiosk.console.application;

import eapli.base.app.kiosk.authz.ProxyRegistry;
import eapli.base.boardmanagement.domain.AccessPermission;
import eapli.base.boardmanagement.domain.Board;
import eapli.base.sharedboard.csvprotocol.client.CsvSharedBoardProtocolProxy;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.springframework.data.util.Pair;

import java.io.IOException;

/**
 * Controlador para compartilhar uma board.
 */
@UseCaseController
public class ShareBoardController {

    private final CsvSharedBoardProtocolProxy csvSharedBoardProtocolProxy = ProxyRegistry.csvSharedBoardProtocolProxy();

    public ShareBoardController() {
    }

    public Iterable<Board> listAllBoardsOwned() throws IOException {
        return csvSharedBoardProtocolProxy.getAllBoardsOwned();
    }

    public Iterable<SystemUser> listAllUsersAvailableToShare(final Board theBoard) throws IOException {
        return csvSharedBoardProtocolProxy.getAllUsersAvailableToShare(theBoard.title());
    }

    public Board shareBoard(final Board theBoard, final Iterable<Pair<SystemUser, AccessPermission>> users) throws IOException {
        theBoard.addListMembers(users);

        return csvSharedBoardProtocolProxy.saveBoard(theBoard);
    }
}
