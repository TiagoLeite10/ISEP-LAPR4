package eapli.base.coursemanagement.exception;

/**
 * Representa exceções de quando o curso não tem disponibilidade para uma aula.
 */
public class CourseAvailabilityException extends RuntimeException {

    /**
     * Lança a exceção com mensagem por defeito.
     */
    public CourseAvailabilityException() {
        super("The course has no availability for class!");
    }

    /**
     * Lança a exceção com uma string personalizada.
     *
     * @param s String personalizada
     */
    public CourseAvailabilityException(String s) {
        super(s);
    }

}
