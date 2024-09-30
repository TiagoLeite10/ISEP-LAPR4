package eapli.base.csvprotocol.server;

import eapli.base.boardmanagement.domain.Board;
import eapli.base.boardmanagement.util.SBPMessageFormat;
import eapli.base.boardmanagement.util.SBPMessageFormatOne;
import eapli.base.daemon.sharedboard.synchronization.ReadWriteBoardsData;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.Username;

import java.util.ArrayList;

public class AllSharedBoardsOwnedOfUserRequest extends SharedBoardProtocolRequest {

    private final AuthorizationService authz;
    private final ReadWriteBoardsData readWriteBoardsData;
    private final byte messageCode = SBPMessageFormat.SBP_MESSAGE_CODE_ALL_BOARDS_OWNED_OF_USER;

    protected AllSharedBoardsOwnedOfUserRequest(SBPMessageFormat inputRequest, AuthorizationService authz,
                                                ReadWriteBoardsData readWriteBoardsData) {
        super(inputRequest);
        this.authz = authz;
        this.readWriteBoardsData = readWriteBoardsData;
    }

    @Override
    public SBPMessageFormat execute() {
        return this.buildResponse();
    }

    private Username currentUser() {
        return authz.session().orElseThrow(IllegalStateException::new).authenticatedUser().identity();
    }

    protected SBPMessageFormat buildResponse() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.TEACHER, BaseRoles.STUDENT);

        ArrayList<Board> boards = (ArrayList<Board>) this.readWriteBoardsData.readVariousBoardsData(this.currentUser(), messageCode);

        return new SBPMessageFormatOne(messageCode, boards);
    }
}
