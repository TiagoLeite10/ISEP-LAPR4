package eapli.base.app.backoffice.console.presentation.courses;

import eapli.base.app.common.console.Helpers;
import eapli.base.coursemanagement.application.OpenCloseCourseController;
import eapli.base.coursemanagement.domain.Course;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ListWidget;
import eapli.framework.presentation.console.SelectWidget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * UI para abrir/fechar um curso.
 */
public class OpenCloseCourseUI extends AbstractUI {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenCloseCourseUI.class);

    private final OpenCloseCourseController theController = new OpenCloseCourseController(
            AuthzRegistry.authorizationService(), PersistenceContext.repositories().courses());

    @Override
    protected boolean doShow() {
        Iterable<Course> allCloseCourses = this.theController.listCloseCourses();
        Iterable<Course> allInProgressCourses = this.theController.listInProgressCourses();

        boolean hasCloseCourses = allCloseCourses.iterator().hasNext();
        boolean hasInProgressCourses = allInProgressCourses.iterator().hasNext();

        if (!hasCloseCourses && !hasInProgressCourses) {
            System.out.println("There is no courses that can be open/closed!");
        } else {
            if (hasCloseCourses) {
                final ListWidget<Course> closeCoursesListing = new ListWidget<>("-- Courses that can be " +
                        "opened --", allCloseCourses, new CoursePrinter());
                closeCoursesListing.show();
            } else {
                System.out.println("-- There are no courses to open! --");
            }

            System.out.println();

            if (hasInProgressCourses) {
                final ListWidget<Course> inProgressCoursesListing = new ListWidget<>("-- Courses that can be " +
                        "closed --", allInProgressCourses, new CoursePrinter());
                inProgressCoursesListing.show();
            } else {
                System.out.println("-- There are no courses to close! --");
            }

            System.out.println();

            List<String> openOrCloseOptions = new ArrayList<>();
            openOrCloseOptions.add("Open course");
            openOrCloseOptions.add("Close course");
            final SelectWidget<String> openOrCloseOptionsSelector = new SelectWidget<>(
                    "Choose the type of operation you want to perform: ", openOrCloseOptions);
            openOrCloseOptionsSelector.show();
            int option = openOrCloseOptionsSelector.selectedOption();

            final Course theCourse;
            String operationDetail;
            if (option == 1) {
                if (!hasCloseCourses) {
                    System.out.println("Unknown option!");
                    return false;
                }

                final SelectWidget<Course> closeCoursesSelector = new SelectWidget<>("-- Courses that can be " +
                        "opened --", allCloseCourses, new CoursePrinter());
                closeCoursesSelector.show();
                theCourse = closeCoursesSelector.selectedElement();
                operationDetail = "opened";
            } else if (option == 2) {
                if (!hasInProgressCourses) {
                    System.out.println("Unknown option!");
                    return false;
                }

                final SelectWidget<Course> inProgressCoursesSelector = new SelectWidget<>("-- Courses that can be " +
                        "closed --", allInProgressCourses, new CoursePrinter());
                inProgressCoursesSelector.show();
                theCourse = inProgressCoursesSelector.selectedElement();
                operationDetail = "closed";
            } else {
                System.out.println("Unknown option!");
                return false;
            }

            if (theCourse != null) {
                System.out.println("Course data to be " + operationDetail + ":");
                new CoursePrinter().visit(theCourse);

                System.out.println();

                boolean confirm = Helpers.askForConfirmation();

                if (confirm) {
                    try {

                        this.theController.openCloseCourse(theCourse);
                        System.out.println("The state of the course was successful altered!");

                    } catch (@SuppressWarnings("unused") final ConcurrencyException ex) {
                        System.out.println(
                                "WARNING: It is not possible to change the course state because it was changed by another user");
                    } catch (final IntegrityViolationException ex) {
                        LOGGER.error("Error performing the operation", ex);
                        System.out.println(
                                "Unfortunatelly there was an unexpected error in the application. Please try again and " +
                                        "if the problem persists, contact your system admnistrator.");
                    }
                } else {
                    System.out.println("Operation cancelled!");
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public String headline() {
        return "Open/close course";
    }
}
