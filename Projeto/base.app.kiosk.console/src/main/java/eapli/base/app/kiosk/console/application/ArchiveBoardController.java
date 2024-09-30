package eapli.base.app.kiosk.console.application;

import eapli.base.app.kiosk.authz.ProxyRegistry;
import eapli.base.boardmanagement.domain.Board;
import eapli.base.sharedboard.csvprotocol.client.CsvSharedBoardProtocolProxy;
import eapli.framework.application.UseCaseController;

import java.io.IOException;

/**
 * Controlador para arquivar um quadro (board).
 */
@UseCaseController
public class ArchiveBoardController {

    private final CsvSharedBoardProtocolProxy csvSharedBoardProtocolProxy = ProxyRegistry.csvSharedBoardProtocolProxy();

    public ArchiveBoardController() {
    }

    public Iterable<Board> listAllBoardsOwned() throws IOException {
        return csvSharedBoardProtocolProxy.getAllBoardsOwned();
    }

    public Board archiveBoard(final Board theBoard) throws IOException {
        theBoard.archiveBoard();

        return csvSharedBoardProtocolProxy.saveBoard(theBoard);
    }
}
