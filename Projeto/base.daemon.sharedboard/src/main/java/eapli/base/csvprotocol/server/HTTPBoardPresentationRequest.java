package eapli.base.csvprotocol.server;

import eapli.base.boardmanagement.domain.Board;
import eapli.base.boardmanagement.domain.BoardElementColumn;
import eapli.base.boardmanagement.domain.BoardElementLine;
import eapli.base.boardmanagement.util.SBPMessageFormat;
import eapli.base.boardmanagement.util.SBPMessageFormatOne;
import eapli.base.daemon.sharedboard.synchronization.ReadWriteBoardsData;
import eapli.base.postitmanagement.domain.PostIt;
import eapli.base.postitmanagement.domain.PostItType;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

import java.util.ArrayList;
import java.util.List;

public class HTTPBoardPresentationRequest extends SharedBoardProtocolRequest {

    private final AuthorizationService authz;
    private final ReadWriteBoardsData readWriteBoardsData;
    private final byte messageCode = SBPMessageFormat.SBP_MESSAGE_CODE_HTTP_BOARD_PRESENTATION;

    protected HTTPBoardPresentationRequest(SBPMessageFormat<Board> inputRequest, AuthorizationService authz,
                                           ReadWriteBoardsData readWriteBoardsData) {
        super(inputRequest);
        this.authz = authz;
        this.readWriteBoardsData = readWriteBoardsData;
    }

    @Override
    public SBPMessageFormat execute() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.TEACHER, BaseRoles.STUDENT);
        return this.buildResponse();
    }

    protected synchronized SBPMessageFormat buildResponse() {
        Board board = this.readWriteBoardsData.readOneBoardData((String) super.request.data(), messageCode);

        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<h1>").append(board.title()).append("</h1>");
        htmlBuilder.append("<table>");

        htmlBuilder.append("<tr>");

        // Célula vazia no canto superior esquerdo
        htmlBuilder.append("<th></th>");

        for (int columnCount = 1; columnCount <= board.numOfColumns(); columnCount++) {
            // Cabeçalho da coluna
            htmlBuilder.append("<th>").append(getColumnTitle(board, columnCount)).append("</th>");
        }

        htmlBuilder.append("</tr>");

        for (int lineCount = 1; lineCount <= board.numOfLines(); lineCount++) {
            htmlBuilder.append("<tr>");

            // Cabeçalho da linha
            htmlBuilder.append("<th>").append(getLineTitle(board, lineCount)).append("</th>");

            // Conteúdo dos post-its
            for (int columnCount = 1; columnCount <= board.numOfColumns(); columnCount++) {
                List<PostIt> postIts = getPostItInPosition(board, lineCount, columnCount);
                if (postIts.isEmpty()) {
                    htmlBuilder.append("<td>").append("</td>");
                } else {
                    htmlBuilder.append("<td>");
                    for (PostIt postIt : postIts) {
                        htmlBuilder.append("<div id=\"post-it\">");

                        if (postIt.type().equals(PostItType.TEXT)) {
                            htmlBuilder.append("<div class=\"post-it-content\"");
                            htmlBuilder.append("<p>").append(postIt.content()).append("</p>");
                            htmlBuilder.append("</div>");
                        } else {
                            htmlBuilder.append("<img class=\"post-it-image\" src=\"").append(postIt.content()).append("\"/>");
                        }

                        htmlBuilder.append("<div class=\"post-it-data\">");
                        htmlBuilder.append("<p><b>Last modified:</b> ").append(postIt.time()).append("</p>");
                        htmlBuilder.append("<p><b>Created by:</b> ").append(postIt.infoCreator()).append("</p>");
                        htmlBuilder.append("</div>");

                        htmlBuilder.append("</div>");
                    }

                    htmlBuilder.append("</td>");
                }
            }

            htmlBuilder.append("</tr>");
        }

        htmlBuilder.append("</table>");

        return new SBPMessageFormatOne(messageCode, htmlBuilder.toString());
    }

    private String getLineTitle(Board board, int lineNumber) {
        for (BoardElementLine line : board.boardElementLine()) {
            if (line.line() == lineNumber) {
                return line.title();
            }
        }

        return "";
    }

    private String getColumnTitle(Board board, int columnNumber) {
        for (BoardElementColumn column : board.boardElementColumn()) {
            if (column.column() == columnNumber) {
                return column.title();
            }
        }

        return "";
    }

    private List<PostIt> getPostItInPosition(Board board, int lineNumber, int columnNumber) {
        List<PostIt> contents = new ArrayList<>();

        for (PostIt postIt : board.existingPostIts()) {
            if (postIt.line() == lineNumber && postIt.column() == columnNumber) {
                contents.add(postIt);
            }
        }

        return contents;
    }
}
