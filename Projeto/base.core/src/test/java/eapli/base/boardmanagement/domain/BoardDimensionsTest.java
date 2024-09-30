package eapli.base.boardmanagement.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe para realizar testes ao value object BoardDimensions.
 */
public class BoardDimensionsTest {

    private final BoardDimensions subject = BoardDimensions.valueOf(5, 2);

    /**
     * Teste para garantir que a dimensão da board tem de possuir um número positivo de linhas.
     */
    @ParameterizedTest
    @ValueSource(ints = {-12, 0})
    void ensureThatBoardLineIsPositiveNumber(final int numLines) {
        assertThrows(IllegalArgumentException.class, () -> BoardDimensions.valueOf(numLines, 10));
    }

    /**
     * Teste para garantir que a dimensão da board tem de possuir um número positivo de colunas.
     */
    @ParameterizedTest
    @ValueSource(ints = {-12, 0})
    void ensureThatBoardColumnIsPositiveNumber(final int numColumns) {
        assertThrows(IllegalArgumentException.class, () -> BoardDimensions.valueOf(10, numColumns));
    }

    /**
     * Teste para garantir que uma dimensão da board tem o número de linhas esperado.
     */
    @Test
    void ensureBoardDimensionsHasExpectedNumLines() {
        assertEquals(5, subject.numLines());
    }

    /**
     * Teste para garantir que uma dimensão da board não tem o número de linhas esperado.
     */
    @Test
    void ensureBoardDimensionsHasNotExpectedNumLines() {
        assertNotEquals(3, subject.numLines());
    }

    /**
     * Teste para garantir que uma dimensão da board tem o número de colunas esperado.
     */
    @Test
    void ensureBoardDimensionsHasExpectedNumColumns() {
        assertEquals(2, subject.numColumns());
    }

    /**
     * Teste para garantir que uma dimensão da board não tem o número de colunas esperado.
     */
    @Test
    void ensureBoardDimensionsHasNotExpectedNumColumns() {
        assertNotEquals(3, subject.numColumns());
    }
}
