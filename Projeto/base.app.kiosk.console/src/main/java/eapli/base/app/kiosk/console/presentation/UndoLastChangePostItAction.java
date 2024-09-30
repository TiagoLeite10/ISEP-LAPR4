package eapli.base.app.kiosk.console.presentation;

import eapli.framework.actions.Action;

/**
 * Menu action para desfazer a última alteração a um post-it.
 */
public class UndoLastChangePostItAction implements Action {

    @Override
    public boolean execute() {
        return new UndoLastChangePostItUI().show();
    }
}
