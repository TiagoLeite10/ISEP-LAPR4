package eapli.base.app.backoffice.console.presentation.courses;

import eapli.base.coursemanagement.application.OpenCloseEnrollmentController;
import eapli.base.coursemanagement.domain.Course;
import eapli.base.coursemanagement.exception.InvalidEnrollmentLimitsException;
import eapli.base.coursemanagement.exception.PendentEnrollmentStateException;
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

public class OpenCloseEnrollmentUI extends AbstractUI {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenCloseCourseUI.class);

    private final OpenCloseEnrollmentController theController = new OpenCloseEnrollmentController(
            AuthzRegistry.authorizationService(), PersistenceContext.repositories().courses());

    @Override
    protected boolean doShow() {
        Iterable<Course> allOpenCourses = this.theController.listOpenCourses();
        Iterable<Course> allEnrollCourses = this.theController.listEnrollCourses();

        boolean hasOpenCourses = allOpenCourses.iterator().hasNext();
        boolean hasEnrollCourses = allEnrollCourses.iterator().hasNext();

        if (!hasOpenCourses && !hasEnrollCourses) {
            System.out.println("There is no courses to open/close enrollments!");
        } else {
            if (hasOpenCourses) {
                final ListWidget<Course> closeCoursesListing = new ListWidget<>("-- Courses to open enrollments " +
                        "--", allOpenCourses, new CoursePrinter());
                closeCoursesListing.show();
            } else {
                System.out.println("-- There are no courses to open enrollments! --");
            }

            System.out.println();

            if (hasEnrollCourses) {
                final ListWidget<Course> inProgressCoursesListing = new ListWidget<>("-- Courses to close enrollments" +
                        " --", allEnrollCourses, new CoursePrinter());
                inProgressCoursesListing.show();
            } else {
                System.out.println("-- There are no courses to close enrollments! --");
            }

            System.out.println();

            List<String> openCloseEnrollmentsOptions = new ArrayList<>();
            openCloseEnrollmentsOptions.add("Open enrollments");
            openCloseEnrollmentsOptions.add("Close enrollments");

            final SelectWidget<String> openOrCloseEnrollmentsOptionsSelector = new SelectWidget<>(
                    "Choose the type of operation you want to perform: ", openCloseEnrollmentsOptions);
            openOrCloseEnrollmentsOptionsSelector.show();
            int option = openOrCloseEnrollmentsOptionsSelector.selectedOption();

            Course theCourse = null;
            if (option == 1) {
                if (hasOpenCourses) {
                    final SelectWidget<Course> openEnrollmentsSelector = new SelectWidget<>("-- Courses that can be " +
                            "opened --", allOpenCourses, new CoursePrinter());
                    openEnrollmentsSelector.show();
                    theCourse = openEnrollmentsSelector.selectedElement();
                } else {
                    System.out.println("-- There are no courses to open enrollments! --");
                }
            } else if (option == 2) {
                if (hasEnrollCourses) {
                    final SelectWidget<Course> closeEnrollmentsSelector = new SelectWidget<>("-- Courses that can be " +
                            "closed --", allEnrollCourses, new CoursePrinter());
                    closeEnrollmentsSelector.show();
                    theCourse = closeEnrollmentsSelector.selectedElement();
                } else {
                    System.out.println("-- There are no courses to close enrollments! --");
                }

            } else {
                System.out.println("Unknown option!");
            }

            try {
                if (theCourse != null) {
                    this.theController.openCloseEnrollment(theCourse);
                    System.out.println("The state of the course was successful altered!");
                }
            } catch (final PendentEnrollmentStateException | InvalidEnrollmentLimitsException ex) {
                System.out.println(ex.getMessage());
            } catch (@SuppressWarnings("unused") final ConcurrencyException ex) {
                System.out.println(
                        "WARNING: It is not possible to change the dish type state because it was changed by another user");
            } catch (final IntegrityViolationException ex) {
                LOGGER.error("Error performing the operation", ex);
                System.out.println(
                        "Unfortunatelly there was an unexpected error in the application. Please try again and if the problem persists, contact your system admnistrator.");
            }

        }

        return true;
    }

    @Override
    public String headline() {
        return "Open/Close Enrollments";
    }
}
