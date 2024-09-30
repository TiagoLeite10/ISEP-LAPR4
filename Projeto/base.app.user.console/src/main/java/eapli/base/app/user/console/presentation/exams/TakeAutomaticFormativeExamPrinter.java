package eapli.base.app.user.console.presentation.exams;

import eapli.base.formativeexammanagement.domain.FormativeExam;
import eapli.framework.visitor.Visitor;

public class TakeAutomaticFormativeExamPrinter implements Visitor<FormativeExam> {
    @Override
    public void visit(FormativeExam visitee) {
        System.out.printf("%-50s %-50s %-36s", visitee.identity(), visitee.description(), visitee.dateString());
    }
}
