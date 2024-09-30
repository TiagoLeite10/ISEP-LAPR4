package eapli.base.app.other.console.presentation.exams;

import eapli.framework.actions.Action;

public class ListGradesByCourseAction implements Action {
    @Override
    public boolean execute() {
        return new ListGradesByCourseUI().show();
    }
}
