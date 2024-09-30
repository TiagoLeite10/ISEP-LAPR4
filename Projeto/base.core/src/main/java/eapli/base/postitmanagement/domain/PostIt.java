package eapli.base.postitmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;

/**
 * Representa a entidade PostIt.
 */
@Entity
public class PostIt implements AggregateRoot<Long> {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postItId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private PostItTimeStamp time;

    @Column(nullable = false)
    private PostItPosition position;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PostItType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PostItStatus status;

    @ManyToOne()
    private SystemUser creator;

    @OneToOne
    private PostIt previousPostIt;

    protected PostIt(final String content, final PostItPosition position, final PostItType type, final SystemUser creator) {
        Preconditions.noneNull(content, position, type, creator, "PostIt must not contain null or empty attributes");

        this.content = content;
        this.time = PostItTimeStamp.valueOf();
        this.position = position;
        this.type = type;
        this.status = PostItStatus.EXIST;
        this.creator = creator;
        this.previousPostIt = null;
    }

    protected PostIt() {
        // for ORM only.
    }

    public void defineNewContent(final String newContent) {
        this.content = newContent;
    }

    public void definePreviousPostIt(final PostIt previousPostIt) {
        this.previousPostIt = previousPostIt;
    }

    public String content() {
        return content;
    }

    public PostItTimeStamp time() {
        return time;
    }

    public int line() {
        return position.line();
    }

    public int column() {
        return position.column();
    }

    public PostItType type() {
        return type;
    }

    public String infoCreator() {
        return creator.name() + " (" + creator.email() + ")";
    }

    public PostIt oldPostIt() {
        return previousPostIt;
    }

    public boolean isExisting() {
        return status.equals(PostItStatus.EXIST);
    }

    public boolean isImage() {
        return type.equals(PostItType.IMAGE);
    }

    public void remove() {
        this.status = PostItStatus.REMOVED;
    }

    public void exist() {
        this.status = PostItStatus.EXIST;
    }

    protected PostIt clone() {
        return new PostIt(this.content, this.position, this.type, this.creator);
    }

    public PostIt cloneWithDifferentContent(final String content) {
        PostIt postIt = clone();
        postIt.content = content;

        return postIt;
    }

    public PostIt cloneWithDifferentPosition(final int line, final int column) {
        PostIt postIt = clone();
        postIt.position = PostItPosition.valueOf(line, column);

        return postIt;
    }

    private Long originalPostItIdentifier() {
        PostIt postIt = this;
        while (postIt.previousPostIt != null) {
            postIt = postIt.previousPostIt;
        }

        return postIt.identity();
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof PostIt))
            return false;

        final PostIt that = (PostIt) other;
        if (this == that)
            return true;

        return content.equalsIgnoreCase(that.content) && position.equals(that.position) && type.equals(that.type)
                && status.equals(that.status) && creator.sameAs(that.creator);
    }

    @Override
    public boolean equals(final Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public Long identity() {
        return this.postItId;
    }

    @Override
    public String toString() {
        return "Post-it identifier: " + this.originalPostItIdentifier() + "\nPost-it content: " + this.content
                + "\n" + (this.previousPostIt == null ? "Created on: " : "Updated on: ")
                + this.time.toString() + "\nPosition line: "
                + this.position.line() + "\nPosition column: " + this.position.column() + "\nPost-it type: "
                + this.type.toString() + (this.previousPostIt == null ? "\nCreated by: " + this.creator.name() : "");
    }
}
