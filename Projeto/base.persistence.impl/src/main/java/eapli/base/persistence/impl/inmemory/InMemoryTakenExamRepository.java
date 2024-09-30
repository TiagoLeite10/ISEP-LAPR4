package eapli.base.persistence.impl.inmemory;

import eapli.base.clientusermanagement.domain.Student;
import eapli.base.coursemanagement.domain.CourseCode;
import eapli.base.takenexammanagement.domain.TakenExam;
import eapli.base.takenexammanagement.dto.GradePerStudent;
import eapli.base.takenexammanagement.repositories.TakenExamRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryTakenExamRepository extends InMemoryDomainRepository<TakenExam, Long>
        implements TakenExamRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<GradePerStudent> findAllGrades(Student student) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public Iterable<GradePerStudent> findAllGradesByCourse(CourseCode courseCode) {
        throw new UnsupportedOperationException("not implemented yet");
    }

}
