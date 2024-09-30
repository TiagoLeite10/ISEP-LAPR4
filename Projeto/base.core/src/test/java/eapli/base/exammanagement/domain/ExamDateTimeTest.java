package eapli.base.exammanagement.domain;

import eapli.base.BuilderHelper;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe para realizar testes ao value object ExamDateTime.
 */
public class ExamDateTimeTest {

    private final ExamDateTime subject = ExamDateTime.valueOf(BuilderHelper.buildDateTime(3000, Calendar.AUGUST, 10, 10, 0),
            BuilderHelper.buildDateTime(3000, Calendar.AUGUST, 15, 23, 59));

    /**
     * Teste para garantir que a data de abertura de um exame não deve ser nula.
     */
    @Test
    void ensureExamOpeningDateTimeMustNotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> ExamDateTime.valueOf(null,
                BuilderHelper.buildDateTime(3000, Calendar.AUGUST, 15, 23, 59)));
    }

    /**
     * Teste para garantir que a data de fecho de um exame não deve ser nula.
     */
    @Test
    void ensureExamClosingDateTimeMustNotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> ExamDateTime.valueOf(BuilderHelper.buildDateTime(3000, Calendar.AUGUST, 10, 10, 0),
                null));
    }

    /**
     * Teste para garantir que a data de abertura de um exame deve ser superior à data atual.
     */
    @Test
    void ensureExamOpeningDateTimeIsBiggerThanCurrentDate() {
        assertThrows(IllegalArgumentException.class, () -> ExamDateTime.valueOf(BuilderHelper.buildDateTime(2023, Calendar.MAY, 15, 23, 59),
                BuilderHelper.buildDateTime(2023, Calendar.AUGUST, 15, 23, 59)));
    }

    /**
     * Teste para garantir que a data de abertura de um exame é posterior a outra.
     */
    @Test
    void ensureExamOpeningDateTimeIsAfter() {
        assertTrue(subject.openingDateIsGreaterThan(BuilderHelper.buildDateTime(3000, Calendar.AUGUST, 1, 23, 59)));
    }

    /**
     * Teste para garantir que a data de abertura de um exame é anterior a outra.
     */
    @Test
    void ensureExamOpeningDateTimeIsBefore() {
        assertFalse(subject.openingDateIsGreaterThan(BuilderHelper.buildDateTime(3000, Calendar.AUGUST, 15, 23, 59)));
    }

    /**
     * Teste para garantir que a data de fecho de um exame é posterior a outra.
     */
    @Test
    void ensureExamClosingDateTimeIsAfter() {
        assertTrue(subject.closingDateIsGreaterThan(BuilderHelper.buildDateTime(3000, Calendar.AUGUST, 10, 23, 59)));
    }

    /**
     * Teste para garantir que a data de fecho de um exame é anterior a outra.
     */
    @Test
    void ensureExamClosingDateTimeIsBefore() {
        assertFalse(subject.closingDateIsGreaterThan(BuilderHelper.buildDateTime(3000, Calendar.AUGUST, 20, 23, 59)));
    }

    /**
     * Teste para garantir que a data de abertura de um exame é igual a outra.
     */
    @Test
    void ensureSectionDescriptionIsEquals() {
        assertEquals(subject, ExamDateTime.valueOf(BuilderHelper.buildDateTime(3000, Calendar.AUGUST, 10, 10, 0),
                BuilderHelper.buildDateTime(3000, Calendar.AUGUST, 15, 23, 59)));
    }

    /**
     * Teste para garantir que a data de fecho de um exame não é igual a outra.
     */
    @Test
    void ensureSectionDescriptionNotEquals() {
        assertNotEquals(subject, ExamDateTime.valueOf(BuilderHelper.buildDateTime(3000, Calendar.AUGUST, 10, 10, 0),
                BuilderHelper.buildDateTime(3000, Calendar.AUGUST, 15, 20, 0)));
    }
}
