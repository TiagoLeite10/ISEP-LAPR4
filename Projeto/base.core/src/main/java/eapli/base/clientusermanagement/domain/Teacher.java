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

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;

/**
 * Representa a entidade Professor.
 *
 * @author Gabriel Gonçalves (1191296)
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Teacher extends Person implements AggregateRoot<Acronym> {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @EmbeddedId
    private Acronym acronym;

    public Teacher(final SystemUser user, final TaxPayerNumber taxPayerNumber, final BirthDate birthDate, final Acronym acronym) {
        super(user, taxPayerNumber, birthDate);

        Preconditions.noneNull(acronym, "Teacher must not contain null or empty attributes");
        this.acronym = acronym;
    }

    protected Teacher() {
        // for ORM only
    }

    @Override
    public boolean sameAs(final Object other) {
        if (!(other instanceof Teacher)) {
            return false;
        }

        final Teacher that = (Teacher) other;
        if (this == that) {
            return true;
        }

        return super.equals(that);
    }

    @Override
    public boolean equals(final Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public Acronym identity() {
        return this.acronym;
    }
}
