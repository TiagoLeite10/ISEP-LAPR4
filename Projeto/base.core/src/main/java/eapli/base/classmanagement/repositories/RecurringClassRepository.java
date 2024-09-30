package eapli.base.classmanagement.repositories;

import eapli.base.classmanagement.domain.ClassTitle;
import eapli.base.classmanagement.domain.RecurringClass;
import eapli.base.clientusermanagement.domain.Teacher;
import eapli.framework.domain.repositories.DomainRepository;

public interface RecurringClassRepository extends DomainRepository<ClassTitle, RecurringClass> {

    Iterable<RecurringClass> findAllRecurringClass(Teacher teacher);

}
