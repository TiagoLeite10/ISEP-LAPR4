package eapli.base.app.common.console.presentation.meeting;

import eapli.framework.actions.Action;

public class ListParticipationMeetingAction implements Action {

    @Override
    public boolean execute() {
        return new ListParticipationMeetingUI().show();
    }
}
