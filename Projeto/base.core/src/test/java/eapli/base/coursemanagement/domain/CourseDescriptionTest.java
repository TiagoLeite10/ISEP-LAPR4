package eapli.base.coursemanagement.domain;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe para realizar testes ao value object CourseDescription.
 */
public class CourseDescriptionTest {

    private final String COURSE_DESCRIPTION = "Descrição do curso";

    /**
     * Método para criar uma descrição de um curso.
     *
     * @return A descrição do curso.
     */
    private CourseDescription createCourseDescription() {
        return CourseDescription.valueOf(COURSE_DESCRIPTION);
    }

    /**
     * Teste para garantir que uma descrição de uma disciplina é criada com sucesso
     */
    @Test
    public void ensureCourseDescriptionIsSuccessfullyCreated() {
        Assert.assertNotNull(this.createCourseDescription());
    }

    /**
     * Teste para garantir que não é possível criar uma descrição de um disciplina com valor nulo
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureCourseDescriptionCannotBeNull() {
        new CourseDescription(null);
    }

    /**
     * Teste para garantir que uma descrição de uma disciplina não pode ter mais do que 250 caracteres.
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureCourseDescriptionCannotHaveMoreThan250Characters() {
        String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam commodo et ex sed tempus. " +
                "Nulla facilisi. Pellentesque id magna vestibulum, laoreet nisi ac, finibus elit. Nunc tristique " +
                "laoreet commodo. Duis pellentesque sem ligula, quis massa nunc..";
        new CourseDescription(description);
    }

    /**
     * Teste para garantir que uma descrição de uma disciplina pode ter exatamente 250 caracteres.
     */
    @Test
    public void ensureCourseDescriptionCanHaveExactly250Characters() {
        String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam commodo et ex sed tempus. " +
                "Nulla facilisi. Pellentesque id magna vestibulum, laoreet nisi ac, finibus elit. Nunc tristique " +
                "laoreet commodo. Duis pellentesque sem ligula, quis massa nunc.";
        new CourseDescription(description);
    }

    /**
     * Teste para garantir que uma descrição de uma disciplina é igual a outra.
     */
    @Test
    public void ensureCourseDescriptionAreEquals() {
        CourseDescription subject = this.createCourseDescription();
        Assert.assertEquals(this.createCourseDescription(), subject);
    }

    /**
     * Teste para garantir que uma descrição de uma disciplina não é igual a outra.
     */
    @Test
    public void ensureCourseDescriptionAreNotEquals() {
        CourseDescription subject = this.createCourseDescription();
        Assert.assertNotEquals("ABCDE Teste", subject);
    }

    /**
     * Teste para garantir que a descrição da disciplina devolve o valor correto no método toString()
     */
    @Test
    public void ensureCourseDescriptionHasCorrectToStringValue() {
        Assert.assertEquals(COURSE_DESCRIPTION, this.createCourseDescription().toString());
    }
}
