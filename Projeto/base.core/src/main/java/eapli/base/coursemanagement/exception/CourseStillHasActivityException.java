package eapli.base.coursemanagement.exception;

/**
 * Representa exceções de quando o curso ainda tem atividade pendente.
 */
public class CourseStillHasActivityException extends RuntimeException {

    /**
     * Lança a exceção com mensagem por defeito.
     */
    public CourseStillHasActivityException() {
        super("The course still has pending activity!");
    }

    /**
     * Lança a exceção com uma string personalizada.
     *
     * @param s String personalizada
     */
    public CourseStillHasActivityException(String s) {
        super(s);
    }

}
