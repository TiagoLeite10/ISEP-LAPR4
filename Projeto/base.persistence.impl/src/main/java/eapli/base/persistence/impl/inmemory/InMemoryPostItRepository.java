package eapli.base.persistence.impl.inmemory;

import eapli.base.postitmanagement.domain.PostIt;
import eapli.base.postitmanagement.repositories.PostItRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryPostItRepository extends InMemoryDomainRepository<PostIt, Long>
        implements PostItRepository {

    @Override
    public Iterable<PostIt> findAllExistingPostIts(String boardTitle) {
        throw new UnsupportedOperationException("not implemented yet");
    }
}
