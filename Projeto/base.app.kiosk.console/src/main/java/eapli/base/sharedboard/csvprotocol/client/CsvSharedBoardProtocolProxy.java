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
package eapli.base.sharedboard.csvprotocol.client;

import eapli.base.Application;
import eapli.base.boardmanagement.domain.Board;
import eapli.base.boardmanagement.util.SBPMessageFormat;
import eapli.base.boardmanagement.util.SBPMessageFormatHelper;
import eapli.base.boardmanagement.util.SBPMessageFormatOne;
import eapli.base.postitmanagement.domain.PostIt;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Proxy for the CsvBookingProtocol.
 *
 * @author Paulo Gandra de Sousa 2021.05.25
 */
public class CsvSharedBoardProtocolProxy {
    private static final Logger LOGGER = LogManager.getLogger(CsvSharedBoardProtocolProxy.class);

    private ClientSocket clientSocket = new ClientSocket();

    /**
     * @author Paulo Gandra de Sousa 2021.05.25
     */
    private static class ClientSocket {

        private Socket sock;

        private DataOutputStream output;

        private DataInputStream input;

        /**
         * @param address
         * @param port
         * @throws IOException
         */
        public void connect(final String address, final int port) throws IOException {
            InetAddress serverIP;

            serverIP = InetAddress.getByName(address);

            sock = new Socket(serverIP, port);
            output = new DataOutputStream(sock.getOutputStream());
            input = new DataInputStream(sock.getInputStream());
            LOGGER.debug("Connected to {}", address);
        }

        /**
         *
         */
        public void send(SBPMessageFormat request) {
            try {
                output.write(request.toMessageFormat());
            } catch (IOException e) {
                if (sock.isClosed())
                    return;
                throw new RuntimeException(e);
            }

            //LOGGER.debug("Sent message with code \n-----\n{}\n-----", request.code());
        }

        public SBPMessageFormat recv() {
            SBPMessageFormat response = null;
            try {
                response = SBPMessageFormatHelper.createApropriateMessageFormat(input.readByte(), input);
            } catch (IOException e) {
                if (sock.isClosed())
                    return new SBPMessageFormatOne(SBPMessageFormat.SBP_MESSAGE_CODE_ERR, "The socket isn't available!");
                throw new RuntimeException(e);
            }
            //LOGGER.debug("Received message");
            return response;
        }

        /**
         * @param
         * @return
         * @throws IOException
         */
        public synchronized SBPMessageFormat sendAndRecv(SBPMessageFormat request) throws IOException {
            send(request);
            return recv();
        }

        /**
         * @throws IOException
         */
        public void stop() throws IOException {
            input.close();
            output.close();
            sock.close();
        }


    }

    private int getPort() {
        return Application.settings().getSharedBoardServerPort();
    }

    private String getAddress() {
        return Application.settings().getSharedBoardServerAddress();
    }

    public void connect() throws IOException {
        this.clientSocket.connect(getAddress(), getPort());
    }

    public void disconnect() throws IOException {
        this.clientSocket.stop();
    }


    // COMMTEST
    public String testConnection() throws IOException {
        SBPMessageFormat request = new SBPMessageFormatOne(SBPMessageFormat.SBP_MESSAGE_CODE_COMMTEST, "");
        final SBPMessageFormat response = this.clientSocket.sendAndRecv(request);
        return new MarshlerUnmarshler().parseResponseMessageCommunicationTestRequest(response);
    }

    // Auth
    public boolean authenticate(String username, String password) throws IOException {
        String data = username + "\0" + password + "\0";
        SBPMessageFormat request = new SBPMessageFormatOne(SBPMessageFormat.SBP_MESSAGE_CODE_AUTH, data);
        final SBPMessageFormat response = this.clientSocket.sendAndRecv(request);

        if (response.code() == SBPMessageFormat.SBP_MESSAGE_CODE_ERR)
            LOGGER.debug("Error on authentication {}", response.data());

        return new MarshlerUnmarshler().parseResponseMessageSuccessfullAck(response);
    }

    // Disconn
    public boolean logout() throws IOException {
        SBPMessageFormat request = new SBPMessageFormatOne(SBPMessageFormat.SBP_MESSAGE_CODE_DISCONN, "");
        final SBPMessageFormat response = this.clientSocket.sendAndRecv(request);
        boolean logoutSuccess = new MarshlerUnmarshler().parseResponseMessageSuccessfullAck(response);

        if (logoutSuccess)
            this.clientSocket.stop();

        return logoutSuccess;
    }

    public Iterable<Board> getAllBoardsOfAUser() throws IOException {
        SBPMessageFormatOne<String> request = new SBPMessageFormatOne<>(SBPMessageFormat.SBP_MESSAGE_CODE_ALL_BOARDS_OF_USER, "");

        final SBPMessageFormat response = this.clientSocket.sendAndRecv(request);

        return response.dataArray();
    }

    public String getBoardDataHTML(String boardId) throws IOException {
        SBPMessageFormatOne<String> request = new SBPMessageFormatOne<>(SBPMessageFormat.SBP_MESSAGE_CODE_HTTP_BOARD_PRESENTATION, boardId);
        final SBPMessageFormat response = this.clientSocket.sendAndRecv(request);

        return new MarshlerUnmarshler().parseResponseHTMLSharedBoardMessage(response);
    }

    public Iterable<Board> getAllBoardsWithPermissionWrite() throws IOException {
        SBPMessageFormatOne<String> request = new SBPMessageFormatOne<>(SBPMessageFormat.SBP_MESSAGE_CODE_ALL_BOARDS_WITH_PERMISSION_WRITE_OF_USER, "");

        final SBPMessageFormat response = this.clientSocket.sendAndRecv(request);

        return response.dataArray();
    }

    public Board saveBoard(final Board theBoard) throws IOException {
        SBPMessageFormatOne<Board> request = new SBPMessageFormatOne<>(SBPMessageFormat.SBP_MESSAGE_CODE_SAVE_BOARD, theBoard);

        final SBPMessageFormat response = this.clientSocket.sendAndRecv(request);

        return (Board) response.data();
    }

    public Iterable<Board> getAllBoardsOwned() throws IOException {
        SBPMessageFormatOne<String> request = new SBPMessageFormatOne<>(SBPMessageFormat.SBP_MESSAGE_CODE_ALL_BOARDS_OWNED_OF_USER, "");

        final SBPMessageFormat response = this.clientSocket.sendAndRecv(request);

        return response.dataArray();
    }

    public Iterable<SystemUser> getAllUsersAvailableToShare(String boardTitle) throws IOException {
        SBPMessageFormatOne<String> request = new SBPMessageFormatOne<>(SBPMessageFormat.SBP_MESSAGE_CODE_ALL_USERS_AVAILABLE_TO_SHARE, boardTitle);

        final SBPMessageFormat response = this.clientSocket.sendAndRecv(request);

        return response.dataArray();
    }

    public SystemUser getCurrentUser() throws IOException {
        SBPMessageFormatOne<String> request = new SBPMessageFormatOne<>(SBPMessageFormat.SBP_MESSAGE_CODE_CURRENT_USER, "");

        final SBPMessageFormat response = this.clientSocket.sendAndRecv(request);

        return (SystemUser) response.data();
    }

    public String viewBoardUpdatesHistory(String boardTitle) throws IOException {
        SBPMessageFormatOne<String> request = new SBPMessageFormatOne<>(SBPMessageFormat.SBP_MESSAGE_CODE_VIEW_ALL_BOARD_UPDATES_HISTORY, boardTitle);

        final SBPMessageFormat response = this.clientSocket.sendAndRecv(request);

        return (String) response.data();
    }

    public Iterable<PostIt> getAllExistingPostIts(String boardTitle) throws IOException {
        SBPMessageFormatOne<String> request = new SBPMessageFormatOne<>(SBPMessageFormat.SBP_MESSAGE_CODE_ALL_EXISTING_POST_ITS, boardTitle);

        final SBPMessageFormat response = this.clientSocket.sendAndRecv(request);

        return response.dataArray();
    }
}