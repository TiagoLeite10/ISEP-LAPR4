// Generated from C:/Users/Gabriel/Desktop/Escola/Engenharia Informática/2º Ano/2º Semetre/Projeto Integrador/sem4pi-22-23-20/antlr4\FormativeExam.g4 by ANTLR 4.12.0

package eapli.base.formativeexammanagement.lprog;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class FormativeExamParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		NONE=1, ON_SUBMISSION=2, AFTER_CLOSING=3, MATCHING=4, MULTIPLE_CHOICE=5, 
		SHORT_ANSWER=6, NUMERICAL=7, SELECT_MISSING_WORDS=8, TRUE_FALSE=9, IDENTIFIER=10, 
		TITLE=11, OPENING_DATE_TIME=12, CLOSING_DATE_TIME=13, HEADER=14, TYPE_OF_FEEDBACK=15, 
		TYPE_OF_GRADE=16, DESCRIPTION=17, SECTION=18, TYPE_QUESTION=19, SCORE=20, 
		DATE=21, TIME=22, NUMS=23, DIVISIVE=24, CHAR=25, NUM=26, NEWLINE=27, SPECIAL_CHAR=28, 
		PUNCTUATION_MARKS=29, SMALLER=30, BIGGER=31, DOT=32, DASH=33, SLASH=34, 
		SPACE=35, COMMA=36, TWO_DOTS=37, WORD=38, WS=39;
	public static final int
		RULE_formativeExam = 0, RULE_title = 1, RULE_header = 2, RULE_section = 3, 
		RULE_typeQuestion = 4, RULE_question = 5, RULE_phrase = 6, RULE_type = 7, 
		RULE_words = 8;
	private static String[] makeRuleNames() {
		return new String[] {
			"formativeExam", "title", "header", "section", "typeQuestion", "question", 
			"phrase", "type", "words"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'none'", "'on-submission'", "'after-closing'", "'Matching'", "'Multiple Choice'", 
			"'Short Answer'", "'Numerical'", "'Select Missing Words'", "'True/False'", 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, "'<'", "'>'", "'.'", 
			"'-'", "'/'", "' '", "','", "':'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "NONE", "ON_SUBMISSION", "AFTER_CLOSING", "MATCHING", "MULTIPLE_CHOICE", 
			"SHORT_ANSWER", "NUMERICAL", "SELECT_MISSING_WORDS", "TRUE_FALSE", "IDENTIFIER", 
			"TITLE", "OPENING_DATE_TIME", "CLOSING_DATE_TIME", "HEADER", "TYPE_OF_FEEDBACK", 
			"TYPE_OF_GRADE", "DESCRIPTION", "SECTION", "TYPE_QUESTION", "SCORE", 
			"DATE", "TIME", "NUMS", "DIVISIVE", "CHAR", "NUM", "NEWLINE", "SPECIAL_CHAR", 
			"PUNCTUATION_MARKS", "SMALLER", "BIGGER", "DOT", "DASH", "SLASH", "SPACE", 
			"COMMA", "TWO_DOTS", "WORD", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "FormativeExam.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public FormativeExamParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FormativeExamContext extends ParserRuleContext {
		public TitleContext title() {
			return getRuleContext(TitleContext.class,0);
		}
		public HeaderContext header() {
			return getRuleContext(HeaderContext.class,0);
		}
		public List<SectionContext> section() {
			return getRuleContexts(SectionContext.class);
		}
		public SectionContext section(int i) {
			return getRuleContext(SectionContext.class,i);
		}
		public FormativeExamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formativeExam; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).enterFormativeExam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).exitFormativeExam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormativeExamVisitor ) return ((FormativeExamVisitor<? extends T>)visitor).visitFormativeExam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormativeExamContext formativeExam() throws RecognitionException {
		FormativeExamContext _localctx = new FormativeExamContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_formativeExam);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(18);
			title();
			setState(19);
			header();
			setState(21); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(20);
				section();
				}
				}
				setState(23); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==SECTION );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TitleContext extends ParserRuleContext {
		public TerminalNode TITLE() { return getToken(FormativeExamParser.TITLE, 0); }
		public PhraseContext phrase() {
			return getRuleContext(PhraseContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(FormativeExamParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(FormativeExamParser.NEWLINE, i);
		}
		public TerminalNode OPENING_DATE_TIME() { return getToken(FormativeExamParser.OPENING_DATE_TIME, 0); }
		public List<TerminalNode> DATE() { return getTokens(FormativeExamParser.DATE); }
		public TerminalNode DATE(int i) {
			return getToken(FormativeExamParser.DATE, i);
		}
		public List<TerminalNode> SPACE() { return getTokens(FormativeExamParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(FormativeExamParser.SPACE, i);
		}
		public List<TerminalNode> TIME() { return getTokens(FormativeExamParser.TIME); }
		public TerminalNode TIME(int i) {
			return getToken(FormativeExamParser.TIME, i);
		}
		public TerminalNode CLOSING_DATE_TIME() { return getToken(FormativeExamParser.CLOSING_DATE_TIME, 0); }
		public TitleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_title; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).enterTitle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).exitTitle(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormativeExamVisitor ) return ((FormativeExamVisitor<? extends T>)visitor).visitTitle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TitleContext title() throws RecognitionException {
		TitleContext _localctx = new TitleContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_title);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(25);
			match(TITLE);
			setState(26);
			phrase();
			setState(27);
			match(NEWLINE);
			setState(28);
			match(OPENING_DATE_TIME);
			setState(29);
			match(DATE);
			setState(30);
			match(SPACE);
			setState(31);
			match(TIME);
			setState(32);
			match(NEWLINE);
			setState(33);
			match(CLOSING_DATE_TIME);
			setState(34);
			match(DATE);
			setState(35);
			match(SPACE);
			setState(36);
			match(TIME);
			setState(37);
			match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class HeaderContext extends ParserRuleContext {
		public TerminalNode HEADER() { return getToken(FormativeExamParser.HEADER, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(FormativeExamParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(FormativeExamParser.NEWLINE, i);
		}
		public TerminalNode TYPE_OF_FEEDBACK() { return getToken(FormativeExamParser.TYPE_OF_FEEDBACK, 0); }
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public TerminalNode TYPE_OF_GRADE() { return getToken(FormativeExamParser.TYPE_OF_GRADE, 0); }
		public TerminalNode DESCRIPTION() { return getToken(FormativeExamParser.DESCRIPTION, 0); }
		public PhraseContext phrase() {
			return getRuleContext(PhraseContext.class,0);
		}
		public HeaderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_header; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).enterHeader(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).exitHeader(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormativeExamVisitor ) return ((FormativeExamVisitor<? extends T>)visitor).visitHeader(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HeaderContext header() throws RecognitionException {
		HeaderContext _localctx = new HeaderContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_header);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			match(HEADER);
			setState(40);
			match(NEWLINE);
			setState(41);
			match(TYPE_OF_FEEDBACK);
			setState(42);
			type();
			setState(43);
			match(NEWLINE);
			setState(44);
			match(TYPE_OF_GRADE);
			setState(45);
			type();
			setState(46);
			match(NEWLINE);
			setState(47);
			match(DESCRIPTION);
			setState(49);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NUMS || _la==WORD) {
				{
				setState(48);
				phrase();
				}
			}

			setState(51);
			match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SectionContext extends ParserRuleContext {
		public TerminalNode SECTION() { return getToken(FormativeExamParser.SECTION, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(FormativeExamParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(FormativeExamParser.NEWLINE, i);
		}
		public TerminalNode DESCRIPTION() { return getToken(FormativeExamParser.DESCRIPTION, 0); }
		public TerminalNode TYPE_QUESTION() { return getToken(FormativeExamParser.TYPE_QUESTION, 0); }
		public PhraseContext phrase() {
			return getRuleContext(PhraseContext.class,0);
		}
		public List<TypeQuestionContext> typeQuestion() {
			return getRuleContexts(TypeQuestionContext.class);
		}
		public TypeQuestionContext typeQuestion(int i) {
			return getRuleContext(TypeQuestionContext.class,i);
		}
		public SectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_section; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).enterSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).exitSection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormativeExamVisitor ) return ((FormativeExamVisitor<? extends T>)visitor).visitSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SectionContext section() throws RecognitionException {
		SectionContext _localctx = new SectionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_section);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			match(SECTION);
			setState(54);
			match(NEWLINE);
			setState(55);
			match(DESCRIPTION);
			setState(57);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NUMS || _la==WORD) {
				{
				setState(56);
				phrase();
				}
			}

			setState(59);
			match(NEWLINE);
			setState(60);
			match(TYPE_QUESTION);
			setState(62); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(61);
				typeQuestion();
				}
				}
				setState(64); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==SMALLER );
			setState(66);
			match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeQuestionContext extends ParserRuleContext {
		public TerminalNode SMALLER() { return getToken(FormativeExamParser.SMALLER, 0); }
		public TerminalNode NUMS() { return getToken(FormativeExamParser.NUMS, 0); }
		public TerminalNode DIVISIVE() { return getToken(FormativeExamParser.DIVISIVE, 0); }
		public QuestionContext question() {
			return getRuleContext(QuestionContext.class,0);
		}
		public TerminalNode BIGGER() { return getToken(FormativeExamParser.BIGGER, 0); }
		public TerminalNode SPACE() { return getToken(FormativeExamParser.SPACE, 0); }
		public TypeQuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeQuestion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).enterTypeQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).exitTypeQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormativeExamVisitor ) return ((FormativeExamVisitor<? extends T>)visitor).visitTypeQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeQuestionContext typeQuestion() throws RecognitionException {
		TypeQuestionContext _localctx = new TypeQuestionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_typeQuestion);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			match(SMALLER);
			setState(69);
			match(NUMS);
			setState(70);
			match(DIVISIVE);
			setState(71);
			question();
			setState(72);
			match(BIGGER);
			setState(74);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SPACE) {
				{
				setState(73);
				match(SPACE);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QuestionContext extends ParserRuleContext {
		public TerminalNode MATCHING() { return getToken(FormativeExamParser.MATCHING, 0); }
		public TerminalNode MULTIPLE_CHOICE() { return getToken(FormativeExamParser.MULTIPLE_CHOICE, 0); }
		public TerminalNode SHORT_ANSWER() { return getToken(FormativeExamParser.SHORT_ANSWER, 0); }
		public TerminalNode NUMERICAL() { return getToken(FormativeExamParser.NUMERICAL, 0); }
		public TerminalNode SELECT_MISSING_WORDS() { return getToken(FormativeExamParser.SELECT_MISSING_WORDS, 0); }
		public TerminalNode TRUE_FALSE() { return getToken(FormativeExamParser.TRUE_FALSE, 0); }
		public QuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).enterQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).exitQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormativeExamVisitor ) return ((FormativeExamVisitor<? extends T>)visitor).visitQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionContext question() throws RecognitionException {
		QuestionContext _localctx = new QuestionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_question);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1008L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PhraseContext extends ParserRuleContext {
		public List<WordsContext> words() {
			return getRuleContexts(WordsContext.class);
		}
		public WordsContext words(int i) {
			return getRuleContext(WordsContext.class,i);
		}
		public TerminalNode PUNCTUATION_MARKS() { return getToken(FormativeExamParser.PUNCTUATION_MARKS, 0); }
		public PhraseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_phrase; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).enterPhrase(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).exitPhrase(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormativeExamVisitor ) return ((FormativeExamVisitor<? extends T>)visitor).visitPhrase(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PhraseContext phrase() throws RecognitionException {
		PhraseContext _localctx = new PhraseContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_phrase);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(78);
				words();
				}
				}
				setState(81); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NUMS || _la==WORD );
			setState(84);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PUNCTUATION_MARKS) {
				{
				setState(83);
				match(PUNCTUATION_MARKS);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeContext extends ParserRuleContext {
		public TerminalNode NONE() { return getToken(FormativeExamParser.NONE, 0); }
		public TerminalNode ON_SUBMISSION() { return getToken(FormativeExamParser.ON_SUBMISSION, 0); }
		public TerminalNode AFTER_CLOSING() { return getToken(FormativeExamParser.AFTER_CLOSING, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormativeExamVisitor ) return ((FormativeExamVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 14L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WordsContext extends ParserRuleContext {
		public TerminalNode WORD() { return getToken(FormativeExamParser.WORD, 0); }
		public TerminalNode NUMS() { return getToken(FormativeExamParser.NUMS, 0); }
		public WordsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_words; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).enterWords(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).exitWords(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormativeExamVisitor ) return ((FormativeExamVisitor<? extends T>)visitor).visitWords(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WordsContext words() throws RecognitionException {
		WordsContext _localctx = new WordsContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_words);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			_la = _input.LA(1);
			if ( !(_la==NUMS || _la==WORD) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\'[\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0001\u0000\u0001\u0000\u0001\u0000\u0004\u0000\u0016\b\u0000"+
		"\u000b\u0000\f\u0000\u0017\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0003\u00022\b\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003:\b\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0004\u0003?\b\u0003\u000b\u0003"+
		"\f\u0003@\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004K\b\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0006\u0004\u0006P\b\u0006\u000b\u0006\f\u0006Q\u0001"+
		"\u0006\u0003\u0006U\b\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001"+
		"\b\u0000\u0000\t\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0000\u0003"+
		"\u0001\u0000\u0004\t\u0001\u0000\u0001\u0003\u0002\u0000\u0017\u0017&"+
		"&X\u0000\u0012\u0001\u0000\u0000\u0000\u0002\u0019\u0001\u0000\u0000\u0000"+
		"\u0004\'\u0001\u0000\u0000\u0000\u00065\u0001\u0000\u0000\u0000\bD\u0001"+
		"\u0000\u0000\u0000\nL\u0001\u0000\u0000\u0000\fO\u0001\u0000\u0000\u0000"+
		"\u000eV\u0001\u0000\u0000\u0000\u0010X\u0001\u0000\u0000\u0000\u0012\u0013"+
		"\u0003\u0002\u0001\u0000\u0013\u0015\u0003\u0004\u0002\u0000\u0014\u0016"+
		"\u0003\u0006\u0003\u0000\u0015\u0014\u0001\u0000\u0000\u0000\u0016\u0017"+
		"\u0001\u0000\u0000\u0000\u0017\u0015\u0001\u0000\u0000\u0000\u0017\u0018"+
		"\u0001\u0000\u0000\u0000\u0018\u0001\u0001\u0000\u0000\u0000\u0019\u001a"+
		"\u0005\u000b\u0000\u0000\u001a\u001b\u0003\f\u0006\u0000\u001b\u001c\u0005"+
		"\u001b\u0000\u0000\u001c\u001d\u0005\f\u0000\u0000\u001d\u001e\u0005\u0015"+
		"\u0000\u0000\u001e\u001f\u0005#\u0000\u0000\u001f \u0005\u0016\u0000\u0000"+
		" !\u0005\u001b\u0000\u0000!\"\u0005\r\u0000\u0000\"#\u0005\u0015\u0000"+
		"\u0000#$\u0005#\u0000\u0000$%\u0005\u0016\u0000\u0000%&\u0005\u001b\u0000"+
		"\u0000&\u0003\u0001\u0000\u0000\u0000\'(\u0005\u000e\u0000\u0000()\u0005"+
		"\u001b\u0000\u0000)*\u0005\u000f\u0000\u0000*+\u0003\u000e\u0007\u0000"+
		"+,\u0005\u001b\u0000\u0000,-\u0005\u0010\u0000\u0000-.\u0003\u000e\u0007"+
		"\u0000./\u0005\u001b\u0000\u0000/1\u0005\u0011\u0000\u000002\u0003\f\u0006"+
		"\u000010\u0001\u0000\u0000\u000012\u0001\u0000\u0000\u000023\u0001\u0000"+
		"\u0000\u000034\u0005\u001b\u0000\u00004\u0005\u0001\u0000\u0000\u0000"+
		"56\u0005\u0012\u0000\u000067\u0005\u001b\u0000\u000079\u0005\u0011\u0000"+
		"\u00008:\u0003\f\u0006\u000098\u0001\u0000\u0000\u00009:\u0001\u0000\u0000"+
		"\u0000:;\u0001\u0000\u0000\u0000;<\u0005\u001b\u0000\u0000<>\u0005\u0013"+
		"\u0000\u0000=?\u0003\b\u0004\u0000>=\u0001\u0000\u0000\u0000?@\u0001\u0000"+
		"\u0000\u0000@>\u0001\u0000\u0000\u0000@A\u0001\u0000\u0000\u0000AB\u0001"+
		"\u0000\u0000\u0000BC\u0005\u001b\u0000\u0000C\u0007\u0001\u0000\u0000"+
		"\u0000DE\u0005\u001e\u0000\u0000EF\u0005\u0017\u0000\u0000FG\u0005\u0018"+
		"\u0000\u0000GH\u0003\n\u0005\u0000HJ\u0005\u001f\u0000\u0000IK\u0005#"+
		"\u0000\u0000JI\u0001\u0000\u0000\u0000JK\u0001\u0000\u0000\u0000K\t\u0001"+
		"\u0000\u0000\u0000LM\u0007\u0000\u0000\u0000M\u000b\u0001\u0000\u0000"+
		"\u0000NP\u0003\u0010\b\u0000ON\u0001\u0000\u0000\u0000PQ\u0001\u0000\u0000"+
		"\u0000QO\u0001\u0000\u0000\u0000QR\u0001\u0000\u0000\u0000RT\u0001\u0000"+
		"\u0000\u0000SU\u0005\u001d\u0000\u0000TS\u0001\u0000\u0000\u0000TU\u0001"+
		"\u0000\u0000\u0000U\r\u0001\u0000\u0000\u0000VW\u0007\u0001\u0000\u0000"+
		"W\u000f\u0001\u0000\u0000\u0000XY\u0007\u0002\u0000\u0000Y\u0011\u0001"+
		"\u0000\u0000\u0000\u0007\u001719@JQT";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}