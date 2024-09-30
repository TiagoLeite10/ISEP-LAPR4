package eapli.base.exammanagement.domain;

import eapli.base.questionmanagement.domain.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe para realizar testes à classe ExamSection.
 */
public class ExamSectionTest {

    private final SectionDescription DESCRIPTION = SectionDescription.valueOf("Jogadores");

    private final List<Question> QUESTIONS = buildQuestions();

    /**
     * Método para contruir uma secção de um exame.
     *
     * @return A secção do exame construída.
     */
    private ExamSection buildExamSection() {
        return new ExamSection(DESCRIPTION, QUESTIONS);
    }

    /**
     * Método para contruir lista de questões.
     *
     * @return A lista de questões construída.
     */
    private List<Question> buildQuestions() {
        List<Question> questions = new ArrayList<>();
        questions.add(new ShortAnswer("Em que ano nasceu Cristiano Ronaldo?", QuestionScore.valueOf(10F),
                new Answer("1985", "Cristiano Ronaldo nasceu em 1985 na Madeira")));

        return questions;
    }

    /**
     * Teste para garantir que uma secção de um exame tem a descrição esperada.
     */
    @Test
    void ensureExamSectionHasExpectedDescription() {
        final ExamSection subject = buildExamSection();

        assertEquals(DESCRIPTION, subject.description());
    }

    /**
     * Teste para garantir que uma secção de um exame tem as questões esperadas.
     */
    @Test
    void ensureExamSectionHasExpectedQuestions() {
        final ExamSection subject = buildExamSection();

        List<Question> expected = new ArrayList<>();
        expected.add(new ShortAnswer("Em que ano nasceu Cristiano Ronaldo?", QuestionScore.valueOf(10F),
                new Answer("1985", "Cristiano Ronaldo nasceu em 1985 na Madeira")));

        assertEquals(expected, subject.questions());
    }


    /**
     * Teste para garantir que duas secções de um exame são iguais para a mesma descrição e questões.
     */
    @Test
    void ensureTwoExamSectionWithSameDescriptionsQuestionsAreTheSame() {
        final ExamSection aExamSection = buildExamSection();
        final ExamSection anotherExamSection = new ExamSectionBuilder().withDescription("Jogadores").withQuestion(new ShortAnswer("Em que ano nasceu Cristiano Ronaldo?", QuestionScore.valueOf(10F),
                new Answer("1985", "Cristiano Ronaldo nasceu em 1985 na Madeira"))).build();

        assertTrue(aExamSection.sameAs(anotherExamSection));
    }

    /**
     * Teste para garantir que duas secções de um exame não são iguais para uma descrição diferente.
     */
    @Test
    void ensureTwoExamSectionWithDifferentDescriptionsAreNotTheSame() {
        final ExamSection aExamSection = buildExamSection();
        final ExamSection anotherExamSection = new ExamSectionBuilder().withDescription("Goat").withQuestion(new ShortAnswer(
                "Em que ano nasceu Cristiano Ronaldo?", QuestionScore.valueOf(10F), new Answer("1985",
                "Cristiano Ronaldo nasceu em 1985 na Madeira"))).build();

        assertFalse(aExamSection.sameAs(anotherExamSection));
    }

    /**
     * Teste para garantir que duas secções de um exame não são iguais para questões diferentes.
     */
    @Test
    void ensureTwoExamSectionWithDifferentQuestionsAreNotTheSame() {
        final ExamSection aExamSection = buildExamSection();
        final ExamSection anotherExamSection = new ExamSectionBuilder().withDescription("Jogadores").withQuestion(new TrueFalse(
                "Estamos no ano 2023?", QuestionScore.valueOf(10F), new Answer("true",
                "O ano atual é 2023"))).build();

        assertFalse(aExamSection.sameAs(anotherExamSection));
    }

    /**
     * Teste para garantir que uma secção de um exame é a mesma para a mesma instância.
     */
    @Test
    void ensureExamSectionAreTheSameForTheSameInstance() {
        final ExamSection aExamSection = new ExamSection();

        assertTrue(aExamSection.sameAs(aExamSection));
    }

    /**
     * Teste para garantir que uma secção de um exame não é a mesmo para diferentes tipos de objetos.
     */
    @Test
    void ensureExamSectionNotTheSameForDifferenteObjectTypes() {
        final ExamSection aExamSection = buildExamSection();

        assertFalse(aExamSection.sameAs(DESCRIPTION));
    }

    /**
     * Teste para garantir que uma questão é adicionada com sucesso.
     */
    @Test
    void ensureExamSectionQuestionIsAddedWithSuccess() {
        ExamSection subject = this.buildExamSection();
        Question question = new Numerical("Quanto é 1 + 1?", QuestionScore.valueOf(2.0F),
                new Answer("2.0", "É resposta direta!"), 0.0f);
        subject.addQuestion(question);
    }

    /**
     * Teste para garantir que não pode ser adicionada uma questão com valor nulo.
     */
    @Test
    void ensureExamSectionQuestionCannotBeNull() {
        ExamSection subject = this.buildExamSection();
        assertThrows(IllegalArgumentException.class, () -> subject.addQuestion(null));
    }
}
