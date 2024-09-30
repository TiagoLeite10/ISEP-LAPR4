package eapli.base.formativeexammanagement.domain;

import org.junit.Test;
import org.springframework.data.util.Pair;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Classe para realizar testes ao builder FormativeExamSectionBuilder.
 */
public class FormativeExamSectionBuilderTest {

    /**
     * Teste para garantir que é possível construir uma secção de um exame formativo com a descrição e uma pergunta e
     * número necessário.
     */
    @Test
    public void ensureCanBuildFormativeExamSectionWithDescriptionQuestionAndRequiredNumber() {
        final FormativeExamSection subject = new FormativeExamSectionBuilder().withDescription("Secção sobre meteorologia").
                withQuestionAndRequiredNumber(Pair.of("Matching", 2)).build();
        assertNotNull(subject);
    }

    /**
     * Teste para garantir que não é possível construir uma secção de um exame formativo sem os dados necessários.
     */
    @Test
    public void ensureCannotBuildFormativeExamSectionWithoutData() {
        assertThrows(IllegalStateException.class, () -> new FormativeExamSectionBuilder().build());
    }

    /**
     * Teste para garantir que não é possível construir uma secção de um exame formativo com a descrição nula.
     */
    @Test
    public void ensureCannotBuildFormativeExamSectionWithNullDescription() {
        assertThrows(IllegalArgumentException.class, () -> new FormativeExamSectionBuilder().withDescription(null).build());
    }
}
