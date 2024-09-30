package eapli.base.enrollmentmanagement.application;

import eapli.base.clientusermanagement.domain.MecanographicNumber;
import eapli.base.clientusermanagement.domain.Student;
import eapli.base.clientusermanagement.repositories.StudentRepository;
import eapli.base.coursemanagement.domain.Course;
import eapli.base.coursemanagement.domain.CourseCode;
import eapli.base.coursemanagement.repositories.CourseRepository;
import eapli.base.enrollmentmanagement.domain.Enrollment;
import eapli.base.enrollmentmanagement.domain.EnrollmentBuilder;
import eapli.base.enrollmentmanagement.repositories.EnrollmentRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import org.springframework.data.util.Pair;

import java.util.*;

public class EnrollmentService {
    private final AuthorizationService authz;

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(final AuthorizationService authz, final StudentRepository studentRepository,
                             final CourseRepository courseRepository) {
        // dependency injection - to make this object more testable we don't create the
        // infrastructure objects to avoid coupling to the implementation. This way, the controller
        // can be used in different scenarios with different implementations of the repository. for
        // instance, unit testing.
        this.authz = authz;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public Iterable<Course> bulkEnrollment(List<Pair<MecanographicNumber, CourseCode>> enrollmentsData) {
        HashMap<CourseCode, Course> courses = new HashMap<>();

        for (Pair<MecanographicNumber, CourseCode> newEnrollmentData : enrollmentsData) {
            Optional<Student> studentOptional = studentRepository.findByMecanographicNumber(newEnrollmentData.getFirst());
            if (studentOptional.isPresent()) {
                Optional<Course> courseOptional = courseRepository.ofIdentity(newEnrollmentData.getSecond());
                if (courseOptional.isPresent()) {
                    final var newEnrollment = new EnrollmentBuilder().withStudent(studentOptional.get()).build();
                    Course course = courseOptional.get();

                    if (courses.get(course.identity()) != null)
                        course = courses.get(course.identity());

                    course.addEnrollment(newEnrollment);
                    courses.put(course.identity(), course);
                }
            }

        }

        return courses.values();
    }
}
