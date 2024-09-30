package eapli.base.formativeexammanagement.domain;

import eapli.base.exammanagement.domain.ExamDateTime;
import eapli.base.exammanagement.domain.ExamTitle;
import eapli.base.exammanagement.domain.Header;
import eapli.framework.domain.model.DomainFactory;

import java.util.Calendar;

public class FormativeExamBuilder implements DomainFactory<FormativeExam> {

    private FormativeExam theFormativeExam;

    private ExamTitle title;

    private ExamDateTime dateTime;

    private Header header;

    public FormativeExamBuilder with(final String title, final Calendar openingDateTime, final Calendar closingDateTime, final Header header) {
        this.withTitle(title);
        this.withDateTime(openingDateTime, closingDateTime);
        this.withHeader(header);
        return this;
    }

    public FormativeExamBuilder withTitle(final String title) {
        this.title = ExamTitle.valueOf(title);
        return this;
    }

    public FormativeExamBuilder withDateTime(final Calendar openingDateTime, final Calendar closingDateTime) {
        this.dateTime = ExamDateTime.valueOf(openingDateTime, closingDateTime);
        return this;
    }

    public FormativeExamBuilder withHeader(final Header header) {
        this.header = header;
        return this;
    }

    public FormativeExamBuilder withSection(final FormativeExamSection formativeExamSection) {
        buildOrThrow();
        theFormativeExam.addFormativeExamSection(formativeExamSection);
        return this;
    }

    private FormativeExam buildOrThrow() {
        if (theFormativeExam != null) {
            return theFormativeExam;
        }
        if (title != null && dateTime != null && header != null) {
            theFormativeExam = new FormativeExam(title, dateTime, header);
            return theFormativeExam;
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public FormativeExam build() {
        final FormativeExam ret = buildOrThrow();
        // make sure we will create a new instance if someone reuses this builder and do
        // not change
        // the previously built dish.
        theFormativeExam = null;
        return ret;
    }
}
