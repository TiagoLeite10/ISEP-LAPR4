package eapli.base.app.other.console.presentation.courses;

import eapli.framework.actions.Action;

public class ListCourseAction implements Action {

    @Override
    public boolean execute() {
        return new ListCourseUI().show();
    }
}
