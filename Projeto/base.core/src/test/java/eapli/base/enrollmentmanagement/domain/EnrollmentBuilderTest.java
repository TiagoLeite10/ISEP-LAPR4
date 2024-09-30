package eapli.base.enrollmentmanagement.domain;

import eapli.base.BuilderHelper;
import eapli.base.clientusermanagement.domain.Student;
import eapli.base.clientusermanagement.domain.StudentBuilder;
import eapli.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.Calendar;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Classe para realizar testes ao builder EnrollmentBuilder.
 */
public class EnrollmentBuilderTest {

    private final Student STUDENT = buildStudent();

    /**
     * Método para contruir um aluno.
     *
     * @return O aluno construído.
     */
    private Student buildStudent() {
        final SystemUser systemUser = UserBuilderHelper.builder().
                with("joao", "Password1", "João", "Vale", "joao@gmail.com").build();

        return new StudentBuilder().with("178582107", BuilderHelper.buildDate(2005, Calendar.APRIL, 23)).withSystemUser(systemUser).build();
    }

    /**
     * Teste para garantir que é possível construir uma inscrição com o aluno.
     */
    @Test
    public void ensureCanBuildEnrollmentWithStudent() {
        final Enrollment subject = new EnrollmentBuilder().with(STUDENT).build();
        assertNotNull(subject);
    }

    /**
     * Teste para garantir que não é possível construir uma inscrição com aluno nulo.
     */
    @Test
    public void ensureCannotBuildEnrollmentWithNullStudent() {
        assertThrows(IllegalArgumentException.class, () -> new EnrollmentBuilder().withStudent(null).build());
    }

    /**
     * Teste para garantir que não é possível construir uma inscrição sem aluno.
     */
    @Test
    public void ensureCannotBuildEnrollmentWithoutStudent() {
        assertThrows(IllegalArgumentException.class, () -> new EnrollmentBuilder().build());
    }
}
