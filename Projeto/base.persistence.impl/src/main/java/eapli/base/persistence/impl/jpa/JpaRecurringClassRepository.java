package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.classmanagement.domain.ClassTitle;
import eapli.base.classmanagement.domain.RecurringClass;
import eapli.base.classmanagement.repositories.RecurringClassRepository;
import eapli.base.clientusermanagement.domain.Teacher;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;

public class JpaRecurringClassRepository extends JpaAutoTxRepository<RecurringClass, ClassTitle, ClassTitle>
        implements RecurringClassRepository {

    public JpaRecurringClassRepository(final TransactionalContext autoTx) {
        super(autoTx, "classTitle");
    }

    public JpaRecurringClassRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "classTitle");
    }

    @Override
    public Iterable<RecurringClass> findAllRecurringClass(Teacher teacher) {
        final TypedQuery<RecurringClass> query = entityManager().createQuery(
                "SELECT r FROM Course c JOIN c.recurringClasses r WHERE r.teacher = :teacher",
                RecurringClass.class);
        query.setParameter("teacher", teacher);

        return query.getResultList();
    }
}
