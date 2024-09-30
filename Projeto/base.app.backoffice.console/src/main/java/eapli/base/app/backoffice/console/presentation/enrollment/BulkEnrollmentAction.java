package eapli.base.app.backoffice.console.presentation.enrollment;

import eapli.framework.actions.Action;

/**
 * Menu action para carregar um ficheiro com dados de inscrições de alunos em disciplinas.
 */
public class BulkEnrollmentAction implements Action {
    @Override
    public boolean execute() {
        return new BulkEnrollmentUI().show();
    }
}
