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
package eapli.base.daemon.sharedboard.presentation;

import eapli.base.boardmanagement.util.SBPMessageFormat;
import eapli.base.boardmanagement.util.SBPMessageFormatHelper;
import eapli.base.csvprotocol.server.CsvSharedBoardProtocolMessageParser;
import eapli.base.csvprotocol.server.SharedBoardProtocolRequest;
import eapli.framework.infrastructure.authz.application.AuthenticationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server socket for booking daemon using the CSV-based protocol.
 *
 * @author Paulo Gandra Sousa 01/06/2020
 */
public class CsvSharedBoardProtocolServer {
    private static final Logger LOGGER = LogManager.getLogger(CsvSharedBoardProtocolServer.class);

    /**
     * Client socket.
     *
     * @author Paulo Gandra Sousa 01/06/2020
     */
    private static class ClientHandler extends Thread {
        private Socket clientSocket;
        private final CsvSharedBoardProtocolMessageParser parser;

        public ClientHandler(final Socket socket, final CsvSharedBoardProtocolMessageParser parser) {
            this.clientSocket = socket;
            this.parser = parser;
        }

        @Override
        public void run() {

            final var clientIP = clientSocket.getInetAddress();
            LOGGER.debug("Acepted connection from {}:{}", clientIP.getHostAddress(), clientSocket.getPort());

            try (var out = new DataOutputStream(clientSocket.getOutputStream());
                    var in = new DataInputStream(clientSocket.getInputStream())) {

                boolean connectionTested = false;
                boolean authenticated = false;
                boolean connected = true;

                while (connected) {
                    SBPMessageFormat requestMessage = SBPMessageFormatHelper.createApropriateMessageFormat(in.readByte(), in);

                    LOGGER.debug("Received message type:----\n{}\n----", requestMessage.code());

                    final SharedBoardProtocolRequest request = parser.parse(requestMessage, connectionTested, authenticated);
                    final SBPMessageFormat response = request.execute();

                    if (requestMessage.code() == SBPMessageFormat.SBP_MESSAGE_CODE_COMMTEST) {
                        if (response.code() == SBPMessageFormat.SBP_MESSAGE_CODE_ACK) {
                            connectionTested = true;
                        }
                    } else if (requestMessage.code() == SBPMessageFormat.SBP_MESSAGE_CODE_AUTH) {
                        if (response.code() == SBPMessageFormat.SBP_MESSAGE_CODE_ACK) {
                            authenticated = true;
                        }
                    }

                    if (requestMessage.code() == SBPMessageFormat.SBP_MESSAGE_CODE_DISCONN && response.code() == SBPMessageFormat.SBP_MESSAGE_CODE_ACK) {
                        connected = false;
                    }

                    out.write(response.toMessageFormat());

                    LOGGER.debug("Sent message type:----\n{}\n----", response.code());

                }

                in.close();
                out.close();
            } catch (final IOException e) {
                LOGGER.error(e);
            } finally {
                try {
                    clientSocket.close();
                    LOGGER.debug("Closing client socket {}:{}", clientIP.getHostAddress(), clientSocket.getPort());
                } catch (final IOException e) {
                    LOGGER.error("While closing the client socket {}:{}", clientIP.getHostAddress(),
                            clientSocket.getPort(), e);
                }
                // null the reference to ensure it will be caught by the garbage collector
                clientSocket = null;

                // helper debug - SHOULD NOT BE USED IN PRODUCTION CODE!!!
                if (LOGGER.isDebugEnabled()) {
                    final int finalThreadCount = Thread.activeCount();
                    LOGGER.debug("Ending client thread - final thread count: {}", finalThreadCount);
                    final Thread[] t = new Thread[finalThreadCount];
                    final int n = Thread.enumerate(t);
                    for (var i = 0; i < n; i++) {
                        LOGGER.debug("T {}: {}", t[i].getId(), t[i].getName());
                    }
                }
            }
        }

    }

    private final CsvSharedBoardProtocolMessageParser parser;

    public CsvSharedBoardProtocolServer(final CsvSharedBoardProtocolMessageParser parser) {
        this.parser = parser;
    }

    /**
     * Wait for connections.
     * <p>
     * Suppress warning java:S2189 - Loops should not be infinite
     *
     * @param port
     */
    @SuppressWarnings("java:S2189")
    private void listen(final int port) {
        try (var serverSocket = new ServerSocket(port)) {
            while (true) {
                final var clientSocket = serverSocket.accept();
                new ClientHandler(clientSocket, parser).start();
            }
        } catch (final IOException e) {
            LOGGER.error(e);
        }
    }

    /**
     * Wait for connections.
     *
     * @param port
     * @param blocking
     *            if {@code false} the socket runs in its own thread and does not block calling
     *            thread.
     */
    public void start(final int port, final boolean blocking) {
        if (blocking) {
            listen(port);
        } else {
            new Thread(() -> listen(port)).start();
        }
    }
}
