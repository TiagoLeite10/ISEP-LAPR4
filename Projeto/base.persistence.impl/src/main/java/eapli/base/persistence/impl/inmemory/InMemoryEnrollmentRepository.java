package eapli.base.persistence.impl.inmemory;

import eapli.base.coursemanagement.domain.CourseCode;
import eapli.base.enrollmentmanagement.domain.Enrollment;
import eapli.base.enrollmentmanagement.repositories.EnrollmentRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

/**
 * @author Gabriel Gonçalves (1191296)
 */
public class InMemoryEnrollmentRepository extends InMemoryDomainRepository<Enrollment, Long>
        implements EnrollmentRepository {

    @Override
    public Iterable<Enrollment> findAllPendingEnrollments(CourseCode courseCode) {
        throw new UnsupportedOperationException("not implemented yet");
    }

}
