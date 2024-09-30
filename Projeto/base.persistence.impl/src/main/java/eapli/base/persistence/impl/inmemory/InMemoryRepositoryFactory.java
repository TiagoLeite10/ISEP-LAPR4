/*
 * Copyright (c) 2013-2022 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package eapli.base.persistence.impl.inmemory;

import eapli.base.boardmanagement.repositories.BoardRepository;
import eapli.base.classmanagement.repositories.RecurringClassRepository;
import eapli.base.clientusermanagement.repositories.StudentRepository;
import eapli.base.clientusermanagement.repositories.TeacherRepository;
import eapli.base.coursemanagement.repositories.CourseRepository;
import eapli.base.enrollmentmanagement.repositories.EnrollmentRepository;
import eapli.base.eventmanagement.repositories.ExtraClassRepository;
import eapli.base.eventmanagement.repositories.MeetingRepository;
import eapli.base.eventmanagement.repositories.ParticipationMeetingRepository;
import eapli.base.exammanagement.repositories.ExamRepository;
import eapli.base.formativeexammanagement.repositories.FormativeExamRepository;
import eapli.base.postitmanagement.repositories.PostItRepository;
import eapli.base.questionmanagement.repositories.QuestionRepository;
import eapli.base.infrastructure.bootstrapers.BaseBootstrapper;
import eapli.base.infrastructure.persistence.RepositoryFactory;
import eapli.base.takenexammanagement.repositories.TakenExamRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.inmemory.InMemoryUserRepository;

/**
 * @author nuno on 20/03/16.
 */
public class InMemoryRepositoryFactory implements RepositoryFactory {

    static {
        // only needed because of the in memory persistence
        new BaseBootstrapper().execute();
    }

    @Override
    public UserRepository users(final TransactionalContext tx) {
        return new InMemoryUserRepository();
    }

    @Override
    public UserRepository users() {
        return users(null);
    }

    @Override
    public StudentRepository students(final TransactionalContext tx) {
        return new InMemoryStudentRepository();
    }

    @Override
    public CourseRepository courses() {
        return courses(null);
    }

    @Override
    public CourseRepository courses(TransactionalContext autoTx) {
        return new InMemoryCourseRepository();
    }

    @Override
    public StudentRepository students() {
        return students(null);
    }

    @Override
    public TeacherRepository teachers(TransactionalContext autoTx) {
        return new InMemoryTeacherRepository();
    }

    @Override
    public TeacherRepository teachers() {
        return teachers(null);
    }

    @Override
    public EnrollmentRepository enrollments(TransactionalContext autoTx) {
        return new InMemoryEnrollmentRepository();
    }

    @Override
    public EnrollmentRepository enrollments() {
        return enrollments(null);
    }

    @Override
    public ExamRepository exams(TransactionalContext autoTx) {
        return new InMemoryExamRepository();
    }

    @Override
    public ExamRepository exams() {
        return exams(null);
    }

    @Override
    public FormativeExamRepository formativeExams(TransactionalContext autoTx) {
        return new InMemoryFormativeExamRepository();
    }

    @Override
    public FormativeExamRepository formativeExams() {
        return formativeExams(null);
    }

    @Override
    public QuestionRepository questions(TransactionalContext autoTx) {
        return new InMemoryQuestionRepository();
    }

    @Override
    public QuestionRepository questions() {
        return questions(null);
    }

    @Override
    public ExtraClassRepository extraClasses(TransactionalContext autoTx) {
        return new InMemoryExtraClassRepository();
    }

    @Override
    public ExtraClassRepository extraClasses() {
        return extraClasses(null);
    }

    @Override
    public RecurringClassRepository recurringClasses(TransactionalContext autoTx) {
        return new InMemoryRecurringClassRepository();
    }

    @Override
    public RecurringClassRepository recurringClasses() {
        return recurringClasses(null);
    }

    @Override
    public MeetingRepository meetings(TransactionalContext autoTx) {
        return new InMemoryMeetingRepository();
    }

    @Override
    public MeetingRepository meetings() {
        return meetings(null);
    }

    @Override
    public BoardRepository boards(TransactionalContext autoTx) {
        return new InMemoryBoardRepository();
    }

    @Override
    public BoardRepository boards() {
        return boards(null);
    }

    @Override
    public TakenExamRepository takenExams(TransactionalContext autoTx) {
        return new InMemoryTakenExamRepository();
    }

    @Override
    public TakenExamRepository takenExams() {
        return takenExams(null);
    }

    @Override
    public ParticipationMeetingRepository participationsMeetings(TransactionalContext autoTx) {
        return new InMemoryParticipationMeetingRepository();
    }

    @Override
    public ParticipationMeetingRepository participationsMeetings() {
        return participationsMeetings(null);
    }

    @Override
    public PostItRepository postIts(TransactionalContext autoTx) {
        return new InMemoryPostItRepository();
    }

    @Override
    public PostItRepository postIts() {
        return postIts(null);
    }

    @Override
    public TransactionalContext newTransactionalContext() {
        // in memory does not support transactions...
        return null;
    }

}
