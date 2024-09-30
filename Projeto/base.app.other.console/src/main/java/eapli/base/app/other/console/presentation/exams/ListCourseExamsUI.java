package eapli.base.app.other.console.presentation.exams;

import eapli.base.app.other.console.presentation.courses.CoursePrinter;
import eapli.base.exammanagement.application.ListCourseExamsController;
import eapli.base.coursemanagement.domain.Course;
import eapli.base.exammanagement.domain.Exam;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ListWidget;
import eapli.framework.presentation.console.SelectWidget;

/**
 * UI para listar todos os exames de um curso.
 */
public class ListCourseExamsUI extends AbstractUI {

    private final ListCourseExamsController theController = new ListCourseExamsController(AuthzRegistry.authorizationService(),
            PersistenceContext.repositories().teachers(), PersistenceContext.repositories().courses(),
            PersistenceContext.repositories().exams());

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
            final Course theCourse = selector.selectedElement();

            if (theCourse != null) {
                Iterable<Exam> allCourseExams = this.theController.listAllCourseExams(theCourse.identity());

                boolean hasCourseExams = allCourseExams.iterator().hasNext();

                if (!hasCourseExams) {
                    System.out.println("There are no exams created in the selected course!");
                } else {
                    System.out.println("List Exams: ");
                    header = String.format("#  %-30s %-50s %-80s", "TITLE", "DESCRIPTION", "OPENING DATE - CLOSING DATE");
                    new ListWidget<>(header, allCourseExams, new ExamPrinter()).show();
                }
            }
        }

        return false;
    }

    @Override
    public String headline() {
        return "List all exams in a course";
    }
}
