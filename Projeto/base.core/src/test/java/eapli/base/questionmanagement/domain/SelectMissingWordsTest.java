package eapli.base.questionmanagement.domain;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe para realizar testes à classe SelectMissingWords.
 */
public class SelectMissingWordsTest {

    private final String TEXT = "<option> é um clube fundado em 1893?";

    private final QuestionScore SCORE = QuestionScore.valueOf(2F);

    private final Answer ANSWER = new Answer("Porto", "Porto foi fundado em 1893 por António Nicolau d'Almeida");

    private final Set<String> POSSIBLE_CHOICES = buildPossibleChoices();

    /**
     * Método para contruir uma questão do tipo selecionar palavras ausentes.
     *
     * @return A questão construída.
     */
    private SelectMissingWords buildSelectMissingWords() {
        return new SelectMissingWords(TEXT, SCORE, ANSWER, POSSIBLE_CHOICES);
    }

    /**
     * Método para contruir as possíveis escolhas da questão do tipo selecionar palavras ausentes.
     *
     * @return As possíveis escolhas.
     */
    private Set<String> buildPossibleChoices() {
        Set<String> possibleChoices = new HashSet<>();
        possibleChoices.add("Porto");
        possibleChoices.add("Rio Ave");
        possibleChoices.add("Barcelona");
        possibleChoices.add("Farense");

        return possibleChoices;
    }

    /**
     * Teste para garantir que é possível construir uma questão do tipo selecionar palavras ausentes com o texto, pontuação, resposta e possíveis escolhas.
     */
    @Test
    public void ensureCanBuildSelectMissingWordsWithTextScoreAnswerPossibleChoices() {
        final SelectMissingWords subject = new SelectMissingWords(TEXT, SCORE, ANSWER, POSSIBLE_CHOICES);
        assertNotNull(subject);
    }

    /**
     * Teste para garantir que não é possível construir uma questão do tipo selecionar palavras ausentes com o texto nulo.
     */
    @Test
    public void ensureCannotBuildSelectMissingWordsWithNullText() {
        assertThrows(IllegalArgumentException.class, () -> new SelectMissingWords(null, SCORE, ANSWER, POSSIBLE_CHOICES));
    }

    /**
     * Teste para garantir que não é possível construir uma questão do tipo selecionar palavras ausentes com a pontuação nula.
     */
    @Test
    public void ensureCannotBuildSelectMissingWordsWithNullScore() {
        assertThrows(IllegalArgumentException.class, () -> new SelectMissingWords(TEXT, null, ANSWER, POSSIBLE_CHOICES));
    }

    /**
     * Teste para garantir que não é possível construir uma questão do tipo selecionar palavras ausentes com a resposta nula.
     */
    @Test
    public void ensureCannotBuildSelectMissingWordsWithNullAnswer() {
        assertThrows(IllegalArgumentException.class, () -> new SelectMissingWords(TEXT, SCORE, null, POSSIBLE_CHOICES));
    }

    /**
     * Teste para garantir que não é possível construir uma questão do tipo selecionar palavras ausentes com as possíveis escolhas nulas.
     */
    @Test
    public void ensureCannotBuildSelectMissingWordsWithNullError() {
        assertThrows(IllegalArgumentException.class, () -> new SelectMissingWords(TEXT, SCORE, ANSWER, null));
    }

    /**
     * Teste para garantir que uma questão do tipo selecionar palavras ausentes tem o tipo de texto esperada.
     */
    @Test
    void ensureSelectMissingWordsHasExpectedTypeText() {
        final SelectMissingWords subject = buildSelectMissingWords();

        StringBuilder choices = new StringBuilder();
        for (String key : POSSIBLE_CHOICES) {
            choices.append(key).append("\n");
        }

        assertEquals("Select Missing Words\n" + TEXT + "\nChoices:\n" + choices, subject.typeText());
    }

    /**
     * Teste para garantir que uma questão do tipo selecionar palavras ausentes não tem o tipo de texto esperada.
     */
    @Test
    void ensureSelectMissingWordsHasNotExpectedTypeText() {
        final SelectMissingWords subject = buildSelectMissingWords();

        assertNotEquals("Matching\n" + TEXT, subject.typeText());
    }

    /**
     * Teste para garantir que uma questão do tipo selecionar palavras ausentes tem a questão e feedback esperado.
     */
    @Test
    void ensureSelectMissingWordsHasExpectedQuestionFeedback() {
        final SelectMissingWords subject = buildSelectMissingWords();

        assertEquals("Question: " + TEXT + "\nFeedback: " + ANSWER.feedback() + "\n",
                subject.questionFeedback());
    }

    /**
     * Teste para garantir que uma questão do tipo selecionar palavras ausentes não tem a questão e feedback esperado.
     */
    @Test
    void ensureSelectMissingWordsHasNotExpectedQuestionFeedback() {
        final SelectMissingWords subject = buildSelectMissingWords();

        assertNotEquals("Question: " + TEXT + "\nFeedback: Porto foi fundado em 1893\n",
                subject.questionFeedback());
    }

    /**
     * Teste para garantir que uma questão do tipo selecionar palavras ausentes tem o texto esperada.
     */
    @Test
    void ensureSelectMissingWordsHasExpectedText() {
        final SelectMissingWords subject = buildSelectMissingWords();

        String expected = TEXT + "\nChoices:\n" +
                "Farense\n" +
                "Porto\n" +
                "Barcelona\n" +
                "Rio Ave\n";

        assertEquals(expected, subject.text());
    }

    /**
     * Teste para garantir que a resposta a uma questão do tipo selecionar palavras ausentes está correta.
     */
    @Test
    void ensureAnswerSelectMissingWordsIsCorrect() {
        final SelectMissingWords subject = buildSelectMissingWords();

        assertTrue(subject.isCorrectAnswer("Porto"));
    }

    /**
     * Teste para garantir que a resposta a uma questão do tipo selecionar palavras ausentes não está correta.
     */
    @Test
    void ensureAnswerSelectMissingWordsIsNotCorrect() {
        final SelectMissingWords subject = buildSelectMissingWords();

        assertFalse(subject.isCorrectAnswer("Barcelona"));
    }

    /**
     * Teste para garantir que uma questão do tipo selecionar palavras ausentes tem a pontuação esperada.
     */
    @Test
    void ensureSelectMissingWordsHasExpectedScore() {
        final SelectMissingWords subject = buildSelectMissingWords();

        assertEquals(2F, subject.score());
    }

    /**
     * Teste para garantir que é possível juntar uma questão do tipo selecionar palavras ausentes com outra do mesmo tipo.
     */
    @Test
    void ensureMergeSelectMissingWordsWithSameObjectType() {
        final SelectMissingWords aSelectMissingWords = buildSelectMissingWords();
        final SelectMissingWords anotherSelectMissingWords = new SelectMissingWords("<option> é um clube português fundado em 1893", QuestionScore.valueOf(5F),
                new Answer("Porto", "Porto foi fundado em 1893 por António Nicolau d'Almeida"), buildPossibleChoices());

        aSelectMissingWords.merge(anotherSelectMissingWords);

        assertEquals(anotherSelectMissingWords, aSelectMissingWords);
    }

    /**
     * Teste para garantir que não é possível juntar uma questão do tipo selecionar palavras ausentes com um tipo de objeto diferente.
     */
    @Test
    void ensureCannotMergeSelectMissingWordsWithDifferenteObjectTypes() {
        final SelectMissingWords subject = buildSelectMissingWords();

        assertThrows(IllegalArgumentException.class, () -> subject.merge(new ShortAnswer(TEXT, SCORE, ANSWER)));
    }

    /**
     * Teste para garantir que duas questões do tipo selecionar palavras ausentes são iguais para o mesmo texto, pontuação, resposta e possíveis escolhas.
     */
    @Test
    void ensureTwoSelectMissingWordsWithSameTextsScoresAnswersPossibleChoicesAreTheSame() {
        final SelectMissingWords aSelectMissingWords = buildSelectMissingWords();
        final SelectMissingWords anotherSelectMissingWords = new SelectMissingWords("<option> é um clube fundado em 1893?", QuestionScore.valueOf(2F),
                new Answer("Porto", "Porto foi fundado em 1893 por António Nicolau d'Almeida"), buildPossibleChoices());

        assertTrue(aSelectMissingWords.sameAs(anotherSelectMissingWords));
    }

    /**
     * Teste para garantir que duas questões do tipo selecionar palavras ausentes não são iguais para um texto diferente.
     */
    @Test
    void ensureTwoSelectMissingWordsWithDifferentTextsAreNotTheSame() {
        final SelectMissingWords aSelectMissingWords = buildSelectMissingWords();
        final SelectMissingWords anotherSelectMissingWords = new SelectMissingWords("<option> é um clube português fundado em 1893?", QuestionScore.valueOf(2F),
                new Answer("Porto", "Porto foi fundado em 1893 por António Nicolau d'Almeida"), buildPossibleChoices());

        assertFalse(aSelectMissingWords.sameAs(anotherSelectMissingWords));
    }

    /**
     * Teste para garantir que duas questões do tipo selecionar palavras ausentes não são iguais para uma pontuação diferente.
     */
    @Test
    void ensureTwoSelectMissingWordsWithDifferentScoresAreNotTheSame() {
        final SelectMissingWords aSelectMissingWords = buildSelectMissingWords();
        final SelectMissingWords anotherSelectMissingWords = new SelectMissingWords("<option> é um clube fundado em 1893?", QuestionScore.valueOf(3F),
                new Answer("Porto", "Porto foi fundado em 1893 por António Nicolau d'Almeida"), buildPossibleChoices());

        assertFalse(aSelectMissingWords.sameAs(anotherSelectMissingWords));
    }

    /**
     * Teste para garantir que duas questões do tipo selecionar palavras ausentes não são iguais para uma resposta diferente.
     */
    @Test
    void ensureTwoSelectMissingWordsWithDifferentAnswersAreNotTheSame() {
        final SelectMissingWords aSelectMissingWords = buildSelectMissingWords();
        final SelectMissingWords anotherSelectMissingWords = new SelectMissingWords("<option> é um clube fundado em 1893?", QuestionScore.valueOf(2F),
                new Answer("Farense", "Farense foi fundado em 1893 por António Nicolau d'Almeida"), buildPossibleChoices());

        assertFalse(aSelectMissingWords.sameAs(anotherSelectMissingWords));
    }

    /**
     * Teste para garantir que duas questões do tipo selecionar palavras ausentes não são iguais para umas possíveis escolhas diferentes.
     */
    @Test
    void ensureTwoSelectMissingWordsWithDifferentPossibleChoicesAreNotTheSame() {
        final SelectMissingWords aSelectMissingWords = buildSelectMissingWords();

        Set<String> possibleChoices = buildPossibleChoices();
        possibleChoices.remove("Farense");
        final SelectMissingWords anotherSelectMissingWords = new SelectMissingWords("<option> é um clube fundado em 1893?", QuestionScore.valueOf(2F),
                new Answer("Farense", "Farense foi fundado em 1893 por António Nicolau d'Almeida"), possibleChoices);

        assertFalse(aSelectMissingWords.sameAs(anotherSelectMissingWords));
    }

    /**
     * Teste para garantir que uma questão do tipo selecionar palavras ausentes é a mesma para a mesma instância.
     */
    @Test
    void ensureSelectMissingWordsAreTheSameForTheSameInstance() {
        final SelectMissingWords aSelectMissingWords = new SelectMissingWords();

        assertTrue(aSelectMissingWords.sameAs(aSelectMissingWords));
    }

    /**
     * Teste para garantir que uma questão do tipo selecionar palavras ausentes não é a mesma para diferentes tipos de objetos.
     */
    @Test
    void ensureSelectMissingWordsNotTheSameForDifferenteObjectTypes() {
        final SelectMissingWords aSelectMissingWords = buildSelectMissingWords();

        assertFalse(aSelectMissingWords.sameAs(SCORE));
    }
}
