package eapli.base.takenexammanagement.domain;

import eapli.framework.domain.model.ValueObject;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@EqualsAndHashCode
public class Grade implements ValueObject {

    private static final long serialVersionUID = 1L;


    @Column(nullable = false)
    private Float grade;

    protected Grade(final Float grade) {
        setGrade(grade);
    }

    protected Grade() {
        // for ORM
    }

    private void setGrade(final Float newGrade) {
        if (!gradeMeetsMinimumRequirements(newGrade)) {
            throw new IllegalArgumentException("The grade must be a number between 0 and 20");
        }
        this.grade = newGrade;
    }

    private boolean gradeMeetsMinimumRequirements(final Float grade) {
        return grade != null && (grade >= 0 && grade <=20);
    }

    public static Grade valueOf(final Float grade) {
        return new Grade(grade);
    }

    @Override
    public String toString() {
        return Float.toString(grade);
    }

}
