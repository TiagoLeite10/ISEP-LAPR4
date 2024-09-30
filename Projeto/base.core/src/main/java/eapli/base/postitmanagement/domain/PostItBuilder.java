package eapli.base.postitmanagement.domain;

import eapli.framework.domain.model.DomainFactory;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public class PostItBuilder implements DomainFactory<PostIt> {

    private String content;

    private PostItPosition position;

    private PostItType type;

    private SystemUser creator;

    public PostItBuilder with(final String content, final int line, final int column, final PostItType type, final SystemUser creator) {
        this.withContent(content);
        this.withPosition(line, column);
        this.withType(type);
        this.withCreator(creator);
        return this;
    }

    public PostItBuilder withContent(final String content) {
        this.content = content;
        return this;
    }

    public PostItBuilder withPosition(final int line, final int column) {
        this.position = PostItPosition.valueOf(line, column);
        return this;
    }

    public PostItBuilder withType(final PostItType type) {
        this.type = type;
        return this;
    }

    public PostItBuilder withCreator(final SystemUser creator) {
        this.creator = creator;
        return this;
    }

    @Override
    public PostIt build() {
        // since the factory knows that all the parts are needed it could throw
        // an exception. however, we will leave that to the constructor
        return new PostIt(content, position, type, creator);
    }
}
