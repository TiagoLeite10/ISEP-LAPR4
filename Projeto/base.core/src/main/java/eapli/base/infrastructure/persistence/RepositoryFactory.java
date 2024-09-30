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
package eapli.base.infrastructure.persistence;

import eapli.base.boardmanagement.repositories.BoardRepository;
import eapli.base.classmanagement.repositories.RecurringClassRepository;
import eapli.base.coursemanagement.repositories.CourseRepository;
import eapli.base.clientusermanagement.repositories.StudentRepository;
import eapli.base.clientusermanagement.repositories.TeacherRepository;
import eapli.base.enrollmentmanagement.repositories.EnrollmentRepository;
import eapli.base.eventmanagement.repositories.ExtraClassRepository;
import eapli.base.eventmanagement.repositories.MeetingRepository;
import eapli.base.eventmanagement.repositories.ParticipationMeetingRepository;
import eapli.base.exammanagement.repositories.ExamRepository;
import eapli.base.formativeexammanagement.repositories.FormativeExamRepository;
import eapli.base.postitmanagement.domain.PostIt;
import eapli.base.postitmanagement.repositories.PostItRepository;
import eapli.base.questionmanagement.repositories.QuestionRepository;
import eapli.base.takenexammanagement.repositories.TakenExamRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;

/**
 * @author Paulo Gandra Sousa
 */
public interface RepositoryFactory {

    /**
     * factory method to create a transactional context to use in the repositories
     *
     * @return
     */
    TransactionalContext newTransactionalContext();

    /**
     * @param autoTx the transactional context to enrol
     * @return
     */
    UserRepository users(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    UserRepository users();

    /**
     * @param autoTx the transactional context to enroll
     * @return
     */
    StudentRepository students(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    CourseRepository courses();

    /**
     * @param autoTx the transactional context to enroll
     * @return
     */
    CourseRepository courses(TransactionalContext autoTx);


    StudentRepository students();

    /**
     * @param autoTx the transactional context to enroll
     * @return
     */
    TeacherRepository teachers(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    TeacherRepository teachers();

    /**
     * @param autoTx the transactional context to enroll
     * @return
     */
    EnrollmentRepository enrollments(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    EnrollmentRepository enrollments();

    /**
     * @param autoTx the transactional context to enroll
     * @return
     */
    ExamRepository exams(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    ExamRepository exams();

    /**
     * @param autoTx the transactional context to enroll
     * @return
     */
    FormativeExamRepository formativeExams(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    FormativeExamRepository formativeExams();

    /**
     * @param autoTx the transactional context to enroll
     * @return
     */
    QuestionRepository questions(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    QuestionRepository questions();

    /**
     * @param autoTx the transactional context to enroll
     * @return
     */
    ExtraClassRepository extraClasses(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    ExtraClassRepository extraClasses();

    /**
     * @param autoTx the transactional context to enroll
     * @return
     */
    RecurringClassRepository recurringClasses(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    RecurringClassRepository recurringClasses();

    /**
     * @param autoTx the transactional context to enroll
     * @return
     */
    MeetingRepository meetings(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    MeetingRepository meetings();

    /**
     * @param autoTx the transactional context to enroll
     * @return
     */
    BoardRepository boards(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    BoardRepository boards();

    /**
     * @param autoTx the transactional context to enroll
     * @return
     */
    TakenExamRepository takenExams(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    TakenExamRepository takenExams();

    /**
     * @param autoTx the transactional context to enroll
     * @return
     */
    ParticipationMeetingRepository participationsMeetings(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    ParticipationMeetingRepository participationsMeetings();

    /**
     * @param autoTx the transactional context to enroll
     * @return
     */
    PostItRepository postIts(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    PostItRepository postIts();

}
