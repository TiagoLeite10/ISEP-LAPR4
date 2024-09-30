package eapli.base.app.other.console.presentation.exams;

import eapli.base.app.other.console.presentation.courses.CoursePrinter;
import eapli.base.coursemanagement.domain.Course;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.takenexammanagement.application.ListGradesByCourseController;
import eapli.base.takenexammanagement.dto.GradePerStudent;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ListWidget;
import eapli.framework.presentation.console.SelectWidget;

public class ListGradesByCourseUI extends AbstractUI {

    private final ListGradesByCourseController theController = new ListGradesByCourseController(AuthzRegistry.authorizationService(),
            PersistenceContext.repositories().teachers(), PersistenceContext.repositories().courses(),
            PersistenceContext.repositories().takenExams());

    @Override
    protected boolean doShow() {
        Iterable<Course> allTeacherCourses = this.theController.listAllTeacherCourses();

        boolean hasTeacherCourses = allTeacherCourses.iterator().hasNext();

        if (!hasTeacherCourses) {
            System.out.println("There are no courses taught by you!");
        } else {
            System.out.println("List Courses: ");
            String header = String.format("#  %-20s %-50s %-20s %-20s %-20s %-15s", "CODE", "TITLE", "MINIMUM LIMIT", "MAXIMUM LIMIT", "ENROLLED APPROVED", "STATUS");

            final SelectWidget<Course> selector = new SelectWidget<>(header, allTeacherCourses, new CoursePrinter());
            selector.show();
            final Course course = selector.selectedElement();

            if (course != null) {
                Iterable<GradePerStudent> allGradesByCourse = this.theController.listAllGradesByCourse(course.identity());

                boolean hasGradeByCourse = allGradesByCourse.iterator().hasNext();

                if (!hasGradeByCourse) {
                    System.out.println("This course has no exams graded!");
                } else {
                    System.out.println("List Exam Grades: ");
                    header = String.format("#  %-30s %-50s %-30s %-30s", "STUDENT", "TITLE", "DATE OF REALIZATION", "GRADE");
                    new ListWidget<>(header, allGradesByCourse, new GradePerStudentPrinter()).show();
                }
            }
        }

        return false;
    }

    @Override
    public String headline() {
        return "List all exams grade in a course";
    }
}
