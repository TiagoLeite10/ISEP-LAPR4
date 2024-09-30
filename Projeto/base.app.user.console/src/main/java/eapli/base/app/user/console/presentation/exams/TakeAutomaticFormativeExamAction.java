package eapli.base.app.user.console.presentation.exams;

import eapli.framework.actions.Action;

/**
 * Menu action para o aluno responder a um exame formativo automático.
 */
public class TakeAutomaticFormativeExamAction implements Action {
    @Override
    public boolean execute() {
        return new TakeAutomaticFormativeExamUI().show();
    }
}
