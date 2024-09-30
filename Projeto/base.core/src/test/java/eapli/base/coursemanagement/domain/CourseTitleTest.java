package eapli.base.coursemanagement.domain;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe para realizar testes ao value object CourseTitle.
 */
public class CourseTitleTest {

    private final String COURSE_TITLE = "Título do curso";

    /**
     * Método para criar um título para a disciplina.
     *
     * @return O título da disciplina criada.
     */
    private CourseTitle createCourseTitle() {
        return CourseTitle.valueOf(COURSE_TITLE);
    }

    /**
     * Teste para garantir que o título da disciplina é criado com sucesso
     */
    @Test
    public void ensureCourseTitleIsSuccessfullyCreated() {
        Assert.assertNotNull(this.createCourseTitle());
    }

    /**
     * Teste para garantir que o título da disciplina não pode ser nulo
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureCourseTitleCannotBeNull() {
        new CourseTitle(null);
    }

    /**
     * Teste para garantir que o título da disciplina não pode ser vazio
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureCourseTitleCannotBeEmpty() {
        new CourseTitle("");
    }

    /**
     * Teste para garantir que o título da disciplina não pode ter mais do que 50 caracteres
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureCourseTitleCannotHaveMoreThan50Characters() {
        new CourseTitle("Lorem ipsum dolor sit amet, consectetur porttitor..");
    }

    /**
     * Teste para garantir que o título da disciplina pode ter exatamente 50 caracteres
     */
    @Test
    public void ensureCourseTitleCanHaveExactly50Characters() {
        new CourseTitle("Lorem ipsum dolor sit amet, consectetur porttitor.");
    }

    /**
     * Teste para garantir que o título da disciplina devolve o valor correto no método toString()
     */
    @Test
    public void ensureCourseTitleHasCorrectToStringValue() {
        Assert.assertEquals(COURSE_TITLE, this.createCourseTitle().toString());
    }
}
