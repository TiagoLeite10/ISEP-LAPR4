package eapli.base.clientusermanagement.domain;

import eapli.base.BuilderHelper;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.Calendar;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Classe para realizar testes ao builder StudentBuilder.
 */
public class StudentBuilderTest {

    private final SystemUser SYSTEM_USER = UserBuilderHelper.builder().with("maria", "Password1",
            "Maria", "Rocha", "maria@gmail.com").withRoles(BaseRoles.STUDENT).build();

    private final String TAX_PAYER_NUMBER = "254588182";

    private final Calendar BIRTH_DATE = BuilderHelper.buildDate(1975, Calendar.JANUARY, 5);


    /**
     * Teste para garantir que é possível construir um aluno com o utilizador do sistema, número de contribuinte
     * e data de nascimento.
     */
    @Test
    public void ensureCanBuildStudentWithSystemUserTaxPayerNumberBirthDate() {
        final Student subject = new StudentBuilder().with(TAX_PAYER_NUMBER, BIRTH_DATE).
                withSystemUser(SYSTEM_USER).build();
        assertNotNull(subject);
    }

    /**
     * Teste para garantir que não é possível construir um aluno com utilizador do sistema nulo.
     */
    @Test
    public void ensureCannotBuildStudentWithNullSystemUser() {
        assertThrows(IllegalArgumentException.class, () -> new StudentBuilder().withSystemUser(null).
                withTaxPayerNumber(TAX_PAYER_NUMBER).withBirthDate(BIRTH_DATE).build());
    }

    /**
     * Teste para garantir que não é possível construir um aluno sem utilizador do sistema.
     */
    @Test
    public void ensureCannotBuildStudentWithoutSystemUser() {
        assertThrows(IllegalArgumentException.class, () -> new StudentBuilder().withTaxPayerNumber(TAX_PAYER_NUMBER).
                withBirthDate(BIRTH_DATE).build());
    }

    /**
     * Teste para garantir que não é possível construir um aluno com número de contribuinte nulo.
     */
    @Test
    public void ensureCannotBuildStudentWithNullTaxPayerNumber() {
        assertThrows(IllegalArgumentException.class, () -> new StudentBuilder().withSystemUser(SYSTEM_USER).
                withTaxPayerNumber(null).withBirthDate(BIRTH_DATE).build());
    }

    /**
     * Teste para garantir que não é possível construir um aluno sem número de contribuinte.
     */
    @Test
    public void ensureCannotBuildStudentWithoutTaxPayerNumber() {
        assertThrows(IllegalArgumentException.class, () -> new StudentBuilder().withSystemUser(SYSTEM_USER).
                withBirthDate(BIRTH_DATE).build());
    }

    /**
     * Teste para garantir que não é possível construir um aluno com data de nascimento nula.
     */
    @Test
    public void ensureCannotBuildStudentWithNullBirthDate() {
        assertThrows(IllegalArgumentException.class, () -> new StudentBuilder().withSystemUser(SYSTEM_USER).
                withTaxPayerNumber(TAX_PAYER_NUMBER).withBirthDate(null).build());
    }

    /**
     * Teste para garantir que não é possível construir um aluno sem data de nascimento.
     */
    @Test
    public void ensureCannotBuildStudentWithoutBirthDate() {
        assertThrows(IllegalArgumentException.class, () -> new StudentBuilder().withSystemUser(SYSTEM_USER).
                withTaxPayerNumber(TAX_PAYER_NUMBER).build());
    }
}
