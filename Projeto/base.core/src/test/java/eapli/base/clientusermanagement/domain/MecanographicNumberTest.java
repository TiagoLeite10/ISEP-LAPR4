package eapli.base.clientusermanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Classe para realizar testes ao value object MecanographicNumber.
 */
public class MecanographicNumberTest {

    private final MecanographicNumber subject = MecanographicNumber.valueOf("202300001");

    /**
     * Teste para garantir que um número mecanográfico não deve ser vazio.
     */
    @Test
    void ensureMecanographicNumberMustNotBeEmpty() {
        assertThrows(IllegalArgumentException.class, () -> MecanographicNumber.valueOf(""));
    }

    /**
     * Teste para garantir que um número mecanográfico não deve ser nulo.
     */
    @Test
    void ensureMecanographicNumberMustNotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> MecanographicNumber.valueOf(null));
    }

    /**
     * Teste para garantir que um número mecanográfico tem de possuir apenas números.
     */
    @Test
    void ensureMecanographicNumberHasOnlyNumbers() {
        assertThrows(IllegalArgumentException.class, () -> MecanographicNumber.valueOf("20230000A"));
    }

    /**
     * Teste para garantir que um número mecanográfico tem de possuir 9 números.
     */
    @Test
    void ensureMecanographicNumberHasNineNumbers() {
        assertThrows(IllegalArgumentException.class, () -> MecanographicNumber.valueOf("20230001"));
    }

    /**
     * Teste para garantir que um número mecanográfico é igual a outro.
     */
    @Test
    void ensureMecanographicNumberIsSame() {
        assertEquals(0, subject.compareTo(MecanographicNumber.valueOf("202300001")));
    }

    /**
     * Teste para garantir que um número mecanográfico é posterior a outro.
     */
    @Test
    void ensureMecanographicNumberIsAfter() {
        assertEquals(1, subject.compareTo(MecanographicNumber.valueOf("202300000")));
    }

    /**
     * Teste para garantir que um número mecanográfico é anterior a outro.
     */
    @Test
    void ensureMecanographicNumberIsBefore() {
        assertEquals(-1, subject.compareTo(MecanographicNumber.valueOf("202300002")));
    }
}
