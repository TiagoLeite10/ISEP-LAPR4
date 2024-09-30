package eapli.base.app.other.console.presentation.exams;

import eapli.framework.actions.Action;

/**
 * Menu Action para adicionar/atualizar questões de exame a um repositório.
 */
public class AddExamQuestionAction implements Action {

    @Override
    public boolean execute() {
        return new AddExamQuestionUI().show();
    }
}
