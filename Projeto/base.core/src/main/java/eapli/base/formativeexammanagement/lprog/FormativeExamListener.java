// Generated from C:/Users/Gabriel/Desktop/Escola/Engenharia Informática/2º Ano/2º Semetre/Projeto Integrador/sem4pi-22-23-20/antlr4\FormativeExam.g4 by ANTLR 4.12.0

package eapli.base.formativeexammanagement.lprog;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FormativeExamParser}.
 */
public interface FormativeExamListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FormativeExamParser#formativeExam}.
	 * @param ctx the parse tree
	 */
	void enterFormativeExam(FormativeExamParser.FormativeExamContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormativeExamParser#formativeExam}.
	 * @param ctx the parse tree
	 */
	void exitFormativeExam(FormativeExamParser.FormativeExamContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormativeExamParser#title}.
	 * @param ctx the parse tree
	 */
	void enterTitle(FormativeExamParser.TitleContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormativeExamParser#title}.
	 * @param ctx the parse tree
	 */
	void exitTitle(FormativeExamParser.TitleContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormativeExamParser#header}.
	 * @param ctx the parse tree
	 */
	void enterHeader(FormativeExamParser.HeaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormativeExamParser#header}.
	 * @param ctx the parse tree
	 */
	void exitHeader(FormativeExamParser.HeaderContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormativeExamParser#section}.
	 * @param ctx the parse tree
	 */
	void enterSection(FormativeExamParser.SectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormativeExamParser#section}.
	 * @param ctx the parse tree
	 */
	void exitSection(FormativeExamParser.SectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormativeExamParser#typeQuestion}.
	 * @param ctx the parse tree
	 */
	void enterTypeQuestion(FormativeExamParser.TypeQuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormativeExamParser#typeQuestion}.
	 * @param ctx the parse tree
	 */
	void exitTypeQuestion(FormativeExamParser.TypeQuestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormativeExamParser#question}.
	 * @param ctx the parse tree
	 */
	void enterQuestion(FormativeExamParser.QuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormativeExamParser#question}.
	 * @param ctx the parse tree
	 */
	void exitQuestion(FormativeExamParser.QuestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormativeExamParser#phrase}.
	 * @param ctx the parse tree
	 */
	void enterPhrase(FormativeExamParser.PhraseContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormativeExamParser#phrase}.
	 * @param ctx the parse tree
	 */
	void exitPhrase(FormativeExamParser.PhraseContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormativeExamParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(FormativeExamParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormativeExamParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(FormativeExamParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormativeExamParser#words}.
	 * @param ctx the parse tree
	 */
	void enterWords(FormativeExamParser.WordsContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormativeExamParser#words}.
	 * @param ctx the parse tree
	 */
	void exitWords(FormativeExamParser.WordsContext ctx);
}