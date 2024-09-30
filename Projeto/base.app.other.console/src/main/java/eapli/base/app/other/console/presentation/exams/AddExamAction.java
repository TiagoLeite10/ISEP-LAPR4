package eapli.base.app.other.console.presentation.exams;

import eapli.framework.actions.Action;

/**
 * Menu Action para adicionar/atualizar um exame.
 */
public class AddExamAction implements Action {
    @Override
    public boolean execute() {
        return new AddExamUI().show();
    }
}
