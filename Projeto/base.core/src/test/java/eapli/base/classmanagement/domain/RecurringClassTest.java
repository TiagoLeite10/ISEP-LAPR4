package eapli.base.classmanagement.domain;

import eapli.base.BuilderHelper;
import eapli.base.clientusermanagement.domain.Teacher;
import eapli.base.clientusermanagement.domain.TeacherBuilder;
import eapli.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Classe para realizar testes à entidade RecurringClass.
 */
public class RecurringClassTest {

    private final ClassTitle TITLE = ClassTitle.valueOf("Aula de meteorologia");

    private final ClassStartTime START_TIME = ClassStartTime.valueOf(BuilderHelper.buildTime(15, 0));

    private final ClassDuration DURATION = ClassDuration.valueOf(90);

    private final ClassDayOfTheWeek DAY_OF_THE_WEEK = ClassDayOfTheWeek.WEDNESDAY;

    private final Teacher TEACHER = buildTeacher("PSLD");

    private final Teacher OTHER_TEACHER = buildTeacher("OJWS");

    /**
     * Método para contruir uma aula recorrente.
     *
     * @return A aula extra construída.
     */
    private RecurringClass buildRecurringClass() {
        return new RecurringClass(TITLE, START_TIME, DURATION, DAY_OF_THE_WEEK, TEACHER);
    }

    /**
     * Método para contruir um professor.
     *
     * @return O professor construído.
     */
    private Teacher buildTeacher(final String acronym) {
        final SystemUser systemUser = UserBuilderHelper.builder().with("maria", "Password1", "Maria",
                "Rocha", "maria@gmail.com").build();

        Calendar birthDate = Calendar.getInstance();
        birthDate.set(2003, Calendar.SEPTEMBER, 1);

        return new TeacherBuilder().with("196743370", birthDate, acronym).withSystemUser(systemUser).build();
    }

    /**
     * Teste para garantir que uma aula recorrente tem de possuir um título.
     */
    @Test
    void ensureRecurringClassHasTitle() {
        assertThrows(IllegalArgumentException.class, () -> new RecurringClass(null, START_TIME, DURATION, DAY_OF_THE_WEEK, TEACHER));
    }

    /**
     * Teste para garantir que uma aula recorrente tem de possuir uma hora de início.
     */
    @Test
    void ensureRecurringClassHasStartTime() {
        assertThrows(IllegalArgumentException.class, () -> new RecurringClass(TITLE, null, DURATION, DAY_OF_THE_WEEK, TEACHER));
    }

    /**
     * Teste para garantir que uma aula recorrente tem de possuir uma duração.
     */
    @Test
    void ensureRecurringClassHasDuration() {
        assertThrows(IllegalArgumentException.class, () -> new RecurringClass(TITLE, START_TIME, null, DAY_OF_THE_WEEK, TEACHER));
    }

    /**
     * Teste para garantir que uma aula recorrente tem de possuir um dia da semana.
     */
    @Test
    void ensureRecurringClassHasDayOfWeek() {
        assertThrows(IllegalArgumentException.class, () -> new RecurringClass(TITLE, START_TIME, DURATION, null, TEACHER));
    }

    /**
     * Teste para garantir que uma aula recorrente tem de possuir um professor.
     */
    @Test
    void ensureRecurringClassHasTeacher() {
        assertThrows(IllegalArgumentException.class, () -> new RecurringClass(TITLE, START_TIME, DURATION, DAY_OF_THE_WEEK, null));
    }

    /**
     * Teste para garantir que uma aula recorrente tem a hora de início esperada.
     */
    @Test
    void ensureRecurringClassHasExpectedStartTime() {
        final RecurringClass subject = buildRecurringClass();

        assertEquals(ClassStartTime.valueOf(BuilderHelper.buildTime(15, 0)), subject.startTime());
    }

    /**
     * Teste para garantir que uma aula recorrente tem o dia da semana esperado.
     */
    @Test
    void ensureRecurringClassHasExpectedDayOfTheWeek() {
        final RecurringClass subject = buildRecurringClass();

        assertEquals(ClassDayOfTheWeek.WEDNESDAY, subject.dayOfWeek());
    }

    /**
     * Teste para garantir que uma aula recorrente tem a duração esperada.
     */
    @Test
    void ensureRecurringClassHasExpectedDuration() {
        final RecurringClass subject = buildRecurringClass();

        assertEquals(ClassDuration.valueOf(90), subject.duration());
    }

    /**
     * Teste para garantir que uma aula recorrente não têm instâncias de aulas pendentes.
     */
    @Test
    void ensureRecurringClassNotHasPendingClassInstances() {
        final RecurringClass subject = buildRecurringClass();

        assertFalse(subject.hasPendingClassInstances());
    }

    /**
     * Teste para garantir que duas aulas recorrentes são iguais para o mesmo título, hora de início, duração, dia da semana e
     * professor.
     */
    @Test
    void ensureTwoRecurringClassWithSameTitlesStartTimesDurationsDayOfTheWeeksTeachersAreTheSame() {
        final RecurringClass aRecurringClass = buildRecurringClass();
        final RecurringClass anotherRecurringClass = new RecurringClassBuilder().withClassTitle("Aula de meteorologia").
                withClassStartTime(BuilderHelper.buildTime(15, 0)).withClassDuration(90).
                withClassDayOfTheWeek(ClassDayOfTheWeek.WEDNESDAY).withTeacher(TEACHER).build();

        assertTrue(aRecurringClass.sameAs(anotherRecurringClass));
    }

    /**
     * Teste para garantir que duas aulas recorrentes não são iguais para um título diferente.
     */
    @Test
    void ensureTwoRecurringClassWithDifferentTitlesAreNotTheSame() {
        final RecurringClass aRecurringClass = buildRecurringClass();
        final RecurringClass anotherRecurringClass = new RecurringClassBuilder().withClassTitle("Aula de forex").
                withClassStartTime(BuilderHelper.buildTime(15, 0)).withClassDuration(90).
                withClassDayOfTheWeek(ClassDayOfTheWeek.WEDNESDAY).withTeacher(TEACHER).build();

        assertFalse(aRecurringClass.sameAs(anotherRecurringClass));
    }

    /**
     * Teste para garantir que duas aulas recorrentes não são iguais para uma hora de início diferente.
     */
    @Test
    void ensureTwoRecurringClassWithDifferentStartTimesAreNotTheSame() {
        final RecurringClass aRecurringClass = buildRecurringClass();
        final RecurringClass anotherRecurringClass = new RecurringClassBuilder().withClassTitle("Aula de meteorologia").
                withClassStartTime(BuilderHelper.buildTime(20, 30)).withClassDuration(90).
                withClassDayOfTheWeek(ClassDayOfTheWeek.WEDNESDAY).withTeacher(TEACHER).build();

        assertFalse(aRecurringClass.sameAs(anotherRecurringClass));
    }

    /**
     * Teste para garantir que duas aulas recorrentes não são iguais para uma duração diferente.
     */
    @Test
    void ensureTwoRecurringClassWithDifferentDurationsAreNotTheSame() {
        final RecurringClass aRecurringClass = buildRecurringClass();
        final RecurringClass anotherRecurringClass = new RecurringClassBuilder().withClassTitle("Aula de meteorologia").
                withClassStartTime(BuilderHelper.buildTime(15, 0)).withClassDuration(60).
                withClassDayOfTheWeek(ClassDayOfTheWeek.WEDNESDAY).withTeacher(TEACHER).build();

        assertFalse(aRecurringClass.sameAs(anotherRecurringClass));
    }

    /**
     * Teste para garantir que duas aulas recorrentes não são iguais para um dia da semana diferente.
     */
    @Test
    void ensureTwoRecurringClassWithDifferentDayOfTheWeeksAreNotTheSame() {
        final RecurringClass aRecurringClass = buildRecurringClass();
        final RecurringClass anotherRecurringClass = new RecurringClassBuilder().withClassTitle("Aula de meteorologia").
                withClassStartTime(BuilderHelper.buildTime(15, 0)).withClassDuration(90).
                withClassDayOfTheWeek(ClassDayOfTheWeek.MONDAY).withTeacher(TEACHER).build();

        assertFalse(aRecurringClass.sameAs(anotherRecurringClass));
    }

    /**
     * Teste para garantir que duas aulas recorrentes não são iguais para um professor diferente.
     */
    @Test
    void ensureTwoRecurringClassWithDifferentTeachersAreNotTheSame() {
        final RecurringClass aRecurringClass = buildRecurringClass();
        final RecurringClass anotherRecurringClass = new RecurringClassBuilder().withClassTitle("Aula de meteorologia").
                withClassStartTime(BuilderHelper.buildTime(15, 0)).withClassDuration(90).
                withClassDayOfTheWeek(ClassDayOfTheWeek.WEDNESDAY).withTeacher(OTHER_TEACHER).build();

        assertFalse(aRecurringClass.sameAs(anotherRecurringClass));
    }

    /**
     * Teste para garantir que uma aula recorrente é a mesma para a mesma instância.
     */
    @Test
    void ensureRecurringClassAreTheSameForTheSameInstance() {
        final RecurringClass aRecurringClass = new RecurringClass();

        assertTrue(aRecurringClass.sameAs(aRecurringClass));
    }

    /**
     * Teste para garantir que uma aula recorrente não é a mesma para diferentes tipos de objetos.
     */
    @Test
    void ensureRecurringClassNotTheSameForDifferenteObjectTypes() {
        final RecurringClass aRecurringClass = buildRecurringClass();

        assertFalse(aRecurringClass.sameAs(TEACHER));
    }

    /**
     * Teste para garantir que uma aula recorrente é igual a outra para o mesmo título.
     */
    @Test
    void ensureRecurringClassEqualsPassesForTheSameTitle() {
        final RecurringClass aRecurringClass = buildRecurringClass();
        final RecurringClass anotherRecurringClass = new RecurringClassBuilder().withClassTitle("Aula de meteorologia").
                withClassStartTime(BuilderHelper.buildTime(5, 0)).withClassDuration(15).
                withClassDayOfTheWeek(ClassDayOfTheWeek.MONDAY).withTeacher(OTHER_TEACHER).build();

        assertEquals(aRecurringClass, anotherRecurringClass);
    }

    /**
     * Teste para garantir que uma aula recorrente não é igual a outra para um título diferente.
     */
    @Test
    void ensureRecurringClassEqualsFailsForDifferenteTitle() {
        final RecurringClass aRecurringClass = buildRecurringClass();
        final RecurringClass anotherRecurringClass = new RecurringClassBuilder().withClassTitle("Aula de forex").
                withClassStartTime(BuilderHelper.buildTime(15, 0)).withClassDuration(90).
                withClassDayOfTheWeek(ClassDayOfTheWeek.MONDAY).withTeacher(TEACHER).build();

        assertNotEquals(aRecurringClass, anotherRecurringClass);
    }
}
