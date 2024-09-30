package eapli.base.app.kiosk.console.presentation;

import eapli.framework.actions.Action;

/**
 * Menu action para compartilhar uma board.
 */
public class ShareBoardAction implements Action {

    @Override
    public boolean execute() {
        return new ShareBoardUI().show();
    }
}
