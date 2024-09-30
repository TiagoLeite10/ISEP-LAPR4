package eapli.base.postitmanagement.repositories;

import eapli.base.postitmanagement.domain.PostIt;
import eapli.framework.domain.repositories.DomainRepository;

public interface PostItRepository extends DomainRepository<Long, PostIt> {

    Iterable<PostIt> findAllExistingPostIts(final String boardTitle);
}
