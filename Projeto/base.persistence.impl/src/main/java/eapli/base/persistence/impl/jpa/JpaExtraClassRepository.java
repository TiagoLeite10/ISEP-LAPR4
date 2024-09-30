package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.eventmanagement.domain.ExtraClass;
import eapli.base.eventmanagement.repositories.ExtraClassRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

/**
 * @author Gabriel Gonçalves (1191296)
 */
public class JpaExtraClassRepository extends JpaAutoTxRepository<ExtraClass, Long, Long>
        implements ExtraClassRepository {

    public JpaExtraClassRepository(final TransactionalContext autoTx) {
        super(autoTx, "extraClassId");
    }

    public JpaExtraClassRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "extraClassId");
    }
}
