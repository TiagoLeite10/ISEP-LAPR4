/*
 * Copyright (c) 2013-2023 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package eapli.base.csvprotocol.server;

import eapli.base.boardmanagement.util.SBPMessageFormat;
import eapli.base.daemon.sharedboard.synchronization.ReadWriteBoardsData;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.Authenticator;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CsvSharedBoardProtocolMessageParser {

    private static final Logger LOGGER = LogManager.getLogger(CsvSharedBoardProtocolMessageParser.class);

    private final Authenticator authenticationService;

    private final ReadWriteBoardsData readWriteBoardsData = new ReadWriteBoardsData(PersistenceContext.repositories().boards(), PersistenceContext.repositories().postIts());

    public CsvSharedBoardProtocolMessageParser(Authenticator authenticationService) {
        this.authenticationService = authenticationService;
    }

    /**
     * Parse and build the request.
     *
     * @param inputData
     * @return
     */
    public SharedBoardProtocolRequest parse(final SBPMessageFormat inputData, final boolean connectionTested, final boolean authenticated) {

        if (inputData.code() > 4 && (!connectionTested || !authenticated))
            return new ErrRequest(inputData, "Test connection and authentication are required!");

        // as a fallback make sure we return unknown
        SharedBoardProtocolRequest request = new UnknownRequest(inputData);

        // parse to determine which type of request and if it is syntactically valid
        //String[] tokens;
        //try {
        switch (inputData.code()) {
            case SBPMessageFormat.SBP_MESSAGE_CODE_COMMTEST:
                request = new CommtestRequest(inputData);
                break;
            case SBPMessageFormat.SBP_MESSAGE_CODE_DISCONN:
                request = new DisconnRequest(inputData);
                break;
            case SBPMessageFormat.SBP_MESSAGE_CODE_AUTH:
                request = new AuthRequest(inputData, authenticationService);
                break;
            case SBPMessageFormat.SBP_MESSAGE_CODE_ALL_BOARDS_OF_USER:
                request = new AllSharedBoardsOfUserRequest(inputData, AuthzRegistry.authorizationService(),
                        this.readWriteBoardsData);
                break;
            case SBPMessageFormat.SBP_MESSAGE_CODE_HTTP_BOARD_PRESENTATION:
                request = new HTTPBoardPresentationRequest(inputData, AuthzRegistry.authorizationService(), this.readWriteBoardsData);
                break;
            case SBPMessageFormat.SBP_MESSAGE_CODE_ALL_BOARDS_WITH_PERMISSION_WRITE_OF_USER:
                request = new AllSharedBoardsWithPermissionWriteOfUserRequest(inputData, AuthzRegistry.authorizationService(), this.readWriteBoardsData);
                break;
            case SBPMessageFormat.SBP_MESSAGE_CODE_SAVE_BOARD:
                request = new SaveBoardRequest(inputData, AuthzRegistry.authorizationService(), this.readWriteBoardsData);
                break;
            case SBPMessageFormat.SBP_MESSAGE_CODE_ALL_BOARDS_OWNED_OF_USER:
                request = new AllSharedBoardsOwnedOfUserRequest(inputData, AuthzRegistry.authorizationService(), this.readWriteBoardsData);
                break;
            case SBPMessageFormat.SBP_MESSAGE_CODE_ALL_USERS_AVAILABLE_TO_SHARE:
                request = new AllUsersAvailableToShareRequest(inputData, AuthzRegistry.authorizationService(), this.readWriteBoardsData);
                break;
            case SBPMessageFormat.SBP_MESSAGE_CODE_CURRENT_USER:
                request = new CurrentUserRequest(inputData, AuthzRegistry.authorizationService());
                break;
            case SBPMessageFormat.SBP_MESSAGE_CODE_VIEW_ALL_BOARD_UPDATES_HISTORY:
                request = new ViewBoardUpdatesHistoryRequest(inputData, AuthzRegistry.authorizationService(), this.readWriteBoardsData);
                break;
            case SBPMessageFormat.SBP_MESSAGE_CODE_ALL_EXISTING_POST_ITS:
                request = new AllExistingPostItsRequest(inputData, AuthzRegistry.authorizationService(), this.readWriteBoardsData);
                break;
            default:
                request = new ErrRequest(inputData, "Unable to parse request");
                LOGGER.info("Unable to parse request: {}", inputData.code());
        }

        return request;
    }

}
