package eapli.base.exammanagement.domain;

import eapli.base.questionmanagement.domain.Question;
import eapli.framework.domain.model.DomainFactory;

public class ExamSectionBuilder implements DomainFactory<ExamSection> {

    private ExamSection theSection;

    private SectionDescription description;

    public ExamSectionBuilder withDescription(final String description) {
        this.description = new SectionDescription(description);
        return this;
    }

    public ExamSectionBuilder withQuestion(final Question question) {
        buildOrThrow();
        theSection.addQuestion(question);
        return this;
    }

    private ExamSection buildOrThrow() {
        if (theSection != null) {
            return theSection;
        }

        if (description != null) {
            theSection = new ExamSection(description);
            return theSection;
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public ExamSection build() {
        final ExamSection ret = buildOrThrow();
        // make sure we will create a new instance if someone reuses this builder and do
        // not change
        // the previously built dish.
        theSection = null;
        return ret;
    }
}
