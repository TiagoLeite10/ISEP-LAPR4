package eapli.base.boardmanagement.domain;

import eapli.base.postitmanagement.domain.PostItBuilder;
import eapli.base.postitmanagement.domain.PostItType;
import eapli.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe para realizar testes à entidade Board.
 */
public class BoardTest {

    private final String TITLE = "Board Test";

    private final BoardCreationDateTime BOARD_CREATION_DATE_TIME = BoardCreationDateTime.valueOf();

    private final SystemUser OWNER_USER = UserBuilderHelper.builder().with("MarcoPaulo", "Password1",
            "Marco", "Paulo", "marcopaulo@gmail.com").build();

    private final BoardDimensions BOARD_DIMENSIONS = BoardDimensions.valueOf(10, 10);

    private final SystemUser OTHER_OWNER_USER = UserBuilderHelper.builder().with("luisa", "Password1",
            "Luísa", "Sobral", "luisa@gmail.com").build();

    private Board buildBoard() {
        return new Board(TITLE, BOARD_CREATION_DATE_TIME, BOARD_DIMENSIONS, OWNER_USER);
    }

    /**
     * Teste para garantir que uma board tem de possuir um título.
     */
    @Test
    void ensureBoardHasTitle() {
        assertThrows(IllegalArgumentException.class, () -> new Board(null, BOARD_CREATION_DATE_TIME, BOARD_DIMENSIONS, OWNER_USER));
    }

    /**
     * Teste para garantir que uma board tem de possuir uma data e hora de criação.
     */
    @Test
    void ensureBoardHasCreationDateTime() {
        assertThrows(IllegalArgumentException.class, () -> new Board(TITLE, null, BOARD_DIMENSIONS, OWNER_USER));
    }

    /**
     * Teste para garantir que uma board tem de possuir uma dimensão.
     */
    @Test
    void ensureBoardHasDimensions() {
        assertThrows(IllegalArgumentException.class, () -> new Board(TITLE, BOARD_CREATION_DATE_TIME, null, OWNER_USER));
    }

    /**
     * Teste para garantir que uma board tem de possuir um dono.
     */
    @Test
    void ensureBoardHasOwnerUser() {
        assertThrows(IllegalArgumentException.class, () -> new Board(TITLE, BOARD_CREATION_DATE_TIME, BOARD_DIMENSIONS, null));
    }

    /**
     * Teste para garantir que uma board tem o titulo esperado.
     */
    @Test
    void ensureBoardHasExpectedTitle() {
        final Board subject = buildBoard();

        assertEquals("Board Test", subject.identity());
    }

    /**
     * Teste para garantir que é possível arquivar uma board.
     */
    @Test
    void ensureIsPossibleArchivedBoard() {
        final Board subject = buildBoard();
        subject.archiveBoard();

        assertTrue(subject.isArchived());
    }

    /**
     * Teste para garantir que não é possível adicionar um *post-it* à board fora dos limites da board.
     */
    @Test
    void ensureIsNotPossibleAddPostItTheBoardOutsideTheBoardLimits() {
        final Board subject = buildBoard();

        assertThrows(IllegalArgumentException.class, () -> subject.addPostIt(new PostItBuilder().withContent("Criar power point")
                .withPosition(25, 2)
                .withType(PostItType.IMAGE)
                .withCreator(UserBuilderHelper.builder().with("luisa", "Password1",
                        "Luísa", "Sobral", "luisa@gmail.com").build()).build()));
    }

    /**
     * Teste para garantir que duas boards são iguais para o mesmo título, hora e data de criação, dimensões e dono.
     */
    @Test
    void ensureTwoBoardWithSameTitlesCreationDateTimeDimensionsOwnersAreTheSame() {
        final Board aBoard = buildBoard();
        final Board anotherBoard = new BoardBuilder().entitled("Board Test").creationDateTime(BoardCreationDateTime.valueOf())
                .dimensions(BoardDimensions.valueOf(10, 10)).owner(OWNER_USER).build();

        assertTrue(aBoard.sameAs(anotherBoard));
    }


    /**
     * Teste para garantir que duas boards não são iguais para um título diferente.
     */
    @Test
    void ensureTwoBoardWithDifferentTitlesAreNotTheSame() {
        final Board aBoard = buildBoard();
        final Board anotherBoard = new BoardBuilder().entitled("Board Test2").creationDateTime(BoardCreationDateTime.valueOf())
                .dimensions(BoardDimensions.valueOf(10, 10)).owner(OWNER_USER).build();

        assertFalse(aBoard.sameAs(anotherBoard));
    }

    /**
     * Teste para garantir que duas boards não são iguais para umas dimensões diferente.
     */
    @Test
    void ensureTwoBoardWithDifferentDimensionsAreNotTheSame() {
        final Board aBoard = buildBoard();
        final Board anotherBoard = new BoardBuilder().entitled("Board Test").creationDateTime(BoardCreationDateTime.valueOf())
                .dimensions(BoardDimensions.valueOf(2, 2)).owner(OWNER_USER).build();

        assertFalse(aBoard.sameAs(anotherBoard));
    }

    /**
     * Teste para garantir que duas boards não são iguais para um dono diferente.
     */
    @Test
    void ensureTwoBoardWithDifferentOwnersAreNotTheSame() {
        final Board aBoard = buildBoard();
        final Board anotherBoard = new BoardBuilder().entitled("Board Test").creationDateTime(BoardCreationDateTime.valueOf())
                .dimensions(BoardDimensions.valueOf(10, 10)).owner(OTHER_OWNER_USER).build();

        assertFalse(aBoard.sameAs(anotherBoard));
    }

    /**
     * Teste para garantir que uma board é a mesma para a mesma instância.
     */
    @Test
    void ensureBoardAreTheSameForTheSameInstance() {
        final Board aBoard = new Board();

        assertTrue(aBoard.sameAs(aBoard));
    }

    /**
     * Teste para garantir que uma board não é a mesma para diferentes tipos de objetos.
     */
    @Test
    void ensureBoardNotTheSameForDifferenteObjectTypes() {
        final Board aBoard = buildBoard();

        assertFalse(aBoard.sameAs(TITLE));
    }
}
