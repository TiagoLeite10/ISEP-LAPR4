package eapli.base.app.user.console.presentation.exams;

import eapli.base.app.common.console.Helpers;
import eapli.base.app.user.console.presentation.courses.CoursePrinter;
import eapli.base.coursemanagement.domain.Course;
import eapli.base.enrollmentmanagement.exception.ErrorInFileException;
import eapli.base.exammanagement.application.TakeExamController;
import eapli.base.exammanagement.domain.Exam;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ListWidget;
import eapli.framework.presentation.console.SelectWidget;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TakeExamUI extends AbstractUI {

    private final TakeExamController theController = new TakeExamController(AuthzRegistry.authorizationService(),
            PersistenceContext.repositories().students(),
            PersistenceContext.repositories().exams(),
            PersistenceContext.repositories().takenExams());

    @Override
    protected boolean doShow() {
        Iterable<Course> studentCoursesWithExams = this.theController.listAllStudentCoursesInProgress();

        boolean hasStudentsCourses = studentCoursesWithExams.iterator().hasNext();

        if (!hasStudentsCourses) {
            System.out.println("You are not enrolled in any course that as an exam!");
        } else {
            System.out.println("List Courses: ");
            String couresHeader = String.format("#  %-20s %-50s", "CODE", "TITLE");

            final SelectWidget<Course> courseSelector = new SelectWidget<>(couresHeader, studentCoursesWithExams, new CoursePrinter());
            courseSelector.show();

            final Course course = courseSelector.selectedElement();

            if (course != null) {
                Iterable<Exam> allExamCourses = this.theController.ListAllCourseExamsInATimeInterval(course.identity());

                System.out.println("List Exams: ");
                String examHeader = String.format("#  %-30s %-50s %-80s", "TITLE", "DESCRIPTION", "OPENING DATE - CLOSING DATE");
                final SelectWidget<Exam> examSelector = new SelectWidget<>(examHeader, allExamCourses, new ExamPrinter());
                examSelector.show();

                final Exam exam = examSelector.selectedElement();

                if (exam != null) {
                    System.out.println(theController.getExamQuestions(exam));

                    float grade = 0.0f;
                    boolean corretExamResolution = false;

                    while (!corretExamResolution) {
                        String pathToFile = "";
                        try {
                            pathToFile = Console.readLine("Insert the path to the file with the resolution of the exam: ");
                            grade = this.theController.getGrade(pathToFile, exam);
                            corretExamResolution = true;
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                            if (!Console.readBoolean("Want to keep trying resolving the exam? (y/n)")) {
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
                                    System.out.println(theController.getExamFeedback(exam));
                                    System.out.println("The grade you get on the exam was " + grade);
                                    this.theController.createTakenExam(exam, grade);
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
                }
            }
        }

        return false;
    }

    @Override
    public String headline() {
        return "Take an exam";
    }
}
