package eapli.base.app.user.console.presentation.exams;

import eapli.framework.actions.Action;

public class ListFutureExamsAction implements Action {
    @Override
    public boolean execute() {
        return new ListFutureExamsUI().show();
    }
}
