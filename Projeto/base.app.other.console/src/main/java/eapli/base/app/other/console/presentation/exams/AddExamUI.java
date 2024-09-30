package eapli.base.app.other.console.presentation.exams;

import eapli.base.app.common.console.Helpers;
import eapli.base.app.other.console.presentation.courses.CoursePrinter;
import eapli.base.coursemanagement.domain.Course;
import eapli.base.enrollmentmanagement.exception.ErrorInFileException;
import eapli.base.exammanagement.application.AddExamController;
import eapli.base.exammanagement.domain.Exam;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

/**
 * UI para adicionar/atualizar um exame.
 */
public class AddExamUI extends AbstractUI {

    private final AddExamController theController = new AddExamController(AuthzRegistry.authorizationService(),
            PersistenceContext.repositories().teachers(), PersistenceContext.repositories().courses());

    @Override
    protected boolean doShow() {
        Iterable<Course> allTeacherCourses = this.theController.listAllTeacherCoursesInProgress();

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
                String filePath = Console.readLine("Insert the path to the file with the exam to add/update to the repository: ");
                try {
                    Exam exam = theController.getExam(filePath);

                    System.out.println("-- Exam data --");
                    System.out.println(exam);

                    boolean confirm = Helpers.askForConfirmation();
                    if (confirm) {
                        theController.addExam(course, exam);
                        System.out.println("The exam has been successfully added/updated to the repository!");
                    } else {
                        System.out.println("Operation cancelled!");
                        return false;
                    }
                } catch (ErrorInFileException | IllegalArgumentException ex) {
                    System.out.println(ex.getMessage());
                    return false;
                }
            }
        }

        return false;
    }

    @Override
    public String headline() {
        return "Add/update exam to a repository";
    }
}
