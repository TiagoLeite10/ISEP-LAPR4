package eapli.base.takenexammanagement.dto;

import eapli.base.clientusermanagement.domain.Student;
import eapli.base.exammanagement.domain.ExamTitle;
import eapli.base.takenexammanagement.domain.Grade;
import eapli.base.takenexammanagement.domain.TakenExamDateTime;
import eapli.framework.representations.dto.DTO;

@DTO
public class GradePerStudent {

    public Student student;

    public ExamTitle examTitle;

    public TakenExamDateTime dateRealization;

    public Grade grade;

    public GradePerStudent(final ExamTitle examTitle, final TakenExamDateTime dateRealization, final Grade grade) {
        this.examTitle = examTitle;
        this.dateRealization = dateRealization;
        this.grade = grade;
    }

    public GradePerStudent(final Student student, final ExamTitle examTitle, final TakenExamDateTime dateRealization, final Grade grade) {
        this.student = student;
        this.examTitle = examTitle;
        this.dateRealization = dateRealization;
        this.grade = grade;
    }
}
