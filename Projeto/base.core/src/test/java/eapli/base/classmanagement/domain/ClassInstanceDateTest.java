package eapli.base.classmanagement.domain;

import eapli.base.BuilderHelper;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Classe para realizar testes ao value object ClassInstanceDate.
 */
public class ClassInstanceDateTest {

    private final ClassInstanceDate subject = ClassInstanceDate.valueOf(BuilderHelper.buildDate(3001, Calendar.JANUARY, 3),
            BuilderHelper.buildDate(3000, Calendar.OCTOBER, 24));


    /**
     * Teste para garantir que uma nova data de uma instância de aula não deve ser nula.
     */
    @Test
    void ensureNewDateTimeMustNotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> ClassInstanceDate.valueOf(null,
                BuilderHelper.buildDate(3000, Calendar.OCTOBER, 24)));
    }

    /**
     * Teste para garantir que uma nova data de uma instância de aula deve ser superior à data atual.
     */
    @Test
    void ensureNewDateTimeIsBiggerThanCurrentDate() {
        assertThrows(IllegalArgumentException.class, () -> ClassInstanceDate.valueOf(BuilderHelper.buildDate(2020, Calendar.DECEMBER, 1),
                BuilderHelper.buildDate(3000, Calendar.OCTOBER, 24)));
    }


    /**
     * Teste para garantir que a data anterior de uma instância de aula não deve ser nula.
     */
    @Test
    void ensurePreviousDateTimeMustNotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> ClassInstanceDate.valueOf(BuilderHelper.buildDate(3001, Calendar.NOVEMBER, 3),
                null));
    }

    /**
     * Teste para garantir que uma data de uma instância tem uma nova data esperada.
     */
    @Test
    void ensureClassInstanceDateHasExpectedNewDateTime() {
        assertEquals(BuilderHelper.buildDate(3001, Calendar.JANUARY, 3), subject.newDateTime());
    }

    /**
     * Teste para garantir que uma nova data de uma instância de aula é igual a outra.
     */
    @Test
    void ensureClassInstanceDateIsSame() {
        assertEquals(0, subject.compareClassInstanceDateCalendar(BuilderHelper.buildDate(3001, Calendar.JANUARY, 3)));
    }

    /**
     * Teste para garantir que uma nova data de uma instância de aula é posterior a outra.
     */
    @Test
    void ensureClassInstanceDateIsAfter() {
        assertEquals(1, subject.compareClassInstanceDateCalendar(BuilderHelper.buildDate(3000, Calendar.SEPTEMBER, 20)));
    }

    /**
     * Teste para garantir que uma nova data de uma instância de aula é anterior a outra.
     */
    @Test
    void ensureClassInstanceDateIsBefore() {
        assertEquals(-1, subject.compareClassInstanceDateCalendar(BuilderHelper.buildDate(3001, Calendar.DECEMBER, 10)));
    }
}
