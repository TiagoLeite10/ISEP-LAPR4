package eapli.base.formativeexammanagement.repositories;

import eapli.base.coursemanagement.domain.CourseCode;
import eapli.base.exammanagement.domain.ExamTitle;
import eapli.base.formativeexammanagement.domain.FormativeExam;
import eapli.framework.domain.repositories.DomainRepository;

public interface FormativeExamRepository extends DomainRepository<ExamTitle, FormativeExam> {

    Iterable<FormativeExam> listAllAvailableFormativeExamsOfACourse(CourseCode courseCode);

}
