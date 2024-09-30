package eapli.base.takenexammanagement.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe para realizar testes à classe Grade.
 */
public class GradeTest {

    private final Grade expected = Grade.valueOf(5F);

    /**
     * Teste para garantir que a nota não deve ser nula.
     */
    @Test
    void ensureGradeMustNotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> Grade.valueOf(null));
    }

    /**
     * Teste para garantir que a nota tem de possuir um valor entre 0 e 20.
     */
    @ParameterizedTest
    @ValueSource(floats = {-25F, -0.1F, 20.1F, 25F})
    void ensureGradeIsBiggerOrEqualsThanZeroAndLessOrEqualThanTwenty(final float score) {
        assertThrows(IllegalArgumentException.class, () -> Grade.valueOf(score));
    }

    /**
     * Teste para garantir que a nota é igual a outra.
     */
    @Test
    void ensureGradeIsEquals() {
        assertEquals(expected, Grade.valueOf(5F));
    }

    /**
     * Teste para garantir que a nota não é igual a outra.
     */
    @Test
    void ensureGradeNotEquals() {
        assertNotEquals(expected, Grade.valueOf(1F));
    }

}
