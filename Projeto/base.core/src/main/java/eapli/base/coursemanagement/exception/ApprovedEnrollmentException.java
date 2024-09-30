package eapli.base.coursemanagement.exception;

/**
 * Representa exceções de quando não é possível aceitar uma inscrição.
 */
public class ApprovedEnrollmentException extends RuntimeException {
    /**
     * Lança a exceção com mensagem por defeito.
     */
    public ApprovedEnrollmentException() {
        super("Unable to approve the student in the course!");
    }

    /**
     * Lança a exceção com uma string personalizada.
     *
     * @param s String personalizada
     */
    public ApprovedEnrollmentException(String s) {
        super(s);
    }
}
