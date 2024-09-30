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

public abstract class SharedBoardProtocolRequest {

    protected final SBPMessageFormat request;

    protected SharedBoardProtocolRequest(final SBPMessageFormat inputRequest) {
        this.request = inputRequest;
    }

    /**
     * Executes the requested action and builds the response to the client.
     *
     * @return the response to send back to the client
     */
    public abstract SBPMessageFormat execute();

    /**
     * Indicates the object is a goodbye message, that is, a message that will close the
     * connection to the client.
     *
     * @return {@code true} if the object is a a goodbye message.
     */
    /*public boolean isGoodbye() {
        return false;
    }*/

    /*protected SBPMessageFormat buildServerError(final String errorDescription) {
        final BaseErrorRequest r = new ErrRequest(request, errorDescription);

        return r.buildResponse();
    }*/

    protected SBPMessageFormat buildErrRequest(final String errorDescription) {
        final BaseErrorRequest r = new ErrRequest(request, errorDescription);

        return r.buildResponse();
    }
}
