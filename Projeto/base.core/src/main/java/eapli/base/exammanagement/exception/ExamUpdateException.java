package eapli.base.exammanagement.exception;

/**
 * Representa exceções de quando é tentado atualizar um exame em progresso ou finalizado.
 */
public class ExamUpdateException extends RuntimeException {

    /**
     * Lança a exceção com mensagem por defeito.
     */
    public ExamUpdateException() {
        super("Unable to update the exam because it is in progress or finish!");
    }

    /**
     * Lança a exceção com uma string personalizada.
     *
     * @param s String personalizada
     */
    public ExamUpdateException(String s) {
        super(s);
    }
}
