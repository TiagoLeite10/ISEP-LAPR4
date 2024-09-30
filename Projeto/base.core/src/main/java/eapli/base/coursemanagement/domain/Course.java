package eapli.base.coursemanagement.domain;

import eapli.base.classmanagement.domain.ClassInstance;
import eapli.base.clientusermanagement.domain.Student;
import eapli.base.clientusermanagement.domain.Teacher;
import eapli.base.coursemanagement.exception.*;
import eapli.base.enrollmentmanagement.domain.Enrollment;
import eapli.base.eventmanagement.domain.ExtraClass;
import eapli.base.exammanagement.domain.Exam;
import eapli.base.classmanagement.domain.RecurringClass;
import eapli.base.exammanagement.exception.ExamUpdateException;
import eapli.base.formativeexammanagement.domain.FormativeExam;
import eapli.base.formativeexammanagement.exception.FormativeExamUpdateException;
import eapli.base.questionmanagement.domain.Question;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;

import java.util.*;
import java.util.Calendar;
import java.util.Iterator;

/**
 * Representa a entidade Course.
 */
@Entity
public class Course implements AggregateRoot<CourseCode> {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @EmbeddedId
    private CourseCode courseCode;

    @Column(nullable = false)
    private CourseEnrolmentLimits courseEnrolmentLimits;

    @Column(nullable = false)
    private CourseTitle courseTitle;

    @Column(nullable = false)
    private CourseDescription courseDescription;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CourseStatus courseStatus;

    private CourseDate courseDate;

    @ManyToMany
    private Set<Teacher> teachers = new HashSet<>();

    @OneToOne()
    private Teacher responsibleTeacher;

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Exam> exams = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<FormativeExam> formativeExams = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Question> questions = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Enrollment> enrollments = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<RecurringClass> recurringClasses = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<ExtraClass> extraClasses = new HashSet<>();

    // Manager (last update ou desnecessário?)

    public Course(final CourseCode courseCode, final CourseEnrolmentLimits courseEnrolmentLimits, final CourseTitle courseTitle,
                  final CourseDescription courseDescription) {
        Preconditions.noneNull(courseCode, courseEnrolmentLimits, courseTitle, courseDescription, "Course must not contain null or empty attributes");

        this.courseCode = courseCode;
        this.courseEnrolmentLimits = courseEnrolmentLimits;
        this.courseTitle = courseTitle;
        this.courseDescription = courseDescription;
        this.courseStatus = CourseStatus.CLOSE;
    }

    protected Course() {
        // for ORM only
    }

    public void addExam(Exam exam) {
        if (exam == null) {
            throw new IllegalArgumentException("Exam cannot be null");
        }

        if (exams.contains(exam)) {
            Exam oldExam = null;
            for (Exam f : exams) {
                if (f.equals(exam)) {
                    oldExam = f;
                }
            }

            assert oldExam != null;
            if (oldExam.inProgressOrFinish()) {
                throw new ExamUpdateException();
            } else {
                oldExam.merge(exam);
            }
        } else {
            exams.add(exam);
        }
    }

    public void addFormativeExam(FormativeExam formativeExam) {
        if (formativeExam == null) {
            throw new IllegalArgumentException("Formative exam cannot be null");
        }

        if (formativeExams.contains(formativeExam)) {
            FormativeExam oldFormativeExam = null;
            for (FormativeExam f : formativeExams) {
                if (f.equals(formativeExam)) {
                    oldFormativeExam = f;
                }
            }

            assert oldFormativeExam != null;
            if (oldFormativeExam.inProgressOrFinish()) {
                throw new FormativeExamUpdateException();
            } else {
                oldFormativeExam.merge(formativeExam);
            }

        } else {
            formativeExams.add(formativeExam);
        }
    }

    public void addListQuestion(Iterable<Question> questions) {
        if (questions == null) {
            throw new IllegalArgumentException("Questions cannot be null");
        }

        for (Question question : questions) {
            if (this.questions.contains(question)) {
                mergeExistingQuestion(question);
            } else {
                this.questions.add(question);
            }
        }
    }

    private void mergeExistingQuestion(Question question) {
        for (Question existingQuestion : this.questions) {
            if (existingQuestion.equals(question)) {
                existingQuestion.merge(question);
                break;
            }
        }
    }

    public boolean addEnrollment(Enrollment enrollment) {
        return enrollments.add(enrollment);
    }

    public boolean addRecurringClass(RecurringClass recurringClass) {
        return recurringClasses.add(recurringClass);
    }

    public boolean addExtraClass(ExtraClass extraClass) {
        return extraClasses.add(extraClass);
    }

    public int numberEnrollmentAccepted() {
        int number = 0;

        for (Enrollment e : enrollments) {
            if (e.isApproved()) {
                number++;
            }
        }

        return number;
    }

    public void approveCourseEnrollment(Enrollment enrollment) {
        // Se a incrição passada por parâmetro existir na lista e não ultrapassar o limite máximo de alunos
        if (enrollments.remove(enrollment) && !courseEnrolmentLimits.exceedsMaximum(numberEnrollmentAccepted() + 1)) {
            enrollment.approvedEnrollment();
            enrollments.add(enrollment);
        } else {
            throw new ApprovedEnrollmentException();
        }

    }

    public void rejectCourseEnrollment(Enrollment enrollment) {
        // Se a incrição passada por parâmetro existir na lista e não ultrapassar o limite máximo de alunos
        if (enrollments.remove(enrollment)) {
            enrollment.rejectEnrollment();
            enrollments.add(enrollment);
        } else {
            throw new RejectEnrollmentException();
        }
    }

    public boolean checkStudentsAvailability(Calendar startDate, Calendar endDate, Student student) {
        Calendar otherStartDate, otherEndDate;

        // Vê a disponibilidade das aulas recorrentes e das aulas instance
        if (checkAvailability(startDate, endDate)) {

            // Vê a disponibilidade das aulas extras que o aluno esteja
            for (ExtraClass e : extraClasses) {
                if (e.students().contains(student)) {

                    otherStartDate = e.eventStartDate();
                    otherEndDate = e.eventEndDate();

                    if (startDate.before(otherEndDate) && otherStartDate.before(endDate)) {
                        return false;
                    }
                }
            }

        } else {
            return false;
        }

        return true;
    }

    public boolean checkAvailability(Calendar startDate, Calendar endDate) {
        Calendar otherStartDate, otherEndDate;

        for (RecurringClass r : recurringClasses) {
            otherStartDate = r.startDateTimeNextClass();
            otherEndDate = r.endDateTimeNextClass();

            if (startDate.before(otherEndDate) && otherStartDate.before(endDate)) {
                return false;
            }

            for (ClassInstance c : r.classInstances()) {
                otherStartDate = c.classInstanceDate();
                otherEndDate = (Calendar) otherStartDate.clone();
                otherEndDate.add(Calendar.MINUTE, r.duration().duration());

                if (startDate.before(otherEndDate) && otherStartDate.before(endDate)) {
                    return false;
                }
            }
        }

        return true;
    }

    public void checkCourseAvailability(RecurringClass newRecurringClass) {
        Calendar startDate, endDate;

        startDate = newRecurringClass.startDateTimeNextClass();
        endDate = newRecurringClass.endDateTimeNextClass();

        if (!checkAvailability(startDate, endDate)) {
            throw new CourseAvailabilityException("The course already has a scheduled class that overlaps the intended one!");
        }
    }

    public void checkCourseAvailability(Calendar startDate, RecurringClass recurringClass) {
        Calendar endDate = (Calendar) startDate.clone();
        endDate.add(Calendar.MINUTE, recurringClass.duration().duration());

        if (!checkAvailability(startDate, endDate)) {
            throw new CourseAvailabilityException("The course already has a scheduled class that overlaps the intended one!");
        }
    }

    public CourseTitle title() {
        return this.courseTitle;
    }

    public CourseStatus status() {
        return this.courseStatus;
    }

    public CourseEnrolmentLimits enrolmentLimits() {
        return this.courseEnrolmentLimits;
    }

    public void nextState() {
        if (courseStatus.equals(CourseStatus.CLOSE)) {

            this.courseStatus = CourseStatus.OPEN;

        } else if (courseStatus.equals(CourseStatus.OPEN)) {

            this.courseStatus = CourseStatus.ENROLL;

        } else if (courseStatus.equals(CourseStatus.ENROLL)) {

            this.canCloseEnrollments();
            this.courseStatus = CourseStatus.IN_PROGRESS;
            Calendar startDate = Calendar.getInstance();
            this.courseDate = new CourseDate(startDate);

        } else if (courseStatus.equals(CourseStatus.IN_PROGRESS)) {
            checkIfCanClose();
            this.courseStatus = CourseStatus.CLOSED;
            Calendar startDate = courseDate.cloneStartDate();
            this.courseDate = new CourseDate(startDate, null);
        }
    }

    private void canCloseEnrollments() {
        Iterator<Enrollment> enrollmentsIterable = this.enrollments.iterator();

        int numEnrollments = 0;
        while (enrollmentsIterable.hasNext()) {
            Enrollment enrollment = enrollmentsIterable.next();
            if (enrollment.isPending()) {
                throw new PendentEnrollmentStateException();
            } else if (enrollment.isApproved()) {
                numEnrollments++;
            }
        }

        if (!this.courseEnrolmentLimits.validEnrollmentsNumber(numEnrollments))
            throw new InvalidEnrollmentLimitsException();

    }

    private boolean hasFutureExams() {
        boolean hasFutureExams = false;
        Calendar todayCalendar = Calendar.getInstance();

        Iterator<Exam> examIterator = this.exams.iterator();
        while (examIterator.hasNext() && !hasFutureExams) {
            Exam exam = examIterator.next();
            if (exam.dateTime().closingDateIsGreaterThan(todayCalendar)) {
                hasFutureExams = true;
            }
        }

        return hasFutureExams;
    }

    private boolean hasFutureExtraClass() {
        boolean hasFutureExtraClass = false;
        Calendar todayCalendar = Calendar.getInstance();

        Iterator<ExtraClass> extraClassIterator = this.extraClasses.iterator();
        while (extraClassIterator.hasNext() && !hasFutureExtraClass) {
            ExtraClass extraClass = extraClassIterator.next();
            if (!extraClass.eventDateLessThan(todayCalendar)) {
                hasFutureExtraClass = true;
            }
        }

        return hasFutureExtraClass;
    }

    private boolean hasFutureRecurringClass() {
        boolean hasFutureRecurringClass = false;

        Iterator<RecurringClass> recurringClassIterator = this.recurringClasses.iterator();
        while (recurringClassIterator.hasNext() && !hasFutureRecurringClass) {
            RecurringClass recurringClass = recurringClassIterator.next();
            if (recurringClass.hasPendingClassInstances()) {
                hasFutureRecurringClass = true;
            }
        }

        return hasFutureRecurringClass;
    }

    private void checkIfCanClose() {
        if (hasFutureExams())
            throw new CourseStillHasActivityException("There are still scheduled and pending exams for this course!");

        if (hasFutureExtraClass())
            throw new CourseStillHasActivityException("There are still extra classes scheduled for this course!");

        if (hasFutureRecurringClass())
            throw new CourseStillHasActivityException("There are still classes rescheduled to take place in this " +
                    "course");
    }

    public void setResponsibleTeacher(Teacher theTeacher) {
        if (this.responsibleTeacher != null)
            throw new CourseAlreadyHasResponsibleException();
        this.responsibleTeacher = theTeacher;
    }

    public void addTeacherToCourse(Teacher theTeacher) {
        if (!teachers.add(theTeacher)) {
            throw new TeacherAlreadyTeachTheCourseException();
        }
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Course))
            return false;

        final Course that = (Course) other;
        if (this == that)
            return true;

        return identity().equals(that.identity()) && courseTitle.equals(that.courseTitle)
                && courseEnrolmentLimits.equals(that.courseEnrolmentLimits) && courseDescription.equals(that.courseDescription)
                && courseStatus.equals(that.courseStatus);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public int compareTo(CourseCode other) {
        return this.courseCode.compareTo(other);
    }

    @Override
    public CourseCode identity() {
        return this.courseCode;
    }
}
