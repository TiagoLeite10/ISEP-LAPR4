package eapli.base.classmanagement.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Classe para realizar testes ao value object ClassTitle.
 */
public class ClassTitleTest {

    private final ClassTitle subject = ClassTitle.valueOf("Aula de risotos");

    /**
     * Teste para garantir que um título de aula não deve ser vazio.
     */
    @Test
    void ensureClassTitleMustNotBeEmpty() {
        assertThrows(IllegalArgumentException.class, () -> ClassTitle.valueOf(""));
    }

    /**
     * Teste para garantir que um título de aula não deve ser nulo.
     */
    @Test
    void ensureClassTitleMustNotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> ClassTitle.valueOf(null));
    }

    /**
     * Teste para garantir que um título de aula não deve ser possuir espaços iniciais ou finais.
     */
    @ParameterizedTest
    @ValueSource(strings = {"  Aula de rochas  ", "Aula de rochas   ", "  Aula de rochas"})
    void ensureClassTitleMustNotContainLeadingSpaces(final String title) {
        assertThrows(IllegalArgumentException.class, () -> ClassTitle.valueOf(title));
    }

    /**
     * Teste para garantir que um título de aula não deve ser possuir mais de 50 caracteres.
     */
    @Test
    void ensureClassTitleMustNotContainMoreThanFiftyCharacters() {
        assertThrows(IllegalArgumentException.class, () -> ClassTitle.valueOf("Aula de rochas sobre as rochas " +
                "do século XX que têm mais dez metros"));
    }

    /**
     * Teste para garantir que um título de aula é igual a outro.
     */
    @Test
    void ensureClassTitleIsSame() {
        assertEquals(0, subject.compareTo(ClassTitle.valueOf("Aula de risotos")));
    }

    /**
     * Teste para garantir que um título de aula é posterior a outro.
     */
    @Test
    void ensureClassTitleIsAfter() {
        assertEquals(1, subject.compareTo(ClassTitle.valueOf("Aula de queijos")));
    }

    /**
     * Teste para garantir que um título de aula é anterior a outro.
     */
    @Test
    void ensureClassTitleIsBefore() {
        assertEquals(-1, subject.compareTo(ClassTitle.valueOf("Aula de sobremesas")));
    }
}
