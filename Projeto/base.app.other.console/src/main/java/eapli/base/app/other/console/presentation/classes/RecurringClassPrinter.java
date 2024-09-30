package eapli.base.app.other.console.presentation.classes;

import eapli.base.classmanagement.domain.RecurringClass;
import eapli.framework.visitor.Visitor;

public class RecurringClassPrinter implements Visitor<RecurringClass> {

    @Override
    public void visit(RecurringClass visitee) {
        System.out.printf("%-50s %-20s %-20s %-25s", visitee.identity(),
                visitee.startTime(),
                visitee.duration(),
                visitee.dayOfWeek());
    }
}
