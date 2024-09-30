package eapli.base.app.kiosk.authz;

import eapli.base.sharedboard.csvprotocol.client.CsvSharedBoardProtocolProxy;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.io.IOException;

public class LoginUI extends AbstractUI {

    private static final int DEFAULT_MAX_ATTEMPTS = 3;
    private final int maxAttempts;
    private final CsvSharedBoardProtocolProxy csvSharedBoardProtocolProxy = ProxyRegistry.csvSharedBoardProtocolProxy();

    public LoginUI() {
        maxAttempts = DEFAULT_MAX_ATTEMPTS;
    }

    public LoginUI(final int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    @Override
    protected boolean doShow() {
        int attempt = 1;
        while (attempt <= maxAttempts) {
            final String userName = Console.readLine("Username:");
            final String password = Console.readLine("Password:");

            try {
                if (csvSharedBoardProtocolProxy.authenticate(userName, password)) {
                    return true;
                } else {
                    System.out.printf("Wrong username or password. You have %d attempts left.%n%n»»»»»»»»»%n",
                            maxAttempts - attempt);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            attempt++;
        }
        System.out.println("Sorry, we are unable to authenticate you. Please contact your system admnistrator.");
        return false;
    }

    @Override
    public String headline() {
        return "Login";
    }
}
