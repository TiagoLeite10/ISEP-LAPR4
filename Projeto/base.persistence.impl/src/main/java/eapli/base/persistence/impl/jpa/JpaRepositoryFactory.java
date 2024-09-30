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
package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.boardmanagement.repositories.BoardRepository;
import eapli.base.classmanagement.repositories.RecurringClassRepository;
import eapli.base.coursemanagement.repositories.CourseRepository;
import eapli.base.enrollmentmanagement.repositories.EnrollmentRepository;
import eapli.base.eventmanagement.repositories.ExtraClassRepository;
import eapli.base.eventmanagement.repositories.MeetingRepository;
import eapli.base.eventmanagement.repositories.ParticipationMeetingRepository;
import eapli.base.exammanagement.repositories.ExamRepository;
import eapli.base.formativeexammanagement.repositories.FormativeExamRepository;
import eapli.base.postitmanagement.repositories.PostItRepository;
import eapli.base.questionmanagement.repositories.QuestionRepository;
import eapli.base.infrastructure.persistence.RepositoryFactory;
import eapli.base.takenexammanagement.repositories.TakenExamRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.jpa.JpaAutoTxUserRepository;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

/**
 * @author nuno on 21/03/16.
 */
public class JpaRepositoryFactory implements RepositoryFactory {

    @Override
    public UserRepository users(final TransactionalContext autoTx) {
        return new JpaAutoTxUserRepository(autoTx);
    }

    @Override
    public UserRepository users() {
        return new JpaAutoTxUserRepository(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public JpaStudentRepository students(final TransactionalContext autoTx) {
        return new JpaStudentRepository(autoTx);
    }

    @Override
    public JpaStudentRepository students() {
        return new JpaStudentRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public JpaTeacherRepository teachers(TransactionalContext autoTx) {
        return new JpaTeacherRepository(autoTx);
    }

    @Override
    public JpaTeacherRepository teachers() {
        return new JpaTeacherRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public CourseRepository courses(TransactionalContext autoTx) {
        return new JpaCourseRepository(autoTx);
    }

    @Override
    public CourseRepository courses() {
        return new JpaCourseRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public EnrollmentRepository enrollments(TransactionalContext autoTx) {
        return new JpaEnrollmentRepository(autoTx);
    }

    @Override
    public EnrollmentRepository enrollments() {
        return new JpaEnrollmentRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public ExamRepository exams(TransactionalContext autoTx) {
        return new JpaExamRepository(autoTx);
    }

    @Override
    public ExamRepository exams() {
        return new JpaExamRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public FormativeExamRepository formativeExams(TransactionalContext autoTx) {
        return new JpaFormativeExamRepository(autoTx);
    }

    @Override
    public FormativeExamRepository formativeExams() {
        return new JpaFormativeExamRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public QuestionRepository questions(TransactionalContext autoTx) {
        return new JpaQuestionRepository(autoTx);
    }

    @Override
    public QuestionRepository questions() {
        return new JpaQuestionRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public ExtraClassRepository extraClasses(TransactionalContext autoTx) {
        return new JpaExtraClassRepository(autoTx);
    }

    @Override
    public ExtraClassRepository extraClasses() {
        return new JpaExtraClassRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public RecurringClassRepository recurringClasses(TransactionalContext autoTx) {
        return new JpaRecurringClassRepository(autoTx);
    }

    @Override
    public RecurringClassRepository recurringClasses() {
        return new JpaRecurringClassRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public MeetingRepository meetings(TransactionalContext autoTx) {
        return new JpaMeetingRepository(autoTx);
    }

    @Override
    public MeetingRepository meetings() {
        return new JpaMeetingRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public BoardRepository boards(TransactionalContext autoTx) {
        return new JpaBoardRepository(autoTx);
    }

    @Override
    public BoardRepository boards() {
        return new JpaBoardRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public TakenExamRepository takenExams(TransactionalContext autoTx) {
        return new JpaTakenExamRepository(autoTx);
    }

    @Override
    public TakenExamRepository takenExams() {
        return new JpaTakenExamRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public ParticipationMeetingRepository participationsMeetings(TransactionalContext autoTx) {
        return new JpaParticipationMeetingRepository(autoTx);
    }

    @Override
    public ParticipationMeetingRepository participationsMeetings() {
        return new JpaParticipationMeetingRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public PostItRepository postIts(TransactionalContext autoTx) {
        return new JpaPostItRepository(autoTx);
    }

    @Override
    public PostItRepository postIts() {
        return new JpaPostItRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public TransactionalContext newTransactionalContext() {
        return JpaAutoTxRepository.buildTransactionalContext(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }
}
