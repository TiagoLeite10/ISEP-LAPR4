package eapli.base.coursemanagement.exception;

/**
 * Representa exceções de quando o curso tem um número inválido de inscrições.
 */
public class InvalidEnrollmentLimitsException extends RuntimeException {
    /**
     * Lança a exceção com mensagem por defeito.
     */
    public InvalidEnrollmentLimitsException() {
        super("The course has invalid enrollments number!");
    }

    /**
     * Lança a exceção com uma string personalizada.
     *
     * @param s String personalizada
     */
    public InvalidEnrollmentLimitsException(String s) {
        super(s);
    }
}
