package eapli.base.formativeexammanagement.domain;

import eapli.base.exammanagement.domain.SectionDescription;
import org.junit.jupiter.api.Test;
import org.springframework.data.util.Pair;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe para realizar testes à entidade FormativeExamSection.
 */
public class FormativeExamSectionTest {

    private final SectionDescription DESCRIPTION = SectionDescription.valueOf("Jogadores");

    private final Map<String, Integer> QUESTION_AND_REQUIRED_NUMBER = buildQuestionAndRequiredNumber();

    /**
     * Método para contruir uma secção de um exame formativo.
     *
     * @return A secção de um exame formativo construída.
     */
    private FormativeExamSection buildFormativeExamSection() {
        return new FormativeExamSection(DESCRIPTION, QUESTION_AND_REQUIRED_NUMBER);
    }

    /**
     * Método para contruir uma lista do tipo de perguntas e número necessário.
     *
     * @return A lista do tipo de perguntas e número necessário construída.
     */
    private Map<String, Integer> buildQuestionAndRequiredNumber() {
        Map<String, Integer> questionAndRequiredNumber = new HashMap<>();
        questionAndRequiredNumber.put("Numerical", 1);


        return questionAndRequiredNumber;
    }

    /**
     * Teste para garantir que uma secção de um exame formativo não pode possui uma lista do tipo de perguntas e número
     * necessário nulo.
     */
    @Test
    void ensureFormativeExamSectionHasQuestionAndRequiredNumberNotNull() {
        assertThrows(IllegalArgumentException.class, () -> new FormativeExamSection(DESCRIPTION, null));
    }

    /**
     * Teste para garantir que não é possível adicionar um tipo de pergunta e número necessário nulo a uma secção de um exame formativo.
     */
    @Test
    void ensureAddQuestionAndRequiredNumberNotNull() {
        assertThrows(IllegalArgumentException.class, () -> buildFormativeExamSection().addQuestionAndRequiredNumber(null));
    }

    /**
     * Teste para garantir que uma secção de um exame formativo tem o tipo de perguntas e número necessário esperado.
     */
    @Test
    void ensureFormativeExamSectionHasExpectedQuestionAndRequiredNumber() {
        final FormativeExamSection subject = buildFormativeExamSection();

        Map<String, Integer> expected = new HashMap<>();
        expected.put("Numerical", 1);

        assertEquals(expected, subject.questionAndRequiredNumbers());
    }

    /**
     * Teste para garantir que uma secção de um exame formativo tem a descrição esperada.
     */
    @Test
    void ensureFormativeExamSectionHasExpectedSectionDescription() {
        final FormativeExamSection subject = buildFormativeExamSection();

        assertEquals(SectionDescription.valueOf("Jogadores"), subject.sectionDescription());
    }

    /**
     * Teste para garantir que uma secção de um exame formativo não tem a descrição esperada.
     */
    @Test
    void ensureFormativeExamSectionHasNotExpectedSectionDescription() {
        final FormativeExamSection subject = buildFormativeExamSection();

        assertNotEquals(SectionDescription.valueOf("Pedras"), subject.sectionDescription());
    }

    /**
     * Teste para garantir que duas secções de um exame formativo são iguais para a mesma descrição e tipo de perguntas
     * e número necessário.
     */
    @Test
    void ensureTwoFormativeExamSectionWithSameDescriptionsQuestionsAndRequiredNumberAreTheSame() {
        final FormativeExamSection aFormativeExamSection = buildFormativeExamSection();
        final FormativeExamSection anotherFormativeExamSection = new FormativeExamSectionBuilder().withDescription("Jogadores").
                withQuestionAndRequiredNumber(Pair.of("Numerical", 1)).build();

        assertTrue(aFormativeExamSection.sameAs(anotherFormativeExamSection));
    }


    /**
     * Teste para garantir que duas secções de um exame formativo não são iguais para uma descrição diferente.
     */
    @Test
    void ensureTwoFormativeExamSectionWithDifferentDescriptionsAreNotTheSame() {
        final FormativeExamSection aFormativeExamSection = buildFormativeExamSection();
        final FormativeExamSection anotherFormativeExamSection = new FormativeExamSectionBuilder().withDescription("Animais").
                withQuestionAndRequiredNumber(Pair.of("Numerical", 1)).build();

        assertFalse(aFormativeExamSection.sameAs(anotherFormativeExamSection));
    }

    /**
     * Teste para garantir que duas secções de um exame formativo não são iguais para um tipo de perguntas
     * e número necessário diferente.
     */
    @Test
    void ensureTwoFormativeExamSectionWithQuestionsAndRequiredNumberAreNotTheSame() {
        final FormativeExamSection aFormativeExamSection = buildFormativeExamSection();
        final FormativeExamSection anotherFormativeExamSection = new FormativeExamSectionBuilder().withDescription("Jogadores").
                withQuestionAndRequiredNumber(Pair.of("Matching", 2)).build();

        assertFalse(aFormativeExamSection.sameAs(anotherFormativeExamSection));
    }

    /**
     * Teste para garantir que uma secção de um exame formativo não é a mesma para a mesma instância.
     */
    @Test
    void ensureFormativeExamSectionAreTheSameForTheSameInstance() {
        final FormativeExamSection aFormativeExamSection = new FormativeExamSection();

        assertTrue(aFormativeExamSection.sameAs(aFormativeExamSection));
    }

    /**
     * Teste para garantir que uma secção de um exame formativo não é a mesma para diferentes tipos de objetos.
     */
    @Test
    void ensureFormativeExamSectionNotTheSameForDifferenteObjectTypes() {
        final FormativeExamSection aFormativeExamSection = buildFormativeExamSection();

        assertFalse(aFormativeExamSection.sameAs(DESCRIPTION));
    }

    /**
     * Teste para garantir que a descrição de uma secção de um exame formativo é devolvida com sucesso
     */
    @Test
    void ensureFormativeExamSectionDescriptionIsCorrectlyReturned() {
        assertEquals(this.DESCRIPTION, this.buildFormativeExamSection().sectionDescription());
    }

}
