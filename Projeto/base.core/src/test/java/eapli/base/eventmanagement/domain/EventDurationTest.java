package eapli.base.eventmanagement.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Classe para realizar testes ao value object EventDuration.
 */
public class EventDurationTest {

    private final EventDuration subject = EventDuration.valueOf(90);

    /**
     * Teste para garantir que a duração do evento não deve ser nula.
     */
    @Test
    void ensureEventDurationMustNotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> EventDuration.valueOf(null));
    }

    /**
     * Teste para garantir que a duração do evento tem de possuir valor superior a 0.
     */
    @ParameterizedTest
    @ValueSource(ints = {0, -25})
    void ensureEventDurationIsBiggerThanZero(final int duration) {
        assertThrows(IllegalArgumentException.class, () -> EventDuration.valueOf(duration));
    }

    /**
     * Teste para garantir que uma duração da aula tem a duração esperada.
     */
    @Test
    void ensureEventDurationHasExpectedDuration() {
        assertEquals(90, subject.duration());
    }

    /**
     * Teste para garantir que uma duração do evento é igual a outra.
     */
    @Test
    void ensureEventDurationIsSame() {
        assertEquals(0, subject.compareTo(EventDuration.valueOf(90)));
    }

    /**
     * Teste para garantir que uma duração do evento é posterior a outra.
     */
    @Test
    void ensureEventDurationIsAfter() {
        assertEquals(1, subject.compareTo(EventDuration.valueOf(60)));
    }

    /**
     * Teste para garantir que uma duração do evento é anterior a outra.
     */
    @Test
    void ensureEventDurationIsBefore() {
        assertEquals(-1, subject.compareTo(EventDuration.valueOf(120)));
    }
}
