package eapli.base.postitmanagement.domain;

import eapli.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Classe para realizar testes ao builder PostItBuilder.
 */
public class PostItBuilderTest {

    private final String CONTENT = "Criar power point";

    private final int LINE = 5;

    private final int COLUMN = 2;

    private final SystemUser CREATOR = UserBuilderHelper.builder().with("MarcoPaulo", "Password1",
            "Marco", "Paulo", "marcopaulo@gmail.com").build();

    /**
     * Teste para garantir que é possível construir um post-it com conteúdo, posição, tipo e criador.
     */
    @Test
    public void ensureCanBuildPostItWithContentTypePositionCreator() {
        final PostIt subject = new PostItBuilder().with(CONTENT, LINE, COLUMN, PostItType.TEXT, CREATOR).build();
        assertNotNull(subject);
    }

    /**
     * Teste para garantir que não é possível construir um post-it com o conteúdo nulo.
     */
    @Test
    public void ensureCanBuildPostItWithContentNull() {
        assertThrows(IllegalArgumentException.class, () -> new PostItBuilder().withContent(null).withPosition(LINE, COLUMN)
                .withType(PostItType.TEXT).withCreator(CREATOR).build());
    }

    /**
     * Teste para garantir que não é possível construir um post-it com a linha da posição inválida.
     */
    @Test
    public void ensureCanBuildPostItWithLineInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new PostItBuilder().withContent(CONTENT).withPosition(-5, COLUMN)
                .withType(null).withCreator(CREATOR).build());
    }

    /**
     * Teste para garantir que não é possível construir um post-it com a coluna da posição inválida.
     */
    @Test
    public void ensureCanBuildPostItWithColumnInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new PostItBuilder().withContent(CONTENT).withPosition(LINE, -2)
                .withType(null).withCreator(CREATOR).build());
    }

    /**
     * Teste para garantir que não é possível construir um post-it com o tipo nulo.
     */
    @Test
    public void ensureCanBuildPostItWithTypeNull() {
        assertThrows(IllegalArgumentException.class, () -> new PostItBuilder().withContent(CONTENT).withPosition(LINE, COLUMN)
                .withType(null).withCreator(CREATOR).build());
    }

    /**
     * Teste para garantir que não é possível construir um post-it com o criador nulo.
     */
    @Test
    public void ensureCanBuildPostItWithCreatorNull() {
        assertThrows(IllegalArgumentException.class, () -> new PostItBuilder().withContent(CONTENT).withPosition(LINE, COLUMN)
                .withType(PostItType.TEXT).withCreator(null).build());
    }
}
