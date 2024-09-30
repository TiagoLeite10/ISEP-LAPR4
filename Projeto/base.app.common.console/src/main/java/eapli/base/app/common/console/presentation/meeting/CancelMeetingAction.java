package eapli.base.app.common.console.presentation.meeting;

import eapli.framework.actions.Action;

/**
 * Menu action para cancelar uma reunião.
 */
public class CancelMeetingAction implements Action {

    @Override
    public boolean execute() {
        return new CancelMeetingUI().show();
    }
}
