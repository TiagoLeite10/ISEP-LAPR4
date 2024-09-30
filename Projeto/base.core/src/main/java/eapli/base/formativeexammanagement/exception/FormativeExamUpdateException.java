package eapli.base.formativeexammanagement.exception;

/**
 * Representa exceções de quando é tentado atualizar um exame formativo automático em progresso ou terminado.
 */
public class FormativeExamUpdateException extends RuntimeException {

    /**
     * Lança a exceção com mensagem por defeito.
     */
    public FormativeExamUpdateException() {
        super("Unable to update the formative exam because it is in progress or finish!");
    }

    /**
     * Lança a exceção com uma string personalizada.
     *
     * @param s String personalizada
     */
    public FormativeExamUpdateException(String s) {
        super(s);
    }
}
