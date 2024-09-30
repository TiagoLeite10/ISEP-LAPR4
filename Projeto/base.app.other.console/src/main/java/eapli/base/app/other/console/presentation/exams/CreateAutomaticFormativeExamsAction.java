package eapli.base.app.other.console.presentation.exams;

import eapli.framework.actions.Action;

/**
 * Menu Action para criar/atualizar um exame formativo automático.
 */
public class CreateAutomaticFormativeExamsAction implements Action {

    @Override
    public boolean execute() {
        return new CreateAutomaticFormativeExamsUI().show();
    }
}
