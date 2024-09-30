package eapli.base.app.kiosk.console.application;

import eapli.base.app.kiosk.authz.ProxyRegistry;
import eapli.base.boardmanagement.domain.Board;
import eapli.base.postitmanagement.domain.PostIt;
import eapli.base.sharedboard.csvprotocol.client.CsvSharedBoardProtocolProxy;
import eapli.framework.application.UseCaseController;

import java.io.IOException;

/**
 * Controlador para desfazer a última alteração a um post-it.
 */
@UseCaseController
public class UndoLastChangePostItController {

    private final CsvSharedBoardProtocolProxy csvSharedBoardProtocolProxy = ProxyRegistry.csvSharedBoardProtocolProxy();

    public UndoLastChangePostItController() {
    }

    public Iterable<Board> listAllBoardsWithPermissionWrite() throws IOException {
        return csvSharedBoardProtocolProxy.getAllBoardsWithPermissionWrite();
    }

    public Iterable<PostIt> listAllExistingPostIts(final Board theBoard) throws IOException {
        return csvSharedBoardProtocolProxy.getAllExistingPostIts(theBoard.title());
    }

    public PostIt findOldPostIt(final PostIt thePostIt) {
        return thePostIt.oldPostIt();
    }

    public Board removePostIt(final PostIt thePostIt, final Board theBoard) throws IOException {
        theBoard.removePostIt(thePostIt);

        return csvSharedBoardProtocolProxy.saveBoard(theBoard);
    }
}
