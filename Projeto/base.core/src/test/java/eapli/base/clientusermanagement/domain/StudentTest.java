/*
 * Copyright (c) 2013-2022 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eapli.base.clientusermanagement.domain;

import eapli.base.BuilderHelper;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.Calendar;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe para realizar testes à entidade Student.
 */
public class StudentTest {

    private final SystemUser SYSTEM_USER = UserBuilderHelper.builder().with("maria", "Password1",
            "Maria", "Rocha", "maria@gmail.com").withRoles(BaseRoles.STUDENT).build();

    private final TaxPayerNumber TAX_PAYER_NUMBER = TaxPayerNumber.valueOf("254588182");

    private final BirthDate BIRTH_DATE = BirthDate.valueOf(BuilderHelper.buildDate(2005, Calendar.JANUARY, 25));

    /**
     * Método para contruir um aluno.
     *
     * @return O aluno construído.
     */
    private Student buildStudent() {
        return new Student(SYSTEM_USER, TAX_PAYER_NUMBER, BIRTH_DATE);
    }

    /**
     * Teste para garantir que um aluno tem de possuir um utilizador do sistema.
     */
    @Test
    void ensureStudentHasSystemUser() {
        assertThrows(IllegalArgumentException.class, () -> new Student(null, TAX_PAYER_NUMBER, BIRTH_DATE));
    }

    /**
     * Teste para garantir que um aluno tem de possuir um número de contribuinte.
     */
    @Test
    void ensureStudentHasTaxPayerNumber() {
        assertThrows(IllegalArgumentException.class, () -> new Student(SYSTEM_USER, null, BIRTH_DATE));
    }

    /**
     * Teste para garantir que um aluno tem de possuir uma data de nascimento.
     */
    @Test
    void ensureStudentHasBirthDate() {
        assertThrows(IllegalArgumentException.class, () -> new Student(SYSTEM_USER, TAX_PAYER_NUMBER, null));
    }

    /**
     * Teste para garantir que um aluno tem o utilizador do sistema esperado.
     */
    @Test
    void ensureStudentHasExpectedSystemUser() {
        final Student subject = buildStudent();

        assertEquals(SYSTEM_USER, subject.user());
    }

    /**
     * Teste para garantir que um aluno tem o número de contribuinte esperado.
     */
    @Test
    void ensureStudentHasExpectedTaxPayerNumber() {
        final Student subject = buildStudent();

        assertEquals(TAX_PAYER_NUMBER, subject.taxPayerNumber());
    }

    /**
     * Teste para garantir que dois alunos são iguais para o mesmo número de contribuinte.
     */
    @Test
    void ensureTwoStudentWithSameTaxPayerNumbersAreTheSame() {
        final Student aStudent = buildStudent();
        final Student anotherStudent = new StudentBuilder().withSystemUser(SYSTEM_USER).withTaxPayerNumber("254588182").
                withBirthDate(BuilderHelper.buildDate(2003, Calendar.DECEMBER, 12)).build();

        assertTrue(aStudent.sameAs(anotherStudent));
    }

    /**
     * Teste para garantir que dois alunos não são iguais para um número de contribuinte diferente.
     */
    @Test
    void ensureTwoStudentWithDifferentTaxPayerNumbersAreNotTheSame() {
        final Student aStudent = buildStudent();
        final Student anotherStudent = new StudentBuilder().withSystemUser(SYSTEM_USER).withTaxPayerNumber("101960417").
                withBirthDate(BuilderHelper.buildDate(1990, Calendar.JANUARY, 1)).build();

        assertFalse(aStudent.sameAs(anotherStudent));
    }

    /**
     * Teste para garantir que um aluno é o mesmo para a mesma instância.
     */
    @Test
    void ensureStudentAreTheSameForTheSameInstance() {
        final Student aStudent = new Student();

        assertTrue(aStudent.sameAs(aStudent));
    }

    /**
     * Teste para garantir que um aluno não é o mesmo para diferentes tipos de objetos.
     */
    @Test
    void ensureStudentNotTheSameForDifferenteObjectTypes() {
        final Student aStudent = buildStudent();

        assertFalse(aStudent.sameAs(SYSTEM_USER));
    }
}
