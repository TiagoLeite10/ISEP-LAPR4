package eapli.base.coursemanagement.exception;

/**
 * Representa exceções de quando o curso ainda tem inscrições pendentes.
 */
public class PendentEnrollmentStateException extends RuntimeException {

    /**
     * Lança a exceção com mensagem por defeito.
     */
    public PendentEnrollmentStateException() {
        super("The course still has enrollments pending!");
    }

    /**
     * Lança a exceção com uma string personalizada.
     *
     * @param s String personalizada
     */
    public PendentEnrollmentStateException(String s) {
        super(s);
    }

}
