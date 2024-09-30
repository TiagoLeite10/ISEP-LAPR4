package eapli.base.clientusermanagement.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Classe para realizar testes ao value object TaxPayerNumber.
 */
public class TaxPayerNumberTest {

    private final TaxPayerNumber subject = TaxPayerNumber.valueOf("218021062");

    /**
     * Teste para garantir que um número de contribuinte não deve ser vazio.
     */
    @Test
    void ensureTaxPayerNumberMustNotBeEmpty() {
        assertThrows(IllegalArgumentException.class, () -> TaxPayerNumber.valueOf(""));
    }

    /**
     * Teste para garantir que um número de contribuinte não deve ser nulo.
     */
    @Test
    void ensureTaxPayerNumberMustNotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> TaxPayerNumber.valueOf(null));
    }

    /**
     * Teste para garantir que um número de contribuintetem de possuir apenas números.
     */
    @Test
    void ensureTaxPayerNumberHasOnlyNumbers() {
        assertThrows(IllegalArgumentException.class, () -> TaxPayerNumber.valueOf("1A1695436"));
    }

    /**
     * Teste para garantir que um número de contribuinte tem de possuir 9 números.
     */
    @Test
    void ensureTaxPayerNumberHasNineNumbers() {
        assertThrows(IllegalArgumentException.class, () -> TaxPayerNumber.valueOf("1316954361"));
    }

    /**
     * Teste para garantir que um número de contribuinte tem de ser válido.
     */
    @ParameterizedTest
    @ValueSource(strings = {"987654320", "734956651", "000000001", "409930486", "813989989", "923415678"})
    void ensureTaxPayerNumberIsValid(final String taxPayerNumber) {
        assertThrows(IllegalArgumentException.class, () -> TaxPayerNumber.valueOf(taxPayerNumber));
    }

    /**
     * Teste para garantir que um número de contribuinte é igual a outro.
     */
    @Test
    void ensureTaxPayerNumberIsSame() {
        assertEquals(0, subject.compareTo(TaxPayerNumber.valueOf("218021062")));
    }

    /**
     * Teste para garantir que um número de contribuinte é posterior a outro.
     */
    @Test
    void ensureTaxPayerNumberIsAfter() {
        assertEquals(1, subject.compareTo(TaxPayerNumber.valueOf("154956651")));
    }

    /**
     * Teste para garantir que um número de contribuinte é anterior a outro.
     */
    @Test
    void ensureTaxPayerNumberIsBefore() {
        assertEquals(-1, subject.compareTo(TaxPayerNumber.valueOf("392458390")));
    }
}
