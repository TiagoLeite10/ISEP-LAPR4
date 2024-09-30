package eapli.base.questionmanagement.domain;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe para realizar testes à classe Matching.
 */
public class MatchingTest {

    private final String TEXT = "Combine capital with country";

    private final QuestionScore SCORE = QuestionScore.valueOf(5F);

    private final Answer ANSWER = new Answer("1)-c) 2)-d) 3)-a) 4)-e) 5)-b)", "Go to Google");

    private final Map<String, String> MATCHING1 = buildMatching1();

    private final Map<String, String> MATCHING2 = buildMatching2();

    private final Map<String, String> MATCHING_ASSOCIATIONS = buildMatchingAssociations();

    /**
     * Método para contruir uma questão do tipo correspondência.
     *
     * @return A questão construída.
     */
    private Matching buildMatching() {
        return new Matching(TEXT, SCORE, ANSWER, MATCHING1, MATCHING2, MATCHING_ASSOCIATIONS);
    }

    /**
     * Método para contruir as opções de correspondência 1.
     *
     * @return As opções de correspondência 1.
     */
    private Map<String, String> buildMatching1() {
        Map<String, String> matching1 = new HashMap<>();
        matching1.put("1", "Canada");
        matching1.put("2", "Italy");
        matching1.put("3", "Japan");
        matching1.put("4", "France");
        matching1.put("5", "Portugal");

        return matching1;
    }

    /**
     * Método para contruir as opções de correspondência 2.
     *
     * @return As opções de correspondência 2.
     */
    private Map<String, String> buildMatching2() {
        Map<String, String> matching2 = new HashMap<>();
        matching2.put("a", "Tokyo");
        matching2.put("b", "Lisbon");
        matching2.put("c", "Ottawa");
        matching2.put("d", "Rome");
        matching2.put("e", "Paris");

        return matching2;
    }

    /**
     * Método para contruir as opções de associação da correspondência.
     *
     * @return As opções de associação da correspondência.
     */
    private Map<String, String> buildMatchingAssociations() {
        Map<String, String> matching2 = new HashMap<>();
        matching2.put("1", "c");
        matching2.put("2", "d");
        matching2.put("3", "a");
        matching2.put("4", "e");
        matching2.put("5", "b");

        return matching2;
    }

    /**
     * Teste para garantir que é possível construir uma questão do tipo correspondência com o texto, pontuação, resposta
     * e as várias opções de correspondência.
     */
    @Test
    public void ensureCanBuildMatchingWithTextScoreAnswerOptions() {
        final Matching subject = new Matching(TEXT, SCORE, ANSWER, MATCHING1, MATCHING2, MATCHING_ASSOCIATIONS);
        assertNotNull(subject);
    }

    /**
     * Teste para garantir que não é possível construir uma questão do tipo correspondência com o texto nulo.
     */
    @Test
    public void ensureCannotBuildMatchingWithNullText() {
        assertThrows(IllegalArgumentException.class, () -> new Matching(null, SCORE, ANSWER, MATCHING1, MATCHING2, MATCHING_ASSOCIATIONS));
    }

    /**
     * Teste para garantir que não é possível construir uma questão do tipo correspondência com a pontuação nula.
     */
    @Test
    public void ensureCannotBuildMatchingWithNullScore() {
        assertThrows(IllegalArgumentException.class, () -> new Matching(TEXT, null, ANSWER, MATCHING1, MATCHING2, MATCHING_ASSOCIATIONS));
    }

    /**
     * Teste para garantir que não é possível construir uma questão do tipo correspondência com a resposta nula.
     */
    @Test
    public void ensureCannotBuildMatchingWithNullAnswer() {
        assertThrows(IllegalArgumentException.class, () -> new Matching(TEXT, SCORE, null, MATCHING1, MATCHING2, MATCHING_ASSOCIATIONS));
    }

    /**
     * Teste para garantir que não é possível construir uma questão do tipo correspondência com as opções de correspondência
     * 1 nulas.
     */
    @Test
    public void ensureCannotBuildMatchingWithNullMatching1() {
        assertThrows(IllegalArgumentException.class, () -> new Matching(TEXT, SCORE, ANSWER, null, MATCHING2, MATCHING_ASSOCIATIONS));
    }

    /**
     * Teste para garantir que não é possível construir uma questão do tipo correspondência com as opções de correspondência
     * 2 nulas.
     */
    @Test
    public void ensureCannotBuildMatchingWithNullMatching2() {
        assertThrows(IllegalArgumentException.class, () -> new Matching(TEXT, SCORE, ANSWER, MATCHING1, null, MATCHING_ASSOCIATIONS));
    }

    /**
     * Teste para garantir que não é possível construir uma questão do tipo correspondência com as opções de associação
     * da correspondência nulas.
     */
    @Test
    public void ensureCannotBuildMatchingWithNullMatchingAssociations() {
        assertThrows(IllegalArgumentException.class, () -> new Matching(TEXT, SCORE, ANSWER, MATCHING1, MATCHING2, null));
    }

    /**
     * Teste para garantir que uma questão do tipo correspondência tem o tipo de texto esperada.
     */
    @Test
    void ensureMatchingHasExpectedTypeText() {
        final Matching subject = buildMatching();

        StringBuilder pairs = new StringBuilder();

        Iterator<Map.Entry<String, String>> matching1EntrySetIterator = MATCHING1.entrySet().iterator();
        Iterator<Map.Entry<String, String>> matching2EntrySetIterator = MATCHING2.entrySet().iterator();

        while (matching1EntrySetIterator.hasNext() && matching2EntrySetIterator.hasNext()) {
            Map.Entry<String, String> matching1Tmp = matching1EntrySetIterator.next();
            Map.Entry<String, String> matching2Tmp = matching2EntrySetIterator.next();
            pairs.append(matching1Tmp.getKey()).append(" ").append(matching1Tmp.getValue()).append(" - ")
                    .append(matching2Tmp.getKey()).append(" ").append(matching2Tmp.getValue()).append("\n");
        }

        assertEquals("Matching\n" + TEXT + "\n" + pairs, subject.typeText());
    }

    /**
     * Teste para garantir que uma questão do tipo correspondência não tem o tipo de texto esperada.
     */
    @Test
    void ensureMatchingHasNotExpectedTypeText() {
        final Matching subject = buildMatching();

        assertNotEquals("Short Answer\n" + TEXT, subject.typeText());
    }

    /**
     * Teste para garantir que uma questão do tipo correspondência tem a questão e feedback esperado.
     */
    @Test
    void ensureMatchingHasExpectedQuestionFeedback() {
        final Matching subject = buildMatching();

        assertEquals("Question: " + TEXT + "\nFeedback: " + ANSWER.feedback() + "\n",
                subject.questionFeedback());
    }

    /**
     * Teste para garantir que uma questão do tipo correspondência não tem a questão e feedback esperado.
     */
    @Test
    void ensureMatchingHasNotExpectedQuestionFeedback() {
        final Matching subject = buildMatching();

        assertNotEquals("Question: " + TEXT + "\nFeedback: Cristiano Ronaldo nasceu em 1985 na ilha da Madeira\n",
                subject.questionFeedback());
    }

    /**
     * Teste para garantir que uma questão do tipo correspondência tem o texto esperada.
     */
    @Test
    void ensureMatchingHasExpectedText() {
        final Matching subject = buildMatching();

        String expected = TEXT + "\n1 Canada - a Tokyo\n" +
                "2 Italy - b Lisbon\n" +
                "3 Japan - c Ottawa\n" +
                "4 France - d Rome\n" +
                "5 Portugal - e Paris\n";

        assertEquals(expected, subject.text());
    }

    /**
     * Teste para garantir que a resposta a uma questão do tipo correspondência está correta.
     */
    @Test
    void ensureAnswerMatchingIsCorrect() {
        final Matching subject = buildMatching();

        assertTrue(subject.isCorrectAnswer("1)-c) 2)-d) 3)-a) 4)-e) 5)-b)"));
    }

    /**
     * Teste para garantir que a resposta a uma questão do tipo correspondência não está correta.
     */
    @Test
    void ensureAnswerMatchingIsNotCorrect() {
        final Matching subject = buildMatching();

        assertFalse(subject.isCorrectAnswer("1)-d) 2)-c) 3)-e) 4)-b) 5)-a)"));
    }

    /**
     * Teste para garantir que uma questão do tipo correspondência tem a pontuação esperada.
     */
    @Test
    void ensureMatchingHasExpectedScore() {
        final Matching subject = buildMatching();

        assertEquals(25F, subject.score());
    }

    /**
     * Teste para garantir que é possível juntar uma questão do tipo correspondência com outra do mesmo tipo.
     */
    @Test
    void ensureMergeMatchingWithSameObjectType() {
        final Matching aMatching = buildMatching();
        final Matching anotherMatching = new Matching("Combine as capitais com o respetivo país", QuestionScore.valueOf(5F),
                new Answer("1)-c) 2)-d) 3)-a) 4)-e) 5)-b)", "É uma questão de cultura geral!"), buildMatching1(),
                buildMatching2(), buildMatchingAssociations());

        aMatching.merge(anotherMatching);

        assertEquals(anotherMatching, aMatching);
    }

    /**
     * Teste para garantir que não é possível juntar uma questão do tipo correspondência com um tipo de objeto diferente.
     */
    @Test
    void ensureCannotMergeMatchingWithDifferenteObjectTypes() {
        final Matching subject = buildMatching();

        assertThrows(IllegalArgumentException.class, () -> subject.merge(new ShortAnswer(TEXT, SCORE, ANSWER)));
    }

    /**
     * Teste para garantir que duas questões do tipo correspondência são iguais para o mesmo texto, pontuação, resposta
     * e as várias opções de correspondência.
     */
    @Test
    void ensureTwoMatchingWithSameTextsScoresAnswersOptionsAreTheSame() {
        final Matching aMatching = buildMatching();
        final Matching anotherMatching = new Matching("Combine capital with country", QuestionScore.valueOf(5F),
                new Answer("1)-c) 2)-d) 3)-a) 4)-e) 5)-b)", "Go to Google"), buildMatching1(),
                buildMatching2(), buildMatchingAssociations());

        assertTrue(aMatching.sameAs(anotherMatching));
    }

    /**
     * Teste para garantir que duas questões do tipo correspondência não são iguais para um texto diferente.
     */
    @Test
    void ensureTwoMatchingWithDifferentTextsAreNotTheSame() {
        final Matching aMatching = buildMatching();
        final Matching anotherMatching = new Matching("Combine as capitais com o respetivo país", QuestionScore.valueOf(5F),
                new Answer("1)-c) 2)-d) 3)-a) 4)-e) 5)-b)", "É uma questão de cultura geral!"), buildMatching1(),
                buildMatching2(), buildMatchingAssociations());

        assertFalse(aMatching.sameAs(anotherMatching));
    }

    /**
     * Teste para garantir que duas questões do tipo correspondência não são iguais para uma pontuação diferente.
     */
    @Test
    void ensureTwoMatchingWithDifferentScoresAreNotTheSame() {
        final Matching aMatching = buildMatching();
        final Matching anotherMatching = new Matching("Combine as capitais com país", QuestionScore.valueOf(10F),
                new Answer("1)-c) 2)-d) 3)-a) 4)-e) 5)-b)", "É uma questão de cultura geral!"), buildMatching1(),
                buildMatching2(), buildMatchingAssociations());

        assertFalse(aMatching.sameAs(anotherMatching));
    }

    /**
     * Teste para garantir que duas questões do tipo correspondência não são iguais para uma resposta diferente.
     */
    @Test
    void ensureTwoMatchingWithDifferentAnswersAreNotTheSame() {
        final Matching aMatching = buildMatching();
        final Matching anotherMatching = new Matching("Combine as capitais com país", QuestionScore.valueOf(10F),
                new Answer("1)-c) 2)-d) 3)-b) 4)-e) 5)-a)", "É uma questão de cultura geral!"), buildMatching1(),
                buildMatching2(), buildMatchingAssociations());

        assertFalse(aMatching.sameAs(anotherMatching));
    }

    /**
     * Teste para garantir que uma questão do tipo correspondência é a mesma para a mesma instância.
     */
    @Test
    void ensureMatchingAreTheSameForTheSameInstance() {
        final Matching aMatching = new Matching();

        assertTrue(aMatching.sameAs(aMatching));
    }

    /**
     * Teste para garantir que uma questão do tipo correspondência não é a mesma para diferentes tipos de objetos.
     */
    @Test
    void ensureMatchingNotTheSameForDifferenteObjectTypes() {
        final Matching aMatching = buildMatching();

        assertFalse(aMatching.sameAs(SCORE));
    }
}
