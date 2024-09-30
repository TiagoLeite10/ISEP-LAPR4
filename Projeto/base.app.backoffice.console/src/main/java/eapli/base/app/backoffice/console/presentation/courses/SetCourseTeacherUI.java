package eapli.base.app.backoffice.console.presentation.courses;

import eapli.base.app.common.console.Helpers;
import eapli.base.clientusermanagement.domain.Teacher;
import eapli.base.coursemanagement.application.SetCourseTeacherController;
import eapli.base.coursemanagement.domain.Course;
import eapli.base.coursemanagement.exception.TeacherAlreadyTeachTheCourseException;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SetCourseTeacherUI extends AbstractUI {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenCloseCourseUI.class);

    private final SetCourseTeacherController theController = new SetCourseTeacherController(
            AuthzRegistry.authorizationService(), PersistenceContext.repositories().courses(),
            PersistenceContext.repositories().teachers());

    @Override
    protected boolean doShow() {

        Iterable<Course> allCoursesNotClosed = this.theController.allCoursesNotClosed();
        if (!allCoursesNotClosed.iterator().hasNext()) {
            System.out.println("There is no courses available to perform this operation!");
            return false;
        }

        final SelectWidget<Course> allCoursesNotClosedSelector = new SelectWidget<>("-- All available " +
                "courses to make this operation --", allCoursesNotClosed, new CoursePrinter());
        allCoursesNotClosedSelector.show();
        Course theCourse = allCoursesNotClosedSelector.selectedElement();
        if (theCourse == null) {
            System.out.println("Operation cancelled!");
            return false;
        }

        Iterable<Teacher> possibleTeachersForTheCourse = this.theController.allTeachersWhoDoNotTeachThisCourse(theCourse);
        if (!possibleTeachersForTheCourse.iterator().hasNext()) {
            System.out.println("There are no available teachers to teach this course!");
            return false;
        }

        final SelectWidget<Teacher> possibleTeachersForTheCourseSelector = new SelectWidget<>("-- Available " +
                "teachers --", possibleTeachersForTheCourse, new TeacherPrinter());
        possibleTeachersForTheCourseSelector.show();
        Teacher theTeacher = possibleTeachersForTheCourseSelector.selectedElement();
        if (theTeacher == null) {
            System.out.println("Operation cancelled!");
            return false;
        }

        System.out.println("Course title: " + theCourse.title());
        System.out.println("New teacher for the course: " + theTeacher.user().name() + " ("
                + theTeacher.identity() + ")");

        boolean confirm = Helpers.askForConfirmation();

        if (confirm) {
            try {
                theCourse = theController.setCourseTeacher(theCourse, theTeacher);
                System.out.println("The teacher was successfully set!");
            } catch (final TeacherAlreadyTeachTheCourseException ex) {
                System.out.println(ex.getMessage());
            } catch (@SuppressWarnings("unused") final ConcurrencyException ex) {
                System.out.println(
                        "WARNING: It is not possible to set the teacher in the course because this operation was made " +
                                "by another user");
            } catch (final IntegrityViolationException ex) {
                LOGGER.error("Error performing the operation", ex);
                System.out.println(
                        "Unfortunatelly there was an unexpected error in the application. Please try again and if " +
                                "the problem persists, contact your system admnistrator.");
            }
        } else {
            System.out.println("Operation cancelled!");
        }

        return true;
    }

    @Override
    public String headline() {
        return "Set course teacher";
    }
}
