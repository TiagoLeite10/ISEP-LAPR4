package eapli.base.app.kiosk.console.application;

import eapli.base.app.kiosk.authz.ProxyRegistry;
import eapli.base.boardmanagement.domain.Board;
import eapli.base.http.server.HTTPServerProxy;
import eapli.base.sharedboard.csvprotocol.client.CsvSharedBoardProtocolProxy;
import eapli.framework.application.UseCaseController;

import java.io.IOException;

/**
 * Controlador para visualizar um quadro (board).
 */
@UseCaseController
public class ViewSharedBoardController {

    private final CsvSharedBoardProtocolProxy csvSharedBoardProtocolProxy = ProxyRegistry.csvSharedBoardProtocolProxy();
    private final HTTPServerProxy httpServerProxy = ProxyRegistry.httpServerProxy();

    public ViewSharedBoardController() {
    }

    public Iterable<Board> listAllBoards() throws IOException {
        return this.csvSharedBoardProtocolProxy.getAllBoardsOfAUser();
    }

    public void viewBoard(final Board theBoard) throws IOException {
        httpServerProxy.initiateHTTPServer(theBoard.identity());
    }
}
