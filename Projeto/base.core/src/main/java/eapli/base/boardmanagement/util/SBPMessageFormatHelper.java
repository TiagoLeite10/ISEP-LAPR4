package eapli.base.boardmanagement.util;

import java.io.DataInputStream;
import java.io.IOException;

public class SBPMessageFormatHelper {
    public static SBPMessageFormat createApropriateMessageFormat(byte version, DataInputStream inputStream) throws IOException {
        switch (version) {
            case 1:
                return new SBPMessageFormatOne(inputStream);
        }

        throw new RuntimeException("Unsupported message format!");
    }
}
