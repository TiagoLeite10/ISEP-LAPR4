package eapli.base.classmanagement.domain;

import eapli.base.BuilderHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Classe para realizar testes ao value object ClassStartTime.
 */
public class ClassStartTimeTest {

    private final ClassStartTime subject = ClassStartTime.valueOf(BuilderHelper.buildTime(12, 30));

    /**
     * Teste para garantir que uma hora de início não deve ser nula.
     */
    @Test
    void ensureClassStartTimeMustNotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> ClassStartTime.valueOf(null));
    }

    /**
     * Teste para garantir que uma hora de início é igual a outra.
     */
    @Test
    void ensureClassStartTimeIsSame() {
        assertEquals(0, subject.compareTo(ClassStartTime.valueOf(BuilderHelper.buildTime(12, 30))));
    }

    /**
     * Teste para garantir que uma hora de início é posterior a outra.
     */
    @Test
    void ensureClassStartTimeIsAfter() {
        assertEquals(1, subject.compareTo(ClassStartTime.valueOf(BuilderHelper.buildTime(11, 0))));
    }

    /**
     * Teste para garantir que uma hora de início é anterior a outra.
     */
    @Test
    void ensureClassStartTimeIsBefore() {
        assertEquals(-1, subject.compareTo(ClassStartTime.valueOf(BuilderHelper.buildTime(15, 45))));
    }

    /**
     * Teste para garantir que uma hora de início tem as horas esperadas.
     */
    @Test
    void ensureClassStartTimeHasExpectedHours() {
        assertEquals(12, subject.hours());
    }

    /**
     * Teste para garantir que uma hora de início tem os minutos esperados.
     */
    @Test
    void ensureClassStartTimeHasExpectedMinutes() {
        assertEquals(30, subject.minutes());
    }
}
