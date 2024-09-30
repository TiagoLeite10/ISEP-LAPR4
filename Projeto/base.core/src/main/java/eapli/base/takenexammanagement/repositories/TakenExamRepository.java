package eapli.base.takenexammanagement.repositories;

import eapli.base.clientusermanagement.domain.Student;
import eapli.base.coursemanagement.domain.CourseCode;
import eapli.base.takenexammanagement.domain.TakenExam;
import eapli.base.takenexammanagement.dto.GradePerStudent;
import eapli.framework.domain.repositories.DomainRepository;

public interface TakenExamRepository extends DomainRepository<Long, TakenExam> {
    Iterable<GradePerStudent> findAllGrades(Student student);

    Iterable<GradePerStudent> findAllGradesByCourse(CourseCode courseCode);
}
