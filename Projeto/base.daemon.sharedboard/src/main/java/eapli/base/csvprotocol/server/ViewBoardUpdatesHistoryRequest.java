package eapli.base.csvprotocol.server;

import eapli.base.boardmanagement.domain.Board;
import eapli.base.boardmanagement.util.SBPMessageFormat;
import eapli.base.boardmanagement.util.SBPMessageFormatOne;
import eapli.base.daemon.sharedboard.synchronization.ReadWriteBoardsData;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

public class ViewBoardUpdatesHistoryRequest extends SharedBoardProtocolRequest {

    private final AuthorizationService authz;
    private final ReadWriteBoardsData readWriteBoardsData;
    private final byte messageCode = SBPMessageFormat.SBP_MESSAGE_CODE_VIEW_ALL_BOARD_UPDATES_HISTORY;


    protected ViewBoardUpdatesHistoryRequest(SBPMessageFormat inputRequest, AuthorizationService authz,
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

    protected SBPMessageFormat buildResponse() {
        String responseText;
        byte responseId;

        Board theBoard = this.readWriteBoardsData.readOneBoardData((String) super.request.data(), messageCode);
        if (theBoard != null) {
            responseId = messageCode;

            responseText = theBoard.boardCreationData();

            responseText += "\n\n-- Board members history: --\n";
            responseText += theBoard.membersHistory();

            responseText += "\n\n-- Board Post-Its history: --\n";
            responseText += theBoard.postItsHistory();
        } else {
            responseText = "Board not found!";
            responseId = SBPMessageFormat.SBP_MESSAGE_CODE_ERR;
        }

        return new SBPMessageFormatOne(responseId, responseText);
    }

}
