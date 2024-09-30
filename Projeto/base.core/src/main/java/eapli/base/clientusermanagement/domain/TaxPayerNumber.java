package eapli.base.clientusermanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Representa o número de contribuinte de uma pessoa.
 *
 * @author Gabriel Gonçalves (1191296)
 */
@Embeddable
@EqualsAndHashCode
public class TaxPayerNumber implements ValueObject, Comparable<TaxPayerNumber> {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String taxPayerNumber;

    protected TaxPayerNumber(final String taxPayerNumber) {
        setTaxPayerNumber(taxPayerNumber);
    }

    protected TaxPayerNumber() {
        // for ORM
    }

    private void setTaxPayerNumber(final String newTaxPayerNumber) {
        if (!taxPayerNumberMeetsMinimumRequirements(newTaxPayerNumber)) {
            throw new IllegalArgumentException("Tax Payer Number must contain 9 numbers and be valid");
        }
        this.taxPayerNumber = newTaxPayerNumber;
    }

    private static boolean taxPayerNumberMeetsMinimumRequirements(final String taxPayerNumber) {
        return !StringPredicates.isNullOrEmpty(taxPayerNumber) && valideTaxPayerNumber(taxPayerNumber);
    }

    private static boolean valideTaxPayerNumber(String taxPayerNumber) {
        final int max = 9;

        // Verifica se é numérico e tem 9 números
        if (!taxPayerNumber.matches("[0-9]+") || taxPayerNumber.length() != max) {
            return false;
        }

        // Calcula checkSum
        int checkSum = 0;
        for (int i = 0; i < max - 1; i++) {
            checkSum += (taxPayerNumber.charAt(i) - '0') * (max - i);
        }

        // Se checkDigit for maior que 9, define-o como zero
        int checkDigit = 11 - (checkSum % 11);
        if (checkDigit > 9) {
            checkDigit = 0;
        }

        // Comparar checkDigit com o último número do tax payer number
        return checkDigit == taxPayerNumber.charAt(max - 1) - '0';
    }

    public static TaxPayerNumber valueOf(final String taxPayerNumber) {
        return new TaxPayerNumber(taxPayerNumber);
    }

    @Override
    public String toString() {
        return this.taxPayerNumber;
    }

    @Override
    public int compareTo(final TaxPayerNumber other) {
        return taxPayerNumber.compareTo(other.taxPayerNumber);
    }
}
