package eapli.base.persistence.impl.inmemory;

import eapli.base.coursemanagement.domain.CourseCode;
import eapli.base.exammanagement.domain.ExamTitle;
import eapli.base.formativeexammanagement.domain.FormativeExam;
import eapli.base.formativeexammanagement.repositories.FormativeExamRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryFormativeExamRepository extends InMemoryDomainRepository<FormativeExam, ExamTitle>
        implements FormativeExamRepository {
    @Override
    public Iterable<FormativeExam> listAllAvailableFormativeExamsOfACourse(CourseCode courseCode) {
        throw new UnsupportedOperationException("not implemented yet");
    }
}
