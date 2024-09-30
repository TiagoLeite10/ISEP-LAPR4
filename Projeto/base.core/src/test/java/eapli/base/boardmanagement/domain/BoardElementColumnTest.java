package eapli.base.boardmanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe para realizar testes à entidade BoardElementColumn.
 */
public class BoardElementColumnTest {

    private final BoardElementColumn subject = BoardElementColumn.valueOf("Done", 5);

    /**
     * Teste para garantir que a coluna do elemento da board tem de possuir um número positivo.
     */
    @Test
    void ensureThatBoardElementColumnHasPositiveNumber() {
        assertThrows(IllegalArgumentException.class, () -> BoardElementColumn.valueOf("Done", -10));
    }

    /**
     * Teste para garantir que a coluna do elemento da board tem o valor esperado.
     */
    @Test
    void ensureBoardElementColumnHasExpectedColumn() {
        assertEquals(5, subject.column());
    }

    /**
     * Teste para garantir que a coluna do elemento da board não tem o valor esperado.
     */
    @Test
    void ensureBoardElementColumnHasNotExpectedColumn() {
        assertNotEquals(3, subject.column());
    }

    /**
     * Teste para garantir que o título da coluna do elemento da board tem o valor esperado.
     */
    @Test
    void ensureBoardElementColumnHasExpectedTitle() {
        assertEquals("Done", subject.title());
    }

    /**
     * Teste para garantir que a título da coluna do elemento da board não tem o valor esperado.
     */
    @Test
    void ensureBoardElementColumnHasNotExpectedTitle() {
        assertNotEquals("Doing", subject.title());
    }
}
