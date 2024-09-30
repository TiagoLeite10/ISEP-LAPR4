package eapli.base.enrollmentmanagement.domain;

import eapli.base.clientusermanagement.domain.MecanographicNumber;
import eapli.base.coursemanagement.domain.CourseCode;
import org.springframework.data.util.Pair;

import java.io.FileNotFoundException;
import java.util.List;

public interface BulkEnrollmentFile {
    int MECANOGRAPHIC_NUMBER_INDEX = 0;
    int COURSE_CODE_INDEX = 1;
    List<Pair<MecanographicNumber, CourseCode>> readFile(String filePath) throws FileNotFoundException;
}
