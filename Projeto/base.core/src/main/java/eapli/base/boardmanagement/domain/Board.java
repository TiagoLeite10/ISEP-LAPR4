package eapli.base.boardmanagement.domain;

import eapli.base.postitmanagement.domain.PostIt;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.infrastructure.authz.domain.model.Name;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;
import org.springframework.data.util.Pair;

import javax.persistence.*;
import java.util.*;

/**
 * Representa a entidade Board.
 */
@Entity
public class Board implements AggregateRoot<String> {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    private String title;

    @Column
    private boolean archived;

    @Column(nullable = false)
    private BoardCreationDateTime boardCreationDateTime;

    @Column(nullable = false)
    private BoardDimensions boardDimensions;

    @ManyToOne()
    private SystemUser ownerUser;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private final Set<BoardElementLine> boardElementLineSet = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private final Set<BoardElementColumn> boardElementColumnSet = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private final Set<BoardMember> boardMembers = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private final List<PostIt> postIts = new ArrayList<>();

    protected Board(final String title, final BoardCreationDateTime boardCreationDateTime, final BoardDimensions boardDimensions,
                    final SystemUser ownerUser) {
        Preconditions.noneNull(title, boardCreationDateTime, ownerUser, boardDimensions);

        this.title = title;
        this.archived = false;
        this.boardCreationDateTime = boardCreationDateTime;
        this.boardDimensions = boardDimensions;
        this.ownerUser = ownerUser;
    }

    protected Board() {
        // for ORM only.
    }

    public void addBoardElementLine(String title, int line) {
        if (line > numOfLines()) {
            throw new IllegalArgumentException("Board element line cannot be in a position outside the board limits");
        }

        boardElementLineSet.add(new BoardElementLine(title, line));
    }

    public void addBoardElementColumn(String title, int column) {
        if (column > numOfColumns()) {
            throw new IllegalArgumentException("Board element column cannot be in a position outside the board limits");
        }

        boardElementColumnSet.add(new BoardElementColumn(title, column));
    }

    public void addPostIt(PostIt postIt) {
        if (postIt == null || postIt.line() > numOfLines() || postIt.column() > numOfColumns()) {
            throw new IllegalArgumentException("Post-it cannot be null nor be in a position outside the board limits");
        }

        postIts.add(postIt);
    }

    public void changePostIt(PostIt oldPostIt, final String content) {
        PostIt newPostIt = oldPostIt.cloneWithDifferentContent(content);

        changePostIt(oldPostIt, newPostIt);
    }

    public void changePostIt(PostIt oldPostIt, final int line, final int column) {
        PostIt newPostIt = oldPostIt.cloneWithDifferentPosition(line, column);

        changePostIt(oldPostIt, newPostIt);
    }

    private void changePostIt(PostIt oldPostIt, PostIt newPostIt) {
        for (PostIt p : postIts) {
            if (p.equals(oldPostIt)) {
                p.remove();
            }
        }

        newPostIt.definePreviousPostIt(oldPostIt);
        addPostIt(newPostIt);
    }

    public void archiveBoard() {
        this.archived = true;
    }

    public String title() {
        return title;
    }

    public BoardCreationDateTime creationDateTime() {
        return boardCreationDateTime;
    }

    public Name createdBy() {
        return ownerUser.name();
    }

    public int numOfColumns() {
        return boardDimensions.numColumns();
    }

    public int numOfLines() {
        return boardDimensions.numLines();
    }

    public boolean isArchived() {
        return archived;
    }

    public Set<BoardElementLine> boardElementLine() {
        return Collections.unmodifiableSet(this.boardElementLineSet);
    }

    public Set<BoardElementColumn> boardElementColumn() {
        return Collections.unmodifiableSet(this.boardElementColumnSet);
    }

    public List<PostIt> existingPostIts() {
        List<PostIt> existingPostIts = new ArrayList<>();

        for (PostIt postIt : postIts) {
            if (postIt.isExisting()) {
                existingPostIts.add(postIt);
            }
        }

        return existingPostIts;
    }

    public String boardCreationData() {
        return "Board Title: " + this.title + "\nBoard was created on " + this.boardCreationDateTime.toString();
    }

    public String membersHistory() {

        if (this.boardMembers.size() == 0)
            return "There is no history data for the members of this board!";

        StringBuilder membersHistory = new StringBuilder();
        for (BoardMember boardMember : this.boardMembers) {
            membersHistory.append("\n***** *****\n");
            membersHistory.append(boardMember.toString()).append("\n");
            membersHistory.append("***** *****\n");
        }

        return membersHistory.toString();
    }

    public String postItsHistory() {

        if (this.postIts.size() == 0)
            return "There is no history data for the post-its of this board!";

        StringBuilder postItsHistory = new StringBuilder();

        for (PostIt postIt : this.postIts) {
            postItsHistory.append("\n***** *****\n");
            postItsHistory.append(postIt.toString()).append("\n");
            postItsHistory.append("***** *****\n");
        }

        return postItsHistory.toString();
    }

    public void removePostIt(PostIt thePostIt) {
        for (PostIt p : postIts) {
            if (p.equals(thePostIt)) {
                p.oldPostIt().exist();
            }
        }

        postIts.remove(thePostIt);
    }

    public void addListMembers(Iterable<Pair<SystemUser, AccessPermission>> users) {
        for (Pair<SystemUser, AccessPermission> user : users) {
            boardMembers.add(new BoardMember(user.getFirst(), user.getSecond()));
        }
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Board))
            return false;

        final Board that = (Board) other;
        if (this == that)
            return true;

        return identity().equals(that.identity()) && archived == that.archived
                && boardDimensions.equals(that.boardDimensions)
                && ownerUser.equals(that.ownerUser);
    }

    @Override
    public String identity() {
        return this.title;
    }
}
