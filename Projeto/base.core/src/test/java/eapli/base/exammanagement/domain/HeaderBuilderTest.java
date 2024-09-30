package eapli.base.exammanagement.domain;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe para realizar testes ao builder HeaderBuilder.
 */
public class HeaderBuilderTest {

    private final String DESCRIPTION = "Primeiro exame sobre rochas";

    private final String TYPE_NOTE = "none";

    private final String TYPE_FEEDBACK = "on-submission";

    /**
     * Teste para garantir que é possível construir um cabeçalho com a descrição, tipo de nota e tipo de feedback.
     */
    @Test
    public void ensureCanBuildHeaderWithDescriptionTypeNoteTypeFeedback() {
        final Header subject = new HeaderBuilder().with(DESCRIPTION, TYPE_NOTE, TYPE_FEEDBACK).build();
        assertNotNull(subject);
    }

    /**
     * Teste para garantir que não é possível construir um cabeçalho com a descrição nula.
     */
    @Test
    public void ensureCannotBuildHeaderWithNullDescription() {
        assertThrows(IllegalArgumentException.class, () -> new HeaderBuilder().withDescription(null).withTypeNote(TYPE_NOTE).
                withTypeFeedback(TYPE_FEEDBACK).build());
    }

    /**
     * Teste para garantir que é possível construir um cabeçalho sem descrição.
     */
    @Test
    public void ensureCanBuildHeaderWithoutDescription() {
        assertDoesNotThrow(() -> new HeaderBuilder().withTypeNote(TYPE_NOTE).withTypeFeedback(TYPE_FEEDBACK).build());
    }

    /**
     * Teste para garantir que não é possível construir um cabeçalho com tipo de nota nulo.
     */
    @Test
    public void ensureCannotBuildHeaderWithNullTypeNote() {
        assertThrows(IllegalArgumentException.class, () -> new HeaderBuilder().withDescription(DESCRIPTION).withTypeNote(null).
                withTypeFeedback(TYPE_FEEDBACK).build());
    }

    /**
     * Teste para garantir que não é possível construir um cabeçalho sem tipo de nota.
     */
    @Test
    public void ensureCannotBuildHeaderWithoutTypeNote() {
        assertThrows(IllegalArgumentException.class, () -> new HeaderBuilder().withDescription(DESCRIPTION).
                withTypeFeedback(TYPE_FEEDBACK).build());
    }

    /**
     * Teste para garantir que não é possível construir um cabeçalho com um tipo de nota inválido.
     */
    @Test
    public void ensureCannotBuildHeaderWithTypeNoteInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new HeaderBuilder().withDescription(DESCRIPTION).withTypeNote("after").
                withTypeFeedback(TYPE_FEEDBACK).build());
    }

    /**
     * Teste para garantir que não é possível construir um cabeçalho com tipo de feedback nulo.
     */
    @Test
    public void ensureCannotBuildHeaderWithNullTypeFeedback() {
        assertThrows(IllegalArgumentException.class, () -> new HeaderBuilder().withDescription(DESCRIPTION).withTypeNote(TYPE_NOTE).
                withTypeFeedback(null).build());
    }

    /**
     * Teste para garantir que não é possível construir um cabeçalho sem tipo de feedback.
     */
    @Test
    public void ensureCannotBuildHeaderWithoutTypeFeedback() {
        assertThrows(IllegalArgumentException.class, () -> new HeaderBuilder().withDescription(DESCRIPTION).
                withTypeNote(TYPE_NOTE).build());
    }

    /**
     * Teste para garantir que não é possível construir um cabeçalho com um tipo de feedback inválido.
     */
    @Test
    public void ensureCannotBuildHeaderWithTypeFeedbackInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new HeaderBuilder().withDescription(DESCRIPTION).withTypeNote(TYPE_NOTE).
                withTypeFeedback("after").build());
    }
}
