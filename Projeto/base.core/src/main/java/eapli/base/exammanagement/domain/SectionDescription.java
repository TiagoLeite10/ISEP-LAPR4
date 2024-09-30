package eapli.base.exammanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Representa a descrição de uma secção.
 */
@Embeddable
@EqualsAndHashCode
public class SectionDescription implements ValueObject {

    private static final int DESCRIPTION_MAX_SIZE = 150;

    private static final long serialVersionUID = 1L;

    @Column
    private String sectionDescription;

    protected SectionDescription(final String sectionDescription) {
        setSectionDescription(sectionDescription);
    }

    protected SectionDescription() {
        // for ORM
    }

    private void setSectionDescription(final String newSectionDescription) {
        if (!sectionDescriptionMeetsMinimumRequirements(newSectionDescription)) {
            throw new IllegalArgumentException("The section description is invalid");
        }
        this.sectionDescription = newSectionDescription;
    }

    private static boolean sectionDescriptionMeetsMinimumRequirements(final String sectionDescription) {
        return sectionDescription != null && sectionDescription.length() <= DESCRIPTION_MAX_SIZE;
    }

    public static SectionDescription valueOf(final String sectionDescription) {
        return new SectionDescription(sectionDescription);
    }

    public SectionDescription clone() {
        return new SectionDescription(this.sectionDescription);
    }

    @Override
    public String toString() {
        return this.sectionDescription;
    }
}
