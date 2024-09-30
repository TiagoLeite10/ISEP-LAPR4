package eapli.base.app.backoffice.console.presentation.courses;

import eapli.base.coursemanagement.application.ApproveRejectStudentCourseController;
import eapli.base.coursemanagement.domain.Course;
import eapli.base.coursemanagement.exception.ApprovedEnrollmentException;
import eapli.base.coursemanagement.exception.RejectEnrollmentException;
import eapli.base.enrollmentmanagement.domain.Enrollment;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * UI para aprovar/rejeitar estudantes numa disciplina.
 */
public class ApproveRejectStudentCourseUI extends AbstractUI {

    private static final Logger LOGGER = LogManager.getLogger(ApproveRejectStudentCourseUI.class);

    private final ApproveRejectStudentCourseController theController = new ApproveRejectStudentCourseController(
            AuthzRegistry.authorizationService(), PersistenceContext.repositories().courses(), PersistenceContext.repositories().enrollments());

    @Override
    protected boolean doShow() {
        Iterable<Course> allEnrollCourses = this.theController.listAllEnrollCourses();

        boolean hasEnrollCourses = allEnrollCourses.iterator().hasNext();

        if (!hasEnrollCourses) {
            System.out.println("There are no courses with open enrollment!");
        } else {
            System.out.println("List Courses: ");
            String header = String.format("#  %-20s %-50s %-20s %-20s %-20s %-15s", "CODE", "TITLE", "MINIMUM LIMIT", "MAXIMUM LIMIT", "ENROLLED APPROVED", "STATUS");

            final SelectWidget<Course> selectorCourse = new SelectWidget<>(header, allEnrollCourses, new CoursePrinter());
            selectorCourse.show();
            final Course theCourse = selectorCourse.selectedElement();

            if (theCourse != null) {
                Iterable<Enrollment> allPendingEnrollments = theController.listAllPendingEnrollments(theCourse.identity());

                boolean hasAllPendingEnrollments = allPendingEnrollments.iterator().hasNext();

                if (!hasAllPendingEnrollments) {
                    System.out.println("There are no pending enrollments in the course!");
                } else {
                    System.out.println("Enrollment list: ");
                    header = String.format("#  %-30s %-30s %-30s %-20s", "NUM. MECHANNOGRAPHIC", "FIRST NAME", "LAST NAME", "TAX PAYER NUMBER");

                    final SelectWidget<Enrollment> selectorEnrollment = new SelectWidget<>(header, allPendingEnrollments, new EnrollmentPrinter());
                    selectorEnrollment.show();
                    final Enrollment theEnrollment = selectorEnrollment.selectedElement();

                    if (theEnrollment != null) {
                        final String option1 = "Approve student";
                        final String option2 = "Reject student";

                        List<String> listOptions = new ArrayList<>();
                        listOptions.add(option1);
                        listOptions.add(option2);

                        final SelectWidget<String> selectorOption = new SelectWidget<>("Options: ", listOptions);
                        selectorOption.show();
                        final String theOption = selectorOption.selectedElement();

                        if (theOption != null) {
                            try {
                                switch (theOption) {
                                    case option1:
                                        theController.approvedEnrollment(theCourse, theEnrollment);
                                        System.out.println("Successfully approved student in the course!");
                                        break;

                                    case option2:
                                        theController.rejectEnrollment(theCourse, theEnrollment);
                                        System.out.println("Successfully rejected student in the course!");
                                        break;
                                }

                            } catch (final ApprovedEnrollmentException | RejectEnrollmentException ex) {
                                System.out.println(ex.getMessage());
                            } catch (@SuppressWarnings("unused") final ConcurrencyException ex) {
                                System.out.println("WARNING: It is not possible to change the enrollment state because it was changed by another user");
                            } catch (final IntegrityViolationException ex) {
                                LOGGER.error("Error performing the operation", ex);
                                System.out.println("Unfortunatelly there was an unexpected error in the application. " +
                                        "Please try again and if the problem persists, contact your system admnistrator.");
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    @Override
    public String headline() {
        return "Approve/reject students in courses";
    }
}
