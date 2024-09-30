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

import javax.persistence.Column;
import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;
import lombok.EqualsAndHashCode;

import java.util.regex.Pattern;

/**
 * Representa o número mecanográfico de um aluno.
 *
 * @author Gabriel Gonçalves (1191296)
 */
@Embeddable
@EqualsAndHashCode
public class MecanographicNumber implements ValueObject, Comparable<MecanographicNumber> {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String mecanographicNumber;

    protected MecanographicNumber(final String mecanographicNumber) {
        setMecanographicNumber(mecanographicNumber);
    }

    protected MecanographicNumber() {
        // for ORM
    }

    private void setMecanographicNumber(final String newMecanographicNumber) {
        if (!mecanographicNumberMeetsMinimumRequirements(newMecanographicNumber)) {
            throw new IllegalArgumentException("Mecanographic Number must contain 9 numbers");
        }
        this.mecanographicNumber = newMecanographicNumber;
    }

    private static boolean mecanographicNumberMeetsMinimumRequirements(final String mecanographicNumber) {
        return !StringPredicates.isNullOrEmpty(mecanographicNumber) && Pattern.compile("[0-9]{9}").matcher(mecanographicNumber).matches();
    }

    public static MecanographicNumber valueOf(final String mecanographicNumber) {
        return new MecanographicNumber(mecanographicNumber);
    }

    @Override
    public String toString() {
        return this.mecanographicNumber;
    }

    @Override
    public int compareTo(final MecanographicNumber other) {
        return mecanographicNumber.compareTo(other.mecanographicNumber);
    }
}
