package eapli.base.exammanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe para realizar testes ao value object SectionDescription.
 */
public class SectionDescriptionTest {

    private final SectionDescription subject = SectionDescription.valueOf("Secção de rochas");

    /**
     * Teste para garantir que a descrição de uma secção pode ser vazia.
     */
    @Test
    void ensureSectionDescriptionMustBeEmpty() {
        assertDoesNotThrow(() -> SectionDescription.valueOf(""));
    }

    /**
     * Teste para garantir que a descrição de uma secção não deve ser nula.
     */
    @Test
    void ensureSectionDescriptionMustNotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> SectionDescription.valueOf(null));
    }

    /**
     * Teste para garantir que a descrição de uma secção não deve possuir mais de 150 caracteres.
     */
    @Test
    void ensureSectionDescriptionMustNotContainMoreThanFiftyCharacters() {
        assertThrows(IllegalArgumentException.class, () -> SectionDescription.valueOf("A secção de rochas " +
                "do século XX é composta por uma variedade de formações rochosas notáveis, algumas das quais ultrapassam " +
                "os dez metros de altura e quinze metros de largura."));
    }

    /**
     * Teste para garantir que a descrição de uma secção é igual a outra.
     */
    @Test
    void ensureSectionDescriptionIsEquals() {
        assertEquals(subject, SectionDescription.valueOf("Secção de rochas"));
    }

    /**
     * Teste para garantir que a descrição de uma secção não é igual a outra.
     */
    @Test
    void ensureSectionDescriptionNotEquals() {
        assertNotEquals(subject, SectionDescription.valueOf("Secção de pedras"));
    }
}
