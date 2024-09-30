package eapli.base.app.user.console.presentation.exams;

import eapli.base.app.common.console.Helpers;
import eapli.base.app.user.console.presentation.courses.CoursePrinter;
import eapli.base.coursemanagement.domain.Course;
import eapli.base.formativeexammanagement.application.TakeAutomaticFormativeExamController;
import eapli.base.exammanagement.domain.Exam;
import eapli.base.formativeexammanagement.domain.FormativeExam;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * UI para responder a um exame formativo automático.
 */
public class TakeAutomaticFormativeExamUI extends AbstractUI {

    private static final Logger LOGGER = LoggerFactory.getLogger(TakeAutomaticFormativeExamUI.class);

    private final TakeAutomaticFormativeExamController theController = new TakeAutomaticFormativeExamController(
            AuthzRegistry.authorizationService(), PersistenceContext.repositories().students(),
            PersistenceContext.repositories().courses(), PersistenceContext.repositories().formativeExams(),
            PersistenceContext.repositories().questions());

    @Override
    protected boolean doShow() {
        Iterable<Course> allStudentCourses = this.theController.findAllStudentApprovedCoursesInProgressWithActualFormativeExams();
        if (!allStudentCourses.iterator().hasNext()) {
            System.out.println("There is no available formative exams for any of the courses that you are enrolled!");
            return false;
        }

        final SelectWidget<Course> allStudentCoursesSelector = new SelectWidget<>("Select the course that you want " +
                "to make a formative exam:", allStudentCourses, new CoursePrinter());
        allStudentCoursesSelector.show();
        Course theCourse = allStudentCoursesSelector.selectedElement();
        if (theCourse == null) {
            System.out.println("Operation cancelled!");
            return false;
        }

        Iterable<FormativeExam> allFormativeExamsOfTheCourse = this.theController.listAllAvailableFormativeExamsOfTheCourse(theCourse);
        if (!allFormativeExamsOfTheCourse.iterator().hasNext()) {
            System.out.println("This course doesn't have formative exams!");
            return false;
        }

        System.out.println("Select the formative exam that you want to take:");
        String header = String.format("#  %-50s %-50s %-36s", "TITLE", "DESCRIPTION", "OPENING DATE - CLOSING DATE");
        final SelectWidget<FormativeExam> allFormativeExamsOfTheCourseSelector = new SelectWidget<>(header, allFormativeExamsOfTheCourse, new TakeAutomaticFormativeExamPrinter());
        allFormativeExamsOfTheCourseSelector.show();
        FormativeExam theFormativeExam = allFormativeExamsOfTheCourseSelector.selectedElement();
        if (theFormativeExam == null) {
            System.out.println("Operation cancelled!");
            return false;
        }

        Exam theExam;
        try {
            theExam = this.theController.transformFormativeExamDataInExam(theCourse, theFormativeExam);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return false;
        }

        String examData = this.theController.getExamQuestionsString(theExam);
        System.out.println(examData);

        float obtainedGrade = 0.0f;

        boolean corretExamResolution = false;
        while (!corretExamResolution) {
            String pathToFile = "";
            try {
                pathToFile = Console.readLine("Insert the path to the file with the resolution of the formative exam: ");
                if (new File(pathToFile).exists()) {
                    obtainedGrade = this.theController.parseFormativeExamAnswers(pathToFile, theExam);
                    corretExamResolution = true;
                } else {
                    System.out.println("The path cannot be null!");
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                if (!Console.readBoolean("Want to keep trying resolving the formative exam? (y/n)")) {
                    System.out.println("Operation cancelled!");
                    return false;
                }
            }

            if (corretExamResolution) {

                System.out.println("Now check your content in the file and verify if you are sure of your " +
                        "answers. If it's all ok, you can submit your resolution.");
                try {
                    System.out.println(new String(Files.readAllBytes(Paths.get(pathToFile))));
                    boolean confirm = Helpers.askForConfirmation();
                    if (confirm) {
                        String examFeedback = this.theController.getExamFeedback(theExam);
                        System.out.println(examFeedback);
                        System.out.printf("The grade you get on the exam was %.2f", obtainedGrade);
                    } else {
                        if (!Console.readBoolean("Want to keep trying resolving the exam? (y/n)")) {
                            System.out.println("Operation cancelled!");
                            return false;
                        }
                        corretExamResolution = false;
                    }
                } catch (IOException e) {
                    System.out.println("Something happen when trying to read the file data! Please try again, and if " +
                            "the error persistes, contact an administrator. Thank you!");
                    corretExamResolution = false;
                }

            }
        }

        return true;
    }

    @Override
    public String headline() {
        return "Take automatic formative exam";
    }

}
