package eapli.base.app.other.console.presentation.classes;

import eapli.base.app.other.console.presentation.courses.CoursePrinter;
import eapli.base.classmanagement.aplication.CreateRecurringClassController;
import eapli.base.classmanagement.domain.ClassDayOfTheWeek;
import eapli.base.coursemanagement.domain.Course;
import eapli.base.coursemanagement.exception.CourseAvailabilityException;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.util.*;

public class CreateRecurringClassUI extends AbstractUI {

    private final CreateRecurringClassController theController = new CreateRecurringClassController(AuthzRegistry.authorizationService(),
            PersistenceContext.repositories().teachers(), PersistenceContext.repositories().courses());

    @Override
    protected boolean doShow() {
        Iterable<Course> allTeacherCoursesNotClosed = this.theController.listAllTeacherCoursesNotClosed();

        boolean hasTeacherCoursesNotClosed = allTeacherCoursesNotClosed.iterator().hasNext();

        if (!hasTeacherCoursesNotClosed) {
            System.out.println("There are no courses not closed taught by you!");
        } else {
            System.out.println("List courses not closed taught by you: ");
            String header = String.format("#  %-20s %-50s %-20s %-20s %-20s %-15s", "CODE", "TITLE", "MINIMUM LIMIT", "MAXIMUM LIMIT", "ENROLLED APPROVED", "STATUS");

            final SelectWidget<Course> selector = new SelectWidget<>(header, allTeacherCoursesNotClosed, new CoursePrinter());
            selector.show();
            final Course course = selector.selectedElement();

            if (course != null) {
                final String classTilte = Console.readLine("Class Title");
                final Calendar classStartTime = Console.readCalendar("Start Time (HH:mm)", "HH:mm");
                final int classDuration = Console.readInteger("Duration");
                final ClassDayOfTheWeek classDayOfTheWeek = readDayOfWeek();

                if (classDayOfTheWeek != null) {

                    try {
                        theController.addRecurringClassToCourse(course, classTilte, classStartTime, classDuration, classDayOfTheWeek);

                        System.out.println("Recurring class successfully created!");
                    } catch (final IllegalArgumentException | CourseAvailabilityException ex) {
                        System.out.println(ex.getMessage());
                    } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
                        System.out.println("You tried to enter a recurring class which already exists in the database!");
                    }
                }
            }
        }

        return true;
    }


    public ClassDayOfTheWeek readDayOfWeek() {
        List<ClassDayOfTheWeek> dayOfWeekList = Arrays.asList(ClassDayOfTheWeek.values());

        final SelectWidget<ClassDayOfTheWeek> selector = new SelectWidget<>("Select day of the week", dayOfWeekList);
        selector.show();

        return selector.selectedElement();
    }

    @Override
    public String headline() {
        return "Schedule a recurring class";
    }
}
