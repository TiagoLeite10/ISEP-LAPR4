package eapli.base.daemon.sharedboard.synchronization;

import eapli.base.boardmanagement.domain.Board;
import eapli.base.boardmanagement.repositories.BoardRepository;
import eapli.base.boardmanagement.util.SBPMessageFormat;
import eapli.base.postitmanagement.domain.PostIt;
import eapli.base.postitmanagement.repositories.PostItRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;

import java.util.ArrayList;

public final class ReadWriteBoardsData {

    private final Object numReadersBlocker = new Object();
    private int numReaders = 0;
    private int numPendingWriters = 0;
    private boolean isWriting = false;
    private final BoardRepository boardRepository;
    private final PostItRepository postItRepository;

    public ReadWriteBoardsData(BoardRepository boardRepository, PostItRepository postItRepository) {
        this.boardRepository = boardRepository;
        this.postItRepository = postItRepository;
    }

    public Board writeDataInBoard(Board theBoard) {
        synchronized (numReadersBlocker) {
            numPendingWriters++;

            while (isWriting || numReaders > 0) {
                try {
                    numReadersBlocker.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            numPendingWriters--;
            isWriting = true;
        }

        // Realiza a escrita
        Board board = boardRepository.save(theBoard);

        synchronized (numReadersBlocker) {
            isWriting = false;
            numReadersBlocker.notifyAll();
        }

        return board;
    }

    private void newReader() {
        synchronized (numReadersBlocker) {
            while (isWriting || numPendingWriters > 0) {
                try {
                    numReadersBlocker.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            numReaders++;
            numReadersBlocker.notifyAll();
        }
    }

    private void readerLeft() {
        synchronized (numReadersBlocker) {
            numReaders--;

            if (numReaders == 0) {
                numReadersBlocker.notify();
            }
        }
    }

    public Board readOneBoardData(String boardTitle, byte typeSBPMessage) {
        this.newReader();

        Board board = null;

        // Realiza a leitura
        switch (typeSBPMessage) {
            case SBPMessageFormat.SBP_MESSAGE_CODE_HTTP_BOARD_PRESENTATION:
                board = this.boardRepository.find(boardTitle);
                break;
            case SBPMessageFormat.SBP_MESSAGE_CODE_VIEW_ALL_BOARD_UPDATES_HISTORY:
                board = this.boardRepository.findBoardWithBoardMembersAndPostIts(boardTitle);
                break;
        }

        this.readerLeft();

        return board;
    }

    public Iterable<Board> readVariousBoardsData(Username username, byte typeSBPMessage) {
        this.newReader();

        Iterable<Board> boards = new ArrayList<>();

        // Realiza a leitura
        switch (typeSBPMessage) {
            case SBPMessageFormat.SBP_MESSAGE_CODE_ALL_BOARDS_OF_USER:
                boards = this.boardRepository.findAllBoards(username);
                break;
            case SBPMessageFormat.SBP_MESSAGE_CODE_ALL_BOARDS_WITH_PERMISSION_WRITE_OF_USER:
                boards = this.boardRepository.findAllBoardsWithPermissionWrite(username);
                break;
            case SBPMessageFormat.SBP_MESSAGE_CODE_ALL_BOARDS_OWNED_OF_USER:
                boards = this.boardRepository.findAllBoardsOwned(username);
                break;
        }

        this.readerLeft();

        return boards;
    }

    public Iterable<SystemUser> readSystemUsersThatAreNotInABoard(String boardTitle) {
        this.newReader();

        // Realiza a leitura
        Iterable<SystemUser> sytemUsersThatAreNotInTheBoard = this.boardRepository.findAllUsersAvailableToShare(boardTitle);

        this.readerLeft();

        return sytemUsersThatAreNotInTheBoard;
    }

    public Iterable<PostIt> readPostItsInABoard(String boardTitle) {
        this.newReader();

        // Realiza a leitura
        Iterable<PostIt> postItsInABoard = this.postItRepository.findAllExistingPostIts(boardTitle);

        this.readerLeft();

        return postItsInABoard;
    }
}
