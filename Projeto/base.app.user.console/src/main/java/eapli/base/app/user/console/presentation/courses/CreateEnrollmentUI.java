package eapli.base.app.user.console.presentation.courses;

import eapli.base.enrollmentmanagement.application.CreateEnrollmentController;
import eapli.base.coursemanagement.domain.Course;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class CreateEnrollmentUI extends AbstractUI {

    private final CreateEnrollmentController theController = new CreateEnrollmentController(AuthzRegistry.authorizationService(),
            PersistenceContext.repositories().students(), PersistenceContext.repositories().courses());

    @Override
    protected boolean doShow() {
        Iterable<Course> allCoursesNotEnrolled = this.theController.listAllCoursesNotEnrolled();

        boolean hasAllCoursesNotEnrolled = allCoursesNotEnrolled.iterator().hasNext();

        if (!hasAllCoursesNotEnrolled) {
            System.out.println("There are no open enrollment courses available for enrollment!");
        } else {
            System.out.println("List Courses: ");
            String header = String.format("#  %-20s %-50s %-20s %-20s %-15s", "CODE", "TITLE", "MINIMUM LIMIT", "MAXIMUM LIMIT", "STATUS");

            final SelectWidget<Course> selector = new SelectWidget<>(header, allCoursesNotEnrolled, new CoursePrinter());
            selector.show();

            final Course course = selector.selectedElement();

            if (course != null) {
                theController.addEnrollment(course);

                System.out.println("The student was successfully enrolled in the course!");
            }
        }

        return false;
    }

    @Override
    public String headline() {
        return "List all courses that the student is not enrolled";
    }
}
