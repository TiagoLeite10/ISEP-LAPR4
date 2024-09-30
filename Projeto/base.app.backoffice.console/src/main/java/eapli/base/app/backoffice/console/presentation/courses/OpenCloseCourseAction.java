package eapli.base.app.backoffice.console.presentation.courses;

import eapli.framework.actions.Action;

/**
 * Menu action para abrir/fechar um curso.
 */
public class OpenCloseCourseAction implements Action {
    @Override
    public boolean execute() {
        return new OpenCloseCourseUI().show();
    }
}
