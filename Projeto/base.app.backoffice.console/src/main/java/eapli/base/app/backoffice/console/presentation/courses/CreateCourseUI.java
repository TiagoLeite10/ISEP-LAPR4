package eapli.base.app.backoffice.console.presentation.courses;

import eapli.base.coursemanagement.application.CreateCourseController;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

public class CreateCourseUI extends AbstractUI {

    private final CreateCourseController theController = new CreateCourseController(AuthzRegistry.authorizationService(), PersistenceContext.repositories().courses());

    @Override
    protected boolean doShow() {
        final String courseCode = Console.readLine("Code:");
        final String courseTitle = Console.readLine("Title:");
        final String courseDescription = Console.readLine("Description:");
        final int minimumEnrolments = Console.readInteger("Number of minimum enrolments:");
        final int maximumEnrolments = Console.readInteger("Number of maximum enrolments:");

        try {
            this.theController.createCourse(courseCode, courseTitle, courseDescription, minimumEnrolments, maximumEnrolments);
            System.out.println("Course creation completed successfully");


        } catch (@SuppressWarnings("unused") final IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
            System.out.println("You tried to insert a course that already exists in the database.");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Create Course";
    }

}
