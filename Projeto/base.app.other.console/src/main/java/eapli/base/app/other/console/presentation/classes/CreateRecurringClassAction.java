package eapli.base.app.other.console.presentation.classes;

import eapli.framework.actions.Action;

public class CreateRecurringClassAction implements Action {

    @Override
    public boolean execute() {
        return new CreateRecurringClassUI().show();
    }
}

