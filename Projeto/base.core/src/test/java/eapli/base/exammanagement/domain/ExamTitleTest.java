package eapli.base.exammanagement.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Classe para realizar testes ao value object ExamTitle.
 */
public class ExamTitleTest {

    private final ExamTitle subject = ExamTitle.valueOf("Exame sobre rochas");

    /**
     * Teste para garantir que o título de um exame não deve ser vazio.
     */
    @Test
    void ensureExamTitleMustNotBeEmpty() {
        assertThrows(IllegalArgumentException.class, () -> ExamTitle.valueOf(""));
    }

    /**
     * Teste para garantir que o título de um exame não deve ser nulo.
     */
    @Test
    void ensureExamTitleMustNotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> ExamTitle.valueOf(null));
    }

    /**
     * Teste para garantir que o título de um exame não deve possuir espaços iniciais ou finais.
     */
    @ParameterizedTest
    @ValueSource(strings = {"  Exame sobre rochas  ", "Exame sobre rochas   ", "  Exame sobre rochas"})
    void ensureExamTitleMustNotContainLeadingSpaces(final String title) {
        assertThrows(IllegalArgumentException.class, () -> ExamTitle.valueOf(title));
    }

    /**
     * Teste para garantir que o título de um exame não deve possuir mais de 50 caracteres.
     */
    @Test
    void ensureExamTitleMustNotContainMoreThanFiftyCharacters() {
        assertThrows(IllegalArgumentException.class, () -> ExamTitle.valueOf("Exame de rochas sobre as rochas " +
                "do século XX que têm mais dez metros"));
    }

    /**
     * Teste para garantir que o título de um exame é igual a outro.
     */
    @Test
    void ensureExamTitleIsSame() {
        assertEquals(0, subject.compareTo(ExamTitle.valueOf("Exame sobre rochas")));
    }

    /**
     * Teste para garantir que o título de um exame é posterior a outro.
     */
    @Test
    void ensureExamTitleIsAfter() {
        assertEquals(1, subject.compareTo(ExamTitle.valueOf("Exame sobre queijos")));
    }

    /**
     * Teste para garantir que o título de um exame é anterior a outro.
     */
    @Test
    void ensureExamTitleIsBefore() {
        assertEquals(-1, subject.compareTo(ExamTitle.valueOf("Exame sobre sobremesas")));
    }
}
