package eapli.base.app.kiosk.console.presentation;

import eapli.framework.actions.Action;

/**
 * Menu action para visualizar o histórico de updates de um quadro (board).
 */
public class ViewBoardUpdatesHistoryAction implements Action {
    @Override
    public boolean execute() {
        return new ViewBoardUpdatesHistoryUI().show();
    }
}
