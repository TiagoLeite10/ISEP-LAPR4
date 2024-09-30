package eapli.base.app.kiosk.console.presentation;

import eapli.framework.actions.Action;

/**
 * Menu action para arquivar um quadro (board).
 */
public class ArchiveBoardAction implements Action {

    @Override
    public boolean execute() {
        return new ArchiveBoardUI().show();
    }
}
