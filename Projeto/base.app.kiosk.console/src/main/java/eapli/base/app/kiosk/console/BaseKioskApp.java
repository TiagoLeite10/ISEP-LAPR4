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
package eapli.base.app.kiosk.console;

import eapli.base.app.common.console.BaseApplication;
import eapli.base.app.kiosk.authz.LoginUI;
import eapli.base.app.kiosk.authz.ProxyRegistry;
import eapli.base.app.kiosk.console.presentation.MainMenu;
import eapli.base.http.server.HTTPServerProxy;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.sharedboard.csvprotocol.client.CsvSharedBoardProtocolProxy;
import eapli.base.usermanagement.domain.BasePasswordPolicy;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.pubsub.EventDispatcher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * eCafeteria KIOSK Application. The client of the Booking Daemon.
 *
 * @author Paulo Gandra de Sousa 2021.05.25
 */
@SuppressWarnings("squid:S106")
public final class BaseKioskApp extends BaseApplication {
    private static final Logger LOGGER = LogManager.getLogger(BaseKioskApp.class);
    private final CsvSharedBoardProtocolProxy csvSharedBoardProtocolProxy = ProxyRegistry.csvSharedBoardProtocolProxy();
    private final HTTPServerProxy httpServerProxy = ProxyRegistry.httpServerProxy();

    /**
     * Empty constructor is private to avoid instantiation of this class.
     */
    private BaseKioskApp() {
    }

    public static void main(final String[] args) {
        ProxyRegistry.configure();

        new BaseKioskApp().run(args);
    }

    @Override
    protected void doMain(final String[] args) {

        try {
            csvSharedBoardProtocolProxy.connect();
            System.out.println(csvSharedBoardProtocolProxy.testConnection());

            if (new LoginUI().show()) {
                final MainMenu menu = new MainMenu();
                menu.mainLoop();
                httpServerProxy.interruptHTTPServer();
            }


            boolean logout = false;
            while (!logout) {
                logout = this.csvSharedBoardProtocolProxy.logout();
                if (logout) {
                    System.out.println("Logout success!");
                } else {
                    System.out.println("Logout failed!");
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected String appTitle() {
        return "KIOSK Base";
    }

    @Override
    protected String appGoodbye() {
        return "Thank you for using 'KIOSK Base'";
    }

    @Override
    protected void doSetupEventHandlers(final EventDispatcher dispatcher) {
        // nothing to do
    }
}
