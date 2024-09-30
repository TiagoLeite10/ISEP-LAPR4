package eapli.base.boardmanagement.domain;

import eapli.framework.domain.model.DomainFactory;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.springframework.data.util.Pair;

import java.util.List;

public class BoardBuilder implements DomainFactory<Board> {

    private Board theBoard;
    private String title;
    private BoardCreationDateTime boardCreationDateTime;
    private BoardDimensions boardDimensions;
    private SystemUser ownerUser;

    public BoardBuilder entitled(String title) {
        this.title = title;
        return this;
    }

    public BoardBuilder creationDateTime(BoardCreationDateTime boardCreationDateTime) {
        this.boardCreationDateTime = boardCreationDateTime;
        return this;
    }

    public BoardBuilder dimensions(BoardDimensions boardDimensions) {
        this.boardDimensions = boardDimensions;
        return this;
    }

    public BoardBuilder owner(SystemUser ownerUser) {
        this.ownerUser = ownerUser;
        return this;
    }

    public BoardBuilder withBoardElementColumns(final List<Pair<String, Integer>> boardElementColumns) {
        // we will simply ignore if we receive a null set
        if (boardElementColumns != null) {
            boardElementColumns.forEach(this::withBoardElementColumns);
        }
        return this;
    }

    public BoardBuilder withBoardElementColumns(final Pair<String, Integer> boardElementColumns) {
        buildOrThrow();
        theBoard.addBoardElementColumn(boardElementColumns.getFirst(), boardElementColumns.getSecond());
        return this;
    }

    public BoardBuilder withBoardElementLines(final List<Pair<String, Integer>> boardElementLines) {
        // we will simply ignore if we receive a null set
        if (boardElementLines != null) {
            boardElementLines.forEach(this::withBoardElementLines);
        }
        return this;
    }

    public BoardBuilder withBoardElementLines(final Pair<String, Integer> boardElementLines) {
        buildOrThrow();
        theBoard.addBoardElementLine(boardElementLines.getFirst(), boardElementLines.getSecond());
        return this;
    }

    private Board buildOrThrow() {
        if (theBoard != null) {
            return theBoard;
        }
        if (title != null && boardCreationDateTime != null && boardDimensions != null && ownerUser != null) {
            theBoard = new Board(title, boardCreationDateTime, boardDimensions, ownerUser);
            return theBoard;
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public Board build() {
        final Board ret = buildOrThrow();
        // make sure we will create a new instance if someone reuses this builder and do
        // not change
        // the previously built dish.
        theBoard = null;
        return ret;
    }
}
