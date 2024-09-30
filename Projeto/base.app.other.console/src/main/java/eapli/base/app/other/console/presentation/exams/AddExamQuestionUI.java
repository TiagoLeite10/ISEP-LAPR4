package eapli.base.app.other.console.presentation.exams;

import eapli.base.app.common.console.Helpers;
import eapli.base.app.other.console.presentation.courses.CoursePrinter;
import eapli.base.coursemanagement.domain.Course;
import eapli.base.enrollmentmanagement.exception.ErrorInFileException;
import eapli.base.questionmanagement.application.AddExamQuestionController;
import eapli.base.questionmanagement.domain.Question;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ListWidget;
import eapli.framework.presentation.console.SelectWidget;

/**
 * UI para adicionar/atualizar questões de exame a um repositório para serem utilizadas nos exames formativos automáticos.
 */
public class AddExamQuestionUI extends AbstractUI {

    private final AddExamQuestionController theController = new AddExamQuestionController(AuthzRegistry.authorizationService(),
            PersistenceContext.repositories().teachers(), PersistenceContext.repositories().courses());

    @Override
    protected boolean doShow() {
        Iterable<Course> availableCourses = this.theController.listAllTeacherCoursesInProgress();

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
                String pathToFile = Console.readLine("Insert the path to the file with the exam questions to add/update to the repository: ");

                Iterable<Question> questions;

                try {
                    questions = theController.parseQuestions(pathToFile);
                } catch (ErrorInFileException | IllegalArgumentException ex) {
                    System.out.println(ex.getMessage());
                    return false;
                }

                System.out.println("-- All questions present in the file --");
                header = String.format("#  %-30s %-100s", "TYPE", "TEXT");
                final ListWidget<Question> fileDataListing = new ListWidget<>(header, questions, new QuestionPrinter());
                fileDataListing.show();

                boolean confirm = Helpers.askForConfirmation();
                if (confirm) {
                    try {
                        theController.addQuestions(theCourse, questions);
                        System.out.println("The exam questions have been successfully added/updated to the repository!");
                    } catch (ErrorInFileException ex) {
                        System.out.println(ex.getMessage());
                        return false;
                    }
                } else {
                    System.out.println("Operation cancelled!");
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public String headline() {
        return "Add/update exam questions to a repository";
    }
}
