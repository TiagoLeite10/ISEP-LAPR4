package eapli.base.clientusermanagement.domain;

import eapli.base.BuilderHelper;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe para realizar testes à entidade Teacher.
 */
public class TeacherTest {

    private final SystemUser SYSTEM_USER = UserBuilderHelper.builder().with("nuna", "Password1",
            "Nuna", "Soares", "nuna@gmail.com").withRoles(BaseRoles.TEACHER).build();

    private final TaxPayerNumber TAX_PAYER_NUMBER = TaxPayerNumber.valueOf("110093089");

    private final BirthDate BIRTH_DATE = BirthDate.valueOf(BuilderHelper.buildDate(1997, Calendar.AUGUST, 12));

    private final Acronym ACRONYM = Acronym.valueOf("NUNA");

    /**
     * Método para contruir um professor.
     *
     * @return O professor construído.
     */
    private Teacher buildTeacher() {
        return new Teacher(SYSTEM_USER, TAX_PAYER_NUMBER, BIRTH_DATE, ACRONYM);
    }

    /**
     * Teste para garantir que um professor tem de possuir um utilizador do sistema.
     */
    @Test
    void ensureTeacherHasSystemUser() {
        assertThrows(IllegalArgumentException.class, () -> new Teacher(null, TAX_PAYER_NUMBER, BIRTH_DATE, ACRONYM));
    }

    /**
     * Teste para garantir que um professor tem de possuir um número de contribuinte.
     */
    @Test
    void ensureTeacherHasTaxPayerNumber() {
        assertThrows(IllegalArgumentException.class, () -> new Teacher(SYSTEM_USER, null, BIRTH_DATE, ACRONYM));
    }

    /**
     * Teste para garantir que um professor tem de possuir uma data de nascimento.
     */
    @Test
    void ensureTeacherHasBirthDate() {
        assertThrows(IllegalArgumentException.class, () -> new Teacher(SYSTEM_USER, TAX_PAYER_NUMBER, null, ACRONYM));
    }

    /**
     * Teste para garantir que um professor tem de possuir um acrónimo.
     */
    @Test
    void ensureTeacherHasAcronym() {
        assertThrows(IllegalArgumentException.class, () -> new Teacher(SYSTEM_USER, TAX_PAYER_NUMBER, BIRTH_DATE, null));
    }

    /**
     * Teste para garantir que um professor tem o utilizador do sistema esperado.
     */
    @Test
    void ensureTeacherHasExpectedSystemUser() {
        final Teacher subject = buildTeacher();

        assertEquals(SYSTEM_USER, subject.user());
    }

    /**
     * Teste para garantir que um professor tem o número de contribuinte esperado.
     */
    @Test
    void ensureTeacherHasExpectedTaxPayerNumber() {
        final Teacher subject = buildTeacher();

        assertEquals(TAX_PAYER_NUMBER, subject.taxPayerNumber());
    }

    /**
     * Teste para garantir que dois professores são iguais para o mesmo número de contribuinte.
     */
    @Test
    void ensureTwoTeacherWithSameTaxPayerNumbersAreTheSame() {
        final Teacher aTeacher = buildTeacher();
        final Teacher anotherTeacher = new TeacherBuilder().withSystemUser(SYSTEM_USER).withTaxPayerNumber("110093089").
                withBirthDate(BuilderHelper.buildDate(2003, Calendar.DECEMBER, 12)).withAcronym("QAPL").build();

        assertTrue(aTeacher.sameAs(anotherTeacher));
    }

    /**
     * Teste para garantir que dois professores não são iguais para um número de contribuinte diferente.
     */
    @Test
    void ensureTwoTeacherWithDifferentTaxPayerNumbersAreNotTheSame() {
        final Teacher aTeacher = buildTeacher();
        final Teacher anotherTeacher = new TeacherBuilder().withSystemUser(SYSTEM_USER).withTaxPayerNumber("101960417").
                withBirthDate(BuilderHelper.buildDate(1990, Calendar.JANUARY, 1)).withAcronym("KPKS").build();

        assertFalse(aTeacher.sameAs(anotherTeacher));
    }

    /**
     * Teste para garantir que um professor é o mesmo para a mesma instância.
     */
    @Test
    void ensureTeacherAreTheSameForTheSameInstance() {
        final Teacher aTeacher = new Teacher();

        assertTrue(aTeacher.sameAs(aTeacher));
    }

    /**
     * Teste para garantir que um professor não é o mesmo para diferentes tipos de objetos.
     */
    @Test
    void ensureTeacherNotTheSameForDifferenteObjectTypes() {
        final Teacher aTeacher = buildTeacher();

        assertFalse(aTeacher.sameAs(SYSTEM_USER));
    }

    /**
     * Teste para garantir que um professor é igual a outro para o mesmo acrónimo.
     */
    @Test
    void ensureTeacherEqualsPassesForTheSameAcronym() {
        final Teacher aTeacher = buildTeacher();
        final Teacher anotherTeacher = new TeacherBuilder().withSystemUser(SYSTEM_USER).withTaxPayerNumber("110093089").
                withBirthDate(BuilderHelper.buildDate(1997, Calendar.AUGUST, 12)).withAcronym("NUNA").build();

        assertEquals(aTeacher, anotherTeacher);
    }

    /**
     * Teste para garantir que um professor não é igual a outro para um acrónimo diferente.
     */
    @Test
    void ensureTeacherEqualsFailsForDifferenteAcronym() {
        final Teacher aTeacher = buildTeacher();
        final Teacher anotherTeacher = new TeacherBuilder().withSystemUser(SYSTEM_USER).withTaxPayerNumber("110093089").
                withBirthDate(BuilderHelper.buildDate(1997, Calendar.AUGUST, 12)).withAcronym("NUNO").build();

        assertNotEquals(aTeacher, anotherTeacher);
    }
}
