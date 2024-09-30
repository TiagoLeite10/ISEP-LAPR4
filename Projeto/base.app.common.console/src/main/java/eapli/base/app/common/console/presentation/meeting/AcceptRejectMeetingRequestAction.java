package eapli.base.app.common.console.presentation.meeting;

import eapli.framework.actions.Action;

/**
 * Menu action para aceitar/recusar o convite para uma meeting.
 */
public class AcceptRejectMeetingRequestAction implements Action {
    @Override
    public boolean execute() {
        return new AcceptRejectMeetingRequestUI().show();
    }
}
