package eapli.base.exammanagement.domain;

import eapli.framework.domain.model.ValueObject;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Representa a descrição de um cabeçalho.
 */
@Embeddable
@EqualsAndHashCode
public class HeaderDescription implements ValueObject {

    private static final int DESCRIPTION_MAX_SIZE = 150;

    private static final long serialVersionUID = 1L;

    @Column
    private String headerDescription;

    protected HeaderDescription(final String headerDescription) {
        setHeaderDescription(headerDescription);
    }

    protected HeaderDescription() {
        // for ORM
    }

    private void setHeaderDescription(final String newHeaderDescription) {
        if (!headerDescriptionMeetsMinimumRequirements(newHeaderDescription)) {
            throw new IllegalArgumentException("The header description is invalid");
        }
        this.headerDescription = newHeaderDescription;
    }

    private static boolean headerDescriptionMeetsMinimumRequirements(final String headerDescription) {
        return headerDescription != null && headerDescription.length() <= DESCRIPTION_MAX_SIZE;
    }

    public static HeaderDescription valueOf(final String headerDescription) {
        return new HeaderDescription(headerDescription);
    }

    @Override
    public String toString() {
        return this.headerDescription;
    }
}
