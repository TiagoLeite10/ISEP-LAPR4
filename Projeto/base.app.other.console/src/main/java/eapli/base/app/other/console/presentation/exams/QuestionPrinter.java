package eapli.base.app.other.console.presentation.exams;

import eapli.base.questionmanagement.domain.Question;
import eapli.framework.visitor.Visitor;

public class QuestionPrinter implements Visitor<Question> {

    @Override
    public void visit(Question visitee) {
        System.out.printf("%-30s %-100s", visitee.getClass().getSimpleName(), visitee.text());
    }
}
