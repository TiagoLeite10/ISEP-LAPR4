package eapli.base.enrollmentmanagement.domain;

import eapli.base.clientusermanagement.domain.Student;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;

/**
 * Representa a entidade inscrição de um aluno.
 */
@Entity
public class Enrollment implements AggregateRoot<Long> {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Student student;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnrollmentStatus enrollmentStatus;

    public Enrollment(Student student) {
        Preconditions.noneNull(student, "Enrollment must not contain null or empty attributes!");

        this.student = student;
        this.enrollmentStatus = EnrollmentStatus.PENDING;
    }

    protected Enrollment() {
        // for ORM only
    }

    public Student student() {
        return this.student;
    }

    public boolean isPending() {
        return enrollmentStatus.equals(EnrollmentStatus.PENDING);
    }

    public boolean isApproved() {
        return enrollmentStatus.equals(EnrollmentStatus.APPROVED);
    }

    public boolean isReject() {
        return enrollmentStatus.equals(EnrollmentStatus.REJECT);
    }

    public void approvedEnrollment() {
        this.enrollmentStatus = EnrollmentStatus.APPROVED;
    }

    public void rejectEnrollment() {
        this.enrollmentStatus = EnrollmentStatus.REJECT;
    }

    @Override
    public boolean sameAs(final Object other) {
        if (!(other instanceof Enrollment)) {
            return false;
        }

        final Enrollment that = (Enrollment) other;
        if (this == other) {
            return true;
        }

        return student.sameAs(that.student);
    }

    @Override
    public boolean equals(final Object other) {
        return sameAs(other);
    }

    @Override
    public int hashCode() {
        return student.hashCode();
    }

    @Override
    public Long identity() {
        return this.id;
    }
}
