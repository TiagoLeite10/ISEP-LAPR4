package eapli.base.takenexammanagement.domain;

import eapli.base.clientusermanagement.domain.Student;
import eapli.base.exammanagement.domain.Exam;
import eapli.framework.domain.model.DomainFactory;

public class TakenExamBuilder implements DomainFactory<TakenExam> {

    private Grade grade;

    private Student student;

    private Exam exam;

    public TakenExamBuilder with(final Student student, final Float grade, final Exam exam) {
        this.withStudent(student);
        this.withGrade(grade);
        this.withExam(exam);
        return this;
    }

    public TakenExamBuilder withStudent(final Student student) {
        this.student = student;
        return this;
    }

    public TakenExamBuilder withGrade(final Float grade) {
        this.grade = Grade.valueOf(grade);
        return this;
    }

    public TakenExamBuilder withExam(final Exam exam) {
        this.exam = exam;
        return this;
    }

    @Override
    public TakenExam build() {
        // since the factory knows that all the parts are needed it could throw
        // an exception. however, we will leave that to the constructor
        return new TakenExam(this.grade, this.student, this.exam);
    }
}
