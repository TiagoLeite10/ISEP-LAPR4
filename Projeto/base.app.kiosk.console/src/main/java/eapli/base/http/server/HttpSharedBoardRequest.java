package eapli.base.http.server;

import eapli.base.app.kiosk.authz.ProxyRegistry;
import eapli.base.sharedboard.csvprotocol.client.CsvSharedBoardProtocolProxy;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class HttpSharedBoardRequest extends Thread {
    String baseFolder;
    Socket sock;
    DataInputStream inS;
    DataOutputStream outS;
    String boardId;
    private CsvSharedBoardProtocolProxy csvSharedBoardProtocolProxy = ProxyRegistry.csvSharedBoardProtocolProxy();

    public HttpSharedBoardRequest(Socket s, String f, String boardId) {
        baseFolder = f;
        sock = s;
        this.boardId = boardId;
    }

    public void run() {
        try {
            outS = new DataOutputStream(sock.getOutputStream());
            inS = new DataInputStream(sock.getInputStream());
        } catch (IOException ex) {
            System.out.println("Thread error on data streams creation");
        }

        try {
            HTTPMessage request = new HTTPMessage(inS);
            HTTPMessage response = new HTTPMessage();
            // System.out.println(request.getURI());

            if (request.getMethod().equals("GET")) {

                if (request.getURI().equals("/sharedBoard")) {
                    response.setContentFromString(csvSharedBoardProtocolProxy.getBoardDataHTML(this.boardId), "text/html");
                    response.setResponseStatus("200 Ok");
                } else {
                    String fullname = baseFolder + "/";
                    if (request.getURI().equals("/")) fullname = fullname + "index.html";
                    else fullname = fullname + request.getURI();
                    if (response.setContentFromFile(fullname)) {
                        response.setResponseStatus("200 Ok");
                    } else {
                        response.setContentFromString("<html><body><h1>404 File not found</h1></body></html>", "text/html");
                        response.setResponseStatus("404 Not Found");
                    }
                }
                response.send(outS);
            } else { // NOT GET
                /*
                if (request.getMethod().equals("PUT")
                        && request.getURI().startsWith("/sharedBoard/")) {
                    HttpServerAjaxVoting.castVote(request.getURI().substring(7));
                    response.setResponseStatus("200 Ok");
                } else {
                    response.setContentFromString(
                            "<html><body><h1>ERROR: 405 Method Not Allowed</h1></body></html>",
                            "text/html");
                    response.setResponseStatus("405 Method Not Allowed");
                }
                response.send(outS);
                */
            }

        } catch (IOException ex) {
            System.out.println("Thread error when reading request");
        }

        try {
            sock.close();
        } catch (IOException ex) {
            System.out.println("CLOSE IOException");
        }
    }
}
