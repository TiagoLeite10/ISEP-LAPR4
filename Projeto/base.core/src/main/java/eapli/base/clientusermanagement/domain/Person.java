package eapli.base.clientusermanagement.domain;

import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;

/**
 * Representa a classe abstrata Person.
 */
@MappedSuperclass
public abstract class Person {

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private SystemUser systemUser;

    @Column(nullable = false, unique = true)
    private TaxPayerNumber taxPayerNumber;

    private BirthDate birthDate;

    public Person(final SystemUser user, final TaxPayerNumber taxPayerNumber, final BirthDate birthDate) {
        Preconditions.noneNull(user, taxPayerNumber, birthDate, "Person must not contain null or empty attributes");

        this.systemUser = user;
        this.taxPayerNumber = taxPayerNumber;
        this.birthDate = birthDate;
    }

    protected Person() {
        // for ORM
    }

    public SystemUser user() {
        return this.systemUser;
    }

    public TaxPayerNumber taxPayerNumber() {
        return taxPayerNumber;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Person)) {
            return false;
        }

        final Person that = (Person) other;
        if (this == that) {
            return true;
        }

        return taxPayerNumber.equals(that.taxPayerNumber);
    }
}
