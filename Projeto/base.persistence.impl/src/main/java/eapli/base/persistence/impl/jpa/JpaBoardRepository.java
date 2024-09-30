package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.boardmanagement.domain.AccessPermission;
import eapli.base.boardmanagement.domain.Board;
import eapli.base.boardmanagement.repositories.BoardRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;

public class JpaBoardRepository extends JpaAutoTxRepository<Board, String, String> implements BoardRepository {

    public JpaBoardRepository(final TransactionalContext autoTx) {
        super(autoTx, "title");
    }

    public JpaBoardRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "title");
    }

    @Override
    public Iterable<Board> findAllBoards(Username user) {
        final TypedQuery<Board> query = entityManager().createQuery(
                "SELECT b FROM Board b WHERE b.archived = FALSE AND b.ownerUser.username = :name OR " +
                        "EXISTS (SELECT bm FROM Board b2 JOIN b2.boardMembers bm WHERE b2 = b AND bm.member.username = :name)",
                Board.class);

        query.setParameter("name", user);

        return query.getResultList();
    }

    @Override
    public Iterable<Board> findAllBoardsWithPermissionWrite(Username user) {
        final TypedQuery<Board> query = entityManager().createQuery(
                "SELECT b FROM Board b WHERE b.archived = FALSE AND b.ownerUser.username = :name OR " +
                        "EXISTS (SELECT bm FROM Board b2 JOIN b2.boardMembers bm WHERE b2 = b AND bm.member.username = :name " +
                        "AND bm.accessPermission = :permission)", Board.class);

        query.setParameter("name", user);
        query.setParameter("permission", AccessPermission.WRITE);

        return query.getResultList();
    }

    @Override
    public Iterable<Board> findAllBoardsOwned(Username user) {
        final TypedQuery<Board> query = entityManager().createQuery(
                "SELECT b FROM Board b WHERE b.archived = FALSE AND b.ownerUser.username = :name ", Board.class);

        query.setParameter("name", user);

        return query.getResultList();
    }

    @Override
    public Board find(String boardTitle) {
        final TypedQuery<Board> query = entityManager().createQuery(
                "SELECT b FROM Board b WHERE b.archived = FALSE AND b.title = :title", Board.class);

        query.setParameter("title", boardTitle);

        return query.getSingleResult();
    }

    @Override
    public Iterable<SystemUser> findAllUsersAvailableToShare(String boardTitle) {
        final TypedQuery<SystemUser> query = entityManager().createQuery(
                "SELECT u FROM SystemUser u WHERE NOT EXISTS " +
                        "(SELECT m FROM Board b JOIN b.boardMembers m WHERE b.title = :title AND m.member = u)" +
                        "AND u <> (SELECT b.ownerUser FROM Board b WHERE b.title = :title)",
                SystemUser.class);

        query.setParameter("title", boardTitle);

        return query.getResultList();
    }

    @Override
    public Board findBoardWithBoardMembersAndPostIts(String boardTitle) {
        final TypedQuery<Board> query = entityManager().createQuery(
                "SELECT b FROM Board b LEFT JOIN b.boardMembers bm LEFT JOIN b.postIts pi WHERE b.archived = FALSE AND b.title = :title",
                Board.class);

        query.setParameter("title", boardTitle);

        return query.getSingleResult();
    }
}
