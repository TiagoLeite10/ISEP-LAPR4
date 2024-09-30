package eapli.base.formativeexammanagement.domain;

import eapli.base.exammanagement.domain.*;

import static eapli.base.BuilderHelper.buildDateTime;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Classe para realizar testes à entidade FormativeExam.
 */
public class FormativeExamTest {

    private final ExamTitle TITLE = ExamTitle.valueOf("Exame sobre jogadores");

    private final Calendar OPENING_DATE = buildDateTime(3000, 7, 26, 0, 0);
    private final Calendar CLOSE_DATE = buildDateTime(3000, 7, 29, 23, 59);
    private final ExamDateTime DATE_TIME = ExamDateTime.valueOf(OPENING_DATE, CLOSE_DATE);

    private final Header HEADER = new HeaderBuilder().withDescription("Primeiro exame sobre jogadores").withTypeNote("on-submission").
            withTypeFeedback("after-closing").build();

    private final FormativeExamSection SECTION = new FormativeExamSectionBuilder().withDescription("Secção sobre golos").
            withQuestionAndRequiredNumber(Pair.of("Matching", 2)).build();

    /**
     * Método para contruir um exame formativo.
     *
     * @return O exame formativo construído.
     */
    private FormativeExam buildFormativeExam() {
        final FormativeExam subject = new FormativeExam(TITLE, DATE_TIME, HEADER);
        subject.addFormativeExamSection(SECTION);

        return subject;
    }

    /**
     * Teste para garantir que não é possível criar uma instância da classe FormativeExam com valores nulos.
     */
    @Test
    public void ensureFormativeExamNullIsNotAllowed() {
        assertThrows(IllegalArgumentException.class, () -> new FormativeExam(null, null, null));
    }

    /**
     * Teste para garantir que um exame formativo tem de possuir um título.
     */
    @Test
    void ensureFormativeExamHasTitle() {
        assertThrows(IllegalArgumentException.class, () -> new FormativeExam(null, DATE_TIME, HEADER));
    }

    /**
     * Teste para garantir que um exame formativo tem de possuir uma data de abertura e fecho.
     */
    @Test
    void ensureFormativeExamHasDateTime() {
        assertThrows(IllegalArgumentException.class, () -> new FormativeExam(TITLE, null, HEADER));
    }

    /**
     * Teste para garantir que um exame formativo tem de possuir um cabeçalho.
     */
    @Test
    void ensureFormativeExamHasHeader() {
        assertThrows(IllegalArgumentException.class, () -> new FormativeExam(TITLE, DATE_TIME, null));
    }

    /**
     * Teste para garantir que um exame formativo tem a data válida.
     */
    @Test
    void ensureFormativeExamDateIsValid() {
        final FormativeExam subject = buildFormativeExam();

        assertTrue(subject.validAnswerFormativeExamDate(buildDateTime(2021, 6, 16, 0, 0)));
    }

    /**
     * Teste para garantir que um exame formativo não tem a data válida.
     */
    @Test
    void ensureFormativeExamDateIsNotValid() {
        final FormativeExam subject = buildFormativeExam();

        assertFalse(subject.validAnswerFormativeExamDate(buildDateTime(3000, 8, 15, 0, 0)));
    }

    /**
     * Teste para garantir que um exame formativo tem a descrição esperada.
     */
    @Test
    void ensureFormativeExamHasExpectedDescription() {
        final FormativeExam subject = buildFormativeExam();

        assertEquals("Primeiro exame sobre jogadores", subject.description());
    }

    /**
     * Teste para garantir que um exame formativo não tem a descrição esperada.
     */
    @Test
    void ensureFormativeExamHasNotExpectedDescription() {
        final FormativeExam subject = buildFormativeExam();

        assertNotEquals("Primeiro exame sobre pedras", subject.description());
    }

    /**
     * Teste para garantir que um exame formativo tem o texto da data esperada.
     */
    @Test
    void ensureFormativeExamHasExpectedDateString() {
        final FormativeExam subject = buildFormativeExam();

        assertEquals("26-08-3000 00:00 - 29-08-3000 23:59", subject.dateString());
    }

    /**
     * Teste para garantir que um exame formativo não tem o texto da data esperada.
     */
    @Test
    void ensureFormativeExamHasNotExpectedDateString() {
        final FormativeExam subject = buildFormativeExam();

        assertNotEquals("26-08-2023 00:00 - 29-09-2023 23:59", subject.dateString());
    }

    /**
     * Teste para garantir que dois exames formativos são iguais para o mesmo título, data de abertura e fecho, cabeçalho
     * e secções
     */
    @Test
    void ensureTwoFormativeExamWithSameTitlesDateTimesHeadersSectionsAreTheSame() {
        final FormativeExam aFormativeExam = buildFormativeExam();
        final FormativeExam anotherFormativeExam = new FormativeExamBuilder().withTitle("Exame sobre jogadores")
                .withDateTime(buildDateTime(3000, 7, 26, 0, 0), buildDateTime(3000, 7, 29, 23, 59))
                .withHeader(new HeaderBuilder().withDescription("Primeiro exame sobre jogadores").withTypeNote("on-submission").
                        withTypeFeedback("after-closing").build())
                .withSection(new FormativeExamSectionBuilder().withDescription("Secção sobre golos").
                        withQuestionAndRequiredNumber(Pair.of("Matching", 2)).build()).build();

        assertTrue(aFormativeExam.sameAs(anotherFormativeExam));
    }

    /**
     * Teste para garantir que dois exames formativos não são iguais para um título diferente.
     */
    @Test
    void ensureTwoFormativeExamWithDifferentTitlesAreNotTheSame() {
        final FormativeExam aFormativeExam = buildFormativeExam();
        final FormativeExam anotherFormativeExam = new FormativeExamBuilder().withTitle("Exame sobre jogadores portugueses")
                .withDateTime(buildDateTime(3000, 7, 26, 0, 0), buildDateTime(3000, 7, 29, 23, 59))
                .withHeader(new HeaderBuilder().withDescription("Primeiro exame sobre jogadores").withTypeNote("on-submission").
                        withTypeFeedback("after-closing").build())
                .withSection(new FormativeExamSectionBuilder().withDescription("Secção sobre golos").
                        withQuestionAndRequiredNumber(Pair.of("Matching", 2)).build()).build();

        assertFalse(aFormativeExam.sameAs(anotherFormativeExam));
    }

    /**
     * Teste para garantir que dois exames formativos não são iguais para uma data de abertura e fecho diferente.
     */
    @Test
    void ensureTwoFormativeExamWithDifferentDateTimesAreNotTheSame() {
        final FormativeExam aFormativeExam = buildFormativeExam();
        final FormativeExam anotherFormativeExam = new FormativeExamBuilder().withTitle("Exame sobre jogadores")
                .withDateTime(buildDateTime(3000, 7, 20, 0, 0), buildDateTime(3000, 7, 29, 23, 59))
                .withHeader(new HeaderBuilder().withDescription("Primeiro exame sobre jogadores").withTypeNote("on-submission").
                        withTypeFeedback("after-closing").build())
                .withSection(new FormativeExamSectionBuilder().withDescription("Secção sobre golos").
                        withQuestionAndRequiredNumber(Pair.of("Matching", 2)).build()).build();

        assertFalse(aFormativeExam.sameAs(anotherFormativeExam));
    }

    /**
     * Teste para garantir que dois exames formativos não são iguais para um cabeçalho diferente.
     */
    @Test
    void ensureTwoFormativeExamWithDifferentHeadersAreNotTheSame() {
        final FormativeExam aFormativeExam = buildFormativeExam();
        final FormativeExam anotherFormativeExam = new FormativeExamBuilder().withTitle("Exame sobre jogadores")
                .withDateTime(buildDateTime(3000, 7, 26, 0, 0), buildDateTime(3000, 7, 29, 23, 59))
                .withHeader(new HeaderBuilder().withDescription("Segundo exame sobre jogadores").withTypeNote("on-submission").
                        withTypeFeedback("after-closing").build())
                .withSection(new FormativeExamSectionBuilder().withDescription("Secção sobre golos").
                        withQuestionAndRequiredNumber(Pair.of("Matching", 2)).build()).build();

        assertFalse(aFormativeExam.sameAs(anotherFormativeExam));
    }

    /**
     * Teste para garantir que dois exames formativos não são iguais para uma secção diferente.
     */
    @Test
    void ensureTwoFormativeExamWithDifferentSectionsAreNotTheSame() {
        final FormativeExam aFormativeExam = buildFormativeExam();
        final FormativeExam anotherFormativeExam = new FormativeExamBuilder().withTitle("Exame sobre jogadores")
                .withDateTime(buildDateTime(3000, 7, 26, 0, 0), buildDateTime(3000, 7, 29, 23, 59))
                .withHeader(new HeaderBuilder().withDescription("Primeiro exame sobre jogadores").withTypeNote("on-submission").
                        withTypeFeedback("after-closing").build())
                .withSection(new FormativeExamSectionBuilder().withDescription("Secção sobre remates").
                        withQuestionAndRequiredNumber(Pair.of("Matching", 2)).build()).build();

        assertFalse(aFormativeExam.sameAs(anotherFormativeExam));
    }

    /**
     * Teste para garantir que um exame formativo é o mesmo para a mesma instância.
     */
    @Test
    void ensureFormativeExamAreTheSameForTheSameInstance() {
        final FormativeExam aFormativeExam = new FormativeExam();

        assertTrue(aFormativeExam.sameAs(aFormativeExam));
    }

    /**
     * Teste para garantir que um exame formativo não é o mesmo para diferentes tipos de objetos.
     */
    @Test
    void ensureFormativeExamNotTheSameForDifferenteObjectTypes() {
        final FormativeExam aFormativeExam = buildFormativeExam();

        assertFalse(aFormativeExam.sameAs(TITLE));
    }

    /**
     * Teste para garantir que um exame formativo tem a identidade esperada.
     */
    @Test
    void ensureFormativeExamHasExpectedIdentity() {
        final FormativeExam subject = buildFormativeExam();

        assertEquals(ExamTitle.valueOf("Exame sobre jogadores"), subject.identity());
    }

    /**
     * Teste para garantir que um exame formativo não tem a identidade esperada.
     */
    @Test
    void ensureFormativeExamHasNotExpectedIdentity() {
        final FormativeExam subject = buildFormativeExam();

        assertNotEquals(ExamTitle.valueOf("Exame sobre pedras"), subject.identity());
    }

    /**
     * Teste para garantir que não pode ser adicionada uma secção com valor nulo.
     */
    @Test
    void ensureCannotAddNullSectionToFormativeExam() {
        final FormativeExam subject = buildFormativeExam();
        assertThrows(IllegalArgumentException.class, () -> subject.addFormativeExamSection(null));
    }

    /**
     * Teste para garantir que é adicionada com sucesso uma secção ao exame formativo.
     */
    @Test
    void ensureSuccessfulAddSectionToFormativeExam() {
        final FormativeExam subject = buildFormativeExam();
        FormativeExamSection newSection = new FormativeExamSectionBuilder().withDescription("Secção sobre programação").
                withQuestionAndRequiredNumber(Pair.of("True/False", 2)).build();
        subject.addFormativeExamSection(newSection);
    }

    /**
     * Teste para garantir que as secções de um exame formativo são devolvidas com sucesso.
     */
    @Test
    void ensureExamFormativeSectionsAreReturnedCorrectly() {
        final FormativeExam subject = buildFormativeExam();
        FormativeExamSection newSection = new FormativeExamSectionBuilder().withDescription("Secção sobre programação").
                withQuestionAndRequiredNumber(Pair.of("True/False", 2)).build();
        subject.addFormativeExamSection(newSection);

        List<FormativeExamSection> expectedList = new ArrayList<>();
        expectedList.add(this.SECTION);
        expectedList.add(newSection);

        List<FormativeExamSection> formativeExamSections = subject.formativeExamSections();

        int expectedSize = expectedList.size();
        assertEquals(expectedSize, formativeExamSections.size());

        for (int i = 0; i < expectedSize; i++) {
            assertTrue(formativeExamSections.get(i).sameAs(expectedList.get(i)));
        }
    }

    /**
     * Teste para verificar que não é possível juntar dois exames formativos caso seja passado um exame formativo por
     * parâmetro com valor nulo
     */
    @Test
    void ensureFormativeExamToMergeCannotBeNull() {
        final FormativeExam subject = buildFormativeExam();
        assertThrows(IllegalArgumentException.class, () -> subject.merge(null));
    }

    /**
     * Teste para verificar que é possível juntar dois exames formativos com sucesso.
     */
    @Test
    void ensureFormativeExamIsSuccessfulMerged() {
        final FormativeExam subject = buildFormativeExam();

        // Novo exame formativo
        ExamDateTime dateTime = ExamDateTime.valueOf(buildDateTime(3000, 1, 31, 8, 30),
                buildDateTime(3001, 2, 25, 22, 55));

        Header header = new HeaderBuilder().withDescription("Primeiro exame sobre programação")
                .withTypeNote("after-closing").withTypeFeedback("on-submission").build();

        final FormativeExam expected = new FormativeExam(TITLE, dateTime, header);
        expected.addFormativeExamSection(SECTION);
        FormativeExamSection newSection = new FormativeExamSectionBuilder().withDescription("Secção sobre programação").
                withQuestionAndRequiredNumber(Pair.of("True/False", 2)).build();
        expected.addFormativeExamSection(newSection);
        // --------------------

        subject.merge(expected);

        assertTrue(subject.sameAs(expected));
    }

    /**
     * Teste para garantir que um exame formativo é transformado com sucesso num objeto do tipo exame
     */
    @Test
    void ensureFormativeExamIsSuccessfulTransformedInExam() {
        final FormativeExam formativeExam = buildFormativeExam();
        Exam subject = formativeExam.transformInExamObject();

        Exam expected = new ExamBuilder().withTitle(this.TITLE.toString()).withDateTime(OPENING_DATE, CLOSE_DATE)
                .withHeader(this.HEADER).buildWithoutCheckScore();

        assertEquals(expected.title(), subject.title());
        assertEquals(expected.header(), subject.header());
    }
}
