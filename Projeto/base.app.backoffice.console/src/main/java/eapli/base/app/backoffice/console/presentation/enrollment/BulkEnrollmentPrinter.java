package eapli.base.app.backoffice.console.presentation.enrollment;

import eapli.base.clientusermanagement.domain.MecanographicNumber;
import eapli.base.coursemanagement.domain.CourseCode;
import org.springframework.data.util.Pair;
import eapli.framework.visitor.Visitor;

public class BulkEnrollmentPrinter implements Visitor<Pair<MecanographicNumber, CourseCode>> {

    @Override
    public void visit(Pair<MecanographicNumber, CourseCode> visitee) {
        System.out.printf("Student mecanographic number: %-9s | Course: %-50s", visitee.getFirst().toString(),
                visitee.getSecond().toString());
    }
}
