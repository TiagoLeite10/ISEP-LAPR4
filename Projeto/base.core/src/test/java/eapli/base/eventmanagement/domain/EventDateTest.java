package eapli.base.eventmanagement.domain;

import java.util.Calendar;

import eapli.base.BuilderHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Classe para realizar testes ao value object EventDate.
 */
public class EventDateTest {

    private final EventDate subject = EventDate.valueOf(BuilderHelper.buildDateTime(3000, Calendar.SEPTEMBER, 10, 15, 0));

    /**
     * Teste para garantir que a data do evento não deve ser nula.
     */
    @Test
    void ensureEventDateMustNotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> EventDate.valueOf(null));
    }

    /**
     * Teste para garantir que a data do evento tem de possuir uma data superior à atual.
     */
    @Test
    void ensureEventDateIsBiggerThanCurrentDate() {
        assertThrows(IllegalArgumentException.class, () -> EventDate.valueOf(BuilderHelper.buildDateTime(1990, Calendar.JUNE, 10, 21, 30)));
    }

    /**
     * Teste para garantir que uma data do evento é igual a outra data.
     */
    @Test
    void ensureEventDateIsSameForAnotherDate() {
        assertEquals(0, subject.compareEventDateCalendar(BuilderHelper.buildDateTime(3000, Calendar.SEPTEMBER, 10, 15, 0)));
    }

    /**
     * Teste para garantir que uma data do evento é posterior a outra data.
     */
    @Test
    void ensureEventDateIsAfterForAnotherDate() {
        assertEquals(1, subject.compareEventDateCalendar(BuilderHelper.buildDateTime(3000, Calendar.JULY, 20, 23, 30)));
    }

    /**
     * Teste para garantir que uma data do evento é anterior a outra data.
     */
    @Test
    void ensureEventDateIsBeforeForAnotherDate() {
        assertEquals(-1, subject.compareEventDateCalendar(BuilderHelper.buildDateTime(3000, Calendar.DECEMBER, 30, 10, 45)));
    }

    /**
     * Teste para garantir que uma data do evento é igual a outra.
     */
    @Test
    void ensureEventDateIsSame() {
        assertEquals(0, subject.compareTo(EventDate.valueOf(BuilderHelper.buildDateTime(3000, Calendar.SEPTEMBER, 10, 15, 0))));
    }

    /**
     * Teste para garantir que uma data do evento é posterior a outra.
     */
    @Test
    void ensureEventDateIsAfter() {
        assertEquals(1, subject.compareTo(EventDate.valueOf(BuilderHelper.buildDateTime(3000, Calendar.JULY, 20, 23, 30))));
    }

    /**
     * Teste para garantir que uma data do evento é anterior a outra.
     */
    @Test
    void ensureEventDateIsBefore() {
        assertEquals(-1, subject.compareTo(EventDate.valueOf(BuilderHelper.buildDateTime(3000, Calendar.DECEMBER, 30, 10, 45))));
    }
}
