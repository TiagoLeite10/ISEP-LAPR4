package eapli.base.csvprotocol.server;

import eapli.base.boardmanagement.util.SBPMessageFormat;
import eapli.base.boardmanagement.util.SBPMessageFormatOne;

public class DisconnRequest extends SharedBoardProtocolRequest {
    protected DisconnRequest(SBPMessageFormat inputRequest) {
        super(inputRequest);
    }

    @Override
    public SBPMessageFormat execute() {
        return new SBPMessageFormatOne(SBPMessageFormat.SBP_MESSAGE_CODE_ACK, "");
    }
}
