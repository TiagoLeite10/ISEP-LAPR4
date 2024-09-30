package eapli.base.usermanagement.domain;

import eapli.framework.infrastructure.authz.domain.model.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Classe para realizar testes à classe BaseRoles.
 */
public class BaseRolesTest {

    /**
     * Teste para garantir as roles dos utilizadores do sistema.
     */
    @Test
    void ensureExtraClassHasDateTime() {
        assertArrayEquals(BaseRoles.nonUserValues(), new Role[]{BaseRoles.ADMIN, BaseRoles.TEACHER, BaseRoles.STUDENT});
    }
}
