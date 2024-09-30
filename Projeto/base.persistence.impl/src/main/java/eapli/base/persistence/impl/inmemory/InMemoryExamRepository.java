package eapli.base.persistence.impl.inmemory;

import eapli.base.clientusermanagement.domain.Student;
import eapli.base.coursemanagement.domain.Course;
import eapli.base.coursemanagement.domain.CourseCode;
import eapli.base.exammanagement.domain.Exam;
import eapli.base.exammanagement.domain.ExamTitle;
import eapli.base.exammanagement.repositories.ExamRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

/**
 * @author Gabriel Gonçalves (1191296)
 */
public class InMemoryExamRepository extends InMemoryDomainRepository<Exam, ExamTitle>
        implements ExamRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<Exam> findAllCourseExams(CourseCode courseCode) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public Iterable<Exam> findAllFutureExams(Username user) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public Iterable<Course> findAllApprovedStudentCoursesInProgressWithExams(Student student) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public Iterable<Exam> findAllCourseExamsInATimeInterval(CourseCode courseCode, Student student) {
        throw new UnsupportedOperationException("not implemented yet");
    }
}
