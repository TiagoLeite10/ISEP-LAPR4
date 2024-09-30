package eapli.base.app.backoffice.console.presentation.courses;

import eapli.framework.actions.Action;

public class SetCourseTeacherAction implements Action {
    @Override
    public boolean execute() {
        return new SetCourseTeacherUI().show();
    }
}
