package eapli.base.app.other.console.presentation.exams;

import eapli.framework.actions.Action;

/**
 * Menu Action para listar todos os exames de um curso.
 */
public class ListCourseExamsAction implements Action {

    @Override
    public boolean execute() {
        return new ListCourseExamsUI().show();
    }
}
