package eapli.base.app.other.console.presentation.classes;

import eapli.base.app.other.console.presentation.courses.CoursePrinter;
import eapli.base.classmanagement.aplication.UpdateClassScheduleController;
import eapli.base.classmanagement.domain.RecurringClass;
import eapli.base.coursemanagement.domain.Course;
import eapli.base.coursemanagement.exception.CourseAvailabilityException;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.util.Calendar;
import java.util.Date;

public class UpdateClassScheduleUI extends AbstractUI {

    private final UpdateClassScheduleController theController = new UpdateClassScheduleController(AuthzRegistry.authorizationService(),
            PersistenceContext.repositories().teachers(), PersistenceContext.repositories().courses(), PersistenceContext.repositories().recurringClasses());

    @Override
    protected boolean doShow() {
        Iterable<Course> allTeacherCoursesNotClosed = this.theController.listAllTeacherCoursesNotClosed();

        boolean hasTeacherCoursesNotClosed = allTeacherCoursesNotClosed.iterator().hasNext();

        if (!hasTeacherCoursesNotClosed) {
            System.out.println("There are no courses not closed taught by you!");
        } else {
            System.out.println("List courses not closed taught by you: ");
            String header = String.format("#  %-20s %-50s %-20s %-20s %-20s %-15s", "CODE", "TITLE", "MINIMUM LIMIT", "MAXIMUM LIMIT", "ENROLLED APPROVED", "STATUS");

            final SelectWidget<Course> selectorCourse = new SelectWidget<>(header, allTeacherCoursesNotClosed, new CoursePrinter());
            selectorCourse.show();
            final Course course = selectorCourse.selectedElement();

            if (course != null) {
                Iterable<RecurringClass> allTeacherRecurringClass = this.theController.listAllTeacherRecurringClass();

                boolean hasTeacherRecurringClass = allTeacherRecurringClass.iterator().hasNext();

                if (!hasTeacherRecurringClass) {
                    System.out.println("There are no recurring classes taught by you!");
                } else {
                    System.out.println("List recurring classes taught by you: ");
                    header = String.format("#  %-50s %-20s %-20s %-25s", "TITLE", "START TIME", "DURATION", "DAY OF WEEK");

                    final SelectWidget<RecurringClass> selectorRecurringClass = new SelectWidget<>(header, allTeacherRecurringClass, new RecurringClassPrinter());
                    selectorRecurringClass.show();
                    final RecurringClass recurringClass = selectorRecurringClass.selectedElement();

                    if (recurringClass != null) {
                        final Calendar newDateTime = Console.readCalendar("Start Time (dd-MM-yyyy HH:mm)", "dd-MM-yyyy HH:mm");
                        try {
                            theController.addClassInstanceToRecurringClass(course, recurringClass, newDateTime);

                            System.out.println("Update successfully schedule class!");
                        } catch (final IllegalArgumentException | CourseAvailabilityException ex) {
                            System.out.println(ex.getMessage());
                        } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
                            System.out.println("You tried to update a schedule class which already exists in the database!");
                        }
                    }
                }
            }
        }
            return false;
        }

        @Override
        public String headline () {
            return "Update schedule class";
        }
    }
