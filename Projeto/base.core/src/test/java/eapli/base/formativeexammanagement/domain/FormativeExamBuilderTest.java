package eapli.base.formativeexammanagement.domain;

import eapli.base.BuilderHelper;
import eapli.base.exammanagement.domain.Header;
import eapli.base.exammanagement.domain.HeaderBuilder;
import eapli.base.exammanagement.domain.SectionDescription;
import org.junit.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Classe para realizar testes ao builder FormativeExamBuilder.
 */
public class FormativeExamBuilderTest {

    private final String TITLE = "Exame sobre jogadores";

    private final Calendar OPENING_DATE_TIME = BuilderHelper.buildDateTime(3000, 7, 26, 0, 0);

    private final Calendar CLOSING_DATE_TIME = BuilderHelper.buildDateTime(3000, 7, 29, 23, 59);

    private final Header HEADER = new HeaderBuilder().withDescription("Primeiro exame sobre jogadores").withTypeNote("on-submission").
            withTypeFeedback("after-closing").build();

    private final FormativeExamSection SECTION = buildFormativeExamSection();

    /**
     * Método para contruir uma secção de um exame formativo.
     *
     * @return A secção do exame construída.
     */
    private FormativeExamSection buildFormativeExamSection() {
        Map<String, Integer> questionAndRequiredNumber = new HashMap<>();
        questionAndRequiredNumber.put("Numerical", 1);

        return new FormativeExamSection(SectionDescription.valueOf("Jogadores de outra galáxia"), questionAndRequiredNumber);
    }

    /**
     * Teste para garantir que é possível construir um exame formativo com o título, data de abertura, data de fecho, cabeçalho e secção.
     */
    @Test
    public void ensureCanBuildFormativeExamWithTitleOpeningDateTimeClosingDateTimeHeader() {
        final FormativeExam subject = new FormativeExamBuilder().with(TITLE, OPENING_DATE_TIME, CLOSING_DATE_TIME, HEADER).
                withSection(SECTION).build();
        assertNotNull(subject);
    }

    /**
     * Teste para garantir que não é possível construir um exame formativo com o título nulo.
     */
    @Test
    public void ensureCannotBuildFormativeExamWithNullTitle() {
        assertThrows(IllegalArgumentException.class, () -> new FormativeExamBuilder().withTitle(null).
                withDateTime(OPENING_DATE_TIME, CLOSING_DATE_TIME).withHeader(HEADER).withSection(SECTION).build());
    }

    /**
     * Teste para garantir que não é possível construir um exame formativo sem título.
     */
    @Test
    public void ensureCannotBuildFormativeExamWithoutTitle() {
        assertThrows(IllegalStateException.class, () -> new FormativeExamBuilder().withDateTime(OPENING_DATE_TIME, CLOSING_DATE_TIME).
                withHeader(HEADER).withSection(SECTION).build());
    }

    /**
     * Teste para garantir que não é possível construir um exame formativo com a data de abertura nula.
     */
    @Test
    public void ensureCannotBuildFormativeExamWithNullOpeningDateTime() {
        assertThrows(IllegalArgumentException.class, () -> new FormativeExamBuilder().withTitle(TITLE).
                withDateTime(null, CLOSING_DATE_TIME).withHeader(HEADER).withSection(SECTION).build());
    }

    /**
     * Teste para garantir que não é possível construir um exame formativo com a data de fecho nula.
     */
    @Test
    public void ensureCannotBuildFormativeExamWithNullClosingDateTime() {
        assertThrows(IllegalArgumentException.class, () -> new FormativeExamBuilder().withTitle(TITLE).
                withDateTime(OPENING_DATE_TIME, null).withHeader(HEADER).withSection(SECTION).build());
    }

    /**
     * Teste para garantir que não é possível construir um exame formativo com o cabeçalho nulo.
     */
    @Test
    public void ensureCannotBuildFormativeExamWithNullHeader() {
        assertThrows(IllegalStateException.class, () -> new FormativeExamBuilder().withTitle(TITLE).
                withDateTime(OPENING_DATE_TIME, CLOSING_DATE_TIME).withHeader(null).withSection(SECTION).build());
    }

    /**
     * Teste para garantir que não é possível construir um exame formativo sem cabeçalho.
     */
    @Test
    public void ensureCannotBuildFormativeExamWithoutHeader() {
        assertThrows(IllegalStateException.class, () -> new FormativeExamBuilder().withTitle(TITLE).
                withDateTime(OPENING_DATE_TIME, CLOSING_DATE_TIME).withSection(SECTION).build());
    }

    /**
     * Teste para garantir que não é possível construir um exame formativo com uma secção nula.
     */
    @Test
    public void ensureCannotBuildExamFormativeWithNullSection() {
        assertThrows(IllegalArgumentException.class, () -> new FormativeExamBuilder().withTitle(TITLE).
                withDateTime(OPENING_DATE_TIME, CLOSING_DATE_TIME).withHeader(HEADER).withSection(null).build());
    }
}
