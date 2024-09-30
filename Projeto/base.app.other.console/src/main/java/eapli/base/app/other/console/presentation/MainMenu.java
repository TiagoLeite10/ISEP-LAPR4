/*
 * Copyright (c) 2013-2022 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eapli.base.app.other.console.presentation;

import eapli.base.Application;
import eapli.base.app.common.console.presentation.authz.MyUserMenu;
import eapli.base.app.common.console.presentation.board.*;
import eapli.base.app.common.console.presentation.meeting.AcceptRejectMeetingRequestAction;
import eapli.base.app.common.console.presentation.meeting.CancelMeetingAction;
import eapli.base.app.common.console.presentation.meeting.CreateMeetingAction;
import eapli.base.app.common.console.presentation.meeting.ListParticipationMeetingAction;
import eapli.base.app.other.console.presentation.classes.CreateExtraClassAction;
import eapli.base.app.other.console.presentation.classes.CreateRecurringClassAction;
import eapli.base.app.other.console.presentation.classes.UpdateClassScheduleAction;
import eapli.base.app.other.console.presentation.courses.ListCourseAction;
import eapli.base.app.other.console.presentation.exams.*;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.menu.HorizontalMenuRenderer;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

/**
 * TODO split this class in more specialized classes for each menu
 *
 * @author Paulo Gandra Sousa
 */
public class MainMenu extends AbstractUI {

    private static final String RETURN_LABEL = "Return ";

    private static final String SEPARATOR_LABEL = "--------------";

    private static final int EXIT_OPTION = 0;

    // CLASSES
    private static final int SCHEDULE_CLASSES_OPTION = 1;
    private static final int UPDATE_CLASS_SCHEDULE_OPTION = 2;
    private static final int SCHEDULE_EXTRA_CLASSES_OPTION = 3;

    // EXAMS
    private static final int CREATE_UPDATE_EXAM_OPTION = 1;
    private static final int CREATE_UPDATE_FORMATIVE_EXAM_OPTION = 2;
    private static final int ADD_EXAM_QUESTION_OPTION = 3;
    private static final int LIST_EXAMS_OPTION = 4;
    private static final int LIST_EXAMS_GRADE_OPTION = 5;

    // COURSE
    private static final int LIST_COURSE_OPTION = 1;

    // MEETING
    private static final int SCHEDULE_MEETING_OPTION = 1;
    private static final int CANCEL_MEETING_OPTION = 2;
    private static final int LIST_PARTICIPANTS_OPTION = 3;

    private static final int ACCEPT_REJECT_MEETING_REQUEST_OPTION = 4;

    // BOARD
    private static final int CREATE_BOARD_OPTION = 1;


    // MAIN MENU
    private static final int MY_USER_OPTION = 1;
    private static final int CLASSES_OPTION = 2;
    private static final int EXAMS_OPTION = 3;
    private static final int COURSE_OPTION = 4;
    private static final int MEETING_OPTION = 5;
    private static final int BOARD_OPTION = 6;

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }

    /**
     * @return true if the user selected the exit option
     */
    @Override
    public boolean doShow() {
        final Menu menu = buildMainMenu();
        final MenuRenderer renderer;
        if (Application.settings().isMenuLayoutHorizontal()) {
            renderer = new HorizontalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        } else {
            renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        }
        return renderer.render();
    }

    @Override
    public String headline() {

        return authz.session().map(s -> "Base [ @" + s.authenticatedUser().identity() + " ]")
                .orElse("Base [ ==Anonymous== ]");
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.addSubMenu(MY_USER_OPTION, myUserMenu);

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.TEACHER)) {
            final Menu classesMenu = buildTeacherClassesMenu();
            mainMenu.addSubMenu(CLASSES_OPTION, classesMenu);

            final Menu examsMenu = buildTeacherExamsMenu();
            mainMenu.addSubMenu(EXAMS_OPTION, examsMenu);

            final Menu coursesMenu = buildTeacherCourseMenu();
            mainMenu.addSubMenu(COURSE_OPTION, coursesMenu);

            final Menu meetingMenu = buildMeetingMenu();
            mainMenu.addSubMenu(MEETING_OPTION, meetingMenu);

            final Menu boardMenu = buildBoardMenu();
            mainMenu.addSubMenu(BOARD_OPTION, boardMenu);
        }

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

        return mainMenu;
    }

    private Menu buildTeacherClassesMenu() {
        final Menu menu = new Menu("Classes  >");

        menu.addItem(SCHEDULE_CLASSES_OPTION, "Schedule a recurring class", new CreateRecurringClassAction());
        menu.addItem(UPDATE_CLASS_SCHEDULE_OPTION, "Update schedule class", new UpdateClassScheduleAction());
        menu.addItem(SCHEDULE_EXTRA_CLASSES_OPTION, "Schedule a extra class", new CreateExtraClassAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildTeacherExamsMenu() {
        final Menu menu = new Menu("Exams  >");

        menu.addItem(CREATE_UPDATE_EXAM_OPTION, "Create/update an exam", new AddExamAction());
        menu.addItem(CREATE_UPDATE_FORMATIVE_EXAM_OPTION, "Create/update automatic formative exam", new CreateAutomaticFormativeExamsAction());
        menu.addItem(ADD_EXAM_QUESTION_OPTION, "Add/update exam questions to a repository", new AddExamQuestionAction());
        menu.addItem(LIST_EXAMS_OPTION, "List all exams in a course", new ListCourseExamsAction());
        menu.addItem(LIST_EXAMS_GRADE_OPTION, "List all the exam grades in a course", new ListGradesByCourseAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildTeacherCourseMenu() {
        final Menu menu = new Menu("Course  >");

        menu.addItem(LIST_COURSE_OPTION, "List all courses that the teacher teaches", new ListCourseAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildMeetingMenu() {
        final Menu menu = new Menu("Meeting >");

        menu.addItem(SCHEDULE_MEETING_OPTION, "Schedule a meeting", new CreateMeetingAction());
        menu.addItem(CANCEL_MEETING_OPTION, "Cancel a meeting", new CancelMeetingAction());
        menu.addItem(LIST_PARTICIPANTS_OPTION, "List participants", new ListParticipationMeetingAction());
        menu.addItem(ACCEPT_REJECT_MEETING_REQUEST_OPTION, "Accept/Reject meeting request",
                new AcceptRejectMeetingRequestAction());

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildBoardMenu() {
        final Menu menu = new Menu("Board >");

        menu.addItem(CREATE_BOARD_OPTION, "Create a board", new CreateBoardAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }
}
