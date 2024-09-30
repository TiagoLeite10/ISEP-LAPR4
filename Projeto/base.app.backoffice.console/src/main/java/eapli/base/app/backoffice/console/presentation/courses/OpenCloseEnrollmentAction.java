package eapli.base.app.backoffice.console.presentation.courses;

import eapli.framework.actions.Action;

public class OpenCloseEnrollmentAction implements Action {
    @Override
    public boolean execute() {
        return new OpenCloseEnrollmentUI().show();
    }
}
