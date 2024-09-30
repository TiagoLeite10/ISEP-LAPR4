package eapli.base.app.common.console.presentation.meeting;

import eapli.framework.actions.Action;

/**
 * Menu action para criar uma meeting.
 */
public class CreateMeetingAction implements Action {

    @Override
    public boolean execute() {
        return new CreateMeetingUI().show();
    }
}