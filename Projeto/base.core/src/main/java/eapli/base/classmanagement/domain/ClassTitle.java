package eapli.base.classmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;
import eapli.framework.validations.CheckIf;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Representa o título da aula recorrente.
 */
@Embeddable
@EqualsAndHashCode
public class ClassTitle implements ValueObject, Comparable<ClassTitle> {

    private static final int TITLE_MAX_SIZE = 50;

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String title;

    protected ClassTitle(String classTitle) {
        setClassTitle(classTitle);
    }

    /**
     * Construtor de caso especial para valores desconhecidos e ORM.
     */
    protected ClassTitle() {
        // for ORM only
    }

    private void setClassTitle(final String newTitle) {
        if (!classTitleMeetsMinimumRequirements(newTitle)) {
            throw new IllegalArgumentException("The class title should neither be null nor empty nor have starting blank " +
                    "spaces nem cannot have more than " + TITLE_MAX_SIZE + " characters!");
        }
        this.title = newTitle;
    }

    private static boolean classTitleMeetsMinimumRequirements(final String title) {
        return StringPredicates.isPhrase(title) && title.length() <= TITLE_MAX_SIZE;
    }

    public static ClassTitle valueOf(final String classTitle) {
        return new ClassTitle(classTitle);
    }

    @Override
    public String toString() {
        return this.title;
    }

    @Override
    public int compareTo(ClassTitle other) {
        return this.title.compareTo(other.title);
    }
}
