package eapli.base.postitmanagement.exception;

/**
 * Representa exceções de quando existe um erro na imegem.
 */
public class ImageException extends RuntimeException {

    /**
     * Lança a exceção com mensagem por defeito.
     */
    public ImageException() {
        super("Failed in image!");
    }

    /**
     * Lança a exceção com uma string personalizada.
     *
     * @param s String personalizada
     */
    public ImageException(String s) {
        super(s);
    }
}
