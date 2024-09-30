package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.postitmanagement.domain.PostIt;
import eapli.base.postitmanagement.domain.PostItStatus;
import eapli.base.postitmanagement.repositories.PostItRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;

public class JpaPostItRepository extends JpaAutoTxRepository<PostIt, Long, Long>
        implements PostItRepository {

    public JpaPostItRepository(final TransactionalContext autoTx) {
        super(autoTx, "postItId");
    }

    public JpaPostItRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "postItId");
    }

    @Override
    public Iterable<PostIt> findAllExistingPostIts(String boardTitle) {
        final TypedQuery<PostIt> query = entityManager().createQuery(
                "SELECT p FROM Board b JOIN b.postIts p WHERE p.status = :status AND b.title = :title",
                PostIt.class);

        query.setParameter("status", PostItStatus.EXIST);
        query.setParameter("title", boardTitle);

        return query.getResultList();
    }
}
