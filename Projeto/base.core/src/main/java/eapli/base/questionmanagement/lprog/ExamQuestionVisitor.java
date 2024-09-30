// Generated from C:/Users/Gabriel/Desktop/Escola/Engenharia Informática/2º Ano/2º Semetre/Projeto Integrador/sem4pi-22-23-20/antlr4\ExamQuestion.g4 by ANTLR 4.12.0

package eapli.base.questionmanagement.lprog;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ExamQuestionParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ExamQuestionVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ExamQuestionParser#examQuestion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExamQuestion(ExamQuestionParser.ExamQuestionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamQuestionParser#question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion(ExamQuestionParser.QuestionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamQuestionParser#matching}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatching(ExamQuestionParser.MatchingContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamQuestionParser#pair}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPair(ExamQuestionParser.PairContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamQuestionParser#matchingOptionCorrection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatchingOptionCorrection(ExamQuestionParser.MatchingOptionCorrectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamQuestionParser#multipleChoice}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultipleChoice(ExamQuestionParser.MultipleChoiceContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamQuestionParser#shortAnswer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShortAnswer(ExamQuestionParser.ShortAnswerContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamQuestionParser#numerical}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumerical(ExamQuestionParser.NumericalContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamQuestionParser#selectMissingWords}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectMissingWords(ExamQuestionParser.SelectMissingWordsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamQuestionParser#uncompleteText}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUncompleteText(ExamQuestionParser.UncompleteTextContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamQuestionParser#possibleChoices}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPossibleChoices(ExamQuestionParser.PossibleChoicesContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamQuestionParser#trueFalse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrueFalse(ExamQuestionParser.TrueFalseContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamQuestionParser#phrase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPhrase(ExamQuestionParser.PhraseContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamQuestionParser#option}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOption(ExamQuestionParser.OptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamQuestionParser#feedback}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFeedback(ExamQuestionParser.FeedbackContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamQuestionParser#words}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWords(ExamQuestionParser.WordsContext ctx);
}