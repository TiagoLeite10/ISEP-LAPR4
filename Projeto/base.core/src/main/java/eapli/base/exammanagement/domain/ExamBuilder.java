package eapli.base.exammanagement.domain;

import eapli.framework.domain.model.DomainFactory;

import java.util.Calendar;

public class ExamBuilder implements DomainFactory<Exam> {

    private Exam theExam;

    private ExamTitle title;

    private ExamDateTime dateTime;

    private Header header;

    public ExamBuilder with(final String title, final Calendar openingDateTime, final Calendar closingDateTime, final Header header) {
        this.withTitle(title);
        this.withDateTime(openingDateTime, closingDateTime);
        this.withHeader(header);
        return this;
    }

    public ExamBuilder withTitle(final String title) {
        this.title = new ExamTitle(title);
        return this;
    }

    public ExamBuilder withDateTime(final Calendar openingDateTime, final Calendar closingDateTime) {
        this.dateTime = new ExamDateTime(openingDateTime, closingDateTime);
        return this;
    }

    public ExamBuilder withHeader(final Header header) {
        this.header = header;
        return this;
    }

    public ExamBuilder withSection(final ExamSection section) {
        buildOrThrow();
        theExam.addSection(section);
        return this;
    }

    public ExamBuilder withDateTime(ExamDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    private Exam buildOrThrow() {
        if (theExam != null) {
            return theExam;
        }
        if (title != null && dateTime != null && header != null) {
            theExam = new Exam(title, dateTime, header);
            return theExam;
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public Exam build() {
        final Exam ret = buildOrThrow();
        ret.checkScore();
        // make sure we will create a new instance if someone reuses this builder and do
        // not change
        // the previously built dish.
        theExam = null;
        return ret;
    }

    public Exam buildWithoutCheckScore() {
        final Exam ret = buildOrThrow();
        // make sure we will create a new instance if someone reuses this builder and do
        // not change
        // the previously built dish.
        theExam = null;
        return ret;
    }
}
