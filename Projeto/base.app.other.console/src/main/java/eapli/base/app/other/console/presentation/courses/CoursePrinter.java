package eapli.base.app.other.console.presentation.courses;

import eapli.base.coursemanagement.domain.Course;
import eapli.framework.visitor.Visitor;

public class CoursePrinter implements Visitor<Course> {

    @Override
    public void visit(Course visitee) {
        System.out.printf("%-20s %-50s %-20s %-20s %-20s %-15s", visitee.identity(), visitee.title(),
                visitee.enrolmentLimits().minimum(), visitee.enrolmentLimits().maximum(),
                visitee.numberEnrollmentAccepted(), visitee.status());
    }
}
