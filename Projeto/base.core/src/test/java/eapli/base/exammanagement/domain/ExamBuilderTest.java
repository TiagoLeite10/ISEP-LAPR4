package eapli.base.exammanagement.domain;

import eapli.base.BuilderHelper;
import eapli.base.exammanagement.exception.InvalidScoreException;
import eapli.base.questionmanagement.domain.Answer;
import eapli.base.questionmanagement.domain.Question;
import eapli.base.questionmanagement.domain.QuestionScore;
import eapli.base.questionmanagement.domain.ShortAnswer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Classe para realizar testes ao builder ExamBuilder.
 */
public class ExamBuilderTest {

    private final String TITLE = "Exame sobre jogadores";

    private final Calendar OPENING_DATE_TIME = BuilderHelper.buildDateTime(3000, 7, 26, 0, 0);

    private final Calendar CLOSING_DATE_TIME = BuilderHelper.buildDateTime(3000, 7, 29, 23, 59);

    private final Header HEADER = new HeaderBuilder().withDescription("Primeiro exame sobre jogadores").withTypeNote("on-submission").
            withTypeFeedback("after-closing").build();

    private final ExamSection SECTION = buildExamSection();

    /**
     * Método para contruir uma secção de um exame.
     *
     * @return A secção do exame construída.
     */
    private ExamSection buildExamSection() {
        List<Question> questions = new ArrayList<>();
        questions.add(new ShortAnswer("Em que ano nasceu Cristiano Ronaldo?", QuestionScore.valueOf(20F),
                new Answer("1985", "Cristiano Ronaldo nasceu em 1985 na Madeira")));

        return new ExamSection(SectionDescription.valueOf("Jogadores de outra galáxia"), questions);
    }

    /**
     * Teste para garantir que é possível construir um exame com o título, data de abertura, data de fecho, cabeçalho e
     * secção.
     */
    @Test
    public void ensureCanBuildExamWithTitleOpeningDateTimeClosingDateTimeHeaderSection() {
        final Exam subject = new ExamBuilder().with(TITLE, OPENING_DATE_TIME, CLOSING_DATE_TIME, HEADER).withSection(SECTION).build();
        assertNotNull(subject);
    }

    /**
     * Teste para garantir que não é possível construir um exame com o título nulo.
     */
    @Test
    public void ensureCannotBuildExamWithNullTitle() {
        assertThrows(IllegalArgumentException.class, () -> new ExamBuilder().withTitle(null).
                withDateTime(OPENING_DATE_TIME, CLOSING_DATE_TIME).withHeader(HEADER).withSection(SECTION).build());
    }

    /**
     * Teste para garantir que não é possível construir um exame sem título.
     */
    @Test
    public void ensureCannotBuildExamWithoutTitle() {
        assertThrows(IllegalStateException.class, () -> new ExamBuilder().withDateTime(OPENING_DATE_TIME, CLOSING_DATE_TIME).
                withHeader(HEADER).withSection(SECTION).build());
    }

    /**
     * Teste para garantir que não é possível construir um exame com a data de abertura nula.
     */
    @Test
    public void ensureCannotBuildExamWithNullOpeningDateTime() {
        assertThrows(IllegalArgumentException.class, () -> new ExamBuilder().withTitle(TITLE).
                withDateTime(null, CLOSING_DATE_TIME).withHeader(HEADER).withSection(SECTION).build());
    }

    /**
     * Teste para garantir que não é possível construir um exame com a data de fecho nula.
     */
    @Test
    public void ensureCannotBuildExamWithNullClosingDateTime() {
        assertThrows(IllegalArgumentException.class, () -> new ExamBuilder().withTitle(TITLE).
                withDateTime(OPENING_DATE_TIME, null).withHeader(HEADER).withSection(SECTION).build());
    }

    /**
     * Teste para garantir que não é possível construir um exame com o cabeçalho nulo.
     */
    @Test
    public void ensureCannotBuildExamWithNullHeader() {
        assertThrows(IllegalStateException.class, () -> new ExamBuilder().withTitle(TITLE).
                withDateTime(OPENING_DATE_TIME, CLOSING_DATE_TIME).withHeader(null).withSection(SECTION).build());
    }

    /**
     * Teste para garantir que não é possível construir um exame sem cabeçalho.
     */
    @Test
    public void ensureCannotBuildExamWithoutHeader() {
        assertThrows(IllegalStateException.class, () -> new ExamBuilder().withTitle(TITLE).
                withDateTime(OPENING_DATE_TIME, CLOSING_DATE_TIME).withSection(SECTION).build());
    }

    /**
     * Teste para garantir que não é possível construir um exame com uma secção nula.
     */
    @Test
    public void ensureCannotBuildExamWithNullSection() {
        assertThrows(IllegalArgumentException.class, () -> new ExamBuilder().withTitle(TITLE).
                withDateTime(OPENING_DATE_TIME, CLOSING_DATE_TIME).withHeader(HEADER).withSection(null).build());
    }

    /**
     * Teste para garantir que não é possível construir um exame sem secções.
     */
    @Test
    public void ensureCannotBuildExamWithoutSections() {
        assertThrows(InvalidScoreException.class, () -> new ExamBuilder().withTitle(TITLE).
                withDateTime(OPENING_DATE_TIME, CLOSING_DATE_TIME).withHeader(HEADER).build());
    }
}
