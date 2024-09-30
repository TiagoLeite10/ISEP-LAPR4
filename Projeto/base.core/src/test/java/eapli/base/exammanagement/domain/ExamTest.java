package eapli.base.exammanagement.domain;

import eapli.base.questionmanagement.domain.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static eapli.base.BuilderHelper.buildDateTime;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExamTest {

    private final ExamTitle TITLE = ExamTitle.valueOf("Exame sobre jogadores");
    private final Calendar OPENING_DATE = buildDateTime(3000, 7, 26, 0, 0);
    private final Calendar CLOSE_DATE = buildDateTime(3000, 7, 29, 23, 59);
    private final ExamDateTime DATE_TIME = ExamDateTime.valueOf(OPENING_DATE, CLOSE_DATE);
    private final Header HEADER = new HeaderBuilder().withDescription("Primeiro exame sobre jogadores").withTypeNote("on-submission").
            withTypeFeedback("after-closing").build();

    private final ExamSection SECTION = new ExamSectionBuilder().withDescription("Secção sobre golos")
            .withQuestion(new ShortAnswer("Em que ano nasceu Cristiano Ronaldo?", QuestionScore.valueOf(10F),
                    new Answer("1985", "Cristiano Ronaldo nasceu em 1985 na Madeira"))).build();

    /**
     * Método para contruir um exame.
     *
     * @return O exame construído.
     */
    private Exam buildExam() {
        final Exam subject = new Exam(TITLE, DATE_TIME, HEADER);
        subject.addSection(SECTION);

        return subject;
    }

    /**
     * Teste para garantir que uma secção é adicionada com sucesso.
     */
    @Test
    void ensureExamSectionIsAddedWithSuccess() {
        Exam subject = this.buildExam();
        ExamSection section = new ExamSection(new SectionDescription("Esta é uma secção para teste."));
        subject.addSection(section);
    }

    /**
     * Teste para garantir que não pode ser adicionada uma secção com valor nulo.
     */
    @Test
    void ensureExamSectionCannotBeNull() {
        Exam subject = this.buildExam();
        assertThrows(IllegalArgumentException.class, () -> subject.addSection(null));
    }

    /**
     * Teste para garantir que o exame é impresso corretamente para o aluno poder ver as suas questões e responder a
     * este mesmo exame
     */
    @Test
    void ensureExamIsCorrectlyPrintToStudentAnswerItQuestions() {
        String expected = "Title: " + this.TITLE.toString()
                + "\nSection description: Secção sobre golos\n"
                + "Short Answer\n" + "Em que ano nasceu Cristiano Ronaldo?\n\n"
                + "\n";

        Exam subject = this.buildExam();
        String result = subject.examQuestionsToString();

        assertEquals(expected, result);
    }

    /**
     * Teste para garantir que o feedback do exame é impresso corretamente.
     */
    @Test
    void ensureExamFeedbackIsCorrectlyPrinted() {
        String expected = "Section description: Secção sobre golos\n"
                + "Question: Em que ano nasceu Cristiano Ronaldo?\nFeedback: Cristiano Ronaldo nasceu em 1985 na " +
                "Madeira\n\n";

        Exam subject = this.buildExam();
        String result = subject.feedback();

        assertEquals(expected, result);
    }

    /**
     * Teste para garantir que as secções de um exame são devolvidas corretamente.
     */
    @Test
    void ensureExamSectionsAreCorrectlyReturned() {
        List<ExamSection> expectedSections = new ArrayList<>();
        expectedSections.add(this.SECTION);

        Exam subject = this.buildExam();
        List<ExamSection> resultSections = subject.sections();

        assertEquals(expectedSections.size(), resultSections.size());

        for (int i = 0; i < expectedSections.size(); i++) {
            assertEquals(expectedSections.get(i), resultSections.get(i));
        }
    }
}
