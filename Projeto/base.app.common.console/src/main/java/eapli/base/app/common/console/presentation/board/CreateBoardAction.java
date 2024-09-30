package eapli.base.app.common.console.presentation.board;

import eapli.framework.actions.Action;

/**
 * Menu action para criar um quadro (board).
 */
public class CreateBoardAction implements Action {
    @Override
    public boolean execute() {
        return new CreateBoardUI().show();
    }
}
