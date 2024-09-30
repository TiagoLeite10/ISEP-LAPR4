package eapli.base.app.kiosk.console.presentation;

import eapli.base.Application;
import eapli.base.app.kiosk.authz.ProxyRegistry;
import eapli.base.app.kiosk.console.BaseKioskApp;
import eapli.base.sharedboard.csvprotocol.client.CsvSharedBoardProtocolProxy;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.menu.HorizontalMenuRenderer;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainMenu extends AbstractUI {

    private static final String SEPARATOR_LABEL = "--------------";

    private static final int EXIT_OPTION = 0;

    //BOARD
    private static final int VIEW_SHARED_BOARD_OPTION = 1;
    private static final int CREATE_POST_IT_OPTION = 2;
    private static final int ARCHIVE_BOARD_OPTION = 3;
    private static final int CHANGE_POST_IT_OPTION = 4;
    private static final int LAST_CHANGE_POST_IT_OPTION = 5;
    private static final int SHARE_BOARD_OPTION = 6;
    private static final int VIEW_BOARD_UPDATES_HISTORY_OPTION = 7;

    // MAIN MENU
    private static final Logger LOGGER = LogManager.getLogger(BaseKioskApp.class);
    private final CsvSharedBoardProtocolProxy csvSharedBoardProtocolProxy = ProxyRegistry.csvSharedBoardProtocolProxy();

    public MainMenu() {
    }

    @Override
    protected boolean doShow() {
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
        return "-- Shared Board --";
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        mainMenu.addItem(VIEW_SHARED_BOARD_OPTION, "Open Shared Board view", new ViewSharedBoardAction());
        mainMenu.addItem(CREATE_POST_IT_OPTION, "Create a post-it on a board", new CreatePostItAction());
        mainMenu.addItem(ARCHIVE_BOARD_OPTION, "Archive a board", new ArchiveBoardAction());
        mainMenu.addItem(CHANGE_POST_IT_OPTION, "Change a post-it on a board", new ChangePostItAction());
        mainMenu.addItem(LAST_CHANGE_POST_IT_OPTION, "Undo the last change in a post-it", new UndoLastChangePostItAction());
        mainMenu.addItem(SHARE_BOARD_OPTION, "Share a board", new ShareBoardAction());
        mainMenu.addItem(VIEW_BOARD_UPDATES_HISTORY_OPTION, "View board updates history", new ViewBoardUpdatesHistoryAction());

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

        return mainMenu;
    }
}
