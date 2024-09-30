package eapli.base.exammanagement.repositories;

import eapli.base.clientusermanagement.domain.Student;
import eapli.base.coursemanagement.domain.Course;
import eapli.base.coursemanagement.domain.CourseCode;
import eapli.base.exammanagement.domain.Exam;
import eapli.base.exammanagement.domain.ExamTitle;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;

/**
 * @author Gabriel Gonçalves (1191296)
 */
public interface ExamRepository extends DomainRepository<ExamTitle, Exam> {

    Iterable<Exam> findAllCourseExams(CourseCode courseCode);

    Iterable<Exam> findAllFutureExams(Username user);

    Iterable<Course> findAllApprovedStudentCoursesInProgressWithExams(Student student);

    Iterable<Exam> findAllCourseExamsInATimeInterval(CourseCode courseCode, Student student);
}
