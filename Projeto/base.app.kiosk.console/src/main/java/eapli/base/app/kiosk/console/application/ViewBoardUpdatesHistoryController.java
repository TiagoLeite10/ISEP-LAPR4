package eapli.base.app.kiosk.console.application;

import eapli.base.app.kiosk.authz.ProxyRegistry;
import eapli.base.boardmanagement.domain.Board;
import eapli.base.sharedboard.csvprotocol.client.CsvSharedBoardProtocolProxy;

import java.io.IOException;

public class ViewBoardUpdatesHistoryController {

    private final CsvSharedBoardProtocolProxy csvSharedBoardProtocolProxy = ProxyRegistry.csvSharedBoardProtocolProxy();

    public ViewBoardUpdatesHistoryController() {
    }

    public Iterable<Board> listAllBoards() throws IOException {
        try {
            return this.csvSharedBoardProtocolProxy.getAllBoardsOfAUser();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String showBoardUpdatesHistory(Board theBoard) {
        try {
            return this.csvSharedBoardProtocolProxy.viewBoardUpdatesHistory(theBoard.title());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
