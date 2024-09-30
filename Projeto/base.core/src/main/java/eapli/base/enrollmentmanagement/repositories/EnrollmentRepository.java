package eapli.base.enrollmentmanagement.repositories;

import eapli.base.clientusermanagement.domain.Student;
import eapli.base.coursemanagement.domain.CourseCode;
import eapli.base.enrollmentmanagement.domain.Enrollment;
import eapli.framework.domain.repositories.DomainRepository;

/**
 * @author Gabriel Gonçalves (1191296)
 */
public interface EnrollmentRepository extends DomainRepository<Long, Enrollment> {

    Iterable<Enrollment> findAllPendingEnrollments(CourseCode courseCode);

}
