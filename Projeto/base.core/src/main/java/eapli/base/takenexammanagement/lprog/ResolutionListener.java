// Generated from C:/Users/Gabriel/Desktop/Escola/Engenharia Informática/2º Ano/2º Semetre/Projeto Integrador/sem4pi-22-23-20/antlr4\Resolution.g4 by ANTLR 4.12.0

package eapli.base.takenexammanagement.lprog;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ResolutionParser}.
 */
public interface ResolutionListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ResolutionParser#resolution}.
	 * @param ctx the parse tree
	 */
	void enterResolution(ResolutionParser.ResolutionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ResolutionParser#resolution}.
	 * @param ctx the parse tree
	 */
	void exitResolution(ResolutionParser.ResolutionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ResolutionParser#section}.
	 * @param ctx the parse tree
	 */
	void enterSection(ResolutionParser.SectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ResolutionParser#section}.
	 * @param ctx the parse tree
	 */
	void exitSection(ResolutionParser.SectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ResolutionParser#answer}.
	 * @param ctx the parse tree
	 */
	void enterAnswer(ResolutionParser.AnswerContext ctx);
	/**
	 * Exit a parse tree produced by {@link ResolutionParser#answer}.
	 * @param ctx the parse tree
	 */
	void exitAnswer(ResolutionParser.AnswerContext ctx);
	/**
	 * Enter a parse tree produced by {@link ResolutionParser#answerToMatching}.
	 * @param ctx the parse tree
	 */
	void enterAnswerToMatching(ResolutionParser.AnswerToMatchingContext ctx);
	/**
	 * Exit a parse tree produced by {@link ResolutionParser#answerToMatching}.
	 * @param ctx the parse tree
	 */
	void exitAnswerToMatching(ResolutionParser.AnswerToMatchingContext ctx);
	/**
	 * Enter a parse tree produced by {@link ResolutionParser#matchingOption}.
	 * @param ctx the parse tree
	 */
	void enterMatchingOption(ResolutionParser.MatchingOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ResolutionParser#matchingOption}.
	 * @param ctx the parse tree
	 */
	void exitMatchingOption(ResolutionParser.MatchingOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ResolutionParser#answerToMultipleChoice}.
	 * @param ctx the parse tree
	 */
	void enterAnswerToMultipleChoice(ResolutionParser.AnswerToMultipleChoiceContext ctx);
	/**
	 * Exit a parse tree produced by {@link ResolutionParser#answerToMultipleChoice}.
	 * @param ctx the parse tree
	 */
	void exitAnswerToMultipleChoice(ResolutionParser.AnswerToMultipleChoiceContext ctx);
	/**
	 * Enter a parse tree produced by {@link ResolutionParser#answerToShortAnswer}.
	 * @param ctx the parse tree
	 */
	void enterAnswerToShortAnswer(ResolutionParser.AnswerToShortAnswerContext ctx);
	/**
	 * Exit a parse tree produced by {@link ResolutionParser#answerToShortAnswer}.
	 * @param ctx the parse tree
	 */
	void exitAnswerToShortAnswer(ResolutionParser.AnswerToShortAnswerContext ctx);
	/**
	 * Enter a parse tree produced by {@link ResolutionParser#answerToNumerical}.
	 * @param ctx the parse tree
	 */
	void enterAnswerToNumerical(ResolutionParser.AnswerToNumericalContext ctx);
	/**
	 * Exit a parse tree produced by {@link ResolutionParser#answerToNumerical}.
	 * @param ctx the parse tree
	 */
	void exitAnswerToNumerical(ResolutionParser.AnswerToNumericalContext ctx);
	/**
	 * Enter a parse tree produced by {@link ResolutionParser#answerToSelectMissingWords}.
	 * @param ctx the parse tree
	 */
	void enterAnswerToSelectMissingWords(ResolutionParser.AnswerToSelectMissingWordsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ResolutionParser#answerToSelectMissingWords}.
	 * @param ctx the parse tree
	 */
	void exitAnswerToSelectMissingWords(ResolutionParser.AnswerToSelectMissingWordsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ResolutionParser#answerToTrueFalse}.
	 * @param ctx the parse tree
	 */
	void enterAnswerToTrueFalse(ResolutionParser.AnswerToTrueFalseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ResolutionParser#answerToTrueFalse}.
	 * @param ctx the parse tree
	 */
	void exitAnswerToTrueFalse(ResolutionParser.AnswerToTrueFalseContext ctx);
	/**
	 * Enter a parse tree produced by {@link ResolutionParser#phrase}.
	 * @param ctx the parse tree
	 */
	void enterPhrase(ResolutionParser.PhraseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ResolutionParser#phrase}.
	 * @param ctx the parse tree
	 */
	void exitPhrase(ResolutionParser.PhraseContext ctx);
	/**
	 * Enter a parse tree produced by {@link ResolutionParser#words}.
	 * @param ctx the parse tree
	 */
	void enterWords(ResolutionParser.WordsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ResolutionParser#words}.
	 * @param ctx the parse tree
	 */
	void exitWords(ResolutionParser.WordsContext ctx);
}