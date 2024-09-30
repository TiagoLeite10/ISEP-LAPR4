package eapli.base.coursemanagement.domain;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe para realizar testes ao value object CourseCode.
 */
public class CourseCodeTest {

    private final String COURSE_CODE = "Course code de teste";

    /**
     * Método para criar um código de curso.
     *
     * @return O código de curso criado.
     */
    private CourseCode createCourseCode() {
        return CourseCode.valueOf(COURSE_CODE);
    }

    /**
     * Teste para garantir que o código do curso é criado com sucesso
     */
    @Test
    public void ensureCourseCodeIsSuccessfullyCreated() {
        Assert.assertNotNull(this.createCourseCode());
    }

    /**
     * Teste para garantir que o código do curso não pode ser nulo
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureCourseCodeCannotBeNull() {
        new CourseCode(null);
    }

    /**
     * Teste para garantir que o código do curso não pode ser vazio
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureCourseCodeCannotBeEmpty() {
        new CourseCode("");
    }

    /**
     * Teste para garantir que o código do curso devolve o valor correto no método toString()
     */
    @Test
    public void ensureCourseCodeHasCorrectToStringValue() {
        Assert.assertEquals(COURSE_CODE, this.createCourseCode().toString());
    }

    /**
     * Teste para garantir que um código de curso é igual a outro.
     */
    @Test
    public void ensureCourseCodeIsSame() {
        int expected = 0;
        int result = this.createCourseCode().compareTo(this.createCourseCode());
        Assert.assertEquals(expected, result);
    }

    /**
     * Teste para garantir que um código de curso é posterior a outro.
     */
    @Test
    public void ensureCourseCodeIsAfter() {
        int result = this.createCourseCode().compareTo(new CourseCode("ABcde teste"));
        Assert.assertTrue(result > 0);
    }

    /**
     * Teste para garantir que um código de curso é anterior a outro.
     */
    @Test
    public void ensureCourseCodeIsBefore() {
        int result = this.createCourseCode().compareTo(new CourseCode("Para testar"));
        Assert.assertTrue(result < 0);
    }

    /**
     * Teste para garantir que um código de curso é igual a outro.
     */
    @Test
    public void ensureCourseCodeIsEquals() {
        Assert.assertTrue(this.createCourseCode().equals(this.createCourseCode()));
    }

    /**
     * Teste para garantir que um código de curso não é igual a outro.
     */
    @Test
    public void ensureCourseCodeIsNotEquals() {
        Assert.assertFalse(this.createCourseCode().equals(new CourseCode("Testeeee")));
    }
}
