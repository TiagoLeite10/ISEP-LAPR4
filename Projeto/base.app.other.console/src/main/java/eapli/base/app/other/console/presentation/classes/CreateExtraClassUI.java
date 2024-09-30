package eapli.base.app.other.console.presentation.classes;

import eapli.base.Helper;
import eapli.base.app.common.console.Helpers;
import eapli.base.app.other.console.presentation.courses.CoursePrinter;
import eapli.base.clientusermanagement.domain.Student;
import eapli.base.coursemanagement.domain.Course;
import eapli.base.eventmanagement.application.CreateExtraClassController;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ListWidget;
import eapli.framework.presentation.console.SelectWidget;

import java.util.*;

/**
 * UI para criar uma aula extra.
 */
public class CreateExtraClassUI extends AbstractUI {

    private final CreateExtraClassController theController = new CreateExtraClassController(AuthzRegistry.authorizationService(),
            PersistenceContext.repositories().teachers(), PersistenceContext.repositories().courses(),
            PersistenceContext.repositories().students());

    @Override
    protected boolean doShow() {
        Iterable<Course> allTeacherCoursesInProgress = this.theController.listAllTeacherCoursesInProgress();

        boolean hasTeacherCoursesInProgress = allTeacherCoursesInProgress.iterator().hasNext();

        if (!hasTeacherCoursesInProgress) {
            System.out.println("There are no courses in progress taught by you!");
        } else {
            System.out.println("List courses taught by you: ");
            String header = String.format("#  %-20s %-50s %-20s %-20s %-20s %-15s", "CODE", "TITLE", "MINIMUM LIMIT", "MAXIMUM LIMIT", "ENROLLED APPROVED", "STATUS");

            final SelectWidget<Course> selector = new SelectWidget<>(header, allTeacherCoursesInProgress, new CoursePrinter());
            selector.show();
            final Course course = selector.selectedElement();

            if (course != null) {
                final Calendar startDate = Console.readCalendar("Date (dd-MM-yyyy HH:mm)", "dd-MM-yyyy HH:mm");
                final int duration = Console.readInteger("Duration");

                Iterable<Student> allApprovedEnrollmentsStudents = theController.listAllApprovedEnrollmentsStudents(course.identity());

                boolean hasAllApprovedEnrollments = allApprovedEnrollmentsStudents.iterator().hasNext();

                if (!hasAllApprovedEnrollments) {
                    System.out.println("There are no students with approved enrollments in the course!");
                } else {
                    System.out.println("List of possible participants in the extra class: ");
                    header = String.format("#  %-30s %-30s %-30s %-20s", "NUM. MECHANNOGRAPHIC", "FIRST NAME", "LAST NAME", "TAX PAYER NUMBER");

                    Set<Student> students = new HashSet<>();
                    boolean flag = true;

                    while (flag && hasAllApprovedEnrollments) {
                        System.out.println("Select a participant for extra class: ");
                        final SelectWidget<Student> selectorEnrollment = new SelectWidget<>(header, allApprovedEnrollmentsStudents, new StudentPrinter());
                        selectorEnrollment.show();
                        final Student student = selectorEnrollment.selectedElement();

                        if (student != null) {
                            students.add(student);
                            Helper.removeElementFromList(allApprovedEnrollmentsStudents, student);
                            hasAllApprovedEnrollments = allApprovedEnrollmentsStudents.iterator().hasNext();
                        } else {
                            flag = false;
                        }
                    }

                    Calendar endDate = (Calendar) startDate.clone();
                    endDate.add(Calendar.MINUTE, duration);

                    theController.checkStudentsAvailability(startDate, endDate, students);
                    System.out.println("Students available for extra class: ");
                    final ListWidget<Student> studentsListWidget = new ListWidget<>(header, students, new StudentPrinter());

                    boolean hasAllAvailableStudents = students.iterator().hasNext();

                    if (!hasAllAvailableStudents) {
                        System.out.println("There are no students available for the extra class!");
                    } else {
                        studentsListWidget.show();

                        boolean confirm = Helpers.askForConfirmation();
                        try {
                            if (confirm) {
                                theController.addExtraClassToCourse(course, startDate, duration, students);

                                System.out.println("Extra class successfully created!");
                            }
                        } catch (final IllegalArgumentException ex) {
                            System.out.println(ex.getMessage());
                        } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
                            System.out.println("You tried to enter a extra class which already exists in the database!");
                        }
                    }
                }
            }
        }

        return false;
    }

    @Override
    public String headline() {
        return "Schedule a extra class";
    }
}
