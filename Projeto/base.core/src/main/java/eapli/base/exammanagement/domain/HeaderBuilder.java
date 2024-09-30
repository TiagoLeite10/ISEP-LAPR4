package eapli.base.exammanagement.domain;

import eapli.framework.domain.model.DomainFactory;

public class HeaderBuilder implements DomainFactory<Header> {

    private HeaderDescription description;

    private HeaderType typeNote;

    private HeaderType typeFeedback;

    public HeaderBuilder with(final String description, final String typeNote, final String headerTypeFeedback) {
        this.withDescription(description);
        this.withTypeNote(typeNote);
        this.withTypeFeedback(headerTypeFeedback);
        return this;
    }

    public HeaderBuilder withDescription(final String description) {
        this.description = new HeaderDescription(description);
        return this;
    }

    public HeaderBuilder withTypeNote(final String typeNote) {
        this.typeNote = convertToHeaderType(typeNote);
        return this;
    }

    public HeaderBuilder withTypeFeedback(final String typeFeedback) {
        this.typeFeedback = convertToHeaderType(typeFeedback);
        return this;
    }

    private HeaderType convertToHeaderType(String headerType) {
        if (headerType == null) {
            return null;
        }

        switch (headerType.toLowerCase()) {
            case "none":
                return HeaderType.NONE;
            case "on-submission":
                return HeaderType.ON_SUBMISSION;
            case "after-closing":
                return HeaderType.AFTER_CLOSING;
            default:
                return null;
        }
    }

    @Override
    public Header build() {
        // since the factory knows that all the parts are needed it could throw
        // an exception. however, we will leave that to the constructor
        return new Header(this.description, this.typeNote, this.typeFeedback);
    }
}
