package eapli.base.coursemanagement.domain;

import eapli.framework.domain.model.DomainFactory;

public class CourseBuilder implements DomainFactory<Course> {

    private CourseCode courseCode;
    private CourseEnrolmentLimits courseEnrolmentLimits;
    private CourseTitle courseTitle;
    private CourseDescription courseDescription;

    public CourseBuilder with(final String courseCode, final String courseTitle, final String courseDescription, final int minimumEnrolments, final int maximumEnrolments) {
        this.withCourseCode(courseCode);
        this.withCourseTitle(courseTitle);
        this.withCourseDescription(courseDescription);
        this.withCourseEnrolmentLimits(minimumEnrolments, maximumEnrolments);
        return this;
    }

    public CourseBuilder withCourseCode(final String courseCode) {
        this.courseCode = new CourseCode(courseCode);
        return this;
    }

    public CourseBuilder withCourseTitle(final String courseTitle) {
        this.courseTitle = new CourseTitle(courseTitle);
        return this;
    }

    public CourseBuilder withCourseDescription(final String courseDescription) {
        this.courseDescription = new CourseDescription(courseDescription);
        return this;
    }

    public CourseBuilder withCourseEnrolmentLimits(final int minimumEnrolments, final int maximumEnrolments) {
        this.courseEnrolmentLimits = new CourseEnrolmentLimits(minimumEnrolments, maximumEnrolments);
        return this;
    }

    @Override
    public Course build() {
        // since the factory knows that all the parts are needed it could throw
        // an exception. however, we will leave that to the constructor
        return new Course(this.courseCode, this.courseEnrolmentLimits, this.courseTitle, this.courseDescription);
    }
}

