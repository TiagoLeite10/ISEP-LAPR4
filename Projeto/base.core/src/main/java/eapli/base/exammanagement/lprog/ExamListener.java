// Generated from C:/Users/Gabriel/Desktop/Escola/Engenharia Informática/2º Ano/2º Semetre/Projeto Integrador/sem4pi-22-23-20/antlr4\Exam.g4 by ANTLR 4.12.0

package eapli.base.exammanagement.lprog;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExamParser}.
 */
public interface ExamListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ExamParser#exam}.
	 * @param ctx the parse tree
	 */
	void enterExam(ExamParser.ExamContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#exam}.
	 * @param ctx the parse tree
	 */
	void exitExam(ExamParser.ExamContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#title}.
	 * @param ctx the parse tree
	 */
	void enterTitle(ExamParser.TitleContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#title}.
	 * @param ctx the parse tree
	 */
	void exitTitle(ExamParser.TitleContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#header}.
	 * @param ctx the parse tree
	 */
	void enterHeader(ExamParser.HeaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#header}.
	 * @param ctx the parse tree
	 */
	void exitHeader(ExamParser.HeaderContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#section}.
	 * @param ctx the parse tree
	 */
	void enterSection(ExamParser.SectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#section}.
	 * @param ctx the parse tree
	 */
	void exitSection(ExamParser.SectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#question}.
	 * @param ctx the parse tree
	 */
	void enterQuestion(ExamParser.QuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#question}.
	 * @param ctx the parse tree
	 */
	void exitQuestion(ExamParser.QuestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#matching}.
	 * @param ctx the parse tree
	 */
	void enterMatching(ExamParser.MatchingContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#matching}.
	 * @param ctx the parse tree
	 */
	void exitMatching(ExamParser.MatchingContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#pair}.
	 * @param ctx the parse tree
	 */
	void enterPair(ExamParser.PairContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#pair}.
	 * @param ctx the parse tree
	 */
	void exitPair(ExamParser.PairContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#matchingOptionCorrection}.
	 * @param ctx the parse tree
	 */
	void enterMatchingOptionCorrection(ExamParser.MatchingOptionCorrectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#matchingOptionCorrection}.
	 * @param ctx the parse tree
	 */
	void exitMatchingOptionCorrection(ExamParser.MatchingOptionCorrectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#multipleChoice}.
	 * @param ctx the parse tree
	 */
	void enterMultipleChoice(ExamParser.MultipleChoiceContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#multipleChoice}.
	 * @param ctx the parse tree
	 */
	void exitMultipleChoice(ExamParser.MultipleChoiceContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#shortAnswer}.
	 * @param ctx the parse tree
	 */
	void enterShortAnswer(ExamParser.ShortAnswerContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#shortAnswer}.
	 * @param ctx the parse tree
	 */
	void exitShortAnswer(ExamParser.ShortAnswerContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#numerical}.
	 * @param ctx the parse tree
	 */
	void enterNumerical(ExamParser.NumericalContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#numerical}.
	 * @param ctx the parse tree
	 */
	void exitNumerical(ExamParser.NumericalContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#selectMissingWords}.
	 * @param ctx the parse tree
	 */
	void enterSelectMissingWords(ExamParser.SelectMissingWordsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#selectMissingWords}.
	 * @param ctx the parse tree
	 */
	void exitSelectMissingWords(ExamParser.SelectMissingWordsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#uncompleteText}.
	 * @param ctx the parse tree
	 */
	void enterUncompleteText(ExamParser.UncompleteTextContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#uncompleteText}.
	 * @param ctx the parse tree
	 */
	void exitUncompleteText(ExamParser.UncompleteTextContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#possibleChoices}.
	 * @param ctx the parse tree
	 */
	void enterPossibleChoices(ExamParser.PossibleChoicesContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#possibleChoices}.
	 * @param ctx the parse tree
	 */
	void exitPossibleChoices(ExamParser.PossibleChoicesContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#trueFalse}.
	 * @param ctx the parse tree
	 */
	void enterTrueFalse(ExamParser.TrueFalseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#trueFalse}.
	 * @param ctx the parse tree
	 */
	void exitTrueFalse(ExamParser.TrueFalseContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#phrase}.
	 * @param ctx the parse tree
	 */
	void enterPhrase(ExamParser.PhraseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#phrase}.
	 * @param ctx the parse tree
	 */
	void exitPhrase(ExamParser.PhraseContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#option}.
	 * @param ctx the parse tree
	 */
	void enterOption(ExamParser.OptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#option}.
	 * @param ctx the parse tree
	 */
	void exitOption(ExamParser.OptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#feedback}.
	 * @param ctx the parse tree
	 */
	void enterFeedback(ExamParser.FeedbackContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#feedback}.
	 * @param ctx the parse tree
	 */
	void exitFeedback(ExamParser.FeedbackContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(ExamParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(ExamParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#words}.
	 * @param ctx the parse tree
	 */
	void enterWords(ExamParser.WordsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#words}.
	 * @param ctx the parse tree
	 */
	void exitWords(ExamParser.WordsContext ctx);
}