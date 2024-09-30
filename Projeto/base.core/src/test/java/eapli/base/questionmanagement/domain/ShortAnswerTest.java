package eapli.base.questionmanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe para realizar testes à classe ShortAnswer.
 */
public class ShortAnswerTest {

    private final String TEXT = "Em que ano nasceu Cristiano Ronaldo?";

    private final QuestionScore SCORE = QuestionScore.valueOf(10F);

    private final Answer ANSWER = new Answer("1985", "Cristiano Ronaldo nasceu em 1985 na Madeira");

    /**
     * Método para contruir uma questão do tipo resposta curta.
     *
     * @return A questão construída.
     */
    private ShortAnswer buildShortAnswer() {
        return new ShortAnswer(TEXT, SCORE, ANSWER);
    }

    /**
     * Teste para garantir que é possível construir uma questão do tipo resposta curta com o texto, pontuação e resposta.
     */
    @Test
    public void ensureCanBuildShortAnswerWithTextScoreAnswer() {
        final ShortAnswer subject = new ShortAnswer(TEXT, SCORE, ANSWER);
        assertNotNull(subject);
    }

    /**
     * Teste para garantir que não é possível construir uma questão do tipo resposta curta com o texto nulo.
     */
    @Test
    public void ensureCannotBuildShortAnswerWithNullText() {
        assertThrows(IllegalArgumentException.class, () -> new ShortAnswer(null, SCORE, ANSWER));
    }

    /**
     * Teste para garantir que não é possível construir uma questão do tipo resposta curta com a pontuação nula.
     */
    @Test
    public void ensureCannotBuildShortAnswerWithNullScore() {
        assertThrows(IllegalArgumentException.class, () -> new ShortAnswer(TEXT, null, ANSWER));
    }

    /**
     * Teste para garantir que não é possível construir uma questão do tipo resposta curta com a resposta nula.
     */
    @Test
    public void ensureCannotBuildShortAnswerWithNullAnswer() {
        assertThrows(IllegalArgumentException.class, () -> new ShortAnswer(TEXT, SCORE, null));
    }

    /**
     * Teste para garantir que uma questão do tipo resposta curta tem o tipo de texto esperada.
     */
    @Test
    void ensureShortAnswerHasExpectedTypeText() {
        final ShortAnswer subject = buildShortAnswer();

        assertEquals("Short Answer\n" + TEXT, subject.typeText());
    }

    /**
     * Teste para garantir que uma questão do tipo resposta curta não tem o tipo de texto esperada.
     */
    @Test
    void ensureShortAnswerHasNotExpectedTypeText() {
        final ShortAnswer subject = buildShortAnswer();

        assertNotEquals("Matching\n" + TEXT, subject.typeText());
    }

    /**
     * Teste para garantir que uma questão do tipo resposta curta tem a questão e feedback esperado.
     */
    @Test
    void ensureShortAnswerHasExpectedQuestionFeedback() {
        final ShortAnswer subject = buildShortAnswer();

        assertEquals("Question: " + TEXT + "\nFeedback: " + ANSWER.feedback() + "\n",
                subject.questionFeedback());
    }

    /**
     * Teste para garantir que uma questão do tipo resposta curta não tem a questão e feedback esperado.
     */
    @Test
    void ensureShortAnswerHasNotExpectedQuestionFeedback() {
        final ShortAnswer subject = buildShortAnswer();

        assertNotEquals("Question: " + TEXT + "\nFeedback: Cristiano Ronaldo nasceu em 1985 na ilha da Madeira\n",
                subject.questionFeedback());
    }

    /**
     * Teste para garantir que uma questão do tipo resposta curta tem o texto esperada.
     */
    @Test
    void ensureShortAnswerHasExpectedText() {
        final ShortAnswer subject = buildShortAnswer();

        assertEquals(TEXT, subject.text());
    }

    /**
     * Teste para garantir que a resposta a uma questão do tipo resposta curta está correta.
     */
    @Test
    void ensureAnswerShortAnswerIsCorrect() {
        final ShortAnswer subject = buildShortAnswer();

        assertTrue(subject.isCorrectAnswer("1985"));
    }

    /**
     * Teste para garantir que a resposta a uma questão do tipo resposta curta não está correta.
     */
    @Test
    void ensureAnswerShortAnswerIsNotCorrect() {
        final ShortAnswer subject = buildShortAnswer();

        assertFalse(subject.isCorrectAnswer("1990"));
    }

    /**
     * Teste para garantir que uma questão do tipo resposta curta tem a pontuação esperada.
     */
    @Test
    void ensureShortAnswerHasExpectedScore() {
        final ShortAnswer subject = buildShortAnswer();

        assertEquals(10F, subject.score());
    }

    /**
     * Teste para garantir que é possível juntar uma questão do tipo resposta curta com outra do mesmo tipo.
     */
    @Test
    void ensureMergeShortAnswerWithSameObjectType() {
        final ShortAnswer aShortAnswer = buildShortAnswer();
        final ShortAnswer anotherShortAnswer = new ShortAnswer("Em que mês nasceu Cristiano Ronaldo?", QuestionScore.valueOf(10F),
                new Answer("Fevereiro", "Cristiano Ronaldo nasceu em fevereiro de 1985 na Madeira"));

        aShortAnswer.merge(anotherShortAnswer);

        assertEquals(anotherShortAnswer, aShortAnswer);
    }

    /**
     * Teste para garantir que não é possível juntar uma questão do tipo resposta curta com um tipo de objeto diferente.
     */
    @Test
    void ensureCannotMergeShortAnswerWithDifferenteObjectTypes() {
        final ShortAnswer subject = buildShortAnswer();

        assertThrows(IllegalArgumentException.class, () -> subject.merge(new TrueFalse(TEXT, SCORE, ANSWER)));
    }

    /**
     * Teste para garantir que duas questões do tipo resposta curta são iguais para o mesmo texto, pontuação e resposta.
     */
    @Test
    void ensureTwoShortAnswerWithSameTextsScoresAnswersAreTheSame() {
        final ShortAnswer aShortAnswer = buildShortAnswer();
        final ShortAnswer anotherShortAnswer = new ShortAnswer("Em que ano nasceu Cristiano Ronaldo?", QuestionScore.valueOf(10F),
                new Answer("1985", "Cristiano Ronaldo nasceu em 1985 na Madeira"));

        assertTrue(aShortAnswer.sameAs(anotherShortAnswer));
    }

    /**
     * Teste para garantir que duas questões do tipo resposta curta não são iguais para um texto diferente.
     */
    @Test
    void ensureTwoShortAnswerWithDifferentTextsAreNotTheSame() {
        final ShortAnswer aShortAnswer = buildShortAnswer();
        final ShortAnswer anotherShortAnswer = new ShortAnswer("Em que ano nasceu o filho mais conhecido de Dolores Aveiro?", QuestionScore.valueOf(10F),
                new Answer("1985", "Cristiano Ronaldo nasceu em 1985 na Madeira"));

        assertFalse(aShortAnswer.sameAs(anotherShortAnswer));
    }

    /**
     * Teste para garantir que duas questões do tipo resposta curta não são iguais para uma pontuação diferente.
     */
    @Test
    void ensureTwoShortAnswerWithDifferentScoresAreNotTheSame() {
        final ShortAnswer aShortAnswer = buildShortAnswer();
        final ShortAnswer anotherShortAnswer = new ShortAnswer("Em que ano nasceu Cristiano Ronaldo?", QuestionScore.valueOf(1F),
                new Answer("1985", "Cristiano Ronaldo nasceu em 1985 na Madeira"));

        assertFalse(aShortAnswer.sameAs(anotherShortAnswer));
    }

    /**
     * Teste para garantir que duas questões do tipo resposta curta não são iguais para uma resposta diferente.
     */
    @Test
    void ensureTwoShortAnswerWithDifferentAnswersAreNotTheSame() {
        final ShortAnswer aShortAnswer = buildShortAnswer();
        final ShortAnswer anotherShortAnswer = new ShortAnswer("Em que ano nasceu Cristiano Ronaldo?", QuestionScore.valueOf(10F),
                new Answer("1986", "Cristiano Ronaldo nasceu em 1986 na Madeira"));

        assertFalse(aShortAnswer.sameAs(anotherShortAnswer));
    }

    /**
     * Teste para garantir que uma questão do tipo resposta curta é a mesma para a mesma instância.
     */
    @Test
    void ensureShortAnswerAreTheSameForTheSameInstance() {
        final ShortAnswer aShortAnswer = new ShortAnswer();

        assertTrue(aShortAnswer.sameAs(aShortAnswer));
    }

    /**
     * Teste para garantir que uma questão do tipo resposta curta não é a mesma para diferentes tipos de objetos.
     */
    @Test
    void ensureShortAnswerNotTheSameForDifferenteObjectTypes() {
        final ShortAnswer aShortAnswer = buildShortAnswer();

        assertFalse(aShortAnswer.sameAs(SCORE));
    }
}
