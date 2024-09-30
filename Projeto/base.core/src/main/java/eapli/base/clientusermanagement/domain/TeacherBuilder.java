/*
 * Copyright (c) 2013-2022 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package eapli.base.clientusermanagement.domain;

import eapli.framework.domain.model.DomainFactory;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.Calendar;

/**
 * @author Gabriel Gonçalves (1191296)
 */
public class TeacherBuilder implements DomainFactory<Teacher> {

    private SystemUser systemUser;

    private TaxPayerNumber taxPayerNumber;

    private BirthDate birthDate;

    private Acronym acronym;

    public TeacherBuilder with(final String taxPayerNumber, final Calendar birthDate, final String acronym) {
        this.withTaxPayerNumber(taxPayerNumber);
        this.withBirthDate(birthDate);
        this.withAcronym(acronym);
        return this;
    }

    public TeacherBuilder withSystemUser(final SystemUser systemUser) {
        this.systemUser = systemUser;
        return this;
    }

    public TeacherBuilder withTaxPayerNumber(final String taxPayerNumber) {
        this.taxPayerNumber = new TaxPayerNumber(taxPayerNumber);
        return this;
    }

    public TeacherBuilder withBirthDate(final Calendar birthDate) {
        this.birthDate = new BirthDate(birthDate);
        return this;
    }

    public TeacherBuilder withAcronym(final String acronym) {
        this.acronym = new Acronym(acronym);
        return this;
    }

    @Override
    public Teacher build() {
        // since the factory knows that all the parts are needed it could throw
        // an exception. however, we will leave that to the constructor
        return new Teacher(this.systemUser, this.taxPayerNumber, this.birthDate, this.acronym);
    }
}
