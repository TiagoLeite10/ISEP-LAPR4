package eapli.base.classmanagement.aplication;

import eapli.base.classmanagement.domain.RecurringClass;
import eapli.base.classmanagement.repositories.RecurringClassRepository;
import eapli.base.clientusermanagement.domain.Teacher;
import eapli.base.coursemanagement.domain.Course;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

@ApplicationService
public class ListRecurringClassService {

    private final AuthorizationService authz;
    private final RecurringClassRepository recurringClassRepository;

    public ListRecurringClassService(final AuthorizationService authz, final RecurringClassRepository recurringClassRepository) {
        // dependency injection - to make this object more testable we don't create the
        // infrastructure objects to avoid coupling to the implementation. This way, the controller
        // can be used in different scenarios with different implementations of the repository. for
        // instance, unit testing.
        this.authz = authz;
        this.recurringClassRepository = recurringClassRepository;
    }

    public Iterable<RecurringClass> allTeacherRecurringClass(Teacher teacher) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER);
        return recurringClassRepository.findAllRecurringClass(teacher);
    }
}

