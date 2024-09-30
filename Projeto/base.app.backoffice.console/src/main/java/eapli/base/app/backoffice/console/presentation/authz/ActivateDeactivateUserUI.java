/*
 * Copyright (c) 2013-2022 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package eapli.base.app.backoffice.console.presentation.authz;

import eapli.framework.presentation.console.SelectWidget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eapli.base.usermanagement.application.ActivateDeactivateUserController;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.presentation.console.AbstractUI;

/**
 * @author Fernando
 */
@SuppressWarnings("squid:S106")
public class ActivateDeactivateUserUI extends AbstractUI {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActivateDeactivateUserUI.class);

    private final ActivateDeactivateUserController theController = new ActivateDeactivateUserController();

    @Override
    protected boolean doShow() {
        final Iterable<SystemUser> allUsers = this.theController.allUsers();

        boolean hasUsers = allUsers.iterator().hasNext();

        if (!hasUsers) {
            System.out.println("There is no registered User");
        } else {
            System.out.println("Select user to active/deactivate: ");
            String header = String.format("#  %-20s %-30s %-30s %-15s %-10s", "USERNAME", "FIRST NAME", "LAST NAME", "TYPE", "ACTIVATED");

            final SelectWidget<SystemUser> selector = new SelectWidget<>(header, allUsers, new SystemUserPrinter());
            selector.show();
            final SystemUser theUser = selector.selectedElement();

            if (theUser != null) {
                try {
                    if (theUser.isActive()) {
                        this.theController.deactivateUser(theUser);
                    } else {
                        this.theController.activateUser(theUser);
                    }
                } catch (IntegrityViolationException | ConcurrencyException ex) {
                    LOGGER.error("Error performing the operation", ex);
                    System.out.println(
                            "Unfortunatelly there was an unexpected error in the application. Please try again and if the problem persists, contact your system admnistrator.");
                }
            }
        }

        return true;
    }

    @Override
    public String headline() {
        return "Activate/Deactivate User";
    }
}
