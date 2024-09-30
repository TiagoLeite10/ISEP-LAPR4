package eapli.base.app.kiosk.console.presentation;

import eapli.framework.actions.Action;

/**
 * Menu action para criar um post-it num quadro (board).
 */
public class CreatePostItAction implements Action {

    @Override
    public boolean execute() {
        return new CreatePostItUI().show();
    }
}
