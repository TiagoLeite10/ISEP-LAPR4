package eapli.base.questionmanagement.domain;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe para realizar testes à classe MultipleChoice.
 */
public class MultipleChoiceTest {

    private final String TEXT = "What is my dog's name?";

    private final QuestionScore SCORE = QuestionScore.valueOf(5F);

    private final Answer ANSWER = new Answer("c", "My mom said so.");

    private final Map<String, String> OPTIONS = buildOptions();

    /**
     * Método para contruir uma questão do tipo escolha múltipla.
     *
     * @return A questão construída.
     */
    private MultipleChoice buildMultipleChoice() {
        return new MultipleChoice(TEXT, SCORE, ANSWER, OPTIONS);
    }

    /**
     * Método para contruir as opções da questão do tipo escolha múltipla.
     *
     * @return As opções.
     */
    private Map<String, String> buildOptions() {
        Map<String, String> options = new HashMap<>();
        options.put("a", "Bea");
        options.put("b", "Antonio");
        options.put("c", "Luna");
        options.put("d", "Bobi");

        return options;
    }

    /**
     * Teste para garantir que é possível construir uma questão do tipo escolha múltipla com o texto, pontuação, resposta e opções.
     */
    @Test
    public void ensureCanBuildMultipleChoiceWithTextScoreAnswerOptions() {
        final MultipleChoice subject = new MultipleChoice(TEXT, SCORE, ANSWER, OPTIONS);
        assertNotNull(subject);
    }

    /**
     * Teste para garantir que não é possível construir uma questão do tipo escolha múltipla com o texto nulo.
     */
    @Test
    public void ensureCannotBuildMultipleChoiceWithNullText() {
        assertThrows(IllegalArgumentException.class, () -> new MultipleChoice(null, SCORE, ANSWER, OPTIONS));
    }

    /**
     * Teste para garantir que não é possível construir uma questão do tipo escolha múltipla com a pontuação nula.
     */
    @Test
    public void ensureCannotBuildMultipleChoiceWithNullScore() {
        assertThrows(IllegalArgumentException.class, () -> new MultipleChoice(TEXT, null, ANSWER, OPTIONS));
    }

    /**
     * Teste para garantir que não é possível construir uma questão do tipo escolha múltipla com a resposta nula.
     */
    @Test
    public void ensureCannotBuildMultipleChoiceWithNullAnswer() {
        assertThrows(IllegalArgumentException.class, () -> new MultipleChoice(TEXT, SCORE, null, OPTIONS));
    }

    /**
     * Teste para garantir que não é possível construir uma questão do tipo escolha múltipla com as opções nulas.
     */
    @Test
    public void ensureCannotBuildMultipleChoiceWithNullOptions() {
        assertThrows(IllegalArgumentException.class, () -> new MultipleChoice(TEXT, SCORE, ANSWER, null));
    }

    /**
     * Teste para garantir que uma questão do tipo escolha múltipla tem o tipo de texto esperada.
     */
    @Test
    void ensureMultipleChoiceHasExpectedTypeText() {
        final MultipleChoice subject = buildMultipleChoice();

        StringBuilder options = new StringBuilder();
        for (String key : OPTIONS.keySet()) {
            options.append(key).append(") ").append(OPTIONS.get(key)).append("\n");
        }

        assertEquals("Multiple Choice\n" + TEXT + "\n" + options, subject.typeText());
    }

    /**
     * Teste para garantir que uma questão do tipo escolha múltipla não tem o tipo de texto esperada.
     */
    @Test
    void ensureMultipleChoiceHasNotExpectedTypeText() {
        final MultipleChoice subject = buildMultipleChoice();

        assertNotEquals("Matching\n" + TEXT, subject.typeText());
    }

    /**
     * Teste para garantir que uma questão do tipo escolha múltipla tem a questão e feedback esperado.
     */
    @Test
    void ensureMultipleChoiceHasExpectedQuestionFeedback() {
        final MultipleChoice subject = buildMultipleChoice();

        assertEquals("Question: " + TEXT + "\nFeedback: " + ANSWER.feedback() + "\n",
                subject.questionFeedback());
    }

    /**
     * Teste para garantir que uma questão do tipo escolha múltipla não tem a questão e feedback esperado.
     */
    @Test
    void ensureMultipleChoiceHasNotExpectedQuestionFeedback() {
        final MultipleChoice subject = buildMultipleChoice();

        assertNotEquals("Question: " + TEXT + "\nFeedback: Cristiano Ronaldo nasceu em 1985 na ilha da Madeira\n",
                subject.questionFeedback());
    }

    /**
     * Teste para garantir que uma questão do tipo escolha múltipla tem o texto esperada.
     */
    @Test
    void ensureMultipleChoiceHasExpectedText() {
        final MultipleChoice subject = buildMultipleChoice();

        String expected = TEXT + "\na) Bea\n" +
                "b) Antonio\n" +
                "c) Luna\n" +
                "d) Bobi\n";

        assertEquals(expected, subject.text());
    }

    /**
     * Teste para garantir que a resposta a uma questão do tipo escolha múltipla está correta.
     */
    @Test
    void ensureAnswerMultipleChoiceIsCorrect() {
        final MultipleChoice subject = buildMultipleChoice();

        assertTrue(subject.isCorrectAnswer("c"));
    }

    /**
     * Teste para garantir que a resposta a uma questão do tipo escolha múltipla não está correta.
     */
    @Test
    void ensureAnswerMultipleChoiceIsNotCorrect() {
        final MultipleChoice subject = buildMultipleChoice();

        assertFalse(subject.isCorrectAnswer("a"));
    }

    /**
     * Teste para garantir que uma questão do tipo escolha múltipla tem a pontuação esperada.
     */
    @Test
    void ensureMultipleChoiceHasExpectedScore() {
        final MultipleChoice subject = buildMultipleChoice();

        assertEquals(5F, subject.score());
    }

    /**
     * Teste para garantir que é possível juntar uma questão do tipo escolha múltipla com outra do mesmo tipo.
     */
    @Test
    void ensureMergeMultipleChoiceWithSameObjectType() {
        final MultipleChoice aMultipleChoice = buildMultipleChoice();
        final MultipleChoice anotherMultipleChoice = new MultipleChoice("Qual destes clubes é do Algarve?", QuestionScore.valueOf(5F),
                new Answer("a", "Farense é um clube da zona do Algarver"), buildOptions());

        aMultipleChoice.merge(anotherMultipleChoice);

        assertEquals(anotherMultipleChoice, aMultipleChoice);
    }

    /**
     * Teste para garantir que não é possível juntar uma questão do tipo escolha múltipla com um tipo de objeto diferente.
     */
    @Test
    void ensureCannotMergeMultipleChoiceWithDifferenteObjectTypes() {
        final MultipleChoice subject = buildMultipleChoice();

        assertThrows(IllegalArgumentException.class, () -> subject.merge(new ShortAnswer(TEXT, SCORE, ANSWER)));
    }

    /**
     * Teste para garantir que duas questões do tipo escolha múltipla são iguais para o mesmo texto, pontuação, resposta e opções.
     */
    @Test
    void ensureTwoMultipleChoiceWithSameTextsScoresAnswersOptionsAreTheSame() {
        final MultipleChoice aMultipleChoice = buildMultipleChoice();
        final MultipleChoice anotherMultipleChoice = new MultipleChoice("What is my dog's name?", QuestionScore.valueOf(5F),
                new Answer("c", "My mom said so."), buildOptions());

        assertTrue(aMultipleChoice.sameAs(anotherMultipleChoice));
    }

    /**
     * Teste para garantir que duas questões do tipo escolha múltipla não são iguais para um texto diferente.
     */
    @Test
    void ensureTwoMultipleChoiceWithDifferentTextsAreNotTheSame() {
        final MultipleChoice aMultipleChoice = buildMultipleChoice();
        final MultipleChoice anotherMultipleChoice = new MultipleChoice("Qual destes clubes não é de Portugal?", QuestionScore.valueOf(5F),
                new Answer("c", "Barcelona é um clube da catalunha"), buildOptions());

        assertFalse(aMultipleChoice.sameAs(anotherMultipleChoice));
    }

    /**
     * Teste para garantir que duas questões do tipo escolha múltipla não são iguais para uma pontuação diferente.
     */
    @Test
    void ensureTwoMultipleChoiceWithDifferentScoresAreNotTheSame() {
        final MultipleChoice aMultipleChoice = buildMultipleChoice();
        final MultipleChoice anotherMultipleChoice = new MultipleChoice("Qual destes clubes não é português?", QuestionScore.valueOf(2F),
                new Answer("c", "Barcelona é um clube da catalunha"), buildOptions());

        assertFalse(aMultipleChoice.sameAs(anotherMultipleChoice));
    }

    /**
     * Teste para garantir que duas questões do tipo escolha múltipla não são iguais para uma resposta diferente.
     */
    @Test
    void ensureTwoMultipleChoiceWithDifferentAnswersAreNotTheSame() {
        final MultipleChoice aMultipleChoice = buildMultipleChoice();
        final MultipleChoice anotherMultipleChoice = new MultipleChoice("Qual destes clubes não é português?", QuestionScore.valueOf(5F),
                new Answer("d", "Farense é um clube do algarve"), buildOptions());

        assertFalse(aMultipleChoice.sameAs(anotherMultipleChoice));
    }

    /**
     * Teste para garantir que duas questões do tipo escolha múltipla não são iguais para umas opções diferentes.
     */
    @Test
    void ensureTwoMultipleChoiceWithDifferentOptionsAreNotTheSame() {
        final MultipleChoice aMultipleChoice = buildMultipleChoice();

        Map<String, String> options = buildOptions();
        options.remove("d");
        final MultipleChoice anotherMultipleChoice = new MultipleChoice("Qual destes clubes não é português?", QuestionScore.valueOf(5F),
                new Answer("c", "Barcelona é um clube da catalunha"), options);

        assertFalse(aMultipleChoice.sameAs(anotherMultipleChoice));
    }

    /**
     * Teste para garantir que uma questão do tipo escolha múltipla é a mesma para a mesma instância.
     */
    @Test
    void ensureMultipleChoiceAreTheSameForTheSameInstance() {
        final MultipleChoice aMultipleChoice = new MultipleChoice();

        assertTrue(aMultipleChoice.sameAs(aMultipleChoice));
    }

    /**
     * Teste para garantir que uma questão do tipo escolha múltipla não é a mesma para diferentes tipos de objetos.
     */
    @Test
    void ensureMultipleChoiceNotTheSameForDifferenteObjectTypes() {
        final MultipleChoice aMultipleChoice = buildMultipleChoice();

        assertFalse(aMultipleChoice.sameAs(SCORE));
    }
}
