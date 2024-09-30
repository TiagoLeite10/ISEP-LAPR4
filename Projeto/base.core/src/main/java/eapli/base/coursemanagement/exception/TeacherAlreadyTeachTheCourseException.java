package eapli.base.coursemanagement.exception;

/**
 * Representa exceções de quando um professor já ensina a disciplina na qual o tentaram colocar a lecionar.
 */
public class TeacherAlreadyTeachTheCourseException extends RuntimeException {
    /**
     * Lança a exceção com mensagem por defeito.
     */
    public TeacherAlreadyTeachTheCourseException() {
        super("This professor already teaches this course!");
    }

    /**
     * Lança a exceção com uma string personalizada.
     *
     * @param s String personalizada
     */
    public TeacherAlreadyTeachTheCourseException(String s) {
        super(s);
    }
}
