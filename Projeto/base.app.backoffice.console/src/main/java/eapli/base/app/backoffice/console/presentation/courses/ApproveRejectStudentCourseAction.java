package eapli.base.app.backoffice.console.presentation.courses;

import eapli.framework.actions.Action;

/**
 * Menu action para aprovar/rejeitar estudantes numa disciplina.
 */
public class ApproveRejectStudentCourseAction implements Action {

    @Override
    public boolean execute() {
        return new ApproveRejectStudentCourseUI().show();
    }
}
