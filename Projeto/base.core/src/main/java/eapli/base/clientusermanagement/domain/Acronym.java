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

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.regex.Pattern;

/**
 * Representa o acrónimo de um professor.
 *
 * @author Gabriel Gonçalves (1191296)
 */
@Embeddable
@EqualsAndHashCode
public class Acronym implements ValueObject, Comparable<Acronym> {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String acronym;

    protected Acronym(final String acronym) {
        setAcronym(acronym);
    }

    protected Acronym() {
        // for ORM
    }

    private void setAcronym(final String newAcronym) {
        if (!acronymMeetsMinimumRequirements(newAcronym)) {
            throw new IllegalArgumentException("The acronym can only contain letters and must contain exactly 4 characters");
        }
        this.acronym = newAcronym.toUpperCase();
    }

    private static boolean acronymMeetsMinimumRequirements(final String acronym) {
        return !StringPredicates.isNullOrEmpty(acronym) && Pattern.compile("[a-zA-z]{4}").matcher(acronym).matches();
    }

    public static Acronym valueOf(final String acronym) {
        return new Acronym(acronym);
    }

    @Override
    public String toString() {
        return this.acronym;
    }

    @Override
    public int compareTo(final Acronym other) {
        return acronym.compareTo(other.acronym);
    }
}
