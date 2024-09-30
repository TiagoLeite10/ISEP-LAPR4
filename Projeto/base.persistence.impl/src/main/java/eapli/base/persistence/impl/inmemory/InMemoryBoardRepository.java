package eapli.base.persistence.impl.inmemory;

import eapli.base.boardmanagement.domain.Board;
import eapli.base.boardmanagement.repositories.BoardRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryBoardRepository extends InMemoryDomainRepository<Board, String> implements BoardRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<Board> findAllBoards(final Username user) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public Iterable<Board> findAllBoardsWithPermissionWrite(Username user) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public Iterable<Board> findAllBoardsOwned(Username user) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public Board find(String boardTitle) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public Iterable<SystemUser> findAllUsersAvailableToShare(String boardTitle) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public Board findBoardWithBoardMembersAndPostIts(String boardTitle) {
        throw new UnsupportedOperationException("not implemented yet");
    }
}
