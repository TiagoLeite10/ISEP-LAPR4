package eapli.base.app.backoffice.console.presentation.courses;

import eapli.base.app.common.console.Helpers;
import eapli.base.clientusermanagement.domain.Teacher;
import eapli.base.coursemanagement.application.SetCourseResponsibleTeacherController;
import eapli.base.coursemanagement.domain.Course;
import eapli.base.coursemanagement.exception.CourseAlreadyHasResponsibleException;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SetCourseResponsibleTeacherUI extends AbstractUI {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenCloseCourseUI.class);

    private final SetCourseResponsibleTeacherController theController = new SetCourseResponsibleTeacherController(
            AuthzRegistry.authorizationService(), PersistenceContext.repositories().courses(),
            PersistenceContext.repositories().teachers());

    @Override
    protected boolean doShow() {
        Iterable<Course> allCoursesWithoutResponsible = this.theController.allCoursesWithoutResponsible();

        if (!allCoursesWithoutResponsible.iterator().hasNext()) {
            System.out.println("There is no courses without a responsible teacher!");
            return false;
        }

        final SelectWidget<Course> allCoursesWithoutResponsibleSelector = new SelectWidget<>("-- Courses that " +
                "do not have a professor in charge --", allCoursesWithoutResponsible, new CoursePrinter());
        allCoursesWithoutResponsibleSelector.show();
        Course theCourse = allCoursesWithoutResponsibleSelector.selectedElement();
        if (theCourse == null) {
            System.out.println("Operation cancelled!");
            return false;
        }

        Iterable<Teacher> allTeachers = this.theController.allTeachers();
        if (!allTeachers.iterator().hasNext()) {
            System.out.println("There are no teachers to be responsible for the course!");
            return false;
        }

        final SelectWidget<Teacher> teacherSelector = new SelectWidget<>("-- Teachers --", allTeachers,
                new TeacherPrinter());
        teacherSelector.show();
        Teacher theTeacher = teacherSelector.selectedElement();
        if (theTeacher == null) {
            System.out.println("Operation cancelled!");
            return false;
        }

        System.out.println("Course title: " + theCourse.title());
        System.out.println("New responsible for the course: " + theTeacher.user().name() + " ("
                + theTeacher.identity() + ")");

        boolean confirm = Helpers.askForConfirmation();

        if (confirm) {
            try {
                theCourse = theController.setResponsibleTeacher(theCourse, theTeacher);
                System.out.println("The responsible teacher was successfully set!");
            } catch (final CourseAlreadyHasResponsibleException ex) {
                System.out.println(ex.getMessage());
            } catch (@SuppressWarnings("unused") final ConcurrencyException ex) {
                System.out.println(
                        "WARNING: It is not possible to set the responsible teacher in the course because this " +
                                "operation was made by another user");
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
        return "Set course responsible teacher";
    }
}
