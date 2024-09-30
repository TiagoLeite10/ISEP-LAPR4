package eapli.base.boardmanagement.domain;

import eapli.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe para realizar testes à entidade BoardMember.
 */
public class BoardMemberTest {

    private final SystemUser USER = UserBuilderHelper.builder().with("MarcoPaulo", "Password1",
            "Marco", "Paulo", "marcopaulo@gmail.com").build();

    private final SystemUser OTHER_USER = UserBuilderHelper.builder().with("luisa", "Password1",
            "Luísa", "Sobral", "luisa@gmail.com").build();

    /**
     * Método para contruir um membro da board.
     *
     * @return Um membro da board.
     */
    private BoardMember buildBoardMember() {
        return new BoardMember(USER, AccessPermission.READ);
    }

    /**
     * Teste para garantir que um membro da board tem de possuir um utilizador.
     */
    @Test
    void ensureBoardMemberHasUser() {
        assertThrows(IllegalArgumentException.class, () -> new BoardMember(null, AccessPermission.READ));
    }

    /**
     * Teste para garantir que um membro da board tem permissões de acesso.
     */
    @Test
    void ensureBoardMemberHasAccessPermission() {
        assertThrows(IllegalArgumentException.class, () -> new BoardMember(USER, null));
    }

    /**
     * Teste para garantir que dois membros da board são iguais para o mesmo utilizador.
     */
    @Test
    void ensureTwoBoardMemberWithSameUsersAreTheSame() {
        final BoardMember aBoardMember = buildBoardMember();
        final BoardMember anotherBoardMember = new BoardMember(USER, AccessPermission.READ);

        assertTrue(aBoardMember.sameAs(anotherBoardMember));
    }

    /**
     * Teste para garantir que dois membros da board não são iguais para um utilizador diferente.
     */
    @Test
    void ensureTwoBoardMemberWithDifferentUsersAreNotTheSame() {
        final BoardMember aBoardMember = buildBoardMember();
        final BoardMember anotherBoardMember = new BoardMember(OTHER_USER, AccessPermission.WRITE);

        assertFalse(aBoardMember.sameAs(anotherBoardMember));
    }

    /**
     * Teste para garantir que um membro da board é a mesma para a mesma instância.
     */
    @Test
    void ensureBoardMemberAreTheSameForTheSameInstance() {
        final BoardMember aBoardMember = new BoardMember();

        assertTrue(aBoardMember.sameAs(aBoardMember));
    }

    /**
     * Teste para garantir que um membro da board não é a mesma para diferentes tipos de objetos.
     */
    @Test
    void ensureBoardMemberNotTheSameForDifferenteObjectTypes() {
        final BoardMember aBoardMember = buildBoardMember();

        assertFalse(aBoardMember.sameAs(USER));
    }
}
