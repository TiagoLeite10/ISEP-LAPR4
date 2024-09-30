package eapli.base.app.backoffice.console.presentation.courses;

import eapli.framework.actions.Action;

public class CreateCourseAction implements Action {

    @Override
    public boolean execute() {
        return new CreateCourseUI().show();
    }

}
