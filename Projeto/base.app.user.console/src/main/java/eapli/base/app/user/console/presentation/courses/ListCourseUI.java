package eapli.base.app.user.console.presentation.courses;

import eapli.base.coursemanagement.application.ListCourseController;
import eapli.base.coursemanagement.domain.Course;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ListWidget;

public class ListCourseUI extends AbstractUI {

    private final ListCourseController theController = new ListCourseController(AuthzRegistry.authorizationService(),
            PersistenceContext.repositories().teachers(), PersistenceContext.repositories().students(),
            PersistenceContext.repositories().courses());

    @Override
    protected boolean doShow() {
        Iterable<Course> allStudentCourses = this.theController.listStudentCourses();

        boolean hasStudentCourses = allStudentCourses.iterator().hasNext();

        if (!hasStudentCourses) {
            System.out.println("You are not enrolled in any course and there is none open");
        } else {
            System.out.println("List Courses: ");
            String header = String.format("#  %-20s %-50s %-20s %-20s %-15s", "CODE", "TITLE", "MINIMUM LIMIT", "MAXIMUM LIMIT", "STATUS");

            final ListWidget<Course> selector = new ListWidget<>(header, allStudentCourses, new CoursePrinter());
            selector.show();
        }

        return false;
    }

    @Override
    public String headline() {
        return "List all courses that the student is enrolled";
    }
}
