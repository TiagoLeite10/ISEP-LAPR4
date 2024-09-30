package eapli.base.boardmanagement.util;

import java.io.Serializable;
import java.util.ArrayList;

public interface SBPMessageFormat<S extends Serializable> {
    byte SBP_MESSAGE_CODE_COMMTEST = 0;
    byte SBP_MESSAGE_CODE_DISCONN = 1;
    byte SBP_MESSAGE_CODE_ACK = 2;
    byte SBP_MESSAGE_CODE_ERR = 3;
    byte SBP_MESSAGE_CODE_AUTH = 4;
    byte SBP_MESSAGE_CODE_ALL_BOARDS_OF_USER = 5;
    byte SBP_MESSAGE_CODE_HTTP_BOARD_PRESENTATION = 6;
    byte SBP_MESSAGE_CODE_ALL_BOARDS_WITH_PERMISSION_WRITE_OF_USER = 7;
    byte SBP_MESSAGE_CODE_SAVE_BOARD = 8;
    byte SBP_MESSAGE_CODE_ALL_BOARDS_OWNED_OF_USER = 9;
    byte SBP_MESSAGE_CODE_ALL_USERS_AVAILABLE_TO_SHARE = 10;
    byte SBP_MESSAGE_CODE_CURRENT_USER = 11;
    byte SBP_MESSAGE_CODE_VIEW_ALL_BOARD_UPDATES_HISTORY = 12;
    byte SBP_MESSAGE_CODE_ALL_EXISTING_POST_ITS = 13;

    int version();

    int code();

    S data();

    ArrayList<S> dataArray();

    byte[] toMessageFormat();
}
