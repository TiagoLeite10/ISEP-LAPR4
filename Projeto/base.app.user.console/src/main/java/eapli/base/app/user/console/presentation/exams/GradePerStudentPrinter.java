package eapli.base.app.user.console.presentation.exams;

import eapli.base.takenexammanagement.dto.GradePerStudent;
import eapli.framework.visitor.Visitor;

public class GradePerStudentPrinter implements Visitor<GradePerStudent> {

    @Override
    public void visit(GradePerStudent visitee) {
        System.out.printf("%-50s %-30s %-30s", visitee.examTitle, visitee.dateRealization, visitee.grade);
    }
}
