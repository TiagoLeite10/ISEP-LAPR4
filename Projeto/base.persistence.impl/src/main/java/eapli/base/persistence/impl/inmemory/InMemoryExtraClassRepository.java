package eapli.base.persistence.impl.inmemory;

import eapli.base.eventmanagement.domain.ExtraClass;
import eapli.base.eventmanagement.repositories.ExtraClassRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

/**
 * @author Gabriel Gonçalves (1191296)
 */
public class InMemoryExtraClassRepository extends InMemoryDomainRepository<ExtraClass, Long>
        implements ExtraClassRepository {

    static {
        InMemoryInitializer.init();
    }
}
