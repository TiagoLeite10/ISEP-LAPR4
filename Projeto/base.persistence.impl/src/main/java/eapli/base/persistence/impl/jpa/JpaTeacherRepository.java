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
import eapli.base.clientusermanagement.domain.Acronym;
import eapli.base.clientusermanagement.domain.Teacher;
import eapli.base.clientusermanagement.repositories.TeacherRepository;
import eapli.base.coursemanagement.domain.CourseCode;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Gabriel Gonçalves (1191296)
 */
class JpaTeacherRepository extends JpaAutoTxRepository<Teacher, Acronym, Acronym>
        implements TeacherRepository {

    public JpaTeacherRepository(final TransactionalContext autoTx) {
        super(autoTx, "acronym");
    }

    public JpaTeacherRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "acronym");
    }

    @Override
    public Optional<Teacher> findByUsername(final Username name) {
        final Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        return matchOne("e.systemUser.username=:name", params);
    }

    @Override
    public Optional<Teacher> findByAcronym(final Acronym acronym) {
        final Map<String, Object> params = new HashMap<>();
        params.put("acronym", acronym);
        return matchOne("e.acronym=:acronym", params);
    }

    @Override
    public Iterable<Teacher> allTeachers() {
        return findAll();
    }

    @Override
    public Iterable<Teacher> allTeachersWhoDoNotTeachThisCourse(CourseCode courseCode) {

        final TypedQuery<Teacher> query = entityManager().createQuery(
                "SELECT t FROM Teacher t WHERE t NOT IN (SELECT t1 FROM Course c " +
                        "JOIN c.teachers t1 WHERE c.courseCode = :courseCode) AND t NOT IN (SELECT responsibleTeacher " +
                        "FROM Course c JOIN c.responsibleTeacher responsibleTeacher WHERE c.courseCode = :courseCode)",
                        Teacher.class);
        query.setParameter("courseCode", courseCode);

        return query.getResultList();
    }
}
