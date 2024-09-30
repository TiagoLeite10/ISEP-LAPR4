package eapli.base.coursemanagement.exception;

/**
 * Representa exceções de quando não é possível rejeitar uma inscrição.
 */
public class RejectEnrollmentException extends RuntimeException {
    /**
     * Lança a exceção com mensagem por defeito.
     */
    public RejectEnrollmentException() {
        super("Unable to reject the student in the course!");
    }

    /**
     * Lança a exceção com uma string personalizada.
     *
     * @param s String personalizada
     */
    public RejectEnrollmentException(String s) {
        super(s);
    }
}
