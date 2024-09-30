package eapli.base.clientusermanagement.domain;

import java.util.Calendar;

import eapli.base.BuilderHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Classe para realizar testes ao value object BirthDate.
 */
public class BirthDateTest {

    private final BirthDate subject = BirthDate.valueOf(BuilderHelper.buildDate(1990, Calendar.JUNE, 10));

    /**
     * Teste para garantir que uma data de nascimento não deve ser nula.
     */
    @Test
    void ensureBirthDateMustNotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> BirthDate.valueOf(null));
    }

    /**
     * Teste para garantir que uma data de nascimento tem de possuir uma data inferior à atual.
     */
    @Test
    void ensureBirthDateIsLessThanCurrentDate() {
        assertThrows(IllegalArgumentException.class, () -> BirthDate.valueOf(BuilderHelper.buildDate(3000, Calendar.AUGUST, 23)));
    }

    /**
     * Teste para garantir que uma data de nascimento é igual a outra.
     */
    @Test
    void ensureBirthDateIsSame() {
        assertEquals(0, subject.compareTo(BirthDate.valueOf(BuilderHelper.buildDate(1990, Calendar.JUNE, 10))));
    }

    /**
     * Teste para garantir que uma data de nascimento é posterior a outra.
     */
    @Test
    void ensureBirthDateIsAfter() {
        assertEquals(1, subject.compareTo(BirthDate.valueOf(BuilderHelper.buildDate(1980, Calendar.MARCH, 1))));
    }

    /**
     * Teste para garantir que uma data de nascimento é anterior a outra.
     */
    @Test
    void ensureBirthDateIsBefore() {
        assertEquals(-1, subject.compareTo(BirthDate.valueOf(BuilderHelper.buildDate(1995, Calendar.APRIL, 20))));
    }
}
