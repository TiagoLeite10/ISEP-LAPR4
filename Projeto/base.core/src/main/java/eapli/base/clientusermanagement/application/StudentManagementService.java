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
package eapli.base.clientusermanagement.application;

import eapli.base.clientusermanagement.domain.*;
import eapli.base.clientusermanagement.repositories.StudentRepository;
import eapli.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.domain.model.*;

import java.util.Calendar;
import java.util.Set;

/**
 * @author Gabriel Gonçalves (1191296)
 */
@ApplicationService
public class StudentManagementService {

    private final StudentRepository studentRepository;

    public StudentManagementService(final StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student registerNewStudent(final String username, final String password, final String firstName,
                                      final String lastName, final String email, final Set<Role> roles, final Calendar createdOn,
                                      final String taxPayerNumber, final Calendar birthDate) {

        SystemUser user = UserBuilderHelper.builder().with(username, password, firstName, lastName, email).
                createdOn(createdOn).withRoles(roles).build();
        Student student = new StudentBuilder().with(taxPayerNumber, birthDate).withSystemUser(user).build();

        return this.studentRepository.save(student);
    }
}
