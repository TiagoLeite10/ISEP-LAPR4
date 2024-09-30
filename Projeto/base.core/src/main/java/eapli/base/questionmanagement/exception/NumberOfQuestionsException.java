package eapli.base.questionmanagement.exception;

/**
 * Representa exceções de quando não existe um número de questões para um tipo de questões.
 */
public class NumberOfQuestionsException extends RuntimeException {

    /**
     * Lança a exceção com mensagem por defeito.
     */
    public NumberOfQuestionsException() {
        super("The desired number of questions does not exist!");
    }

    /**
     * Lança a exceção com uma string personalizada.
     *
     * @param typeQuestion Tipo de questão
     * @param number       Number desejado
     */
    public NumberOfQuestionsException(String typeQuestion, Integer number) {
        super("There are no " + number + " questions of this type " + typeQuestion + " in the repository!");
    }
}
