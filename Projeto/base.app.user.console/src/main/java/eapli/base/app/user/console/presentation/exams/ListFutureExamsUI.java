package eapli.base.app.user.console.presentation.exams;

import eapli.base.exammanagement.application.ListFutureExamsController;

import eapli.base.exammanagement.domain.Exam;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ListWidget;

public class ListFutureExamsUI extends AbstractUI {

    private final ListFutureExamsController theController = new ListFutureExamsController(AuthzRegistry.authorizationService(),
            PersistenceContext.repositories().exams());

    @Override
    protected boolean doShow() {
        Iterable<Exam> allFutureExams = this.theController.listFutureExams(AuthzRegistry.authorizationService()
                .session().orElseThrow(IllegalStateException::new).authenticatedUser().identity());

        boolean hasFutureExams = allFutureExams.iterator().hasNext();

        if (!hasFutureExams) {
            System.out.println("You have no future exams!");
        } else {
            System.out.println("List Future Exams: ");
            String header = String.format("#  %-30s %-50s %-80s", "TITLE", "DESCRIPTION", "OPENING DATE - CLOSING DATE");
            new ListWidget<>(header, allFutureExams, new ExamPrinter()).show();
        }

        return false;
    }

    @Override
    public String headline() {
        return "List all the future exams";
    }
}
