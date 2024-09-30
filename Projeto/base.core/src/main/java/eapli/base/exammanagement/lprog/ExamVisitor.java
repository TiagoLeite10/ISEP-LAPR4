// Generated from C:/Users/Gabriel/Desktop/Escola/Engenharia Informática/2º Ano/2º Semetre/Projeto Integrador/sem4pi-22-23-20/antlr4\Exam.g4 by ANTLR 4.12.0

package eapli.base.exammanagement.lprog;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ExamParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ExamVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ExamParser#exam}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExam(ExamParser.ExamContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#title}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTitle(ExamParser.TitleContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#header}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeader(ExamParser.HeaderContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#section}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSection(ExamParser.SectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion(ExamParser.QuestionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#matching}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatching(ExamParser.MatchingContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#pair}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPair(ExamParser.PairContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#matchingOptionCorrection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatchingOptionCorrection(ExamParser.MatchingOptionCorrectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#multipleChoice}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultipleChoice(ExamParser.MultipleChoiceContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#shortAnswer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShortAnswer(ExamParser.ShortAnswerContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#numerical}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumerical(ExamParser.NumericalContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#selectMissingWords}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectMissingWords(ExamParser.SelectMissingWordsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#uncompleteText}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUncompleteText(ExamParser.UncompleteTextContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#possibleChoices}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPossibleChoices(ExamParser.PossibleChoicesContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#trueFalse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrueFalse(ExamParser.TrueFalseContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#phrase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPhrase(ExamParser.PhraseContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#option}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOption(ExamParser.OptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#feedback}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFeedback(ExamParser.FeedbackContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(ExamParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#words}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWords(ExamParser.WordsContext ctx);
}