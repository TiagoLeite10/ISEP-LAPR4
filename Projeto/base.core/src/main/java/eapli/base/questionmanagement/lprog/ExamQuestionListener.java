// Generated from C:/Users/Gabriel/Desktop/Escola/Engenharia Informática/2º Ano/2º Semetre/Projeto Integrador/sem4pi-22-23-20/antlr4\ExamQuestion.g4 by ANTLR 4.12.0

package eapli.base.questionmanagement.lprog;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExamQuestionParser}.
 */
public interface ExamQuestionListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ExamQuestionParser#examQuestion}.
	 * @param ctx the parse tree
	 */
	void enterExamQuestion(ExamQuestionParser.ExamQuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamQuestionParser#examQuestion}.
	 * @param ctx the parse tree
	 */
	void exitExamQuestion(ExamQuestionParser.ExamQuestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamQuestionParser#question}.
	 * @param ctx the parse tree
	 */
	void enterQuestion(ExamQuestionParser.QuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamQuestionParser#question}.
	 * @param ctx the parse tree
	 */
	void exitQuestion(ExamQuestionParser.QuestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamQuestionParser#matching}.
	 * @param ctx the parse tree
	 */
	void enterMatching(ExamQuestionParser.MatchingContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamQuestionParser#matching}.
	 * @param ctx the parse tree
	 */
	void exitMatching(ExamQuestionParser.MatchingContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamQuestionParser#pair}.
	 * @param ctx the parse tree
	 */
	void enterPair(ExamQuestionParser.PairContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamQuestionParser#pair}.
	 * @param ctx the parse tree
	 */
	void exitPair(ExamQuestionParser.PairContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamQuestionParser#matchingOptionCorrection}.
	 * @param ctx the parse tree
	 */
	void enterMatchingOptionCorrection(ExamQuestionParser.MatchingOptionCorrectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamQuestionParser#matchingOptionCorrection}.
	 * @param ctx the parse tree
	 */
	void exitMatchingOptionCorrection(ExamQuestionParser.MatchingOptionCorrectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamQuestionParser#multipleChoice}.
	 * @param ctx the parse tree
	 */
	void enterMultipleChoice(ExamQuestionParser.MultipleChoiceContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamQuestionParser#multipleChoice}.
	 * @param ctx the parse tree
	 */
	void exitMultipleChoice(ExamQuestionParser.MultipleChoiceContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamQuestionParser#shortAnswer}.
	 * @param ctx the parse tree
	 */
	void enterShortAnswer(ExamQuestionParser.ShortAnswerContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamQuestionParser#shortAnswer}.
	 * @param ctx the parse tree
	 */
	void exitShortAnswer(ExamQuestionParser.ShortAnswerContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamQuestionParser#numerical}.
	 * @param ctx the parse tree
	 */
	void enterNumerical(ExamQuestionParser.NumericalContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamQuestionParser#numerical}.
	 * @param ctx the parse tree
	 */
	void exitNumerical(ExamQuestionParser.NumericalContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamQuestionParser#selectMissingWords}.
	 * @param ctx the parse tree
	 */
	void enterSelectMissingWords(ExamQuestionParser.SelectMissingWordsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamQuestionParser#selectMissingWords}.
	 * @param ctx the parse tree
	 */
	void exitSelectMissingWords(ExamQuestionParser.SelectMissingWordsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamQuestionParser#uncompleteText}.
	 * @param ctx the parse tree
	 */
	void enterUncompleteText(ExamQuestionParser.UncompleteTextContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamQuestionParser#uncompleteText}.
	 * @param ctx the parse tree
	 */
	void exitUncompleteText(ExamQuestionParser.UncompleteTextContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamQuestionParser#possibleChoices}.
	 * @param ctx the parse tree
	 */
	void enterPossibleChoices(ExamQuestionParser.PossibleChoicesContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamQuestionParser#possibleChoices}.
	 * @param ctx the parse tree
	 */
	void exitPossibleChoices(ExamQuestionParser.PossibleChoicesContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamQuestionParser#trueFalse}.
	 * @param ctx the parse tree
	 */
	void enterTrueFalse(ExamQuestionParser.TrueFalseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamQuestionParser#trueFalse}.
	 * @param ctx the parse tree
	 */
	void exitTrueFalse(ExamQuestionParser.TrueFalseContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamQuestionParser#phrase}.
	 * @param ctx the parse tree
	 */
	void enterPhrase(ExamQuestionParser.PhraseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamQuestionParser#phrase}.
	 * @param ctx the parse tree
	 */
	void exitPhrase(ExamQuestionParser.PhraseContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamQuestionParser#option}.
	 * @param ctx the parse tree
	 */
	void enterOption(ExamQuestionParser.OptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamQuestionParser#option}.
	 * @param ctx the parse tree
	 */
	void exitOption(ExamQuestionParser.OptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamQuestionParser#feedback}.
	 * @param ctx the parse tree
	 */
	void enterFeedback(ExamQuestionParser.FeedbackContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamQuestionParser#feedback}.
	 * @param ctx the parse tree
	 */
	void exitFeedback(ExamQuestionParser.FeedbackContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamQuestionParser#words}.
	 * @param ctx the parse tree
	 */
	void enterWords(ExamQuestionParser.WordsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamQuestionParser#words}.
	 * @param ctx the parse tree
	 */
	void exitWords(ExamQuestionParser.WordsContext ctx);
}