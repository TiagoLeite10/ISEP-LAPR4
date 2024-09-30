package eapli.base.coursemanagement.exception;

/**
 * Representa exceções de quando o curso já tem um professor responsável (regente).
 */
public class CourseAlreadyHasResponsibleException extends RuntimeException {
    /**
     * Lança a exceção com mensagem por defeito.
     */
    public CourseAlreadyHasResponsibleException() {
        super("The course already has a responsible teacher!");
    }

    /**
     * Lança a exceção com uma string personalizada.
     *
     * @param s String personalizada
     */
    public CourseAlreadyHasResponsibleException(String s) {
        super(s);
    }
}
