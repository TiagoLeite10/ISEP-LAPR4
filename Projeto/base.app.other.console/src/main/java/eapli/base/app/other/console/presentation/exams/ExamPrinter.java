package eapli.base.app.other.console.presentation.exams;

import eapli.base.exammanagement.domain.Exam;
import eapli.framework.visitor.Visitor;

public class ExamPrinter implements Visitor<Exam> {
    @Override
    public void visit(Exam visitee) {

        System.out.printf("%-30s %-50s %-80s", visitee.title(), visitee.header().description(), visitee.dateTime());
    }
}
