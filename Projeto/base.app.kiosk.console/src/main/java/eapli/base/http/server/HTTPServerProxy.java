package eapli.base.http.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HTTPServerProxy {

    private static final Logger LOGGER = LogManager.getLogger(HTTPServerProxy.class);

    private HttpServerSharedBoard httpServerSharedBoard;
    private Thread httpServerSharedBoardThread;

    public void initiateHTTPServer(String boardId) {
        if (this.httpServerSharedBoardThread != null)
            this.interruptHTTPServer();

        this.httpServerSharedBoard = new HttpServerSharedBoard(boardId);
        this.httpServerSharedBoardThread = new Thread(this.httpServerSharedBoard);
        this.httpServerSharedBoardThread.start();
    }

    public void interruptHTTPServer() {
        if (this.httpServerSharedBoardThread != null) {
            this.httpServerSharedBoardThread.interrupt();
            try {
                this.httpServerSharedBoardThread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            this.httpServerSharedBoard = null;
            this.httpServerSharedBoardThread = null;
        }
    }
}
