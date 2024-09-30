package eapli.base.app.user.console.presentation.exams;

import eapli.framework.actions.Action;

public class TakeExamAction implements Action {
    @Override
    public boolean execute() {
        return new TakeExamUI().show();
    }
}
