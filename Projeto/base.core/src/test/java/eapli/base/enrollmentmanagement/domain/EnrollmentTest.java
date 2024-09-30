package eapli.base.enrollmentmanagement.domain;

import eapli.base.BuilderHelper;
import eapli.base.clientusermanagement.domain.*;
import eapli.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Classe para realizar testes à classe Enrollment.
 */
public class EnrollmentTest {

    private final Student STUDENT = buildStudent();

    private final Student OTHER_STUDENT = buildOtherStudent();

    private Enrollment buildEnrollment() {
        return new Enrollment(STUDENT);
    }

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
     * Método para contruir outro aluno.
     *
     * @return O outro aluno construído.
     */
    private Student buildOtherStudent() {
        final SystemUser systemUser = UserBuilderHelper.builder().
                with("ze", "Password1", "Zé", "Pedro", "ze@gmail.com").build();

        return new StudentBuilder().with("152367314", BuilderHelper.buildDate(2003, Calendar.FEBRUARY, 13)).withSystemUser(systemUser).build();
    }

    /**
     * Teste para garantir que uma inscrição tem um aluno.
     */
    @Test
    void ensureEnrollmentHasStudent() {
        assertThrows(IllegalArgumentException.class, () -> new Enrollment(null));
    }

    /**
     * Teste para garantir que uma inscrição tem o aluno esperado.
     */
    @Test
    void ensureEnrollmentHasExpectedStudent() {
        Enrollment subject = buildEnrollment();

        assertEquals(STUDENT, subject.student());
    }

    /**
     * Teste para garantir que quando uma inscrição é criada tem um estado 'PENDING'.
     */
    @Test
    void ensureParticipationMeetingCreateHasStatusCreated() {
        final Enrollment subject = buildEnrollment();

        assertTrue(subject.isPending());
    }

    /**
     * Teste para garantir que uma inscrição de um aluno é aprovada.
     */
    @Test
    void ensureEnrollmentStudentIsApproved() {
        Enrollment subject = buildEnrollment();
        subject.approvedEnrollment();

        assertTrue(subject.isApproved());
    }

    /**
     * Teste para garantir que uma inscrição de um aluno é rejeitada.
     */
    @Test
    void ensureEnrollmentStudentIsReject() {
        Enrollment subject = buildEnrollment();
        subject.rejectEnrollment();

        assertTrue(subject.isReject());
    }

    /**
     * Teste para garantir que duas incrições são iguais para o mesmo aluno.
     */
    @Test
    void ensureTwoEnrollmentWithSameStudentsAreTheSame() {
        final Enrollment aEnrollment = buildEnrollment();
        final Enrollment anotherEnrollment = new EnrollmentBuilder().withStudent(STUDENT).build();

        assertTrue(aEnrollment.sameAs(anotherEnrollment));
    }

    /**
     * Teste para garantir que duas inscrições não são iguais para alunos diferentes.
     */
    @Test
    void ensureTwoEnrollmentWithDifferentTaxPayerNumbersAreNotTheSame() {
        final Enrollment aEnrollment = buildEnrollment();
        final Enrollment anotherEnrollment = new EnrollmentBuilder().withStudent(OTHER_STUDENT).build();

        assertFalse(aEnrollment.sameAs(anotherEnrollment));
    }

    /**
     * Teste para garantir que uma inscrição é a mesma para a mesma instância.
     */
    @Test
    void ensureEnrollmentAreTheSameForTheSameInstance() {
        final Enrollment aEnrollment = new Enrollment();

        assertTrue(aEnrollment.sameAs(aEnrollment));
    }

    /**
     * Teste para garantir que uma inscrição não é a mesma para diferentes tipos de objetos.
     */
    @Test
    void ensureEnrollmentNotTheSameForDifferenteObjectTypes() {
        final Enrollment aEnrollment = buildEnrollment();

        assertFalse(aEnrollment.sameAs(TaxPayerNumber.valueOf("178582107")));
    }

    /**
     * Teste para garantir que uma inscrição é igual a outra para o mesmo aluno.
     */
    @Test
    void ensureEnrollmentEqualsPassesForTheSameStudent() {
        final Enrollment aEnrollment = buildEnrollment();
        final Enrollment anotherEnrollment = new EnrollmentBuilder().withStudent(STUDENT).build();

        assertEquals(aEnrollment, anotherEnrollment);
    }

    /**
     * Teste para garantir que uma inscrição não é igual a outra para um aluno diferente.
     */
    @Test
    void ensureEnrollmentEqualsFailsForDifferenteStudent() {
        final Enrollment aEnrollment = buildEnrollment();
        final Enrollment anotherEnrollment = new EnrollmentBuilder().withStudent(OTHER_STUDENT).build();

        assertNotEquals(aEnrollment, anotherEnrollment);
    }
}
