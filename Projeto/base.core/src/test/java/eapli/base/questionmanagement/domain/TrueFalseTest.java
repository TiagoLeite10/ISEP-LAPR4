package eapli.base.questionmanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Classe para realizar testes à classe TrueFalse.
 */
public class TrueFalseTest {

    private final String TEXT = "Elon Musk é português?";

    private final QuestionScore SCORE = QuestionScore.valueOf(2F);

    private final Answer ANSWER = new Answer("false", "Elon Musk é americano");

    /**
     * Método para contruir uma questão do tipo verdadeiro/falso.
     *
     * @return A questão construída.
     */
    private TrueFalse buildTrueFalse() {
        return new TrueFalse(TEXT, SCORE, ANSWER);
    }

    /**
     * Teste para garantir que é possível construir uma questão do tipo verdadeiro/falso com o texto, pontuação e resposta.
     */
    @Test
    public void ensureCanBuildTrueFalseWithTextScoreAnswer() {
        final TrueFalse subject = new TrueFalse(TEXT, SCORE, ANSWER);
        assertNotNull(subject);
    }

    /**
     * Teste para garantir que não é possível construir uma questão do tipo verdadeiro/falso com o texto nulo.
     */
    @Test
    public void ensureCannotBuildTrueFalseWithNullText() {
        assertThrows(IllegalArgumentException.class, () -> new TrueFalse(null, SCORE, ANSWER));
    }

    /**
     * Teste para garantir que não é possível construir uma questão do tipo verdadeiro/falso com a pontuação nula.
     */
    @Test
    public void ensureCannotBuildTrueFalseWithNullScore() {
        assertThrows(IllegalArgumentException.class, () -> new TrueFalse(TEXT, null, ANSWER));
    }

    /**
     * Teste para garantir que não é possível construir uma questão do tipo verdadeiro/falso com a resposta nula.
     */
    @Test
    public void ensureCannotBuildTrueFalseWithNullAnswer() {
        assertThrows(IllegalArgumentException.class, () -> new TrueFalse(TEXT, SCORE, null));
    }

    /**
     * Teste para garantir que uma questão do tipo verdadeiro/falso tem o tipo de texto esperada.
     */
    @Test
    void ensureTrueFalseHasExpectedTypeText() {
        final TrueFalse subject = buildTrueFalse();

        assertEquals("True/False\n" + TEXT, subject.typeText());
    }

    /**
     * Teste para garantir que uma questão do tipo verdadeiro/falso não tem o tipo de texto esperada.
     */
    @Test
    void ensureTrueFalseHasNotExpectedTypeText() {
        final TrueFalse subject = buildTrueFalse();

        assertNotEquals("Matching\n" + TEXT, subject.typeText());
    }

    /**
     * Teste para garantir que uma questão do tipo verdadeiro/falso tem a questão e feedback esperado.
     */
    @Test
    void ensureTrueFalseHasExpectedQuestionFeedback() {
        final TrueFalse subject = buildTrueFalse();

        assertEquals("Question: " + TEXT + "\nFeedback: " + ANSWER.feedback() + "\n",
                subject.questionFeedback());
    }

    /**
     * Teste para garantir que uma questão do tipo verdadeiro/falso não tem a questão e feedback esperado.
     */
    @Test
    void ensureTrueFalseHasNotExpectedQuestionFeedback() {
        final TrueFalse subject = buildTrueFalse();

        assertNotEquals("Question: " + TEXT + "\nFeedback: Elon Musk é americano com ascendência indiana\n",
                subject.questionFeedback());
    }

    /**
     * Teste para garantir que uma questão do tipo verdadeiro/falso tem o texto esperada.
     */
    @Test
    void ensureTrueFalseHasExpectedText() {
        final TrueFalse subject = buildTrueFalse();

        assertEquals(TEXT, subject.text());
    }

    /**
     * Teste para garantir que a resposta a uma questão do tipo verdadeiro/falso está correta.
     */
    @Test
    void ensureAnswerTrueFalseIsCorrect() {
        final TrueFalse subject = buildTrueFalse();

        assertTrue(subject.isCorrectAnswer("false"));
    }

    /**
     * Teste para garantir que a resposta a uma questão do tipo verdadeiro/falso não está correta.
     */
    @Test
    void ensureAnswerTrueFalseIsNotCorrect() {
        final TrueFalse subject = buildTrueFalse();

        assertFalse(subject.isCorrectAnswer("true"));
    }

    /**
     * Teste para garantir que uma questão do tipo verdadeiro/falso tem a pontuação esperada.
     */
    @Test
    void ensureTrueFalseHasExpectedScore() {
        final TrueFalse subject = buildTrueFalse();

        assertEquals(2F, subject.score());
    }

    /**
     * Teste para garantir que é possível juntar uma questão do tipo verdadeiro/falso com outra do mesmo tipo.
     */
    @Test
    void ensureMergeTrueFalseWithSameObjectType() {
        final TrueFalse aTrueFalse = buildTrueFalse();
        final TrueFalse anotherTrueFalse = new TrueFalse("Elon Musk é espanhol?", QuestionScore.valueOf(6F),
                new Answer("false", "Elon Musk é americano"));

        aTrueFalse.merge(anotherTrueFalse);

        assertEquals(anotherTrueFalse, aTrueFalse);
    }

    /**
     * Teste para garantir que não é possível juntar uma questão do tipo verdadeiro/falso com um tipo de objeto diferente.
     */
    @Test
    void ensureCannotMergeTrueFalseWithDifferenteObjectTypes() {
        final TrueFalse subject = buildTrueFalse();

        assertThrows(IllegalArgumentException.class, () -> subject.merge(new ShortAnswer(TEXT, SCORE, ANSWER)));
    }

    /**
     * Teste para garantir que duas questões do tipo verdadeiro/falso são iguais para o mesmo texto, pontuação e resposta.
     */
    @Test
    void ensureTwoTrueFalseWithSameTextsScoresAnswersAreTheSame() {
        final TrueFalse aTrueFalse = buildTrueFalse();
        final TrueFalse anotherTrueFalse = new TrueFalse("Elon Musk é português?", QuestionScore.valueOf(2F),
                new Answer("false", "Elon Musk é americano"));

        assertTrue(aTrueFalse.sameAs(anotherTrueFalse));
    }

    /**
     * Teste para garantir que duas questões do tipo verdadeiro/falso não são iguais para um texto diferente.
     */
    @Test
    void ensureTwoTrueFalseWithDifferentTextsAreNotTheSame() {
        final TrueFalse aTrueFalse = buildTrueFalse();
        final TrueFalse anotherTrueFalse = new TrueFalse("Elon Musk é espanhol?", QuestionScore.valueOf(2F),
                new Answer("false", "Elon Musk é americano"));

        assertFalse(aTrueFalse.sameAs(anotherTrueFalse));
    }

    /**
     * Teste para garantir que duas questões do tipo verdadeiro/falso não são iguais para uma pontuação diferente.
     */
    @Test
    void ensureTwoTrueFalseWithDifferentScoresAreNotTheSame() {
        final TrueFalse aTrueFalse = buildTrueFalse();
        final TrueFalse anotherTrueFalse = new TrueFalse("Elon Musk é português?", QuestionScore.valueOf(3F),
                new Answer("false", "Elon Musk é americano"));

        assertFalse(aTrueFalse.sameAs(anotherTrueFalse));
    }

    /**
     * Teste para garantir que duas questões do tipo verdadeiro/falso não são iguais para uma resposta diferente.
     */
    @Test
    void ensureTwoTrueFalseWithDifferentAnswersAreNotTheSame() {
        final TrueFalse aTrueFalse = buildTrueFalse();
        final TrueFalse anotherTrueFalse = new TrueFalse("Elon Musk é português?", QuestionScore.valueOf(2F),
                new Answer("true", "Elon Musk é naturalizou-se português recentemente"));

        assertFalse(aTrueFalse.sameAs(anotherTrueFalse));
    }

    /**
     * Teste para garantir que uma questão do tipo verdadeiro/falso é a mesma para a mesma instância.
     */
    @Test
    void ensureTrueFalseAreTheSameForTheSameInstance() {
        final TrueFalse aTrueFalse = new TrueFalse();

        assertTrue(aTrueFalse.sameAs(aTrueFalse));
    }

    /**
     * Teste para garantir que uma questão do tipo verdadeiro/falso não é a mesma para diferentes tipos de objetos.
     */
    @Test
    void ensureTrueFalseNotTheSameForDifferenteObjectTypes() {
        final TrueFalse aTrueFalse = buildTrueFalse();

        assertFalse(aTrueFalse.sameAs(SCORE));
    }
}
