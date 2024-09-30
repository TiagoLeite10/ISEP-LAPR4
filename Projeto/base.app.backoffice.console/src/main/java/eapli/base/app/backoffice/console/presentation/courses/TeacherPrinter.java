package eapli.base.app.backoffice.console.presentation.courses;

import eapli.base.clientusermanagement.domain.Teacher;
import eapli.framework.visitor.Visitor;

public class TeacherPrinter implements Visitor<Teacher> {

    @Override
    public void visit(Teacher visitee) {
        System.out.printf("Acronym: %-5s | Name: %-25s | Email: %-55s", visitee.identity(),
                visitee.user().name(), visitee.user().email().toString());
    }
}
