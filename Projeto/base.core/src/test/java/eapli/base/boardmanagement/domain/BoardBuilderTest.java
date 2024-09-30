package eapli.base.boardmanagement.domain;

import eapli.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Classe para realizar testes à entidade BoardBuilder.
 */
public class BoardBuilderTest {

    private final String TITLE = "Board Test";
    private final BoardCreationDateTime CREATION_DATE_TIME = BoardCreationDateTime.valueOf();
    private final BoardDimensions DIMENSIONS = BoardDimensions.valueOf(10, 10);

    private final SystemUser OWNER_USER = UserBuilderHelper.builder().with("MarcoPaulo", "Password1",
            "Marco", "Paulo", "marcopaulo@gmail.com").build();

    /**
     * Teste para garantir que não é possível construir uma board com título nulo.
     */
    @Test
    public void ensureCannotBuildBoardWithNullTitle() {
        assertThrows(IllegalStateException.class, () -> new BoardBuilder().entitled(null).creationDateTime(CREATION_DATE_TIME).
                dimensions(DIMENSIONS).owner(OWNER_USER).build());
    }

    /**
     * Teste para garantir que não é possível construir uma board com a data de criação nula.
     */
    @Test
    public void ensureCannotBuildBoardWithNullCreationDateTime() {
        assertThrows(IllegalStateException.class, () -> new BoardBuilder().entitled(TITLE).creationDateTime(null).
                dimensions(DIMENSIONS).owner(OWNER_USER).build());
    }

    /**
     * Teste para garantir que não é possível construir uma board com a dimensão nula.
     */
    @Test
    public void ensureCannotBuildBoardWithNullDimensions() {
        assertThrows(IllegalStateException.class, () -> new BoardBuilder().entitled(TITLE).creationDateTime(CREATION_DATE_TIME).
                dimensions(null).owner(OWNER_USER).build());
    }

    /**
     * Teste para garantir que não é possível construir uma board com o dono nulo.
     */
    @Test
    public void ensureCannotBuildBoardWithNullOwner() {
        assertThrows(IllegalStateException.class, () -> new BoardBuilder().entitled(TITLE).creationDateTime(CREATION_DATE_TIME).
                dimensions(DIMENSIONS).owner(null).build());
    }
}
