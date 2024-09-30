package eapli.base.eventmanagement.domain;

import eapli.base.BuilderHelper;
import eapli.base.clientusermanagement.domain.Student;
import eapli.base.clientusermanagement.domain.StudentBuilder;
import eapli.base.clientusermanagement.domain.Teacher;
import eapli.base.clientusermanagement.domain.TeacherBuilder;
import eapli.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.junit.Test;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Classe para realizar testes ao builder ExtraClassBuilder.
 */
public class ExtraClassBuilderTest {

    private final Calendar DATE_TIME = BuilderHelper.buildDateTime(3000, Calendar.AUGUST, 23, 15, 0);

    private final Integer DURATION = 90;

    private final Teacher TEACHER = buildTeacher();

    private final Set<Student> STUDENTS = buildStudents();

    /**
     * Método para contruir um professor.
     *
     * @return O professor construído.
     */
    private Teacher buildTeacher() {
        final SystemUser user = UserBuilderHelper.builder().with("luis", "Password1", "Luís",
                "Neves", "luis@gmail.com").build();

        return new TeacherBuilder().with("190001259", BuilderHelper.buildDate(2003, Calendar.JUNE, 15), "LNPO").withSystemUser(user).build();
    }

    /**
     * Método para contruir um Set de alunos.
     *
     * @return O Set de alunos construído.
     */
    private Set<Student> buildStudents() {
        Set<Student> students = new HashSet<>();

        final SystemUser user1 = UserBuilderHelper.builder().with("joao", "Password1", "João",
                "Vale", "joao@gmail.com").build();

        final Student student1 = new StudentBuilder().with("193927560", BuilderHelper.buildDate(2000, Calendar.DECEMBER, 19)).
                withSystemUser(user1).build();

        final SystemUser user2 = UserBuilderHelper.builder().with("rui", "Password1", "Rui",
                "Pereira", "rui@gmail.com").build();

        final Student student2 = new StudentBuilder().with("137017081", BuilderHelper.buildDate(1995, Calendar.APRIL, 1)).
                withSystemUser(user2).build();

        students.add(student1);
        students.add(student2);

        return students;
    }

    /**
     * Teste para garantir que é possível construir uma aula extra com data, duração, professor e alunos.
     */
    @Test
    public void ensureCanBuildExtraClassWithDurationDateTeacherStudents() {
        final ExtraClass subject = new ExtraClassBuilder().with(DATE_TIME, DURATION, TEACHER, STUDENTS).build();
        assertNotNull(subject);
    }

    /**
     * Teste para garantir que não é possível construir uma aula extra com data nula.
     */
    @Test
    public void ensureCannotBuildExtraClassWithNullDate() {
        assertThrows(IllegalArgumentException.class, () -> new ExtraClassBuilder().withEventDate(null).
                withEventDuration(DURATION).withTeacher(TEACHER).withStudents(STUDENTS).build());
    }

    /**
     * Teste para garantir que não é possível construir uma aula extra sem data.
     */
    @Test
    public void ensureCannotBuildExtraClassWithoutDate() {
        assertThrows(IllegalArgumentException.class, () -> new ExtraClassBuilder().withEventDuration(DURATION).
                withTeacher(TEACHER).withStudents(STUDENTS).build());
    }

    /**
     * Teste para garantir que não é possível construir uma aula extra com duração nula.
     */
    @Test
    public void ensureCannotBuildExtraClassWithNullDuration() {
        assertThrows(IllegalArgumentException.class, () -> new ExtraClassBuilder().withEventDate(DATE_TIME).
                withEventDuration(null).withTeacher(TEACHER).withStudents(STUDENTS).build());
    }

    /**
     * Teste para garantir que não é possível construir uma aula extra sem duração.
     */
    @Test
    public void ensureCannotBuildExtraClassWithoutDuration() {
        assertThrows(IllegalArgumentException.class, () -> new ExtraClassBuilder().withEventDate(DATE_TIME).
                withTeacher(TEACHER).withStudents(STUDENTS).build());
    }

    /**
     * Teste para garantir que não é possível construir uma aula extra com professor nulo.
     */
    @Test
    public void ensureCannotBuildExtraClassWithNullTeacher() {
        assertThrows(IllegalArgumentException.class, () -> new ExtraClassBuilder().withEventDate(DATE_TIME).
                withEventDuration(DURATION).withTeacher(null).withStudents(STUDENTS).build());
    }

    /**
     * Teste para garantir que não é possível construir uma aula extra sem professor.
     */
    @Test
    public void ensureCannotBuildExtraClassWithoutTeacher() {
        assertThrows(IllegalArgumentException.class, () -> new ExtraClassBuilder().withEventDate(DATE_TIME).
                withEventDuration(DURATION).withStudents(STUDENTS).build());
    }

    /**
     * Teste para garantir que não é possível construir uma aula extra com alunos nulos.
     */
    @Test
    public void ensureCannotBuildExtraClassWithNullStudents() {
        assertThrows(IllegalArgumentException.class, () -> new ExtraClassBuilder().withEventDate(DATE_TIME).
                withEventDuration(DURATION).withTeacher(TEACHER).withStudents(null).build());
    }

    /**
     * Teste para garantir que não é possível construir uma aula extra sem alunos.
     */
    @Test
    public void ensureCannotBuildExtraClassWithoutStudents() {
        assertThrows(IllegalArgumentException.class, () -> new ExtraClassBuilder().withEventDate(DATE_TIME).
                withEventDuration(DURATION).withTeacher(TEACHER).build());
    }
}
