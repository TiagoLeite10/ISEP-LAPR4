package eapli.base.boardmanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe para realizar testes à entidade BoardElementLine.
 */
public class BoardElementLineTest {

    private final BoardElementLine subject = BoardElementLine.valueOf("Low Priority", 5);

    /**
     * Teste para garantir que a linha do elemento da board tem de possuir um número positivo.
     */
    @Test
    void ensureThatBoardElementLineHasPositiveNumber() {
        assertThrows(IllegalArgumentException.class, () -> BoardElementLine.valueOf("Low Priority", -10));
    }

    /**
     * Teste para garantir que a linha do elemento da board tem o valor esperado.
     */
    @Test
    void ensureBoardElementLineHasExpectedLine() {
        assertEquals(5, subject.line());
    }

    /**
     * Teste para garantir que a linha do elemento da board não tem o valor esperado.
     */
    @Test
    void ensureBoardElementLineHasNotExpectedLine() {
        assertNotEquals(3, subject.line());
    }

    /**
     * Teste para garantir que o título da linha do elemento da board tem o valor esperado.
     */
    @Test
    void ensureBoardElementLineHasExpectedTitle() {
        assertEquals("Low Priority", subject.title());
    }

    /**
     * Teste para garantir que a título da linha do elemento da board não tem o valor esperado.
     */
    @Test
    void ensureBoardElementLineHasNotExpectedTitle() {
        assertNotEquals("High Priority", subject.title());
    }
}
