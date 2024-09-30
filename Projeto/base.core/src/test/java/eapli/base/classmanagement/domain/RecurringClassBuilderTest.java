package eapli.base.classmanagement.domain;

import eapli.base.BuilderHelper;
import eapli.base.clientusermanagement.domain.Teacher;
import eapli.base.clientusermanagement.domain.TeacherBuilder;
import eapli.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Classe para realizar testes ao builder RecurringClassBuilder.
 */
public class RecurringClassBuilderTest {

    private final String TITLE = "Aula de meteorologia";

    private final Calendar START_TIME = BuilderHelper.buildTime(15, 0);

    private final Integer DURATION = 90;

    private final ClassDayOfTheWeek DAY_OF_THE_WEEK = ClassDayOfTheWeek.WEDNESDAY;

    private final Teacher TEACHER = buildTeacher();

    /**
     * Método para contruir um professor.
     *
     * @return O professor construído.
     */
    private Teacher buildTeacher() {
        final SystemUser user = UserBuilderHelper.builder().with("pedro", "Password1", "Pedro",
                "Silva", "pedro@gmail.com").build();

        Calendar birthDate = Calendar.getInstance();
        birthDate.set(2003, Calendar.SEPTEMBER, 1);

        return new TeacherBuilder().with("196743370", birthDate, "PSLD").withSystemUser(user).build();
    }

    /**
     * Teste para garantir que é possível construir uma aula recorrente com o título, hora de início, duração, dia da semana
     * e professor.
     */
    @Test
    public void ensureCanBuildRecurringClassWithTitleStartTimeDurationDayOfTheWeekTeacher() {
        final RecurringClass subject = new RecurringClassBuilder().with(TITLE, START_TIME, DURATION, DAY_OF_THE_WEEK, TEACHER).build();
        assertNotNull(subject);
    }

    /**
     * Teste para garantir que não é possível construir uma aula recorrente com título nulo.
     */
    @Test
    public void ensureCannotBuildRecurringClassWithNullTitle() {
        assertThrows(IllegalArgumentException.class, () -> new RecurringClassBuilder().withClassTitle(null).withClassStartTime(START_TIME).
                withClassDuration(DURATION).withClassDayOfTheWeek(DAY_OF_THE_WEEK).withTeacher(TEACHER).build());
    }

    /**
     * Teste para garantir que não é possível construir uma aula recorrente sem título.
     */
    @Test
    public void ensureCannotBuildRecurringClassWithoutTitle() {
        assertThrows(IllegalArgumentException.class, () -> new RecurringClassBuilder().withClassStartTime(START_TIME).
                withClassDuration(DURATION).withClassDayOfTheWeek(DAY_OF_THE_WEEK).withTeacher(TEACHER).build());
    }

    /**
     * Teste para garantir que não é possível construir uma aula recorrente com hora de início nula.
     */
    @Test
    public void ensureCannotBuildRecurringClassWithNullStartTime() {
        assertThrows(IllegalArgumentException.class, () -> new RecurringClassBuilder().withClassTitle(TITLE).withClassStartTime(null).
                withClassDuration(DURATION).withClassDayOfTheWeek(DAY_OF_THE_WEEK).withTeacher(TEACHER).build());
    }

    /**
     * Teste para garantir que não é possível construir uma aula recorrente sem hora de início.
     */
    @Test
    public void ensureCannotBuildRecurringClassWithoutStartTime() {
        assertThrows(IllegalArgumentException.class, () -> new RecurringClassBuilder().withClassTitle(TITLE).
                withClassDuration(DURATION).withClassDayOfTheWeek(DAY_OF_THE_WEEK).withTeacher(TEACHER).build());
    }

    /**
     * Teste para garantir que não é possível construir uma aula recorrente com duração nula.
     */
    @Test
    public void ensureCannotBuildRecurringClassWithNullDuration() {
        assertThrows(IllegalArgumentException.class, () -> new RecurringClassBuilder().withClassTitle(TITLE).withClassStartTime(START_TIME).
                withClassDuration(null).withClassDayOfTheWeek(DAY_OF_THE_WEEK).withTeacher(TEACHER).build());
    }

    /**
     * Teste para garantir que não é possível construir uma aula recorrente sem duração.
     */
    @Test
    public void ensureCannotBuildRecurringClassWithoutDuration() {
        assertThrows(IllegalArgumentException.class, () -> new RecurringClassBuilder().withClassTitle(TITLE).withClassStartTime(START_TIME).
                withClassDayOfTheWeek(DAY_OF_THE_WEEK).withTeacher(TEACHER).build());
    }

    /**
     * Teste para garantir que não é possível construir uma aula recorrente com dia da semana nulo.
     */
    @Test
    public void ensureCannotBuildRecurringClassWithNullDayOfTheWeek() {
        assertThrows(IllegalArgumentException.class, () -> new RecurringClassBuilder().withClassTitle(TITLE).withClassStartTime(START_TIME).
                withClassDuration(DURATION).withClassDayOfTheWeek(null).withTeacher(TEACHER).build());
    }

    /**
     * Teste para garantir que não é possível construir uma aula recorrente sem dia da semana.
     */
    @Test
    public void ensureCannotBuildRecurringClassWithoutDayOfTheWeek() {
        assertThrows(IllegalArgumentException.class, () -> new RecurringClassBuilder().withClassTitle(TITLE).withClassStartTime(START_TIME).
                withClassDuration(DURATION).withTeacher(TEACHER).build());
    }

    /**
     * Teste para garantir que não é possível construir uma aula recorrente com professor nulo.
     */
    @Test
    public void ensureCannotBuildRecurringClassWithNullTeacher() {
        assertThrows(IllegalArgumentException.class, () -> new RecurringClassBuilder().withClassTitle(TITLE).withClassStartTime(START_TIME).
                withClassDuration(DURATION).withClassDayOfTheWeek(DAY_OF_THE_WEEK).withTeacher(null).build());
    }

    /**
     * Teste para garantir que não é possível construir uma aula recorrente sem professor.
     */
    @Test
    public void ensureCannotBuildRecurringClassWithoutTeacher() {
        assertThrows(IllegalArgumentException.class, () -> new RecurringClassBuilder().withClassTitle(TITLE).withClassStartTime(START_TIME).
                withClassDuration(DURATION).withClassDayOfTheWeek(DAY_OF_THE_WEEK).build());
    }
}
