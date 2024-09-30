package eapli.base.enrollmentmanagement.exception;

/**
 * Representa exceções de quando existe um erro na sintaxe do ficheiro.
 */
public class ErrorInFileException extends RuntimeException {
    /**
     * Lança a exceção com mensagem por defeito.
     */
    public ErrorInFileException() {
        super("There is an sintaxe erro in the file!");
    }

    /**
     * Lança a exceção com uma string personalizada.
     *
     * @param s String personalizada
     */
    public ErrorInFileException(String s) {
        super(s);
    }

    /**
     * Lança a exceção com uma string personalizada indicando a linha do ficheiro onde o erro se encontra.
     *
     * @param s String personalizada
     * @param line A linha onde existe o erro
     */
    public ErrorInFileException(String s, int line) {
        super(s + " The error is in the line " + line + " of the file!");
    }
}
