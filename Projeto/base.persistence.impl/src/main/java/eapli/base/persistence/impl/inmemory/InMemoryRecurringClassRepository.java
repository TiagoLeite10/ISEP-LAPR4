package eapli.base.persistence.impl.inmemory;

import eapli.base.classmanagement.domain.ClassTitle;
import eapli.base.classmanagement.domain.RecurringClass;
import eapli.base.classmanagement.repositories.RecurringClassRepository;
import eapli.base.clientusermanagement.domain.Teacher;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryRecurringClassRepository extends InMemoryDomainRepository<RecurringClass, ClassTitle>
        implements RecurringClassRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<RecurringClass> findAllRecurringClass(Teacher teacher) {
        throw new UnsupportedOperationException("not implemented yet");
    }
}
