package eapli.base.app.other.console.presentation.classes;

import eapli.base.clientusermanagement.domain.Student;
import eapli.framework.visitor.Visitor;

public class StudentPrinter implements Visitor<Student> {

    @Override
    public void visit(Student visitee) {
        System.out.printf("%-30s %-30s %-30s %-20s", visitee.identity(),
                visitee.user().name().firstName(),
                visitee.user().name().lastName(),
                visitee.identity());
    }
}

