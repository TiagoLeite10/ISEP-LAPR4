package eapli.base.app.kiosk.console.application;

import eapli.base.app.kiosk.authz.ProxyRegistry;
import eapli.base.boardmanagement.domain.Board;
import eapli.base.boardmanagement.domain.BoardElementColumn;
import eapli.base.boardmanagement.domain.BoardElementLine;
import eapli.base.postitmanagement.application.PostItImageService;
import eapli.base.postitmanagement.domain.PostIt;
import eapli.base.sharedboard.csvprotocol.client.CsvSharedBoardProtocolProxy;
import eapli.framework.application.UseCaseController;

import java.io.IOException;

/**
 * Controlador para mudar um post-it num quadro (board).
 */
@UseCaseController
public class ChangePostItController {

    private final CsvSharedBoardProtocolProxy csvSharedBoardProtocolProxy = ProxyRegistry.csvSharedBoardProtocolProxy();

    public ChangePostItController() {
    }

    public Iterable<Board> listAllBoardsWithPermissionWrite() throws IOException {
        return csvSharedBoardProtocolProxy.getAllBoardsWithPermissionWrite();
    }

    public Iterable<PostIt> listAllExistingPostIts(final Board theBoard) throws IOException {
        return csvSharedBoardProtocolProxy.getAllExistingPostIts(theBoard.title());
    }

    public Iterable<BoardElementLine> listAllBoardElementLines(final Board theBoard) {
        return theBoard.boardElementLine();
    }

    public Iterable<BoardElementColumn> listAllBoardElementColumns(final Board theBoard) {
        return theBoard.boardElementColumn();
    }

    public Board changeContentPostIt(final Board theBoard, final PostIt thePostIt, String content) throws IOException {
        if (thePostIt.isImage())
            content = PostItImageService.moveImage(content);

        theBoard.changePostIt(thePostIt, content);

        return csvSharedBoardProtocolProxy.saveBoard(theBoard);
    }

    public Board changePositionPostIt(final Board theBoard, final PostIt thePostIt, final int line, final int column) throws IOException {
        theBoard.changePostIt(thePostIt, line, column);

        return csvSharedBoardProtocolProxy.saveBoard(theBoard);
    }
}
