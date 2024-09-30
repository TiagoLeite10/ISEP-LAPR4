package eapli.base.coursemanagement.domain;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe para realizar testes ao value object CourseEnrolmentLimits.
 */
public class CourseEnrolmentLimitsTest {

    private final int MINIMUM_ENROLMENTS = 20;

    private final int MAXIMUM_ENROLMENTS = 40;

    /**
     * Método para criar os limites de inscrições de uma disciplina.
     *
     * @return Os limites de inscrições de uma disciplina.
     */
    private CourseEnrolmentLimits createCourseEnrolmentLimits() {
        return CourseEnrolmentLimits.valueOf(MINIMUM_ENROLMENTS, MAXIMUM_ENROLMENTS);
    }

    /**
     * Teste para garantir que os limites de inscrição do curso é criado com sucesso
     */
    @Test
    public void ensureCourseEnrolmentLimitsIsSuccessfullyCreated() {
        Assert.assertNotNull(this.createCourseEnrolmentLimits());
    }

    /**
     * Teste para garantir que o limite inferior deve sar maior do que 0
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureMinimumLimitShouldBeHigherThan0() {
        new CourseEnrolmentLimits(0, MAXIMUM_ENROLMENTS);
    }

    /**
     * Teste para garantir que o limite superior deve sar maior do que 0
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureMaximumLimitShouldBeHigherThan0() {
        new CourseEnrolmentLimits(MINIMUM_ENROLMENTS, 0);
    }

    /**
     * Teste para garantir que o limite inferior não pode ser maior do que o limite superior
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureMinimumLimitsCannotBeHigherThanMaximumLimit() {
        new CourseEnrolmentLimits(MAXIMUM_ENROLMENTS + 1, MAXIMUM_ENROLMENTS);
    }

    /**
     * Teste para garantir que o limite inferior pode ser igual ao limite superior
     */
    @Test
    public void ensureMinimumLimitsCanBeTheSameAsMaximumLimit() {
        new CourseEnrolmentLimits(MAXIMUM_ENROLMENTS, MAXIMUM_ENROLMENTS);
    }

    /**
     * Teste para validar que o limite mínimo é devolvido corretamente
     */
    @Test
    public void ensureMinimumLimitIsCorrectlyGiven() {
        CourseEnrolmentLimits subject = this.createCourseEnrolmentLimits();
        Assert.assertEquals(MINIMUM_ENROLMENTS, subject.minimum());
    }

    /**
     * Teste para validar que o limite máximo é devolvido corretamente
     */
    @Test
    public void ensureMaximumLimitIsCorrectlyGiven() {
        CourseEnrolmentLimits subject = this.createCourseEnrolmentLimits();
        Assert.assertEquals(MAXIMUM_ENROLMENTS, subject.maximum());
    }

    /**
     * Teste para validar que o limite de inscrições é realmente ultrapassado
     */
    @Test
    public void ensureMaximumEnrolmentsIsCorrectlyVerifiedWhenBigger() {
        CourseEnrolmentLimits subject = this.createCourseEnrolmentLimits();
        Assert.assertTrue(subject.exceedsMaximum(MAXIMUM_ENROLMENTS + 1));
    }

    /**
     * Teste para validar que o limite de inscrições não é ultrapassado quando é o mesmo
     */
    @Test
    public void ensureMaximumEnrolmentsIsCorrectlyVerifiedWhenSame() {
        CourseEnrolmentLimits subject = this.createCourseEnrolmentLimits();
        Assert.assertFalse(subject.exceedsMaximum(MAXIMUM_ENROLMENTS));
    }

    /**
     * Teste para verificar se o número de inscrições é valido quando não existe o número mínimo requerido
     */
    @Test
    public void ensureValidEnrollmentsNumberIsCorrectlyValidatedWhenLessThanMinimum() {
        CourseEnrolmentLimits subject = this.createCourseEnrolmentLimits();
        Assert.assertFalse(subject.validEnrollmentsNumber(MINIMUM_ENROLMENTS - 1));
    }

    /**
     * Teste para verificar se o número de inscrições é valido quando existe o número mínimo exato requerido
     */
    @Test
    public void ensureValidEnrollmentsNumberIsCorrectlyValidatedWhenSameThanMinimum() {
        CourseEnrolmentLimits subject = this.createCourseEnrolmentLimits();
        Assert.assertTrue(subject.validEnrollmentsNumber(MINIMUM_ENROLMENTS));
    }

    /**
     * Teste para verificar se o número de inscrições é valido quando ultrapassa o número máximo requerido
     */
    @Test
    public void ensureValidEnrollmentsNumberIsCorrectlyValidatedWhenGreaterThanMaximum() {
        CourseEnrolmentLimits subject = this.createCourseEnrolmentLimits();
        Assert.assertFalse(subject.validEnrollmentsNumber(MAXIMUM_ENROLMENTS + 1));
    }

    /**
     * Teste para verificar se o número de inscrições é valido quando existe o número máximo exato requerido
     */
    @Test
    public void ensureValidEnrollmentsNumberIsCorrectlyValidatedWhenSameThanMaximum() {
        CourseEnrolmentLimits subject = this.createCourseEnrolmentLimits();
        Assert.assertTrue(subject.validEnrollmentsNumber(MAXIMUM_ENROLMENTS));
    }

    /**
     * Teste para garantir que o limite de inscrições numa disciplina é igual a outro.
     */
    @Test
    public void ensureCourseCodeIsEquals() {
        Assert.assertTrue(this.createCourseEnrolmentLimits().equals(this.createCourseEnrolmentLimits()));
    }

    /**
     * Teste para garantir que o limite de inscrições numa disciplina não é igual a outro.
     */
    @Test
    public void ensureCourseCodeIsNotEquals() {
        Assert.assertFalse(this.createCourseEnrolmentLimits().equals(new CourseEnrolmentLimits(5, 10)));
    }

    /**
     * Teste para garantir que os limites de inscrição numa disciplina devolve o valor correto no método toString()
     */
    @Test
    public void ensureCourseEnrolmentsLimitsHasCorrectToStringValue() {
        Assert.assertEquals("Minimum enrollment: " + MINIMUM_ENROLMENTS + "\nMaximum enrollment: " + MAXIMUM_ENROLMENTS,
                this.createCourseEnrolmentLimits().toString());
    }
}
