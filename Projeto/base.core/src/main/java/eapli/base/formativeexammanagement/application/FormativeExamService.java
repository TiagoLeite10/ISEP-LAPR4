package eapli.base.formativeexammanagement.application;

import eapli.base.coursemanagement.domain.Course;
import eapli.base.coursemanagement.domain.CourseCode;
import eapli.base.exammanagement.domain.Exam;
import eapli.base.exammanagement.domain.ExamSection;
import eapli.base.formativeexammanagement.domain.FormativeExam;
import eapli.base.formativeexammanagement.domain.FormativeExamSection;
import eapli.base.questionmanagement.domain.Question;
import eapli.base.questionmanagement.exception.NumberOfQuestionsException;
import eapli.base.questionmanagement.repositories.QuestionRepository;
import eapli.base.takenexammanagement.lprog.ResolutionMain;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

import java.util.*;

@ApplicationService
public class FormativeExamService {

    private final AuthorizationService authz;

    private final QuestionRepository questionRepo;

    public FormativeExamService(final AuthorizationService authz, final QuestionRepository questionRepo) {
        this.authz = authz;
        this.questionRepo = questionRepo;
    }

    public void checkFormativeExamNumberOfTypeQuestions(final Course theCourse, final FormativeExam formativeExam) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER);

        Map<String, Integer> valuesToVerify = this.countAllTypeQuestionsQuantityInFormativeExam(formativeExam);
        for (String typeQuestion : valuesToVerify.keySet()) {
            Integer number = valuesToVerify.get(typeQuestion);

            if (number > questionRepo.countTypeQuestion(theCourse.identity(), typeQuestion)) {
                throw new NumberOfQuestionsException(typeQuestion, number);
            }
        }
    }

    private Map<String, Integer> countAllTypeQuestionsQuantityInFormativeExam(final FormativeExam formativeExam) {
        Map<String, Integer> result = new HashMap<>();
        for (FormativeExamSection f : formativeExam.formativeExamSections()) {
            Map<String, Integer> numberQuestions = f.questionAndRequiredNumbers();
            for (String typeQuestion : numberQuestions.keySet()) {
                if (result.get(typeQuestion) == null) {
                    result.put(typeQuestion, numberQuestions.get(typeQuestion));
                } else {
                    int num = result.get(typeQuestion) + numberQuestions.get(typeQuestion);
                    result.put(typeQuestion, num);
                }
            }
        }

        return result;
    }

    public Exam transformFormativeExamDataInExam(final CourseCode courseCode, final FormativeExam theFormativeExam) {
        if (!theFormativeExam.validAnswerFormativeExamDate(Calendar.getInstance()))
            throw new IllegalStateException("The formative exam expired!");

        Exam theExam = theFormativeExam.transformInExamObject();
        List<FormativeExamSection> formativeExamSections = theFormativeExam.formativeExamSections();

        // Gerar as questões de forma aleatória
        Map<String, Iterable<Question>> generatedQuestions = this.generateRandomQuestions(courseCode, theFormativeExam);

        for (FormativeExamSection formativeExamSection : formativeExamSections) {
            ExamSection examSection = new ExamSection(formativeExamSection.sectionDescription());
            Set<String> sectionQuestionsTypes = formativeExamSection.questionAndRequiredNumbers().keySet();
            for (String typeQuestion : sectionQuestionsTypes) {
                Iterator<Question> questionIterator = generatedQuestions.get(typeQuestion).iterator();
                if (questionIterator.hasNext()) {
                    Question question = questionIterator.next();
                    examSection.addQuestion(question);
                    questionIterator.remove();
                }
            }
            theExam.addSection(examSection);
        }

        return theExam;
    }

    private Map<String, Iterable<Question>> generateRandomQuestions(final CourseCode courseCode, final FormativeExam theFormativeExam) {
        Map<String, Iterable<Question>> generatedQuestions = new HashMap<>();

        Map<String, Integer> questionAndRequiredNumber = this.countAllTypeQuestionsQuantityInFormativeExam(theFormativeExam);
        for (String typeQuestion : questionAndRequiredNumber.keySet()) {
            generatedQuestions.put(typeQuestion, this.questionRepo.generateRandomQuestions(courseCode, typeQuestion, questionAndRequiredNumber.get(typeQuestion)));
        }

        return generatedQuestions;
    }

    public float parseAndHandleFormativeExamGrade(String filePath, Exam theExam) {
        float grade = ResolutionMain.parseWithVisitor(filePath, theExam);
        float maxGrade = this.calculateFormativeExamMaxGrade(theExam);

        float actualGradePercentage = (grade / maxGrade) * 100; // percentagem obtida neste exam

        // Transforma a percentagem num valor representativo da nota obtida no exame seguindo o seu padrão
        return (Exam.MAXIMUM_SCORE * actualGradePercentage) / 100;
    }

    private float calculateFormativeExamMaxGrade(Exam theExam) {
        float maxGrade = 0f;

        for (ExamSection examSection : theExam.sections()) {
            for (Question question : examSection.questions()) {
                maxGrade += question.score();
            }
        }

        return maxGrade;
    }
}
