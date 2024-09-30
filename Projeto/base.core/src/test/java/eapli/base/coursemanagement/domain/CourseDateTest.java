package eapli.base.coursemanagement.domain;

import eapli.base.BuilderHelper;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;

/**
 * Classe para realizar testes ao value object CourseDate.
 */
public class CourseDateTest {

    private final Calendar COURSE_START_DATE = BuilderHelper.buildDate(9999, 5, 5);
    private final Calendar COURSE_END_DATE = BuilderHelper.buildDate(9999, 5, 20);

    /**
     * Método para criar uma data de duração da disciplina.
     *
     * @return A data de duração do curso.
     */
    private CourseDate createCourseDate() {
        return CourseDate.valueOf(COURSE_START_DATE, COURSE_END_DATE);
    }

    /**
     * Teste para garantir que a data da disciplina é criada com sucesso
     */
    @Test
    public void ensureCourseDateIsSuccessfullyCreated() {
        Assert.assertNotNull(this.createCourseDate());
    }

    /**
     * Valida que a data de fim não pode ser antes da data de início
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureStartDateIsBeforeEndDate() {
        new CourseDate(COURSE_END_DATE, COURSE_START_DATE);
    }

    /**
     * Verifica que a data de início não pode ser antes de hoje
     */
    @Test(expected = IllegalArgumentException.class)
     public void ensureStartDateCannotBeBeforeToday() {
        new CourseDate(BuilderHelper.buildDate(2023, 5, 5), COURSE_END_DATE);
    }

    /**
     * Teste para garantir que a data de início é clonada com sucesso
     */
    @Test
    public void ensureStartDateIsSuccessfullyCloned() {
        CourseDate subject = this.createCourseDate();
        Calendar subjectStartDate = subject.cloneStartDate();
        System.out.println(subjectStartDate.toString());
        System.out.println(COURSE_START_DATE.toString());
        Assert.assertEquals(0, subjectStartDate.compareTo(COURSE_START_DATE));
    }

    /**
     * Teste para garantir que a data de início que é clonada não é o mesmo objeto que pertence à classe
     */
    @Test
    public void ensureStartDateIsReallyCloned() {
        CourseDate subject = this.createCourseDate();
        Calendar subjectStartDate = subject.cloneStartDate();
        Assert.assertNotSame(COURSE_START_DATE, subjectStartDate);
    }

    /**
     * Teste para garantir que os dados são impressos com sucesso
     */
    @Test
    public void ensureCourseDateIsRepresentedRigth() {
        CourseDate subject = this.createCourseDate();
        Assert.assertEquals("Start date: " + COURSE_START_DATE + "\nEnd date: " + COURSE_END_DATE, subject.toString());
    }

}
