// Generated from C:/Users/Gabriel/Desktop/Escola/Engenharia Informática/2º Ano/2º Semetre/Projeto Integrador/sem4pi-22-23-20/antlr4\Resolution.g4 by ANTLR 4.12.0

package eapli.base.takenexammanagement.lprog;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ResolutionParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ResolutionVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ResolutionParser#resolution}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitResolution(ResolutionParser.ResolutionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ResolutionParser#section}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSection(ResolutionParser.SectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ResolutionParser#answer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnswer(ResolutionParser.AnswerContext ctx);
	/**
	 * Visit a parse tree produced by {@link ResolutionParser#answerToMatching}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnswerToMatching(ResolutionParser.AnswerToMatchingContext ctx);
	/**
	 * Visit a parse tree produced by {@link ResolutionParser#matchingOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatchingOption(ResolutionParser.MatchingOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ResolutionParser#answerToMultipleChoice}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnswerToMultipleChoice(ResolutionParser.AnswerToMultipleChoiceContext ctx);
	/**
	 * Visit a parse tree produced by {@link ResolutionParser#answerToShortAnswer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnswerToShortAnswer(ResolutionParser.AnswerToShortAnswerContext ctx);
	/**
	 * Visit a parse tree produced by {@link ResolutionParser#answerToNumerical}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnswerToNumerical(ResolutionParser.AnswerToNumericalContext ctx);
	/**
	 * Visit a parse tree produced by {@link ResolutionParser#answerToSelectMissingWords}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnswerToSelectMissingWords(ResolutionParser.AnswerToSelectMissingWordsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ResolutionParser#answerToTrueFalse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnswerToTrueFalse(ResolutionParser.AnswerToTrueFalseContext ctx);
	/**
	 * Visit a parse tree produced by {@link ResolutionParser#phrase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPhrase(ResolutionParser.PhraseContext ctx);
	/**
	 * Visit a parse tree produced by {@link ResolutionParser#words}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWords(ResolutionParser.WordsContext ctx);
}