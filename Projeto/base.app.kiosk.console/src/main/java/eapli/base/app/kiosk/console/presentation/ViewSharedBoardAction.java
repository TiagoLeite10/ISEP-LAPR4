package eapli.base.app.kiosk.console.presentation;

import eapli.framework.actions.Action;

/**
 * Menu action para visualizar um quadro (board).
 */
public class ViewSharedBoardAction implements Action {

    @Override
    public boolean execute() {
        return new ViewSharedBoardUI().show();
    }
}
