package eapli.base.coursemanagement.domain;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Classe para realizar testes ao builder CourseBuilder.
 */
public class CourseBuilderTest {

    private final String COURSE_CODE = "Curso de teste";
    private final String COURSE_TITLE = "ABCDE";
    private final String COURSE_DESCRIPTION = "Descrição do curso";
    private final int COURSE_MINIMUM_ENROLMENTS = 20;
    private final int COURSE_MAXIMUM_ENROLMENTS = 40;

    /**
     * Teste para garantir que é possível construir um curso através do método with().
     */
    @Test
    public void ensureCanBuildCourse() {
        final Course subject = new CourseBuilder().with("Course Teste", "ABCDE", "Descrição do curso", 20, 40).build();
        assertNotNull(subject);
    }

    /**
     * Teste para garantir que é possível construir um curso.
     */
    @Test
    public void ensureCanBuildCourseWithAllRequiredData() {
        final Course subject = new CourseBuilder().withCourseCode(COURSE_CODE).withCourseDescription(COURSE_DESCRIPTION)
                .withCourseTitle(COURSE_TITLE)
                .withCourseEnrolmentLimits(COURSE_MINIMUM_ENROLMENTS, COURSE_MAXIMUM_ENROLMENTS).build();
        assertNotNull(subject);
    }

    /**
     * Teste para garantir que não é possível construir um curso com o código de curso nulo.
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureCannotBuildCourseWithNullCourseCode() {
        new CourseBuilder().withCourseCode(null).withCourseDescription(COURSE_DESCRIPTION)
                .withCourseTitle(COURSE_TITLE)
                .withCourseEnrolmentLimits(COURSE_MINIMUM_ENROLMENTS, COURSE_MAXIMUM_ENROLMENTS).build();
    }

    /**
     * Teste para garantir que não é possível construir um curso com a descrição do curso nulo.
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureCannotBuildCourseWithNullCourseDescription() {
        new CourseBuilder().withCourseCode(COURSE_CODE).withCourseDescription(null)
                .withCourseTitle(COURSE_TITLE)
                .withCourseEnrolmentLimits(COURSE_MINIMUM_ENROLMENTS, COURSE_MAXIMUM_ENROLMENTS).build();
    }

    /**
     * Teste para garantir que não é possível construir um curso com o título de curso nulo.
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureCannotBuildCourseWithNullCourseTitle() {
        new CourseBuilder().withCourseCode(COURSE_CODE).withCourseDescription(COURSE_DESCRIPTION)
                .withCourseTitle(null)
                .withCourseEnrolmentLimits(COURSE_MINIMUM_ENROLMENTS, COURSE_MAXIMUM_ENROLMENTS).build();
    }

    /**
     * Teste para garantir que não é possível construir um curso sem o código de curso.
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureCannotBuildCourseWithoutCourseCode() {
        new CourseBuilder().withCourseDescription(COURSE_DESCRIPTION)
                .withCourseTitle(COURSE_TITLE)
                .withCourseEnrolmentLimits(COURSE_MINIMUM_ENROLMENTS, COURSE_MAXIMUM_ENROLMENTS).build();
    }

    /**
     * Teste para garantir que não é possível construir um curso sem a descrição do curso.
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureCannotBuildCourseWithoutCourseDescription() {
        new CourseBuilder().withCourseCode(COURSE_CODE).withCourseTitle(COURSE_TITLE)
                .withCourseEnrolmentLimits(COURSE_MINIMUM_ENROLMENTS, COURSE_MAXIMUM_ENROLMENTS).build();
    }

    /**
     * Teste para garantir que não é possível construir um curso sem o título de curso.
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureCannotBuildCourseWithoutCourseTitle() {
        new CourseBuilder().withCourseCode(COURSE_CODE).withCourseDescription(COURSE_DESCRIPTION)
                .withCourseEnrolmentLimits(COURSE_MINIMUM_ENROLMENTS, COURSE_MAXIMUM_ENROLMENTS).build();
    }

    /**
     * Teste para garantir que não é possível construir um curso sem os limites de inscrições.
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureCannotBuildCourseWithoutEnrolmentLimits() {
        new CourseBuilder().withCourseCode(COURSE_CODE).withCourseDescription(COURSE_DESCRIPTION)
                .withCourseTitle(COURSE_TITLE).build();
    }
}
