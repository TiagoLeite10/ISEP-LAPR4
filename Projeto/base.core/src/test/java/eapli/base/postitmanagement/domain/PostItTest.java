package eapli.base.postitmanagement.domain;

import eapli.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe para realizar testes à entidade PostIt.
 */
public class PostItTest {

    private final String CONTENT = "Criar power point";

    private final int LINE = 5;

    private final int COLUMN = 2;

    final PostItType TYPE = PostItType.IMAGE;

    final SystemUser CREATOR = UserBuilderHelper.builder().with("luisa", "Password1",
            "Luísa", "Sobral", "luisa@gmail.com").build();

    private final SystemUser OTHER_CREATOR = UserBuilderHelper.builder().with("luisa", "Password1",
            "Luísa", "Sobral", "luisa@gmail.com").build();

    /**
     * Método para contruir um post-it.
     *
     * @return O post-it construído.
     */
    private PostIt buildPostIt() {
        return new PostItBuilder().with(CONTENT, LINE, COLUMN, TYPE, CREATOR).build();
    }

    /**
     * Teste para garantir que não é possível criar uma instância da classe PostIt com valores nulos.
     */
    @Test
    public void ensurePostItNullIsNotAllowed() {
        assertThrows(IllegalArgumentException.class, () -> new PostItBuilder().with(null, 0, 0, null, null));
    }

    /**
     * Teste para garantir que um post-it tem de possuir um conteúdo.
     */
    @Test
    void ensurePostItHasContent() {
        assertThrows(IllegalArgumentException.class, () -> new PostItBuilder().with(null, LINE, COLUMN, TYPE, null).build());
    }

    /**
     * Teste para garantir que um post-it tem de possuir uma linha válida.
     */
    @Test
    void ensurePostItHasValidLine() {
        assertThrows(IllegalArgumentException.class, () -> new PostItBuilder().with(CONTENT, -1, COLUMN, TYPE, CREATOR).build());
    }

    /**
     * Teste para garantir que um post-it tem de possuir uma coluna válida.
     */
    @Test
    void ensurePostItHasValidColumn() {
        assertThrows(IllegalArgumentException.class, () -> new PostItBuilder().with(CONTENT, LINE, -1, TYPE, CREATOR).build());
    }

    /**
     * Teste para garantir que um post-it tem de possuir um tipo.
     */
    @Test
    void ensurePostItHasType() {
        assertThrows(IllegalArgumentException.class, () -> new PostItBuilder().with(CONTENT, LINE, COLUMN, null, CREATOR).build());
    }

    /**
     * Teste para garantir que um post-it tem de possuir um criador.
     */
    @Test
    void ensurePostItHasCreator() {
        assertThrows(IllegalArgumentException.class, () -> new PostItBuilder().with(CONTENT, LINE, COLUMN, TYPE, null).build());
    }

    /**
     * Teste para garantir que um post-it tem a informação do criador esperada.
     */
    @Test
    void ensurePostItHasExpectedInfoCreator() {
        final PostIt subject = buildPostIt();

        assertEquals("Luísa Sobral (luisa@gmail.com)", subject.infoCreator());
    }

    /**
     * Teste para garantir que um post-it não tem a informação do criador esperada.
     */
    @Test
    void ensurePostItHasNotExpectedInfoCreator() {
        final PostIt subject = buildPostIt();

        assertNotEquals("João Sobral (joao@gmail.com)", subject.infoCreator());
    }

    /**
     * Teste para garantir que um post-it tem o conteúdo esperado.
     */
    @Test
    void ensurePostItHasExpectedContent() {
        final PostIt subject = buildPostIt();

        assertEquals(CONTENT, subject.content());
    }

    /**
     * Teste para garantir que um post-it não tem o conteúdo esperado.
     */
    @Test
    void ensurePostItHasNotExpectedContent() {
        final PostIt subject = buildPostIt();

        assertNotEquals("Fazer o jantar", subject.content());
    }

    /**
     * Teste para garantir que um post-it tem a linha esperada.
     */
    @Test
    void ensurePostItHasExpectedLine() {
        final PostIt subject = buildPostIt();

        assertEquals(LINE, subject.line());
    }

    /**
     * Teste para garantir que um post-it não tem a linha esperada.
     */
    @Test
    void ensurePostItHasNotExpectedLine() {
        final PostIt subject = buildPostIt();

        assertNotEquals(10, subject.line());
    }

    /**
     * Teste para garantir que um post-it tem a coluna esperada.
     */
    @Test
    void ensurePostItHasExpectedColumn() {
        final PostIt subject = buildPostIt();

        assertEquals(COLUMN, subject.column());
    }

    /**
     * Teste para garantir que um post-it não tem a coluna esperada.
     */
    @Test
    void ensurePostItHasNotExpectedColumn() {
        final PostIt subject = buildPostIt();

        assertNotEquals(15, subject.column());
    }

    /**
     * Teste para garantir que um post-it tem o tipo esperado.
     */
    @Test
    void ensurePostItHasExpectedType() {
        final PostIt subject = buildPostIt();

        assertEquals(TYPE, subject.type());
    }

    /**
     * Teste para garantir que um post-it não tem o tipo esperado.
     */
    @Test
    void ensurePostItHasNotExpectedType() {
        final PostIt subject = buildPostIt();

        assertNotEquals(PostItType.TEXT, subject.type());
    }

    /**
     * Teste para garantir que um post-it é existente.
     */
    @Test
    void ensurePostItIsExisting() {
        final PostIt subject = buildPostIt();

        assertTrue(subject.isExisting());
    }

    /**
     * Teste para garantir que um post-it não é existente.
     */
    @Test
    void ensurePostItNotIsExisting() {
        final PostIt subject = buildPostIt();
        subject.remove();

        assertFalse(subject.isExisting());
    }

    /**
     * Teste para garantir que um post-it é uma imagem.
     */
    @Test
    void ensurePostItIsImage() {
        final PostIt subject = buildPostIt();

        assertTrue(subject.isImage());
    }

    /**
     * Teste para garantir que um post-it é clonado.
     */
    @Test
    void ensurePostItIsCloned() {
        final PostIt subject = buildPostIt();

        assertEquals(subject, subject.clone());
    }

    /**
     * Teste para garantir que um post-it é clonado com um conteúdo diferente.
     */
    @Test
    void ensurePostItIsClonedWithDifferentContent() {
        final PostIt subject = buildPostIt();

        assertEquals("Fazer a sopa", subject.cloneWithDifferentContent("Fazer a sopa").content());
    }

    /**
     * Teste para garantir que um post-it é clonado com uma linha da posição diferente.
     */
    @Test
    void ensurePostItIsClonedWithDifferentLine() {
        final PostIt subject = buildPostIt();

        assertEquals(10, subject.cloneWithDifferentPosition(10, 2).line());
    }

    /**
     * Teste para garantir que um post-it é clonado com uma coluna da posição diferente.
     */
    @Test
    void ensurePostItIsClonedWithDifferentColumn() {
        final PostIt subject = buildPostIt();

        assertEquals(3, subject.cloneWithDifferentPosition(5, 3).column());
    }

    /**
     * Teste para garantir que dois post-its são iguais para o mesmo conteúdo, linha, coluna, tipo e criador.
     */
    @Test
    void ensureTwoPostItWithSameContentsLinesColumnsTypesCreatorsAreTheSame() {
        final PostIt aPostIt = buildPostIt();
        final PostIt anotherPostIt = new PostItBuilder().withContent("Criar power point").withPosition(5, 2)
                .withType(PostItType.IMAGE).withCreator(CREATOR).build();

        assertTrue(aPostIt.sameAs(anotherPostIt));
    }


    /**
     * Teste para garantir que dois post-its não são iguais para um conteúdo diferente.
     */
    @Test
    void ensureTwoPostItWithDifferentContentsAreNotTheSame() {
        final PostIt aPostIt = buildPostIt();
        final PostIt anotherPostIt = new PostItBuilder().withContent("Criar um documento em txt").withPosition(5, 2)
                .withType(PostItType.IMAGE).withCreator(CREATOR).build();

        assertFalse(aPostIt.sameAs(anotherPostIt));
    }

    /**
     * Teste para garantir que dois post-its não são iguais para uma linha diferente.
     */
    @Test
    void ensureTwoPostItWithDifferentLinesAreNotTheSame() {
        final PostIt aPostIt = buildPostIt();
        final PostIt anotherPostIt = new PostItBuilder().withContent("Criar power point").withPosition(10, 2)
                .withType(PostItType.IMAGE).withCreator(CREATOR).build();

        assertFalse(aPostIt.sameAs(anotherPostIt));
    }


    /**
     * Teste para garantir que dois post-its não são iguais para uma coluna diferente.
     */
    @Test
    void ensureTwoPostItWithDifferentColumnsAreNotTheSame() {
        final PostIt aPostIt = buildPostIt();
        final PostIt anotherPostIt = new PostItBuilder().withContent("Criar power point").withPosition(5, 10)
                .withType(PostItType.IMAGE).withCreator(CREATOR).build();

        assertFalse(aPostIt.sameAs(anotherPostIt));
    }

    /**
     * Teste para garantir que dois post-its não são iguais para um tipo diferente.
     */
    @Test
    void ensureTwoPostItWithDifferentTypesAreNotTheSame() {
        final PostIt aPostIt = buildPostIt();
        final PostIt anotherPostIt = new PostItBuilder().withContent("Criar power point").withPosition(5, 2)
                .withType(PostItType.TEXT).withCreator(CREATOR).build();

        assertFalse(aPostIt.sameAs(anotherPostIt));
    }

    /**
     * Teste para garantir que dois post-its não são iguais para um criador diferente.
     */
    @Test
    void ensureTwoPostItWithDifferentCreatorsAreNotTheSame() {
        final PostIt aPostIt = buildPostIt();
        final PostIt anotherPostIt = new PostItBuilder().withContent("Criar power point").withPosition(5, 2)
                .withType(PostItType.IMAGE).withCreator(OTHER_CREATOR).build();

        assertFalse(aPostIt.sameAs(anotherPostIt));
    }

    /**
     * Teste para garantir que um post-it é o mesmo para a mesma instância.
     */
    @Test
    void ensurePostItAreTheSameForTheSameInstance() {
        final PostIt aPostIt = new PostIt();

        assertTrue(aPostIt.sameAs(aPostIt));
    }

    /**
     * Teste para garantir que um post-it não é o mesmo para diferentes tipos de objetos.
     */
    @Test
    void ensurePostItNotTheSameForDifferenteObjectTypes() {
        final PostIt aPostIt = buildPostIt();

        assertFalse(aPostIt.sameAs(CONTENT));
    }


}
