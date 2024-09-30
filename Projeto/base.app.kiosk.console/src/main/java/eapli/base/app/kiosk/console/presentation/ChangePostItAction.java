package eapli.base.app.kiosk.console.presentation;

import eapli.framework.actions.Action;

/**
 * Menu action para mudar um post-it num quadro (board).
 */
public class ChangePostItAction implements Action {

    @Override
    public boolean execute() {
        return new ChangePostItUI().show();
    }
}
