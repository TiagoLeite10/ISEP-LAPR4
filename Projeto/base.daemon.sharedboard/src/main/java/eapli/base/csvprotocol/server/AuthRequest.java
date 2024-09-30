package eapli.base.csvprotocol.server;

import eapli.base.boardmanagement.util.SBPMessageFormat;
import eapli.base.boardmanagement.util.SBPMessageFormatOne;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.application.Authenticator;
import org.springframework.data.util.Pair;

import java.util.Optional;

public class AuthRequest extends SharedBoardProtocolRequest {

    private final Authenticator authenticationService;
    private final String username;
    private final String password;

    protected AuthRequest(SBPMessageFormat inputRequest, Authenticator authenticationService) {
        super(inputRequest);
        this.authenticationService = authenticationService;
        String data = (String) inputRequest.data();
        Pair<String, String> usernameAndPasswordData = this.usernameAndPasswordData(data);
        this.username = usernameAndPasswordData.getFirst();
        this.password = usernameAndPasswordData.getSecond();
    }

    @Override
    public SBPMessageFormat execute() {
        Optional<UserSession> userSession;

        try {
            userSession = authenticationService.authenticate(username, password);
        } catch (IllegalArgumentException e) {
            return new SBPMessageFormatOne(SBPMessageFormat.SBP_MESSAGE_CODE_ERR, e.getMessage());
        }

        if (userSession.isPresent()) {
            return new SBPMessageFormatOne(SBPMessageFormat.SBP_MESSAGE_CODE_ACK, "");
        }

        return new SBPMessageFormatOne(SBPMessageFormat.SBP_MESSAGE_CODE_ERR, "Invalid user credentials!");
    }

    private Pair<String, String> usernameAndPasswordData(String data) {

        int length = data.length();
        StringBuilder username = new StringBuilder();
        StringBuilder password = new StringBuilder();
        boolean readingUsername = true;
        for (int i = 0; i < length; i++) {
            char c = data.charAt(i);
            if (c != '\0') {
                if (readingUsername) {
                    username.append(data.charAt(i));
                } else {
                    password.append(data.charAt(i));
                }
            } else {
                readingUsername = false;
            }
        }

        return Pair.of(username.toString(), password.toString());
    }

}
