package eapli.base.app.other.console.presentation.courses;

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
        Iterable<Course> allTeacherCourses = this.theController.listTeacherCourses();

        boolean hasTeacherCourses = allTeacherCourses.iterator().hasNext();

        if (!hasTeacherCourses) {
            System.out.println("There are no courses taught by you!");
        } else {
            System.out.println("List Courses: ");
            String header = String.format("#  %-20s %-50s %-20s %-20s %-20s %-15s", "CODE", "TITLE", "MINIMUM LIMIT", "MAXIMUM LIMIT", "ENROLLED APPROVED", "STATUS");

            final ListWidget<Course> selector = new ListWidget<>(header, allTeacherCourses, new CoursePrinter());
            selector.show();
        }

        return false;
    }

    @Override
    public String headline() {
        return "List all courses that the teacher teaches";
    }
}
