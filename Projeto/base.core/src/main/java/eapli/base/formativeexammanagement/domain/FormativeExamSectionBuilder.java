package eapli.base.formativeexammanagement.domain;

import eapli.base.exammanagement.domain.SectionDescription;
import eapli.framework.domain.model.DomainFactory;
import org.springframework.data.util.Pair;

public class FormativeExamSectionBuilder implements DomainFactory<FormativeExamSection> {

    private FormativeExamSection theFormativeExamSection;

    private SectionDescription description;

    public FormativeExamSectionBuilder withDescription(final String description) {
        this.description = SectionDescription.valueOf(description);
        return this;
    }

    public FormativeExamSectionBuilder withQuestionAndRequiredNumber(final Pair<String, Integer> questionAndRequiredNumber) {
        buildOrThrow();
        theFormativeExamSection.addQuestionAndRequiredNumber(questionAndRequiredNumber);
        return this;
    }

    private FormativeExamSection buildOrThrow() {
        if (theFormativeExamSection != null) {
            return theFormativeExamSection;
        }

        if (description != null) {
            theFormativeExamSection = new FormativeExamSection(description);
            return theFormativeExamSection;
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public FormativeExamSection build() {
        final FormativeExamSection ret = buildOrThrow();
        // make sure we will create a new instance if someone reuses this builder and do
        // not change
        // the previously built dish.
        theFormativeExamSection = null;
        return ret;
    }
}
