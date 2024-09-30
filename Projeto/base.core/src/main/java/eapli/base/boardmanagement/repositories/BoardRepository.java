package eapli.base.boardmanagement.repositories;

import eapli.base.boardmanagement.domain.Board;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;

public interface BoardRepository extends DomainRepository<String, Board> {

    Iterable<Board> findAllBoards(final Username user);

    Iterable<Board> findAllBoardsWithPermissionWrite(final Username user);

    Iterable<Board> findAllBoardsOwned(final Username user);

    Board find(final String boardTitle);

    Iterable<SystemUser> findAllUsersAvailableToShare(final String boardTitle);

    Board findBoardWithBoardMembersAndPostIts(final String boardTitle);
}
