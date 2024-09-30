package eapli.base.exammanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Classe para realizar testes à classe Header.
 */
public class HeaderTest {

    private final HeaderDescription DESCRIPTION = HeaderDescription.valueOf("Primeiro exame sobre rochas");

    private final HeaderType TYPE_NOTE = HeaderType.AFTER_CLOSING;

    private final HeaderType TYPE_FEEDBACK = HeaderType.ON_SUBMISSION;

    /**
     * Método para contruir um cabeçalho.
     *
     * @return O cabeçalho construído.
     */
    private Header buildHeader() {
        return new Header(DESCRIPTION, TYPE_NOTE, TYPE_FEEDBACK);
    }

    /**
     * Teste para garantir que um cabeçalho tem de possuir um tipo de nota.
     */
    @Test
    void ensureHeaderHasTypeNote() {
        assertThrows(IllegalArgumentException.class, () -> new Header(DESCRIPTION, null, TYPE_FEEDBACK));
    }

    /**
     * Teste para garantir que um cabeçalho tem de possuir um tipo de feedback.
     */
    @Test
    void ensureHeaderHasTypeFeedback() {
        assertThrows(IllegalArgumentException.class, () -> new Header(DESCRIPTION, TYPE_NOTE, null));
    }

    /**
     * Teste para garantir que um cabeçalho tem a descrição esperada.
     */
    @Test
    void ensureHeaderHasExpectedDescription() {
        final Header subject = buildHeader();

        assertEquals(DESCRIPTION, subject.description());
    }

    /**
     * Teste para garantir que dois cabeçalhos são iguais para a mesma descrição, tipo de nota e tipo de feedback.
     */
    @Test
    void ensureTwoHeaderWithSameDescriptionsTypesNoteTypesFeedbackAreTheSame() {
        final Header aHeader = buildHeader();
        final Header anotherHeader = new HeaderBuilder().withDescription("Primeiro exame sobre rochas").withTypeNote("after-closing").
                withTypeFeedback("on-submission").build();

        assertTrue(aHeader.sameAs(anotherHeader));
    }

    /**
     * Teste para garantir que dois cabeçalhos não são iguais para uma descrição diferente.
     */
    @Test
    void ensureTwoHeaderWithDifferentDescriptionsAreNotTheSame() {
        final Header aHeader = buildHeader();
        final Header anotherHeader = new HeaderBuilder().withDescription("Primeiro exame sobre pedras").withTypeNote("after-closing").
                withTypeFeedback("on-submission").build();

        assertFalse(aHeader.sameAs(anotherHeader));
    }

    /**
     * Teste para garantir que dois cabeçalhos não são iguais para um tipo de nota diferente.
     */
    @Test
    void ensureTwoHeaderWithDifferentTypesNoteAreNotTheSame() {
        final Header aHeader = buildHeader();
        final Header anotherHeader = new HeaderBuilder().withDescription("Primeiro exame sobre rochas").withTypeNote("none").
                withTypeFeedback("on-submission").build();

        assertFalse(aHeader.sameAs(anotherHeader));
    }

    /**
     * Teste para garantir que dois cabeçalhos não são iguais para um tipo de feedback diferente.
     */
    @Test
    void ensureTwoHeaderWithDifferentTypesFeedbackAreNotTheSame() {
        final Header aHeader = buildHeader();
        final Header anotherHeader = new HeaderBuilder().withDescription("Primeiro exame sobre rochas").withTypeNote("after-closing").
                withTypeFeedback("none").build();

        assertFalse(aHeader.sameAs(anotherHeader));
    }

    /**
     * Teste para garantir que um cabeçalho é o mesmo para a mesma instância.
     */
    @Test
    void ensureHeaderAreTheSameForTheSameInstance() {
        final Header aHeader = new Header();

        assertTrue(aHeader.sameAs(aHeader));
    }

    /**
     * Teste para garantir que um cabeçalho não é o mesmo para diferentes tipos de objetos.
     */
    @Test
    void ensureHeaderNotTheSameForDifferenteObjectTypes() {
        final Header aHeader = buildHeader();

        assertFalse(aHeader.sameAs(DESCRIPTION));
    }
}
