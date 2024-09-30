package eapli.base.app.user.console.presentation.exams;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.takenexammanagement.application.ListGradesController;
import eapli.base.takenexammanagement.dto.GradePerStudent;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ListWidget;

public class ListGradeUI extends AbstractUI {


    private final ListGradesController theController = new ListGradesController(AuthzRegistry.authorizationService(),
            PersistenceContext.repositories().students(), PersistenceContext.repositories().takenExams());

    @Override
    protected boolean doShow() {
        Iterable<GradePerStudent> allGrades = this.theController.listAllGrades();

        boolean hasGrades = allGrades.iterator().hasNext();
        if (!hasGrades) {
            System.out.println("You have no grade!");
        } else {
            System.out.println("List Exam Grades: ");
            String header = String.format("#  %-50s %-30s %-30s", "TITLE", "DATE OF REALIZATION", "GRADE");
            new ListWidget<>(header, allGrades, new GradePerStudentPrinter()).show();
        }
        return false;
    }

    @Override
    public String headline() {
        return "View a list of my grades";
    }


}
