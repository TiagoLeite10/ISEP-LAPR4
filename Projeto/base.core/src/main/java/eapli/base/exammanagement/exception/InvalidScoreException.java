package eapli.base.exammanagement.exception;

/**
 * Representa exceções de quando a pontuação de um exame é inválida.
 */
public class InvalidScoreException extends RuntimeException {

    /**
     * Lança a exceção com mensagem por defeito.
     */
    public InvalidScoreException() {
        super("The score for an exam has invalid!");
    }

    /**
     * Lança a exceção com uma string personalizada.
     *
     * @param s String personalizada
     */
    public InvalidScoreException(String s) {
        super(s);
    }
}
