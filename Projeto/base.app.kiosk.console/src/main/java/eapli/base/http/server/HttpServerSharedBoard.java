package eapli.base.http.server;

import eapli.base.Application;

import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.URI;

public class HttpServerSharedBoard implements Runnable {

    static private final String BASE_FOLDER = "www";
    static private ServerSocket sock;
    static private final int PORT = Application.settings().getSharedBoardPort();
    private String boardId;
    private final int MAX_ACCEPT_WAIT_TIME_FOR_CHECK_FOR_INTERRUPT = 3000;

    // DATA ACCESSED BY THREADS - LOCKING REQUIRED
    public HttpServerSharedBoard(String boardId) {
        this.boardId = boardId;
    }

    @Override
    public void run() {
        Socket cliSock;

        try {
            sock = new ServerSocket(PORT);

            String link = "http://localhost:" + PORT;

            //System.out.println("HTTP server running. Access: " + link);

            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                if (desktop.isSupported(Desktop.Action.BROWSE)) {
                    desktop.browse(URI.create(link));
                }
            }

        } catch (IOException ex) {
            System.out.println("Server failed to open local port " + PORT);
            System.exit(1);
        }

        while (!Thread.interrupted()) {
            try {
                sock.setSoTimeout(this.MAX_ACCEPT_WAIT_TIME_FOR_CHECK_FOR_INTERRUPT);
                cliSock = sock.accept();
                HttpSharedBoardRequest req = new HttpSharedBoardRequest(cliSock, BASE_FOLDER, this.boardId);
                req.start();
            } catch (SocketTimeoutException e) {
                // Não faz nada, porqueo timeout é usado apenas para caso o cliente feche o website,
                // e neste caso precisamos de garantir que o cliente pode sair da consola, ao realizar o interrupt
                // desta thread. (Para evitar o Thread.stop())
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            sock.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
