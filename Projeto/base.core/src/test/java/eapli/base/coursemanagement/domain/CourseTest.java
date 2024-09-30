package eapli.base.coursemanagement.domain;

import eapli.base.BuilderHelper;
import eapli.base.classmanagement.domain.*;
import eapli.base.clientusermanagement.domain.Student;
import eapli.base.clientusermanagement.domain.StudentBuilder;
import eapli.base.clientusermanagement.domain.Teacher;
import eapli.base.clientusermanagement.domain.TeacherBuilder;
import eapli.base.enrollmentmanagement.domain.Enrollment;
import eapli.base.eventmanagement.domain.EventDate;
import eapli.base.eventmanagement.domain.EventDuration;
import eapli.base.eventmanagement.domain.ExtraClass;
import eapli.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe para realizar testes à classe Course.
 */
public class CourseTest {

    private final CourseCode COURSE_CODE = CourseCode.valueOf("Course code de teste");
    private final CourseEnrolmentLimits COURSE_ENROLMENTS_LIMITS = CourseEnrolmentLimits.valueOf(20, 40);
    private final CourseTitle COURSE_TITLE = CourseTitle.valueOf("Título do curso");
    private final CourseDescription COURSE_DESCRIPTION = CourseDescription.valueOf("Descrição do curso");

    /**
     * Método para contruir um curso.
     *
     * @return O curso construído.
     */
    private Course buildCourse() {
        return new Course(COURSE_CODE, COURSE_ENROLMENTS_LIMITS, COURSE_TITLE, COURSE_DESCRIPTION);
    }

    /**
     * Método para construir uma inscrição
     *
     * @return A inscrição construída.
     */
    private Enrollment buildEnrollment() {
        return new Enrollment(buildStudent());
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
     * Método para contruir uma aula recorrente.
     *
     * @return A aula extra construída.
     */
    private RecurringClass buildRecurringClass() {
        final ClassTitle TITLE = ClassTitle.valueOf("Aula de meteorologia");
        final ClassStartTime START_TIME = ClassStartTime.valueOf(BuilderHelper.buildTime(15, 0));
        final ClassDuration DURATION = ClassDuration.valueOf(90);
        final ClassDayOfTheWeek DAY_OF_THE_WEEK = ClassDayOfTheWeek.WEDNESDAY;
        final Teacher TEACHER = buildTeacher("PSLD");
        return new RecurringClass(TITLE, START_TIME, DURATION, DAY_OF_THE_WEEK, TEACHER);
    }

    /**
     * Método para contruir uma aula extra.
     *
     * @return A aula extra construída.
     */
    private ExtraClass buildExtraClass() {
        final EventDate EVENT_DATE = EventDate.valueOf(BuilderHelper.buildDateTime(3000, Calendar.AUGUST, 23, 15, 0));
        final EventDuration EVENT_DURATION = EventDuration.valueOf(30);
        final Teacher TEACHER = buildTeacher("ASXZ");
        final Set<Student> STUDENTS = buildStudents();
        return new ExtraClass(EVENT_DATE, EVENT_DURATION, TEACHER, STUDENTS);
    }

    /**
     * Método para contruir um Set de alunos.
     *
     * @return O Set de alunos construído.
     */
    private Set<Student> buildStudents() {
        Set<Student> students = new HashSet<>();

        final SystemUser systemUser1 = UserBuilderHelper.builder().with("joao", "Password1", "João",
                "Vale", "joao@gmail.com").build();

        final Student student1 = new StudentBuilder().with("193927560", BuilderHelper.buildDate(2000, Calendar.DECEMBER, 19)).
                withSystemUser(systemUser1).build();

        final SystemUser systemUser2 = UserBuilderHelper.builder().with("rui", "Password1", "Rui",
                "Pereira", "rui@gmail.com").build();

        final Student student2 = new StudentBuilder().with("137017081", BuilderHelper.buildDate(1995, Calendar.APRIL, 1)).
                withSystemUser(systemUser2).build();

        students.add(student1);
        students.add(student2);

        return students;
    }

    /**
     * Método para contruir um professor.
     *
     * @return O professor construído.
     */
    public Teacher buildTeacher(final String acronym) {
        final SystemUser systemUser = UserBuilderHelper.builder().with("maria", "Password1", "Maria",
                "Rocha", "maria@gmail.com").build();

        return new TeacherBuilder().with("142544779", BuilderHelper.buildDate(1985, Calendar.MAY, 11), acronym).
                withSystemUser(systemUser).build();
    }

    /**
     * Teste para garantir que uma disciplina não pode ter código nulo.
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureCourseCodeCannotBeNull() {
        new Course(null, COURSE_ENROLMENTS_LIMITS, COURSE_TITLE, COURSE_DESCRIPTION);
    }

    /**
     * Teste para garantir que uma disciplina não pode ter os limites de inscrições nulo.
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureCourseEnrollmentsLimitsCannotBeNull() {
        new Course(COURSE_CODE, null, COURSE_TITLE, COURSE_DESCRIPTION);
    }

    /**
     * Teste para garantir que uma disciplina não pode ter título nulo.
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureCourseTitleCannotBeNull() {
        new Course(COURSE_CODE, COURSE_ENROLMENTS_LIMITS, null, COURSE_DESCRIPTION);
    }

    /**
     * Teste para garantir que uma disciplina não pode ter descrição nula.
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureCourseDescriptionCannotBeNull() {
        new Course(COURSE_CODE, COURSE_ENROLMENTS_LIMITS, COURSE_TITLE, null);
    }

    /**
     * Teste para garantir que uma inscrição é adicionada com sucesso à disciplina
     */
    @Test
    public void ensureEnrollmentIsSuccessfullyAddedToCourse() {
        Course subjectCourse = this.buildCourse();
        Assert.assertTrue(subjectCourse.addEnrollment(this.buildEnrollment()));
    }

    /**
     * Teste para garantir que uma inscrição não pode ser adicionada duas vezes à mesma disciplina
     */
    @Test
    public void ensureEnrollmentCannotBeAddedToCourseTwice() {
        Course subjectCourse = this.buildCourse();
        Assert.assertTrue(subjectCourse.addEnrollment(this.buildEnrollment()));
        Assert.assertFalse(subjectCourse.addEnrollment(this.buildEnrollment()));
    }

    /**
     * Teste para garantir que uma aula recorrente é adicionada com sucesso à disciplina
     */
    @Test
    public void ensureRecurringClassIsSuccessfullyAddedToCourse() {
        Course subjectCourse = this.buildCourse();
        Assert.assertTrue(subjectCourse.addRecurringClass(this.buildRecurringClass()));
    }

    /**
     * Teste para garantir que uma aula recorrente não pode ser adicionada duas vezes à mesma disciplina
     */
    @Test
    public void ensureRecurringClassCannotBeAddedToCourseTwice() {
        Course subjectCourse = this.buildCourse();
        Assert.assertTrue(subjectCourse.addRecurringClass(this.buildRecurringClass()));
        Assert.assertFalse(subjectCourse.addRecurringClass(this.buildRecurringClass()));
    }

    /**
     * Teste para garantir que uma aula extra é adicionada com sucesso à disciplina
     */
    @Test
    public void ensureExtraClassIsSuccessfullyAddedToCourse() {
        Course subjectCourse = this.buildCourse();
        Assert.assertTrue(subjectCourse.addExtraClass(this.buildExtraClass()));
    }

    /**
     * Teste para garantir que uma aula extra não pode ser adicionada duas vezes à mesma disciplina
     */
    @Test
    public void ensureExtraClassCannotBeAddedToCourseTwice() {
        Course subjectCourse = this.buildCourse();
        Assert.assertTrue(subjectCourse.addExtraClass(this.buildExtraClass()));
        Assert.assertFalse(subjectCourse.addExtraClass(this.buildExtraClass()));
    }

    // ...

    /**
     * Teste para garantir que o título da disciplina é devolvido com sucesso
     */
    @Test
    public void ensureCourseTitleIsCorrectlyGiven() {
        Course subject = this.buildCourse();
        Assert.assertEquals(COURSE_TITLE, subject.title());
    }

    /**
     * Teste para garantir que o estado da disciplina é devolvido com sucesso
     */
    @Test
    public void ensureCourseStatusIsCorrectlyGiven() {
        Course subject = this.buildCourse();
        Assert.assertEquals(CourseStatus.CLOSE, subject.status());
    }

    /**
     * Teste para garantir que os limites de inscrições da disciplina são devolvidos com sucesso
     */
    @Test
    public void ensureCourseEnrolmentsLimitsAreCorrectlyGiven() {
        Course subject = this.buildCourse();
        Assert.assertEquals(COURSE_ENROLMENTS_LIMITS, subject.enrolmentLimits());
    }

    /**
     * Teste para garantir que o limite mínimo de inscrições da disciplina é devolvido com sucesso
     */
    @Test
    public void ensureCourseMinimumEnrolmentsLimitIsCorrectlyGiven() {
        Course subject = this.buildCourse();
        Assert.assertEquals(COURSE_ENROLMENTS_LIMITS.minimum(), subject.enrolmentLimits().minimum());
    }

    /**
     * Teste para garantir que o limite máximo de inscrições da disciplina é devolvido com sucesso
     */
    @Test
    public void ensureCourseMaximumEnrolmentsLimitIsCorrectlyGiven() {
        Course subject = this.buildCourse();
        Assert.assertEquals(COURSE_ENROLMENTS_LIMITS.maximum(), subject.enrolmentLimits().maximum());
    }

    /**
     * Teste para garantir que o próximo estado de um curso depois de criado (estado Close) é aberto (estado Open)
     */
    @Test
    public void ensureCourseStatusAfterCloseIsOpen() {
        Course subject = this.buildCourse();
        subject.nextState();
        Assert.assertEquals(CourseStatus.OPEN, subject.status());
    }

    /**
     * Teste para garantir que o próximo estado de um curso depois de aberto (estado Open) é aberto para inscrições
     * (estado Enroll)
     */
    @Test
    public void ensureCourseStatusAfterOpenIsEnroll() {
        Course subject = this.buildCourse();
        subject.nextState();
        subject.nextState();
        Assert.assertEquals(CourseStatus.ENROLL, subject.status());
    }

    /**
     * Teste para garantir que o próximo estado de um curso depois de aberto para inscrições (estado Enroll) é em
     * progresso (estado In_progress)
     */
    /*@Test
    @Test
    public void ensureCourseStatusAfterEnrollIsInProgress() {
        Course subject = this.buildCourse();
        subject.nextState();
        subject.nextState();
        subject.nextState();
//...
        Assert.assertEquals(CourseStatus.IN_PROGRESS, subject.status());
    }*/

    // ...

    /**
     * Teste para garantir que um professor é adicionado com sucesso à disciplina
     */
    /*@Test
    public void ensureTeacherIsSuccessfullyAddedToCourse() {
        Course subjectCourse = this.buildCourse();
        Teacher subjectTeacher = this.buildTeacher("ABCD");
        Assert.assertTrue(subjectCourse.addTeacher(subjectTeacher));
    }*/

    /**
     * Teste para garantir que um professor não pode ser adicionado duas vezes à mesma disciplina
     */
    /*@Test
    public void ensureTeacherCannotBeAddedToCourseTwice() {
        Course subjectCourse = this.buildCourse();
        Teacher subjectTeacher = this.buildTeacher("ABCD");
        Assert.assertTrue(subjectCourse.addTeacher(subjectTeacher));
        Assert.assertFalse(subjectCourse.addTeacher(subjectTeacher));
    }*/
}
