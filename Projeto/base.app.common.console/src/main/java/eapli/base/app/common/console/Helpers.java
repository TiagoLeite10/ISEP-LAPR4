package eapli.base.app.common.console;

import eapli.framework.presentation.console.SelectWidget;

import java.util.ArrayList;
import java.util.List;

public class Helpers {

    public static boolean askForConfirmation() {

        boolean confirm = false;

        List<String> confirmOperationOptions = new ArrayList<>();
        confirmOperationOptions.add("Yes.");
        confirmOperationOptions.add("No.");

        final SelectWidget<String> confirmOperationSelector = new SelectWidget<>(
                "Are you sure you want to perform the operation?", confirmOperationOptions);
        confirmOperationSelector.show();
        int option = confirmOperationSelector.selectedOption();

        if (option == 1)
            confirm = true;

        return confirm;

    }

}
