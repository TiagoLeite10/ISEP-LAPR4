package eapli.base.app.kiosk.console.application;

import eapli.base.app.kiosk.authz.ProxyRegistry;
import eapli.base.boardmanagement.domain.Board;
import eapli.base.boardmanagement.domain.BoardElementColumn;
import eapli.base.boardmanagement.domain.BoardElementLine;
import eapli.base.postitmanagement.application.PostItImageService;
import eapli.base.postitmanagement.domain.PostItBuilder;
import eapli.base.postitmanagement.domain.PostItType;
import eapli.base.sharedboard.csvprotocol.client.CsvSharedBoardProtocolProxy;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.io.IOException;

/**
 * Controlador para criar um post-it num quadro (board).
 */
@UseCaseController
public class CreatePostItController {

    private final CsvSharedBoardProtocolProxy csvSharedBoardProtocolProxy = ProxyRegistry.csvSharedBoardProtocolProxy();

    public CreatePostItController() {
    }

    private SystemUser currentUser() throws IOException {
        return csvSharedBoardProtocolProxy.getCurrentUser();
    }

    public Iterable<Board> listAllBoardsWithPermissionWrite() throws IOException {
        return csvSharedBoardProtocolProxy.getAllBoardsWithPermissionWrite();
    }

    public Iterable<BoardElementLine> listAllBoardElementLines(final Board theBoard) {
        return theBoard.boardElementLine();
    }

    public Iterable<BoardElementColumn> listAllBoardElementColumns(final Board theBoard) {
        return theBoard.boardElementColumn();
    }

    public Board createPostIt(final Board theBoard, final String content, final int line, final int column, final PostItType type) throws IOException {
        final var thePostIt = new PostItBuilder().withContent(content).withPosition(line, column).withType(type).withCreator(currentUser()).build();

        if (thePostIt.isImage())
            thePostIt.defineNewContent(PostItImageService.moveImage(content));

        theBoard.addPostIt(thePostIt);

        return csvSharedBoardProtocolProxy.saveBoard(theBoard);
    }
}
