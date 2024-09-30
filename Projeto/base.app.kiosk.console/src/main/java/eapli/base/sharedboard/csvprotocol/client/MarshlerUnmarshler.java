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

import eapli.base.boardmanagement.util.SBPMessageFormat;

import java.util.List;

/**
 * Marshler/Unmarsheler for the CsvBookingProtocol. It is responsible for
 * marshling the data to create a proper network message and unmarshal the
 * network message to the proper DTO class
 *
 * @author Paulo Gandra de Sousa 2021.05.30
 */
/* package */ class MarshlerUnmarshler {

    public String parseResponseMessageCommunicationTestRequest(SBPMessageFormat response) {
        if (response.code() == SBPMessageFormat.SBP_MESSAGE_CODE_ACK) {
            return "Successfull communication!";
        }

        return "Insuccessfull comunication!";
    }

    public boolean parseResponseMessageSuccessfullAck(SBPMessageFormat response) {
        return response.code() == SBPMessageFormat.SBP_MESSAGE_CODE_ACK;
    }

    public String parseResponseHTMLSharedBoardMessage(SBPMessageFormat response) {
        if (response.code() == SBPMessageFormat.SBP_MESSAGE_CODE_ERR) {
            return "<div class=\"loading-message\"><p>No server reply, still trying... </p></div>";
        } else if (response.code() == SBPMessageFormat.SBP_MESSAGE_CODE_HTTP_BOARD_PRESENTATION) {
            return (String) response.data();
        } else {
            return "Unknown response!";
        }
    }

    private String removeDoubleQuotes(final String token) {
        return token.replace("\"", "").trim();
    }

    private void checkForErrorMessage(final List<String> response) throws FailedRequestException {
        final String[] tokens = response.get(0).split(",");
        final String messageType = tokens[0];

        if (messageType.equals("SERVER_ERROR") || messageType.equals("BAD_REQUEST")
                || messageType.equals("UNKNOWN_REQUEST") || messageType.equals("ERROR_IN_REQUEST")) {
            throw new FailedRequestException(messageType + ":" + tokens[tokens.length - 1]);
        }
    }
}
