package eapli.base.app.other.console.presentation.classes;

import eapli.framework.actions.Action;

/**
 * Menu Action para criar uma aula extra.
 */
public class CreateExtraClassAction implements Action {

    @Override
    public boolean execute() {
        return new CreateExtraClassUI().show();
    }
}
