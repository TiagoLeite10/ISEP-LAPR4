package eapli.base.csvprotocol.server;

import eapli.base.boardmanagement.util.SBPMessageFormat;
import eapli.base.boardmanagement.util.SBPMessageFormatOne;

public class CommtestRequest extends SharedBoardProtocolRequest {
    protected CommtestRequest(final SBPMessageFormat request) {
        super(request);
    }

    @Override
    public SBPMessageFormat execute() {
        // nothing to do, just build the response
        return buildResponse();
    }

    protected SBPMessageFormat buildResponse() {
        return new SBPMessageFormatOne(SBPMessageFormat.SBP_MESSAGE_CODE_ACK, "");
    }
}
