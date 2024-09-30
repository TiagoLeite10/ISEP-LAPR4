package eapli.base.exammanagement.domain;

import eapli.base.questionmanagement.domain.Answer;
import eapli.base.questionmanagement.domain.Question;
import eapli.base.questionmanagement.domain.QuestionScore;
import eapli.base.questionmanagement.domain.TrueFalse;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Classe para realizar testes ao builder ExamSectionBuilder.
 */
public class ExamSectionBuilderTest {

    /**
     * Método para contruir uma questão do tipo verdadeiro/falso.
     *
     * @return A questão construída.
     */
    private Question buildQuestion() {
        String text = "Elon Musk é português?";
        QuestionScore score = QuestionScore.valueOf(2F);
        Answer answer = new Answer("false", "Elon Musk é americano");

        return new TrueFalse(text, score, answer);
    }

    /**
     * Teste para garantir que é possível construir uma secção com a descrição e uma questão.
     */
    @Test
    public void ensureCanBuildSectionWithDescriptionQuestion() {
        final ExamSection subject = new ExamSectionBuilder().withDescription("Secção sobre meteorologia").withQuestion(buildQuestion()).build();
        assertNotNull(subject);
    }

    /**
     * Teste para garantir que não é possível construir uma secção sem os dados necessários.
     */
    @Test
    public void ensureCannotBuildSectionWithoutData() {
        assertThrows(IllegalStateException.class, () -> new ExamSectionBuilder().build());
    }

    /**
     * Teste para garantir que não é possível construir uma secção com a descrição nula.
     */
    @Test
    public void ensureCannotBuildSectionWithNullDescription() {
        assertThrows(IllegalArgumentException.class, () -> new ExamSectionBuilder().withDescription(null).build());
    }
}
