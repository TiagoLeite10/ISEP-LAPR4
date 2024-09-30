package eapli.base.questionmanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe para realizar testes à classe Answer.
 */
public class AnswerTest {

    private final String CORRECT_ANSWER = "Neil Armstrong";

    private final String FEEDBACK = "Neil Armstrong was an american astronaut";

    /**
     * Método para contruir uma resposta.
     *
     * @return A resposta construída.
     */
    private Answer buildAnswer() {
        return new Answer(CORRECT_ANSWER, FEEDBACK);
    }

    /**
     * Teste para garantir que uma resposta tem de possuir uma resposta correta.
     */
    @Test
    void ensureAnswerHasCorrectAnswer() {
        assertThrows(IllegalArgumentException.class, () -> new Answer(null, FEEDBACK));
    }

    /**
     * Teste para garantir que uma resposta tem a resposta correta esperada.
     */
    @Test
    void ensureAnswerHasExpectedCorrectAnswer() {
        final Answer subject = buildAnswer();

        assertEquals(CORRECT_ANSWER, subject.correctAnswer());
    }

    /**
     * Teste para garantir que uma resposta não tem a resposta correta esperada.
     */
    @Test
    void ensureAnswerHasExpectedNotCorrectAnswer() {
        final Answer subject = buildAnswer();

        assertNotEquals("Guita Pimpolho", subject.correctAnswer());
    }

    /**
     * Teste para garantir que uma resposta tem o feedback esperado.
     */
    @Test
    void ensureAnswerHasExpectedFeedback() {
        final Answer subject = buildAnswer();

        assertEquals(FEEDBACK, subject.feedback());
    }

    /**
     * Teste para garantir que uma resposta não tem o feedback esperado.
     */
    @Test
    void ensureAnswerHasExpectedNotFeedback() {
        final Answer subject = buildAnswer();

        assertNotEquals("Guita Pimpolho was an american astronaut", subject.feedback());
    }

    /**
     * Teste para garantir que duas respostas são iguais para a mesma resposta correta e feedback.
     */
    @Test
    void ensureTwoAnswerWithSameCorrectAnswersFeedbacksAreTheSame() {
        final Answer aAnswer = buildAnswer();
        final Answer anotherAnswer = new Answer(CORRECT_ANSWER, FEEDBACK);

        assertTrue(aAnswer.sameAs(anotherAnswer));
    }

    /**
     * Teste para garantir que duas respostas não são iguais para uma resposta correta diferente.
     */
    @Test
    void ensureTwoAnswerWithDifferentCorrectAnswersAreNotTheSame() {
        final Answer aAnswer = buildAnswer();
        final Answer anotherAnswer = new Answer("Elon Musk", FEEDBACK);

        assertFalse(aAnswer.sameAs(anotherAnswer));
    }

    /**
     * Teste para garantir que duas respostas não são iguais para um feedback diferente.
     */
    @Test
    void ensureTwoAnswerWithDifferentFeedbacksAreNotTheSame() {
        final Answer aAnswer = buildAnswer();
        final Answer anotherAnswer = new Answer(CORRECT_ANSWER, "Elon Mush was an american astronaut");

        assertFalse(aAnswer.sameAs(anotherAnswer));
    }

    /**
     * Teste para garantir que uma resposta é o mesmo para a mesma instância.
     */
    @Test
    void ensureAnswerAreTheSameForTheSameInstance() {
        final Answer aAnswer = new Answer();

        assertTrue(aAnswer.sameAs(aAnswer));
    }

    /**
     * Teste para garantir que uma resposta não é o mesmo para diferentes tipos de objetos.
     */
    @Test
    void ensureAnswerNotTheSameForDifferenteObjectTypes() {
        final Answer aAnswer = buildAnswer();

        assertFalse(aAnswer.sameAs(CORRECT_ANSWER));
    }
}
