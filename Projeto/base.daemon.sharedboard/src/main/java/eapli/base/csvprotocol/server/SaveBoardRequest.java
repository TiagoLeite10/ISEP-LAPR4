package eapli.base.csvprotocol.server;

import eapli.base.boardmanagement.domain.Board;
import eapli.base.boardmanagement.util.SBPMessageFormat;
import eapli.base.boardmanagement.util.SBPMessageFormatOne;
import eapli.base.daemon.sharedboard.synchronization.ReadWriteBoardsData;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

public class SaveBoardRequest extends SharedBoardProtocolRequest {

    private final AuthorizationService authz;
    private final ReadWriteBoardsData readWriteBoardsData;
    private final byte messageCode = SBPMessageFormat.SBP_MESSAGE_CODE_SAVE_BOARD;

    protected SaveBoardRequest(SBPMessageFormat inputRequest, AuthorizationService authz,
                               ReadWriteBoardsData readWriteBoardsData) {
        super(inputRequest);
        this.authz = authz;
        this.readWriteBoardsData = readWriteBoardsData;
    }

    @Override
    public SBPMessageFormat execute() {
        return this.buildResponse();
    }

    protected SBPMessageFormat buildResponse() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.TEACHER, BaseRoles.STUDENT);

        Board theBoard = this.readWriteBoardsData.writeDataInBoard((Board) super.request.data());

        return new SBPMessageFormatOne(messageCode, theBoard);
    }
}
