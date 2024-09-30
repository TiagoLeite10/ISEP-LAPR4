package eapli.base.eventmanagement.exception;

/**
 * Representa exceções de quando não é possível cancelar uma reunião.
 */
public class MeetingNotHeldException extends RuntimeException {

    /**
     * Lança a exceção com mensagem por defeito.
     */
    public MeetingNotHeldException() {
        super("Cannot cancel meeting because it has already taken place!");
    }

    /**
     * Lança a exceção com uma string personalizada.
     *
     * @param s String personalizada
     */
    public MeetingNotHeldException(String s) {
        super(s);
    }

}
