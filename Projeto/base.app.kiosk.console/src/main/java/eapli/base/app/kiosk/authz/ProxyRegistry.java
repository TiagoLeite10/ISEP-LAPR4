package eapli.base.app.kiosk.authz;

import eapli.base.http.server.HTTPServerProxy;
import eapli.base.sharedboard.csvprotocol.client.CsvSharedBoardProtocolProxy;
import eapli.framework.validations.Invariants;

public final class ProxyRegistry {
    private static CsvSharedBoardProtocolProxy csvSharedBoardProtocolProxy;
    private static HTTPServerProxy httpServerProxy;

    private ProxyRegistry() {
    }

    public static void configure() {
        csvSharedBoardProtocolProxy = new CsvSharedBoardProtocolProxy();
        httpServerProxy = new HTTPServerProxy();
    }

    public static CsvSharedBoardProtocolProxy csvSharedBoardProtocolProxy() {
        Invariants.nonNull(csvSharedBoardProtocolProxy);
        return csvSharedBoardProtocolProxy;
    }

    public static HTTPServerProxy httpServerProxy() {
        Invariants.nonNull(httpServerProxy);
        return httpServerProxy;
    }
}
