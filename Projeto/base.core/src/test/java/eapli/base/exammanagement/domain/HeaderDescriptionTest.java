package eapli.base.exammanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe para realizar testes ao value object HeaderDescription.
 */
public class HeaderDescriptionTest {

    private final HeaderDescription subject = HeaderDescription.valueOf("Primeiro exame sobre rochas");

    /**
     * Teste para garantir que a descrição de um cabeçalho pode deve ser vazia.
     */
    @Test
    void ensureHeaderDescriptionMustBeEmpty() {
        assertDoesNotThrow(() -> HeaderDescription.valueOf(""));
    }

    /**
     * Teste para garantir que a descrição de um cabeçalho não deve ser nula.
     */
    @Test
    void ensureHeaderDescriptionMustNotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> HeaderDescription.valueOf(null));
    }

    /**
     * Teste para garantir que a descrição de um cabeçalho não deve possuir mais de 150 caracteres.
     */
    @Test
    void ensureHeaderDescriptionMustNotContainMoreThanFiftyCharacters() {
        assertThrows(IllegalArgumentException.class, () -> HeaderDescription.valueOf("Primeiro exame sobre rochas " +
                "do século XX que são compostas por uma variedade de formações rochosas notáveis, algumas das quais ultrapassam " +
                "os dez metros de altura e quinze metros de largura."));
    }

    /**
     * Teste para garantir que a descrição de um cabeçalho é igual a outra.
     */
    @Test
    void ensureHeaderDescriptionIsEquals() {
        assertEquals(subject, HeaderDescription.valueOf("Primeiro exame sobre rochas"));
    }

    /**
     * Teste para garantir que a descrição de um cabeçalho não é igual a outra.
     */
    @Test
    void ensureHeaderDescriptionNotEquals() {
        assertNotEquals(subject, HeaderDescription.valueOf("Primeiro exame sobre pedras"));
    }
}
