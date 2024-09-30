package eapli.base.clientusermanagement.domain;

import eapli.base.BuilderHelper;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Classe para realizar testes ao builder TeacherBuilder.
 */
public class TeacherBuilderTest {

    private final SystemUser SYSTEM_USER = UserBuilderHelper.builder().with("joana", "Password1",
            "Joana", "Pereira", "joana@gmail.com").withRoles(BaseRoles.TEACHER).build();

    private final String TAX_PAYER_NUMBER = "125691408";

    private final Calendar BIRTH_DATE = BuilderHelper.buildDate(1960, Calendar.MAY, 13);

    private final String ACRONYM = "JPAZ";

    /**
     * Teste para garantir que é possível construir um professor com o utilizador do sistema, número de contribuinte,
     * data de nascimento e acrónimo.
     */
    @Test
    public void ensureCanBuildTeacherWithSystemUserTaxPayerNumberBirthDateAcronym() {
        final Teacher subject = new TeacherBuilder().with(TAX_PAYER_NUMBER, BIRTH_DATE, ACRONYM).
                withSystemUser(SYSTEM_USER).build();
        assertNotNull(subject);
    }

    /**
     * Teste para garantir que não é possível construir um professor com utilizador do sistema nulo.
     */
    @Test
    public void ensureCannotBuildTeacherWithNullSystemUser() {
        assertThrows(IllegalArgumentException.class, () -> new TeacherBuilder().withSystemUser(null).
                withTaxPayerNumber(TAX_PAYER_NUMBER).withBirthDate(BIRTH_DATE).withAcronym(ACRONYM).build());
    }

    /**
     * Teste para garantir que não é possível construir um professor sem utilizador do sistema.
     */
    @Test
    public void ensureCannotBuildTeacherWithoutSystemUser() {
        assertThrows(IllegalArgumentException.class, () -> new TeacherBuilder().withTaxPayerNumber(TAX_PAYER_NUMBER).
                withBirthDate(BIRTH_DATE).withAcronym(ACRONYM).build());
    }

    /**
     * Teste para garantir que não é possível construir um professor com número de contribuinte nulo.
     */
    @Test
    public void ensureCannotBuildTeacherWithNullTaxPayerNumber() {
        assertThrows(IllegalArgumentException.class, () -> new TeacherBuilder().withSystemUser(SYSTEM_USER).
                withTaxPayerNumber(null).withBirthDate(BIRTH_DATE).withAcronym(ACRONYM).build());
    }

    /**
     * Teste para garantir que não é possível construir um professor sem número de contribuinte.
     */
    @Test
    public void ensureCannotBuildTeacherWithoutTaxPayerNumber() {
        assertThrows(IllegalArgumentException.class, () -> new TeacherBuilder().withSystemUser(SYSTEM_USER).
                withBirthDate(BIRTH_DATE).withAcronym(ACRONYM).build());
    }

    /**
     * Teste para garantir que não é possível construir um professor com data de nascimento nula.
     */
    @Test
    public void ensureCannotBuildTeacherWithNullBirthDate() {
        assertThrows(IllegalArgumentException.class, () -> new TeacherBuilder().withSystemUser(SYSTEM_USER).
                withTaxPayerNumber(TAX_PAYER_NUMBER).withBirthDate(null).withAcronym(ACRONYM).build());
    }

    /**
     * Teste para garantir que não é possível construir um professor sem data de nascimento.
     */
    @Test
    public void ensureCannotBuildTeacherWithoutBirthDate() {
        assertThrows(IllegalArgumentException.class, () -> new TeacherBuilder().withSystemUser(SYSTEM_USER).
                withTaxPayerNumber(TAX_PAYER_NUMBER).withAcronym(ACRONYM).build());
    }

    /**
     * Teste para garantir que não é possível construir um professor com acrónimo nulo.
     */
    @Test
    public void ensureCannotBuildTeacherWithNullAcronym() {
        assertThrows(IllegalArgumentException.class, () -> new TeacherBuilder().withSystemUser(SYSTEM_USER).
                withTaxPayerNumber(TAX_PAYER_NUMBER).withBirthDate(BIRTH_DATE).withAcronym(null).build());
    }

    /**
     * Teste para garantir que não é possível construir um professor sem acrónimo.
     */
    @Test
    public void ensureCannotBuildTeacherWithoutAcronym() {
        assertThrows(IllegalArgumentException.class, () -> new TeacherBuilder().withSystemUser(SYSTEM_USER).
                withTaxPayerNumber(TAX_PAYER_NUMBER).withBirthDate(BIRTH_DATE).build());
    }
}
