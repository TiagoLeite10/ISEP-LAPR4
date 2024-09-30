package eapli.base.classmanagement.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Classe para realizar testes ao value object ClassDuration.
 */
public class ClassDurationTest {

    private final ClassDuration subject = ClassDuration.valueOf(90);

    /**
     * Teste para garantir que a duração da aula não deve ser nula.
     */
    @Test
    void ensureClassDurationMustNotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> ClassDuration.valueOf(null));
    }

    /**
     * Teste para garantir que a duração da aula tem de possuir valor superior a 0.
     */
    @ParameterizedTest
    @ValueSource(ints = {0, -25})
    void ensureClassDurationIsBiggerThanZero(final int duration) {
        assertThrows(IllegalArgumentException.class, () -> ClassDuration.valueOf(duration));
    }

    /**
     * Teste para garantir que uma duração da aula tem a duração esperada.
     */
    @Test
    void ensureClassDurationHasExpectedDuration() {
        assertEquals(90, subject.duration());
    }

    /**
     * Teste para garantir que uma duração da aula é igual a outra.
     */
    @Test
    void ensureClassDurationIsSame() {
        assertEquals(0, subject.compareTo(ClassDuration.valueOf(90)));
    }

    /**
     * Teste para garantir que uma duração da aula é posterior a outra.
     */
    @Test
    void ensureClassDurationIsAfter() {
        assertEquals(1, subject.compareTo(ClassDuration.valueOf(60)));
    }

    /**
     * Teste para garantir que uma duração da aula é anterior a outra.
     */
    @Test
    void ensureClassDurationIsBefore() {
        assertEquals(-1, subject.compareTo(ClassDuration.valueOf(120)));
    }
}
