package eapli.base.app.other.console.presentation.exams;

import eapli.base.takenexammanagement.dto.GradePerStudent;
import eapli.framework.visitor.Visitor;

public class GradePerStudentPrinter implements Visitor<GradePerStudent> {

    @Override
    public void visit(GradePerStudent visitee) {
        System.out.printf("%-30s %-50s %-30s %-30s", visitee.student.user().name(), visitee.examTitle, visitee.dateRealization,
                visitee.grade);
    }
}
