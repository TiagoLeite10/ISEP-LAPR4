package eapli.base.usermanagement.domain;

import eapli.base.usermanagement.domain.BasePasswordPolicy.PasswordStrength;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Classe para realizar testes à classe BasePasswordPolicy.
 */
public class BasePasswordPolicyTest {

    private final BasePasswordPolicy subject = new BasePasswordPolicy();

    /**
     * Teste para garantir que uma palavra-passe não deve ser vazia.
     */
    @Test
    void ensurePasswordMustNotBeEmpty() {
        assertFalse(subject.isSatisfiedBy(""));
    }

    /**
     * Teste para garantir que uma palavra-passe não deve ser nula.
     */
    @Test
    void ensurePasswordMustNotBeNull() {
        assertFalse(subject.isSatisfiedBy(null));
    }

    /**
     * Teste para garantir que uma palavra-passe deve possuir pelo menos seis caracteres.
     */
    @Test
    void ensurePasswordsSmallerThanSixCharactersAreNotAllowed() {
        assertFalse(subject.isSatisfiedBy("ab1c"));
    }

    /**
     * Teste para garantir que uma palavra-passe deve possuir pelo menos um dígito.
     */
    @Test
    void ensurePasswordsWithoutDigitsAreNotAllowed() {
        assertFalse(subject.isSatisfiedBy("abcefghi"));
    }

    /**
     * Teste para garantir que uma palavra-passe deve possuir pelo menos uma maiúscula.
     */
    @Test
    void ensurePasswordsWithoutCapitalLetterAreNotAllowed() {
        assertFalse(subject.isSatisfiedBy("abcefghi1"));
    }

    /**
     * Teste para garantir que uma palavra-passe deve possuir um dígito, uma maiúscula e 6 caracteres.
     */
    @Test
    void ensurePasswordHasAtLeastOneDigitOneCapitalAndSixCharactersLong() {
        assertTrue(subject.isSatisfiedBy("abCfefgh1"));
    }

    /**
     * Teste para verificar a força fraca de uma palavra-passe.
     */
    @ParameterizedTest
    @ValueSource(strings = {"A23456", "A234567"})
    void testWeakPassword(final String password) {
        assertEquals(PasswordStrength.WEAK, subject.strength(password));
    }

    /**
     * Teste para verificar a força boa de uma palavra-passe.
     */
    @ParameterizedTest
    @ValueSource(strings = {"A2345678", "A23456789"})
    void testGoodPassword(final String password) {
        assertEquals(PasswordStrength.GOOD, subject.strength(password));
    }

    /**
     * Teste para verificar a força excelente de uma palavra-passe.
     */
    @ParameterizedTest
    @ValueSource(strings = {"123456789ABC", "123456789ABCD", "A234$5678", "A234#5678", "A234!5678", "A234?5678"})
    void testExcelentPassword(final String password) {
        assertEquals(PasswordStrength.EXCELENT, subject.strength(password));
    }
}
