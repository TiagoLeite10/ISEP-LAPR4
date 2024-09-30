package eapli.base.app.backoffice.console.presentation.enrollment;

import eapli.base.app.backoffice.console.presentation.courses.OpenCloseCourseUI;
import eapli.base.app.common.console.Helpers;
import eapli.base.clientusermanagement.domain.MecanographicNumber;
import eapli.base.coursemanagement.domain.CourseCode;
import eapli.base.enrollmentmanagement.application.BulkEnrollmentController;
import eapli.base.enrollmentmanagement.exception.ErrorInFileException;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ListWidget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * UI para inscrever estudantes em disciplinas através da importação de um ficheiro (inscrições em massa).
 */
public class BulkEnrollmentUI extends AbstractUI {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenCloseCourseUI.class);

    private final BulkEnrollmentController theController = new BulkEnrollmentController(
            AuthzRegistry.authorizationService(), PersistenceContext.repositories().courses(),
            PersistenceContext.repositories().students());

    @Override
    protected boolean doShow() {

        String filePath = Console.readLine("Insert the path to the file with the students enrollment bulk " +
                "information:");

        List<Pair<MecanographicNumber, CourseCode>> fileData = new ArrayList<>();

        try {
            fileData = theController.getFileData(filePath);
        } catch (ErrorInFileException | IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            return false;
        }

        final ListWidget<Pair<MecanographicNumber, CourseCode>> fileDataListing = new ListWidget<>("** All data present " +
                "in the file **", fileData, new BulkEnrollmentPrinter());
        fileDataListing.show();

        boolean confirm = Helpers.askForConfirmation();

        if (confirm) {
            try {
                theController.bulkStudentEnrollments(fileData);
                System.out.println("The file data was successfully imported!");
            } catch (ErrorInFileException ex) {
                System.out.println(ex.getMessage());
                return false;
            }
        } else {
            System.out.println("Operation cancelled!");
            return false;
        }

        return true;
    }

    @Override
    public String headline() {
        return "Import file to enroll students in bulk";
    }
}
