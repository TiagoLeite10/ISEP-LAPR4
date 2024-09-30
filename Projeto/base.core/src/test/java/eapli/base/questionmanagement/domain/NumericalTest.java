package eapli.base.questionmanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe para realizar testes à classe Numerical.
 */
public class NumericalTest {

    private final String TEXT = "Quanto é 1 + 1?";

    private final QuestionScore SCORE = QuestionScore.valueOf(10F);

    private final Answer ANSWER = new Answer("2.0", "A soma de dois 1 é 2");

    private final Float ERROR = 0F;

    /**
     * Método para contruir uma questão do tipo númerico.
     *
     * @return A questão construída.
     */
    private Numerical buildNumerical() {
        return new Numerical(TEXT, SCORE, ANSWER, ERROR);
    }

    /**
     * Teste para garantir que é possível construir uma questão do tipo númerico com o texto, pontuação, resposta e erro.
     */
    @Test
    public void ensureCanBuildNumericalWithTextScoreAnswerError() {
        final Numerical subject = new Numerical(TEXT, SCORE, ANSWER, ERROR);
        assertNotNull(subject);
    }

    /**
     * Teste para garantir que não é possível construir uma questão do tipo númerico com o texto nulo.
     */
    @Test
    public void ensureCannotBuildNumericalWithNullText() {
        assertThrows(IllegalArgumentException.class, () -> new Numerical(null, SCORE, ANSWER, ERROR));
    }

    /**
     * Teste para garantir que não é possível construir uma questão do tipo númerico com a pontuação nula.
     */
    @Test
    public void ensureCannotBuildNumericalWithNullScore() {
        assertThrows(IllegalArgumentException.class, () -> new Numerical(TEXT, null, ANSWER, ERROR));
    }

    /**
     * Teste para garantir que não é possível construir uma questão do tipo númerico com a resposta nula.
     */
    @Test
    public void ensureCannotBuildNumericalWithNullAnswer() {
        assertThrows(IllegalArgumentException.class, () -> new Numerical(TEXT, SCORE, null, ERROR));
    }

    /**
     * Teste para garantir que não é possível construir uma questão do tipo númerico com o erro nulo.
     */
    @Test
    public void ensureCannotBuildNumericalWithNullError() {
        assertThrows(IllegalArgumentException.class, () -> new Numerical(TEXT, SCORE, ANSWER, null));
    }

    /**
     * Teste para garantir que não é possível construir uma questão do tipo númerico com o erro menor que 0.
     */
    @Test
    public void ensureCannotBuildNumericalWithErrorLessThanZero() {
        assertThrows(IllegalArgumentException.class, () -> new Numerical(TEXT, SCORE, ANSWER, -5F));
    }

    /**
     * Teste para garantir que uma questão do tipo númerico tem o tipo de texto esperada.
     */
    @Test
    void ensureNumericalHasExpectedTypeText() {
        final Numerical subject = buildNumerical();

        assertEquals("Numerical\n" + TEXT, subject.typeText());
    }

    /**
     * Teste para garantir que uma questão do tipo númerico não tem o tipo de texto esperada.
     */
    @Test
    void ensureNumericalHasNotExpectedTypeText() {
        final Numerical subject = buildNumerical();

        assertNotEquals("Matching\n" + TEXT, subject.typeText());
    }

    /**
     * Teste para garantir que uma questão do tipo númerico tem a questão e feedback esperado.
     */
    @Test
    void ensureNumericalHasExpectedQuestionFeedback() {
        final Numerical subject = buildNumerical();

        assertEquals("Question: " + TEXT + "\nFeedback: " + ANSWER.feedback() + "\n",
                subject.questionFeedback());
    }

    /**
     * Teste para garantir que uma questão do tipo númerico não tem a questão e feedback esperado.
     */
    @Test
    void ensureNumericalHasNotExpectedQuestionFeedback() {
        final Numerical subject = buildNumerical();

        assertNotEquals("Question: " + TEXT + "\nFeedback: Cristiano Ronaldo nasceu em 1985 na ilha da Madeira\n",
                subject.questionFeedback());
    }

    /**
     * Teste para garantir que uma questão do tipo numérico tem o texto esperada.
     */
    @Test
    void ensureNumericalHasExpectedText() {
        final Numerical subject = buildNumerical();

        assertEquals(TEXT, subject.text());
    }

    /**
     * Teste para garantir que a resposta a uma questão do tipo numérico está correta.
     */
    @Test
    void ensureAnswerNumericalIsCorrect() {
        final Numerical subject = buildNumerical();

        assertTrue(subject.isCorrectAnswer("2.0"));
    }

    /**
     * Teste para garantir que a resposta a uma questão do tipo numérico não está correta.
     */
    @Test
    void ensureAnswerNumericalIsNotCorrect() {
        final Numerical subject = buildNumerical();

        assertFalse(subject.isCorrectAnswer("35.0"));
    }

    /**
     * Teste para garantir que uma questão do tipo numérico tem a pontuação esperada.
     */
    @Test
    void ensureNumericalHasExpectedScore() {
        final Numerical subject = buildNumerical();

        assertEquals(10F, subject.score());
    }

    /**
     * Teste para garantir que é possível juntar uma questão do tipo numérico com outra do mesmo tipo.
     */
    @Test
    void ensureMergeNumericalWithSameObjectType() {
        final Numerical aNumerical = buildNumerical();
        final Numerical anotherNumerical = new Numerical("Quanto é 1 + 2?", QuestionScore.valueOf(10F),
                new Answer("3.0", "A soma de 1 + 2 é 3 foi aprovada em 1980 por mim!"), 0F);

        aNumerical.merge(anotherNumerical);

        assertEquals(anotherNumerical, aNumerical);
    }

    /**
     * Teste para garantir que não é possível juntar uma questão do tipo numérico com um tipo de objeto diferente.
     */
    @Test
    void ensureCannotMergeNumericalWithDifferenteObjectTypes() {
        final Numerical subject = buildNumerical();

        assertThrows(IllegalArgumentException.class, () -> subject.merge(new ShortAnswer(TEXT, SCORE, ANSWER)));
    }

    /**
     * Teste para garantir que duas questões do tipo numérico são iguais para o mesmo texto, pontuação, resposta e erro.
     */
    @Test
    void ensureTwoNumericalWithSameTextsScoresAnswersErrorsAreTheSame() {
        final Numerical aNumerical = buildNumerical();
        final Numerical anotherNumerical = new Numerical("Quanto é 1 + 1?", QuestionScore.valueOf(10F),
                new Answer("2.0", "A soma de dois 1 é 2"), 0F);

        assertTrue(aNumerical.sameAs(anotherNumerical));
    }

    /**
     * Teste para garantir que duas questões do tipo numérico não são iguais para um texto diferente.
     */
    @Test
    void ensureTwoNumericalWithDifferentTextsAreNotTheSame() {
        final Numerical aNumerical = buildNumerical();
        final Numerical anotherNumerical = new Numerical("Quanto é 1 + 1 - 1 + 1?", QuestionScore.valueOf(10F),
                new Answer("2.0", "A soma de dois 1 é 2"), 0F);

        assertFalse(aNumerical.sameAs(anotherNumerical));
    }

    /**
     * Teste para garantir que duas questões do tipo numérico não são iguais para uma pontuação diferente.
     */
    @Test
    void ensureTwoNumericalWithDifferentScoresAreNotTheSame() {
        final Numerical aNumerical = buildNumerical();
        final Numerical anotherNumerical = new Numerical("Quanto é 1 + 1?", QuestionScore.valueOf(1F),
                new Answer("2.0", "A soma de dois 1 é 2"), 0F);

        assertFalse(aNumerical.sameAs(anotherNumerical));
    }

    /**
     * Teste para garantir que duas questões do tipo numérico não são iguais para uma resposta diferente.
     */
    @Test
    void ensureTwoNumericalWithDifferentAnswersAreNotTheSame() {
        final Numerical aNumerical = buildNumerical();
        final Numerical anotherNumerical = new Numerical("Quanto é 1 + 1?", QuestionScore.valueOf(10F),
                new Answer("3.0", "A nova política da matemática determina que 1 + 1 é 3"), 0F);

        assertFalse(aNumerical.sameAs(anotherNumerical));
    }

    /**
     * Teste para garantir que duas questões do tipo numérico não são iguais para um erro diferente.
     */
    @Test
    void ensureTwoNumericalWithDifferentErrorsAreNotTheSame() {
        final Numerical aNumerical = buildNumerical();
        final Numerical anotherNumerical = new Numerical("Quanto é 1 + 1?", QuestionScore.valueOf(10F),
                new Answer("3.0", "A nova política da matemática determina que 1 + 1 é 3"), 1F);

        assertFalse(aNumerical.sameAs(anotherNumerical));
    }

    /**
     * Teste para garantir que uma questão do tipo numérico é a mesma para a mesma instância.
     */
    @Test
    void ensureNumericalAreTheSameForTheSameInstance() {
        final Numerical aNumerical = new Numerical();

        assertTrue(aNumerical.sameAs(aNumerical));
    }

    /**
     * Teste para garantir que uma questão do tipo numérico não é a mesma para diferentes tipos de objetos.
     */
    @Test
    void ensureNumericalNotTheSameForDifferenteObjectTypes() {
        final Numerical aNumerical = buildNumerical();

        assertFalse(aNumerical.sameAs(SCORE));
    }
}
