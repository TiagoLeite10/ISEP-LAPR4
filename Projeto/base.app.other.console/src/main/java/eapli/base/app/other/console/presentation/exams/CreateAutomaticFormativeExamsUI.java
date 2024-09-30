package eapli.base.app.other.console.presentation.exams;

import eapli.base.app.common.console.Helpers;
import eapli.base.app.other.console.presentation.courses.CoursePrinter;
import eapli.base.coursemanagement.domain.Course;
import eapli.base.enrollmentmanagement.exception.ErrorInFileException;
import eapli.base.formativeexammanagement.application.CreateAutomaticFormativeExamsController;
import eapli.base.formativeexammanagement.domain.FormativeExam;
import eapli.base.questionmanagement.exception.NumberOfQuestionsException;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

/**
 * UI para criar/atualizar um exame formativo automático.
 */
public class CreateAutomaticFormativeExamsUI extends AbstractUI {

    private final CreateAutomaticFormativeExamsController theController = new CreateAutomaticFormativeExamsController(AuthzRegistry.authorizationService(),
            PersistenceContext.repositories().teachers(), PersistenceContext.repositories().courses(), PersistenceContext.repositories().questions());

    @Override
    protected boolean doShow() {
        Iterable<Course> availableCourses = this.theController.findAllTeacherCoursesInProgress();

        boolean hasCourses = availableCourses.iterator().hasNext();

        if (!hasCourses) {
            System.out.println("There are no courses taught by you!");
        } else {
            System.out.println("List Courses: ");
            String header = String.format("#  %-20s %-50s %-20s %-20s %-20s %-15s", "CODE", "TITLE", "MINIMUM LIMIT", "MAXIMUM LIMIT", "ENROLLED APPROVED", "STATUS");

            final SelectWidget<Course> selector = new SelectWidget<>(header, availableCourses, new CoursePrinter());
            selector.show();

            final Course theCourse = selector.selectedElement();

            if (theCourse != null) {
                String pathToFile = Console.readLine("Insert the path to the file with the automatic formative exam to add/update to the repository: ");

                try {
                    FormativeExam theFormativeExam = theController.parseFormativeExam(theCourse, pathToFile);

                    System.out.println("-- Formative Exam data --");
                    System.out.println(theFormativeExam);

                    boolean confirm = Helpers.askForConfirmation();
                    if (confirm) {
                        theController.createFormativeExam(theCourse, theFormativeExam);
                        System.out.println("The automatic formative exam has been successfully added/updated to the repository!");
                    } else {
                        System.out.println("Operation cancelled!");
                        return false;
                    }

                } catch (ErrorInFileException | IllegalArgumentException | NumberOfQuestionsException ex) {
                    System.out.println(ex.getMessage());
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public String headline() {
        return "Create/update automatic formative exam";
    }
}
