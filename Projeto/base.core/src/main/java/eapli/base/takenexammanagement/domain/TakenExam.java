package eapli.base.takenexammanagement.domain;

import eapli.base.clientusermanagement.domain.Student;
import eapli.base.exammanagement.domain.Exam;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;

@Entity
public class TakenExam implements AggregateRoot<Long> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long takenExamId;

    @Column(nullable = false)
    private TakenExamDateTime dateRealization;

    @Column(nullable = false)
    private Grade grade;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Exam exam;

    protected TakenExam(Grade grade, Student student, Exam exam) {
        Preconditions.noneNull(grade, student, exam, "TakenExam must not contain null or empty attributes");

        this.dateRealization = TakenExamDateTime.valueOf();
        this.grade = grade;
        this.student = student;
        this.exam = exam;
    }

    protected TakenExam() {
        // for ORM only
    }

    public Exam exam() {
        return exam;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof TakenExam)) {
            return false;
        }

        final TakenExam that = (TakenExam) other;
        if (this == that) {
            return true;
        }

        return grade.equals(that.grade) && student.sameAs(that.student) && exam.equals(that.exam);
    }

    @Override
    public Long identity() {
        return this.takenExamId;
    }
}
