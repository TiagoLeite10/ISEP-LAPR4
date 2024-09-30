package eapli.base.questionmanagement.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe para realizar testes ao value object QuestionScore.
 */
public class QuestionScoreTest {

    private final QuestionScore subject = QuestionScore.valueOf(5F);

    /**
     * Teste para garantir que a pontuação de uma pergunta não deve ser nula.
     */
    @Test
    void ensureQuestionScoreMustNotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> QuestionScore.valueOf(null));
    }

    /**
     * Teste para garantir que a pontuação de uma pergunta tem de possuir um valor entre 0 e 20.
     */
    @ParameterizedTest
    @ValueSource(floats = {-25F, 25F})
    void ensureQuestionScoreIsBiggerOrEqualsThanZeroAndLessOrEqualThanTwenty(final float score) {
        assertThrows(IllegalArgumentException.class, () -> QuestionScore.valueOf(score));
    }

    /**
     * Teste para garantir que a pontuação de uma pergunta é igual a outra.
     */
    @Test
    void ensureQuestionScoreIsEquals() {
        assertEquals(subject, QuestionScore.valueOf(5F));
    }

    /**
     * Teste para garantir que a pontuação de uma pergunta não é igual a outra.
     */
    @Test
    void ensureQuestionScoreNotEquals() {
        assertNotEquals(subject, QuestionScore.valueOf(1F));
    }
}
