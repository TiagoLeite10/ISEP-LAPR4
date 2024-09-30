package eapli.base.csvprotocol.server;

import eapli.base.boardmanagement.util.SBPMessageFormat;
import eapli.base.boardmanagement.util.SBPMessageFormatOne;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public class CurrentUserRequest extends SharedBoardProtocolRequest {

    private final AuthorizationService authz;

    protected CurrentUserRequest(SBPMessageFormat inputRequest, AuthorizationService authz) {
        super(inputRequest);
        this.authz = authz;
    }

    @Override
    public SBPMessageFormat execute() {
        return this.buildResponse();
    }

    protected SBPMessageFormat buildResponse() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.TEACHER, BaseRoles.STUDENT);

        SystemUser user = authz.session().orElseThrow(IllegalStateException::new).authenticatedUser();

        return new SBPMessageFormatOne(SBPMessageFormat.SBP_MESSAGE_CODE_CURRENT_USER, user);
    }
}
