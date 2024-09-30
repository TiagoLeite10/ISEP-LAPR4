package eapli.base.app.backoffice.console.presentation.courses;

import eapli.base.enrollmentmanagement.domain.Enrollment;
import eapli.framework.visitor.Visitor;

public class EnrollmentPrinter implements Visitor<Enrollment> {

    @Override
    public void visit(Enrollment visitee) {
        System.out.printf("%-30s %-30s %-30s %-20s", visitee.student().identity(),
                visitee.student().user().name().firstName(),
                visitee.student().user().name().lastName(),
                visitee.student().taxPayerNumber());
    }
}

