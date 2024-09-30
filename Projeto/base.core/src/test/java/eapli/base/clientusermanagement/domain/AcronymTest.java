package eapli.base.clientusermanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Classe para realizar testes ao value object Acronym.
 */
public class AcronymTest {

    private final Acronym subject = Acronym.valueOf("ABCD");

    /**
     * Teste para garantir que um acrónimo não deve ser vazio.
     */
    @Test
    void ensureAcronymMustNotBeEmpty() {
        assertThrows(IllegalArgumentException.class, () -> Acronym.valueOf(""));
    }

    /**
     * Teste para garantir que um acrónimo não deve ser nulo.
     */
    @Test
    void ensureAcronymMustNotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> Acronym.valueOf(null));
    }

    /**
     * Teste para garantir que um acrónimo tem de possuir apenas letras.
     */
    @Test
    void ensureAcronymHasOnlyLetters() {
        assertThrows(IllegalArgumentException.class, () -> Acronym.valueOf("ABC1"));
    }

    /**
     * Teste para garantir que um acrónimo tem de possuir 4 letras.
     */
    @Test
    void ensureAcronymHasFourLetters() {
        assertThrows(IllegalArgumentException.class, () -> Acronym.valueOf("ABCDE"));
    }

    /**
     * Teste para garantir que um acrónimo é igual a outro.
     */
    @Test
    void ensureAcronymIsSame() {
        assertEquals(0, subject.compareTo(Acronym.valueOf("ABCD")));
    }

    /**
     * Teste para garantir que um acrónimo é posterior a outro.
     */
    @Test
    void ensureAcronymIsAfter() {
        assertEquals(1, subject.compareTo(Acronym.valueOf("ABCC")));
    }

    /**
     * Teste para garantir que um acrónimo é anterior a outro.
     */
    @Test
    void ensureAcronymIsBefore() {
        assertEquals(-1, subject.compareTo(Acronym.valueOf("ABCE")));
    }
}
