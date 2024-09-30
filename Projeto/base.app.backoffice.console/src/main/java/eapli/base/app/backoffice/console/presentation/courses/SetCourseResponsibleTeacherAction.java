package eapli.base.app.backoffice.console.presentation.courses;

import eapli.framework.actions.Action;

/**
 * Menu action para atribuir um professor responsável a um curso.
 */
public class SetCourseResponsibleTeacherAction implements Action {
    @Override
    public boolean execute() {
        return new SetCourseResponsibleTeacherUI().show();
    }
}
