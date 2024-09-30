package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.coursemanagement.domain.CourseCode;
import eapli.base.enrollmentmanagement.domain.Enrollment;
import eapli.base.enrollmentmanagement.domain.EnrollmentStatus;
import eapli.base.enrollmentmanagement.repositories.EnrollmentRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;

/**
 * @author Gabriel Gonçalves (1191296)
 */
public class JpaEnrollmentRepository extends JpaAutoTxRepository<Enrollment, Long, Long>
        implements EnrollmentRepository {

    public JpaEnrollmentRepository(final TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    public JpaEnrollmentRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "id");
    }

    @Override
    public Iterable<Enrollment> findAllPendingEnrollments(CourseCode courseCode) {
        final TypedQuery<Enrollment> query = entityManager().createQuery(
                "SELECT en FROM Course c JOIN c.enrollments en WHERE c.courseCode = :code " +
                        "AND en.enrollmentStatus = : status", Enrollment.class);
        query.setParameter("code", courseCode);
        query.setParameter("status", EnrollmentStatus.PENDING);

        return query.getResultList();
    }
}
