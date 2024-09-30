package eapli.base.postitmanagement.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe para realizar testes ao value object PostItPosition.
 */
public class PostItPositionTest {

    private final PostItPosition subject = PostItPosition.valueOf(5, 2);

    /**
     * Teste para garantir que a linha da posição de um post-it tem de possuir um número positivo.
     */
    @ParameterizedTest
    @ValueSource(ints = {-12, 0})
    public void ensureThatPostItPositionLineIsPositiveNumber(final int line) {
        assertThrows(IllegalArgumentException.class, () -> new PostItPosition(line, 10));
    }

    /**
     * Teste para garantir que a coluna da posição de um post-it tem de possuir um número positivo.
     */
    @ParameterizedTest
    @ValueSource(ints = {-12, 0})
    public void ensureThatPostItPositionColumnIsPositiveNumber(final int column) {
        assertThrows(IllegalArgumentException.class, () -> new PostItPosition(10, column));
    }

    /**
     * Teste para garantir que uma coluna da posição de um post-it tem a linha esperada.
     */
    @Test
    void ensurePostItPositionHasExpectedLine() {
        assertEquals(5, subject.line());
    }

    /**
     * Teste para garantir que uma coluna da posição de um post-it não a linha esperada.
     */
    @Test
    void ensurePostItPositionHasNotExpectedLine() {
        assertNotEquals(3, subject.line());
    }

    /**
     * Teste para garantir que uma coluna da posição de um post-it tem a coluna esperada.
     */
    @Test
    void ensurePostItPositionHasExpectedColumn() {
        assertEquals(2, subject.column());
    }

    /**
     * Teste para garantir que uma coluna da posição de um post-it não tem a coluna esperada.
     */
    @Test
    void ensurePostItPositionHasNotExpectedColumn() {
        assertNotEquals(3, subject.column());
    }
}
