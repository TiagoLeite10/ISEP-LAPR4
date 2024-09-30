// Generated from C:/Users/Gabriel/Desktop/Escola/Engenharia Informática/2º Ano/2º Semetre/Projeto Integrador/sem4pi-22-23-20/antlr4\FormativeExam.g4 by ANTLR 4.12.0

package eapli.base.formativeexammanagement.lprog;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link FormativeExamParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface FormativeExamVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link FormativeExamParser#formativeExam}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormativeExam(FormativeExamParser.FormativeExamContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormativeExamParser#title}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTitle(FormativeExamParser.TitleContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormativeExamParser#header}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeader(FormativeExamParser.HeaderContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormativeExamParser#section}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSection(FormativeExamParser.SectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormativeExamParser#typeQuestion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeQuestion(FormativeExamParser.TypeQuestionContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormativeExamParser#question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion(FormativeExamParser.QuestionContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormativeExamParser#phrase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPhrase(FormativeExamParser.PhraseContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormativeExamParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(FormativeExamParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormativeExamParser#words}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWords(FormativeExamParser.WordsContext ctx);
}