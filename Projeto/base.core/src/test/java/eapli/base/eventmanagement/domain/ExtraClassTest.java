package eapli.base.eventmanagement.domain;

import eapli.base.BuilderHelper;
import eapli.base.clientusermanagement.domain.Student;
import eapli.base.clientusermanagement.domain.StudentBuilder;
import eapli.base.clientusermanagement.domain.Teacher;
import eapli.base.clientusermanagement.domain.TeacherBuilder;
import eapli.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.infrastructure.authz.domain.model.*;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Classe para realizar testes à classe ExtraClass.
 */
public class ExtraClassTest {

    private final EventDate EVENT_DATE = EventDate.valueOf(BuilderHelper.buildDateTime(3000, Calendar.AUGUST, 23, 15, 0));

    private final EventDuration EVENT_DURATION = EventDuration.valueOf(30);

    private final Teacher TEACHER = buildTeacher("ASXZ");

    private final Teacher OTHER_TEACHER = buildTeacher("ASPL");

    private final Set<Student> STUDENTS = buildStudents();

    /**
     * Método para contruir uma aula extra.
     *
     * @return A aula extra construída.
     */
    private ExtraClass buildExtraClass() {
        return new ExtraClass(EVENT_DATE, EVENT_DURATION, TEACHER, STUDENTS);
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
     * Teste para garantir que uma aula extra tem de possuir uma data.
     */
    @Test
    void ensureExtraClassHasDateTime() {
        assertThrows(IllegalArgumentException.class, () -> new ExtraClass(null, EVENT_DURATION, TEACHER, STUDENTS));
    }

    /**
     * Teste para garantir que uma aula extra tem de possuir uma duração.
     */
    @Test
    void ensureExtraClassHasDuration() {
        assertThrows(IllegalArgumentException.class, () -> new ExtraClass(EVENT_DATE, null, TEACHER, STUDENTS));
    }

    /**
     * Teste para garantir que uma aula extra tem de possuir um professor.
     */
    @Test
    void ensureExtraClassHasTeacher() {
        assertThrows(IllegalArgumentException.class, () -> new ExtraClass(EVENT_DATE, EVENT_DURATION, null, STUDENTS));
    }

    /**
     * Teste para garantir que uma aula extra tem de possuir alunos.
     */
    @Test
    void ensureExtraClassHasStudents() {
        assertThrows(IllegalArgumentException.class, () -> new ExtraClass(EVENT_DATE, EVENT_DURATION, TEACHER, null));
    }

    /**
     * Teste para garantir que uma aula extra tem a data de início esperada.
     */
    @Test
    void ensureExtraClassHasExpectedStartDate() {
        final ExtraClass subject = buildExtraClass();

        assertEquals(EVENT_DATE, subject.eventDate());
    }

    /**
     * Teste para garantir que uma aula extra tem a data de fim esperada.
     */
    @Test
    void ensureMeetingHasExpectedEndDate() {
        final ExtraClass subject = buildExtraClass();

        assertEquals(BuilderHelper.buildDateTime(3000, Calendar.AUGUST, 23, 15, 30), subject.eventEndDate());
    }

    /**
     * Teste para garantir que uma aula extra não tem a data de fim esperada.
     */
    @Test
    void ensureMeetingNotHasExpectedEndDate() {
        final ExtraClass subject = buildExtraClass();

        assertNotEquals(BuilderHelper.buildDateTime(3000, Calendar.AUGUST, 23, 15, 29), subject.eventEndDate());
    }

    /**
     * Teste para garantir que uma aula extra tem a duração esperada.
     */
    @Test
    void ensureExtraClassHasExpectedDuration() {
        final ExtraClass subject = buildExtraClass();

        assertEquals(EVENT_DURATION, subject.eventDuration());
    }

    /**
     * Teste para garantir que uma aula extra tem os alunos esperados.
     */
    @Test
    void ensureExtraClassHasExpectedStudents() {
        final ExtraClass subject = buildExtraClass();

        assertEquals(buildStudents(), subject.students());
    }

    /**
     * Teste para garantir que uma aula extra é anterior a uma data.
     */
    @Test
    void ensureExtraClassEventDateLessThanDate() {
        final ExtraClass subject = buildExtraClass();

        assertTrue(subject.eventDateLessThan(BuilderHelper.buildDateTime(3000, Calendar.AUGUST, 23, 23, 0)));
    }

    /**
     * Teste para garantir que uma aula extra não é anterior a uma data.
     */
    @Test
    void ensureExtraClassEventDateNotLessThanDate() {
        final ExtraClass subject = buildExtraClass();

        assertFalse(subject.eventDateLessThan(BuilderHelper.buildDateTime(3000, Calendar.AUGUST, 23, 11, 0)));
    }


    /**
     * Teste para garantir que duas aulas extras são iguais para a mesma data, duração, professor e alunos.
     */
    @Test
    void ensureTwoExtraClassWithSameDatesDurationsTeachersStudentsAreTheSame() {
        final ExtraClass aExtraClass = buildExtraClass();
        final ExtraClass anotherExtraClass = new ExtraClassBuilder().withEventDate(BuilderHelper.buildDateTime(3000, Calendar.AUGUST, 23, 15, 0)).
                withEventDuration(30).withTeacher(TEACHER).withStudents(STUDENTS).build();

        assertTrue(aExtraClass.sameAs(anotherExtraClass));
    }

    /**
     * Teste para garantir que duas aulas extras não são iguais para uma data diferente.
     */
    @Test
    void ensureTwoExtraClassWithDifferentDatesAreNotTheSame() {
        final ExtraClass aExtraClass = buildExtraClass();
        final ExtraClass anotherExtraClass = new ExtraClassBuilder().withEventDate(BuilderHelper.buildDateTime(3001, Calendar.JANUARY, 3, 10, 30)).
                withEventDuration(30).withTeacher(TEACHER).withStudents(STUDENTS).build();

        assertFalse(aExtraClass.sameAs(anotherExtraClass));
    }

    /**
     * Teste para garantir que duas aulas extras não são iguais para uma duração diferente.
     */
    @Test
    void ensureTwoExtraClassWithDifferentDurationsAreNotTheSame() {
        final ExtraClass aExtraClass = buildExtraClass();
        final ExtraClass anotherExtraClass = new ExtraClassBuilder().withEventDate(BuilderHelper.buildDateTime(3000, Calendar.AUGUST, 23, 15, 0)).
                withEventDuration(60).withTeacher(TEACHER).withStudents(STUDENTS).build();

        assertFalse(aExtraClass.sameAs(anotherExtraClass));
    }

    /**
     * Teste para garantir que duas aulas extras não são iguais para um dono diferente.
     */
    @Test
    void ensureTwoExtraClassWithDifferentTeachesAreNotTheSame() {
        final ExtraClass aExtraClass = buildExtraClass();
        final ExtraClass anotherExtraClass = new ExtraClassBuilder().withEventDate(BuilderHelper.buildDateTime(3000, Calendar.AUGUST, 23, 15, 0)).
                withEventDuration(30).withTeacher(OTHER_TEACHER).withStudents(STUDENTS).build();

        assertFalse(aExtraClass.sameAs(anotherExtraClass));
    }

    /**
     * Teste para garantir que uma aula extra é a mesma para a mesma instância.
     */
    @Test
    void ensureExtraClassAreTheSameForTheSameInstance() {
        final ExtraClass aExtraClass = new ExtraClass();

        assertTrue(aExtraClass.sameAs(aExtraClass));
    }

    /**
     * Teste para garantir que uma aula extra não é a mesma para diferentes tipos de objetos.
     */
    @Test
    void ensureExtraClassNotTheSameForDifferenteObjectTypes() {
        final ExtraClass aExtraClass = buildExtraClass();

        assertFalse(aExtraClass.sameAs(TEACHER));
    }
}
