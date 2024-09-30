package eapli.base.classmanagement.domain;

import java.util.Calendar;

import eapli.base.BuilderHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe para realizar testes à entidade ClassInstance.
 */
public class ClassInstanceTest {

    private final ClassInstanceDate DATE = ClassInstanceDate.valueOf(BuilderHelper.buildDate(3001, Calendar.JANUARY, 3),
            BuilderHelper.buildDate(3000, Calendar.OCTOBER, 24));

    /**
     * Método para contruir uma instância de aula.
     *
     * @return A instância de aula construída.
     */
    private ClassInstance buildClassInstance() {
        return new ClassInstance(DATE);
    }

    /**
     * Teste para garantir que uma instância de aula tem de possuir uma instância de data.
     */
    @Test
    void ensureClassInstanceHasInstanceDate() {
        assertThrows(IllegalArgumentException.class, () -> new ClassInstance(null));
    }


    /**
     * Teste para garantir que uma instância de aula é anterior a uma data.
     */
    @Test
    void ensureClassInstanceWillHappenBeforeCalendar() {
        final ClassInstance subject = buildClassInstance();

        assertTrue(subject.classWillHappenBeforeCalendar(BuilderHelper.buildDate(3001, Calendar.DECEMBER, 10)));
    }

    /**
     * Teste para garantir que uma instância de aula tem a nova data esperada.
     */
    @Test
    void ensureClassInstanceHasExpectedNewDateTime() {
        final ClassInstance subject = buildClassInstance();

        assertEquals(BuilderHelper.buildDate(3001, Calendar.JANUARY, 3), subject.classInstanceDate());
    }

    /**
     * Teste para garantir que duas instâncias de aula são iguais para a mesma instância de data.
     */
    @Test
    void ensureTwoClassInstanceWithSameInstanceDatesAreTheSame() {
        final ClassInstance aClassInstance = buildClassInstance();
        final ClassInstance anotherClassInstance = new ClassInstance(ClassInstanceDate.valueOf(BuilderHelper.buildDate(3001, Calendar.JANUARY, 3),
                BuilderHelper.buildDate(3000, Calendar.OCTOBER, 24)));

        assertTrue(aClassInstance.sameAs(anotherClassInstance));
    }

    /**
     * Teste para garantir que duas instâncias de aula não são iguais para uma instância de data diferente.
     */
    @Test
    void ensureTwoClassInstanceWithDifferenteInstanceDatesAreNotTheSame() {
        final ClassInstance aClassInstance = buildClassInstance();
        final ClassInstance anotherClassInstance = new ClassInstance(ClassInstanceDate.valueOf(BuilderHelper.buildDate(3001, Calendar.JULY, 30),
                BuilderHelper.buildDate(3000, Calendar.DECEMBER, 23)));

        assertFalse(aClassInstance.sameAs(anotherClassInstance));
    }

    /**
     * Teste para garantir que uma instância de aula é a mesma para a mesma instância.
     */
    @Test
    void ensureClassInstanceAreTheSameForTheSameInstance() {
        final ClassInstance aClassInstance = new ClassInstance();

        assertTrue(aClassInstance.sameAs(aClassInstance));
    }

    /**
     * Teste para garantir que uma instância de aula não é a mesma para diferentes tipos de objetos.
     */
    @Test
    void ensureClassInstanceNotTheSameForDifferenteObjectTypes() {
        final ClassInstance aClassInstance = buildClassInstance();

        assertFalse(aClassInstance.sameAs(DATE));
    }
}
