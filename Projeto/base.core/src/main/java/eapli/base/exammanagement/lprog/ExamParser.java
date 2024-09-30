// Generated from C:/Users/Gabriel/Desktop/Escola/Engenharia Informática/2º Ano/2º Semetre/Projeto Integrador/sem4pi-22-23-20/antlr4\Exam.g4 by ANTLR 4.12.0

package eapli.base.exammanagement.lprog;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class ExamParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		TRUE_OR_FALSE=1, NONE=2, ON_SUBMISSION=3, CHOSING_OPTION_SELECT_MISSING_WORDS=4, 
		AFTER_CLOSING=5, IDENTIFIER=6, TITLE=7, OPENING_DATE_TIME=8, CLOSING_DATE_TIME=9, 
		HEADER=10, TYPE_OF_FEEDBACK=11, TYPE_OF_GRADE=12, DESCRIPTION=13, SECTION=14, 
		MATCHING=15, MULTIPLE_CHOICE=16, SHORT_ANSWER=17, NUMERICAL=18, SELECT_MISSING_WORDS=19, 
		TRUE_FALSE=20, SCORE_PER_LINE=21, SCORE=22, CORRECT_ANSWER=23, FEEDBACK=24, 
		ERROR_NUMERICAL=25, POSSIBLE_CHOICES_SELECT_MISSING_WORDS=26, DATE=27, 
		TIME=28, NUMS=29, CHAR=30, NUM=31, NEWLINE=32, SPECIAL_CHAR=33, PUNCTUATION_MARKS=34, 
		DOT=35, DASH=36, SLASH=37, SPACE=38, COMMA=39, TWO_DOTS=40, UNDERSCORE=41, 
		RIGHT_PARENTHESES=42, DIVISIVE=43, FLOAT=44, NUMERICAL_OPTION=45, ALPHABETICAL_OPTION=46, 
		MATCHING_OPTION_CORRECTION=47, WORD=48, WS=49;
	public static final int
		RULE_exam = 0, RULE_title = 1, RULE_header = 2, RULE_section = 3, RULE_question = 4, 
		RULE_matching = 5, RULE_pair = 6, RULE_matchingOptionCorrection = 7, RULE_multipleChoice = 8, 
		RULE_shortAnswer = 9, RULE_numerical = 10, RULE_selectMissingWords = 11, 
		RULE_uncompleteText = 12, RULE_possibleChoices = 13, RULE_trueFalse = 14, 
		RULE_phrase = 15, RULE_option = 16, RULE_feedback = 17, RULE_type = 18, 
		RULE_words = 19;
	private static String[] makeRuleNames() {
		return new String[] {
			"exam", "title", "header", "section", "question", "matching", "pair", 
			"matchingOptionCorrection", "multipleChoice", "shortAnswer", "numerical", 
			"selectMissingWords", "uncompleteText", "possibleChoices", "trueFalse", 
			"phrase", "option", "feedback", "type", "words"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, "'none'", "'on-submission'", "'<option>'", "'after-closing'", 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, "'.'", "'-'", "'/'", "' '", "','", "':'", 
			"'_'", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "TRUE_OR_FALSE", "NONE", "ON_SUBMISSION", "CHOSING_OPTION_SELECT_MISSING_WORDS", 
			"AFTER_CLOSING", "IDENTIFIER", "TITLE", "OPENING_DATE_TIME", "CLOSING_DATE_TIME", 
			"HEADER", "TYPE_OF_FEEDBACK", "TYPE_OF_GRADE", "DESCRIPTION", "SECTION", 
			"MATCHING", "MULTIPLE_CHOICE", "SHORT_ANSWER", "NUMERICAL", "SELECT_MISSING_WORDS", 
			"TRUE_FALSE", "SCORE_PER_LINE", "SCORE", "CORRECT_ANSWER", "FEEDBACK", 
			"ERROR_NUMERICAL", "POSSIBLE_CHOICES_SELECT_MISSING_WORDS", "DATE", "TIME", 
			"NUMS", "CHAR", "NUM", "NEWLINE", "SPECIAL_CHAR", "PUNCTUATION_MARKS", 
			"DOT", "DASH", "SLASH", "SPACE", "COMMA", "TWO_DOTS", "UNDERSCORE", "RIGHT_PARENTHESES", 
			"DIVISIVE", "FLOAT", "NUMERICAL_OPTION", "ALPHABETICAL_OPTION", "MATCHING_OPTION_CORRECTION", 
			"WORD", "WS"
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
	public String getGrammarFileName() { return "Exam.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ExamParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExamContext extends ParserRuleContext {
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
		public ExamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exam; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterExam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitExam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitExam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExamContext exam() throws RecognitionException {
		ExamContext _localctx = new ExamContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_exam);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			title();
			setState(41);
			header();
			setState(43); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(42);
				section();
				}
				}
				setState(45); 
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
		public TerminalNode TITLE() { return getToken(ExamParser.TITLE, 0); }
		public PhraseContext phrase() {
			return getRuleContext(PhraseContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(ExamParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ExamParser.NEWLINE, i);
		}
		public TerminalNode OPENING_DATE_TIME() { return getToken(ExamParser.OPENING_DATE_TIME, 0); }
		public List<TerminalNode> DATE() { return getTokens(ExamParser.DATE); }
		public TerminalNode DATE(int i) {
			return getToken(ExamParser.DATE, i);
		}
		public List<TerminalNode> SPACE() { return getTokens(ExamParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(ExamParser.SPACE, i);
		}
		public List<TerminalNode> TIME() { return getTokens(ExamParser.TIME); }
		public TerminalNode TIME(int i) {
			return getToken(ExamParser.TIME, i);
		}
		public TerminalNode CLOSING_DATE_TIME() { return getToken(ExamParser.CLOSING_DATE_TIME, 0); }
		public TitleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_title; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterTitle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitTitle(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitTitle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TitleContext title() throws RecognitionException {
		TitleContext _localctx = new TitleContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_title);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			match(TITLE);
			setState(48);
			phrase();
			setState(49);
			match(NEWLINE);
			setState(50);
			match(OPENING_DATE_TIME);
			setState(51);
			match(DATE);
			setState(52);
			match(SPACE);
			setState(53);
			match(TIME);
			setState(54);
			match(NEWLINE);
			setState(55);
			match(CLOSING_DATE_TIME);
			setState(56);
			match(DATE);
			setState(57);
			match(SPACE);
			setState(58);
			match(TIME);
			setState(59);
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
		public TerminalNode HEADER() { return getToken(ExamParser.HEADER, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(ExamParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ExamParser.NEWLINE, i);
		}
		public TerminalNode TYPE_OF_FEEDBACK() { return getToken(ExamParser.TYPE_OF_FEEDBACK, 0); }
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public TerminalNode TYPE_OF_GRADE() { return getToken(ExamParser.TYPE_OF_GRADE, 0); }
		public TerminalNode DESCRIPTION() { return getToken(ExamParser.DESCRIPTION, 0); }
		public PhraseContext phrase() {
			return getRuleContext(PhraseContext.class,0);
		}
		public HeaderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_header; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterHeader(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitHeader(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitHeader(this);
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
			setState(61);
			match(HEADER);
			setState(62);
			match(NEWLINE);
			setState(63);
			match(TYPE_OF_FEEDBACK);
			setState(64);
			type();
			setState(65);
			match(NEWLINE);
			setState(66);
			match(TYPE_OF_GRADE);
			setState(67);
			type();
			setState(68);
			match(NEWLINE);
			setState(69);
			match(DESCRIPTION);
			setState(71);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NUMS || _la==WORD) {
				{
				setState(70);
				phrase();
				}
			}

			setState(73);
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
		public TerminalNode SECTION() { return getToken(ExamParser.SECTION, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(ExamParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ExamParser.NEWLINE, i);
		}
		public TerminalNode DESCRIPTION() { return getToken(ExamParser.DESCRIPTION, 0); }
		public PhraseContext phrase() {
			return getRuleContext(PhraseContext.class,0);
		}
		public List<QuestionContext> question() {
			return getRuleContexts(QuestionContext.class);
		}
		public QuestionContext question(int i) {
			return getRuleContext(QuestionContext.class,i);
		}
		public SectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_section; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitSection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitSection(this);
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
			setState(75);
			match(SECTION);
			setState(76);
			match(NEWLINE);
			setState(77);
			match(DESCRIPTION);
			setState(79);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NUMS || _la==WORD) {
				{
				setState(78);
				phrase();
				}
			}

			setState(81);
			match(NEWLINE);
			setState(83); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(82);
				question();
				}
				}
				setState(85); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 2064384L) != 0) );
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
		public MatchingContext matching() {
			return getRuleContext(MatchingContext.class,0);
		}
		public MultipleChoiceContext multipleChoice() {
			return getRuleContext(MultipleChoiceContext.class,0);
		}
		public ShortAnswerContext shortAnswer() {
			return getRuleContext(ShortAnswerContext.class,0);
		}
		public NumericalContext numerical() {
			return getRuleContext(NumericalContext.class,0);
		}
		public SelectMissingWordsContext selectMissingWords() {
			return getRuleContext(SelectMissingWordsContext.class,0);
		}
		public TrueFalseContext trueFalse() {
			return getRuleContext(TrueFalseContext.class,0);
		}
		public QuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionContext question() throws RecognitionException {
		QuestionContext _localctx = new QuestionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_question);
		try {
			setState(93);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MATCHING:
				enterOuterAlt(_localctx, 1);
				{
				setState(87);
				matching();
				}
				break;
			case MULTIPLE_CHOICE:
				enterOuterAlt(_localctx, 2);
				{
				setState(88);
				multipleChoice();
				}
				break;
			case SHORT_ANSWER:
				enterOuterAlt(_localctx, 3);
				{
				setState(89);
				shortAnswer();
				}
				break;
			case NUMERICAL:
				enterOuterAlt(_localctx, 4);
				{
				setState(90);
				numerical();
				}
				break;
			case SELECT_MISSING_WORDS:
				enterOuterAlt(_localctx, 5);
				{
				setState(91);
				selectMissingWords();
				}
				break;
			case TRUE_FALSE:
				enterOuterAlt(_localctx, 6);
				{
				setState(92);
				trueFalse();
				}
				break;
			default:
				throw new NoViableAltException(this);
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
	public static class MatchingContext extends ParserRuleContext {
		public TerminalNode MATCHING() { return getToken(ExamParser.MATCHING, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(ExamParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ExamParser.NEWLINE, i);
		}
		public PhraseContext phrase() {
			return getRuleContext(PhraseContext.class,0);
		}
		public List<PairContext> pair() {
			return getRuleContexts(PairContext.class);
		}
		public PairContext pair(int i) {
			return getRuleContext(PairContext.class,i);
		}
		public TerminalNode SCORE_PER_LINE() { return getToken(ExamParser.SCORE_PER_LINE, 0); }
		public TerminalNode FLOAT() { return getToken(ExamParser.FLOAT, 0); }
		public TerminalNode CORRECT_ANSWER() { return getToken(ExamParser.CORRECT_ANSWER, 0); }
		public List<MatchingOptionCorrectionContext> matchingOptionCorrection() {
			return getRuleContexts(MatchingOptionCorrectionContext.class);
		}
		public MatchingOptionCorrectionContext matchingOptionCorrection(int i) {
			return getRuleContext(MatchingOptionCorrectionContext.class,i);
		}
		public FeedbackContext feedback() {
			return getRuleContext(FeedbackContext.class,0);
		}
		public MatchingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matching; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterMatching(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitMatching(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitMatching(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchingContext matching() throws RecognitionException {
		MatchingContext _localctx = new MatchingContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_matching);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			match(MATCHING);
			setState(96);
			match(NEWLINE);
			setState(97);
			phrase();
			setState(98);
			match(NEWLINE);
			setState(99);
			pair();
			setState(101); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(100);
				pair();
				}
				}
				setState(103); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NUMERICAL_OPTION );
			setState(105);
			match(SCORE_PER_LINE);
			setState(106);
			match(FLOAT);
			setState(107);
			match(NEWLINE);
			setState(108);
			match(CORRECT_ANSWER);
			setState(110); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(109);
				matchingOptionCorrection();
				}
				}
				setState(112); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NUMERICAL_OPTION );
			setState(114);
			match(NEWLINE);
			setState(116);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FEEDBACK) {
				{
				setState(115);
				feedback();
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
	public static class PairContext extends ParserRuleContext {
		public TerminalNode NUMERICAL_OPTION() { return getToken(ExamParser.NUMERICAL_OPTION, 0); }
		public List<PhraseContext> phrase() {
			return getRuleContexts(PhraseContext.class);
		}
		public PhraseContext phrase(int i) {
			return getRuleContext(PhraseContext.class,i);
		}
		public TerminalNode DIVISIVE() { return getToken(ExamParser.DIVISIVE, 0); }
		public TerminalNode ALPHABETICAL_OPTION() { return getToken(ExamParser.ALPHABETICAL_OPTION, 0); }
		public TerminalNode NEWLINE() { return getToken(ExamParser.NEWLINE, 0); }
		public PairContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pair; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterPair(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitPair(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitPair(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairContext pair() throws RecognitionException {
		PairContext _localctx = new PairContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_pair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			match(NUMERICAL_OPTION);
			setState(119);
			phrase();
			setState(120);
			match(DIVISIVE);
			setState(121);
			match(ALPHABETICAL_OPTION);
			setState(122);
			phrase();
			setState(123);
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
	public static class MatchingOptionCorrectionContext extends ParserRuleContext {
		public TerminalNode NUMERICAL_OPTION() { return getToken(ExamParser.NUMERICAL_OPTION, 0); }
		public TerminalNode DASH() { return getToken(ExamParser.DASH, 0); }
		public TerminalNode ALPHABETICAL_OPTION() { return getToken(ExamParser.ALPHABETICAL_OPTION, 0); }
		public MatchingOptionCorrectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matchingOptionCorrection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterMatchingOptionCorrection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitMatchingOptionCorrection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitMatchingOptionCorrection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchingOptionCorrectionContext matchingOptionCorrection() throws RecognitionException {
		MatchingOptionCorrectionContext _localctx = new MatchingOptionCorrectionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_matchingOptionCorrection);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			match(NUMERICAL_OPTION);
			setState(126);
			match(DASH);
			setState(127);
			match(ALPHABETICAL_OPTION);
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
	public static class MultipleChoiceContext extends ParserRuleContext {
		public TerminalNode MULTIPLE_CHOICE() { return getToken(ExamParser.MULTIPLE_CHOICE, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(ExamParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ExamParser.NEWLINE, i);
		}
		public PhraseContext phrase() {
			return getRuleContext(PhraseContext.class,0);
		}
		public TerminalNode SCORE() { return getToken(ExamParser.SCORE, 0); }
		public TerminalNode FLOAT() { return getToken(ExamParser.FLOAT, 0); }
		public TerminalNode CORRECT_ANSWER() { return getToken(ExamParser.CORRECT_ANSWER, 0); }
		public TerminalNode CHAR() { return getToken(ExamParser.CHAR, 0); }
		public List<OptionContext> option() {
			return getRuleContexts(OptionContext.class);
		}
		public OptionContext option(int i) {
			return getRuleContext(OptionContext.class,i);
		}
		public FeedbackContext feedback() {
			return getRuleContext(FeedbackContext.class,0);
		}
		public MultipleChoiceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multipleChoice; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterMultipleChoice(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitMultipleChoice(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitMultipleChoice(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultipleChoiceContext multipleChoice() throws RecognitionException {
		MultipleChoiceContext _localctx = new MultipleChoiceContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_multipleChoice);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(129);
			match(MULTIPLE_CHOICE);
			setState(130);
			match(NEWLINE);
			setState(131);
			phrase();
			setState(132);
			match(NEWLINE);
			setState(134); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(133);
				option();
				}
				}
				setState(136); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ALPHABETICAL_OPTION );
			setState(138);
			match(SCORE);
			setState(139);
			match(FLOAT);
			setState(140);
			match(NEWLINE);
			setState(141);
			match(CORRECT_ANSWER);
			setState(142);
			match(CHAR);
			setState(143);
			match(NEWLINE);
			setState(145);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FEEDBACK) {
				{
				setState(144);
				feedback();
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
	public static class ShortAnswerContext extends ParserRuleContext {
		public TerminalNode SHORT_ANSWER() { return getToken(ExamParser.SHORT_ANSWER, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(ExamParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ExamParser.NEWLINE, i);
		}
		public List<PhraseContext> phrase() {
			return getRuleContexts(PhraseContext.class);
		}
		public PhraseContext phrase(int i) {
			return getRuleContext(PhraseContext.class,i);
		}
		public TerminalNode SCORE() { return getToken(ExamParser.SCORE, 0); }
		public TerminalNode FLOAT() { return getToken(ExamParser.FLOAT, 0); }
		public TerminalNode CORRECT_ANSWER() { return getToken(ExamParser.CORRECT_ANSWER, 0); }
		public FeedbackContext feedback() {
			return getRuleContext(FeedbackContext.class,0);
		}
		public ShortAnswerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shortAnswer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterShortAnswer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitShortAnswer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitShortAnswer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ShortAnswerContext shortAnswer() throws RecognitionException {
		ShortAnswerContext _localctx = new ShortAnswerContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_shortAnswer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			match(SHORT_ANSWER);
			setState(148);
			match(NEWLINE);
			setState(149);
			phrase();
			setState(150);
			match(NEWLINE);
			setState(151);
			match(SCORE);
			setState(152);
			match(FLOAT);
			setState(153);
			match(NEWLINE);
			setState(154);
			match(CORRECT_ANSWER);
			setState(155);
			phrase();
			setState(156);
			match(NEWLINE);
			setState(158);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FEEDBACK) {
				{
				setState(157);
				feedback();
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
	public static class NumericalContext extends ParserRuleContext {
		public TerminalNode NUMERICAL() { return getToken(ExamParser.NUMERICAL, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(ExamParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ExamParser.NEWLINE, i);
		}
		public PhraseContext phrase() {
			return getRuleContext(PhraseContext.class,0);
		}
		public TerminalNode SCORE() { return getToken(ExamParser.SCORE, 0); }
		public List<TerminalNode> FLOAT() { return getTokens(ExamParser.FLOAT); }
		public TerminalNode FLOAT(int i) {
			return getToken(ExamParser.FLOAT, i);
		}
		public TerminalNode CORRECT_ANSWER() { return getToken(ExamParser.CORRECT_ANSWER, 0); }
		public TerminalNode ERROR_NUMERICAL() { return getToken(ExamParser.ERROR_NUMERICAL, 0); }
		public FeedbackContext feedback() {
			return getRuleContext(FeedbackContext.class,0);
		}
		public NumericalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numerical; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterNumerical(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitNumerical(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitNumerical(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumericalContext numerical() throws RecognitionException {
		NumericalContext _localctx = new NumericalContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_numerical);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160);
			match(NUMERICAL);
			setState(161);
			match(NEWLINE);
			setState(162);
			phrase();
			setState(163);
			match(NEWLINE);
			setState(164);
			match(SCORE);
			setState(165);
			match(FLOAT);
			setState(166);
			match(NEWLINE);
			setState(167);
			match(CORRECT_ANSWER);
			setState(168);
			match(FLOAT);
			setState(169);
			match(NEWLINE);
			setState(170);
			match(ERROR_NUMERICAL);
			setState(171);
			match(FLOAT);
			setState(172);
			match(NEWLINE);
			setState(174);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FEEDBACK) {
				{
				setState(173);
				feedback();
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
	public static class SelectMissingWordsContext extends ParserRuleContext {
		public TerminalNode SELECT_MISSING_WORDS() { return getToken(ExamParser.SELECT_MISSING_WORDS, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(ExamParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ExamParser.NEWLINE, i);
		}
		public UncompleteTextContext uncompleteText() {
			return getRuleContext(UncompleteTextContext.class,0);
		}
		public TerminalNode POSSIBLE_CHOICES_SELECT_MISSING_WORDS() { return getToken(ExamParser.POSSIBLE_CHOICES_SELECT_MISSING_WORDS, 0); }
		public TerminalNode SCORE() { return getToken(ExamParser.SCORE, 0); }
		public TerminalNode FLOAT() { return getToken(ExamParser.FLOAT, 0); }
		public TerminalNode CORRECT_ANSWER() { return getToken(ExamParser.CORRECT_ANSWER, 0); }
		public PhraseContext phrase() {
			return getRuleContext(PhraseContext.class,0);
		}
		public List<PossibleChoicesContext> possibleChoices() {
			return getRuleContexts(PossibleChoicesContext.class);
		}
		public PossibleChoicesContext possibleChoices(int i) {
			return getRuleContext(PossibleChoicesContext.class,i);
		}
		public FeedbackContext feedback() {
			return getRuleContext(FeedbackContext.class,0);
		}
		public SelectMissingWordsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectMissingWords; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterSelectMissingWords(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitSelectMissingWords(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitSelectMissingWords(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectMissingWordsContext selectMissingWords() throws RecognitionException {
		SelectMissingWordsContext _localctx = new SelectMissingWordsContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_selectMissingWords);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176);
			match(SELECT_MISSING_WORDS);
			setState(177);
			match(NEWLINE);
			setState(178);
			uncompleteText();
			setState(179);
			match(NEWLINE);
			setState(180);
			match(POSSIBLE_CHOICES_SELECT_MISSING_WORDS);
			setState(182); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(181);
				possibleChoices();
				}
				}
				setState(184); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NUMS || _la==WORD );
			setState(186);
			match(NEWLINE);
			setState(187);
			match(SCORE);
			setState(188);
			match(FLOAT);
			setState(189);
			match(NEWLINE);
			setState(190);
			match(CORRECT_ANSWER);
			setState(191);
			phrase();
			setState(192);
			match(NEWLINE);
			setState(194);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FEEDBACK) {
				{
				setState(193);
				feedback();
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
	public static class UncompleteTextContext extends ParserRuleContext {
		public TerminalNode CHOSING_OPTION_SELECT_MISSING_WORDS() { return getToken(ExamParser.CHOSING_OPTION_SELECT_MISSING_WORDS, 0); }
		public List<PhraseContext> phrase() {
			return getRuleContexts(PhraseContext.class);
		}
		public PhraseContext phrase(int i) {
			return getRuleContext(PhraseContext.class,i);
		}
		public UncompleteTextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_uncompleteText; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterUncompleteText(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitUncompleteText(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitUncompleteText(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UncompleteTextContext uncompleteText() throws RecognitionException {
		UncompleteTextContext _localctx = new UncompleteTextContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_uncompleteText);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(197);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NUMS || _la==WORD) {
				{
				setState(196);
				phrase();
				}
			}

			setState(199);
			match(CHOSING_OPTION_SELECT_MISSING_WORDS);
			setState(201);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NUMS || _la==WORD) {
				{
				setState(200);
				phrase();
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
	public static class PossibleChoicesContext extends ParserRuleContext {
		public TerminalNode UNDERSCORE() { return getToken(ExamParser.UNDERSCORE, 0); }
		public List<WordsContext> words() {
			return getRuleContexts(WordsContext.class);
		}
		public WordsContext words(int i) {
			return getRuleContext(WordsContext.class,i);
		}
		public PossibleChoicesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_possibleChoices; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterPossibleChoices(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitPossibleChoices(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitPossibleChoices(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PossibleChoicesContext possibleChoices() throws RecognitionException {
		PossibleChoicesContext _localctx = new PossibleChoicesContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_possibleChoices);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(203);
				words();
				}
				}
				setState(206); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NUMS || _la==WORD );
			setState(208);
			match(UNDERSCORE);
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
	public static class TrueFalseContext extends ParserRuleContext {
		public TerminalNode TRUE_FALSE() { return getToken(ExamParser.TRUE_FALSE, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(ExamParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ExamParser.NEWLINE, i);
		}
		public PhraseContext phrase() {
			return getRuleContext(PhraseContext.class,0);
		}
		public TerminalNode SCORE() { return getToken(ExamParser.SCORE, 0); }
		public TerminalNode FLOAT() { return getToken(ExamParser.FLOAT, 0); }
		public TerminalNode CORRECT_ANSWER() { return getToken(ExamParser.CORRECT_ANSWER, 0); }
		public TerminalNode TRUE_OR_FALSE() { return getToken(ExamParser.TRUE_OR_FALSE, 0); }
		public FeedbackContext feedback() {
			return getRuleContext(FeedbackContext.class,0);
		}
		public TrueFalseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_trueFalse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterTrueFalse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitTrueFalse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitTrueFalse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TrueFalseContext trueFalse() throws RecognitionException {
		TrueFalseContext _localctx = new TrueFalseContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_trueFalse);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210);
			match(TRUE_FALSE);
			setState(211);
			match(NEWLINE);
			setState(212);
			phrase();
			setState(213);
			match(NEWLINE);
			setState(214);
			match(SCORE);
			setState(215);
			match(FLOAT);
			setState(216);
			match(NEWLINE);
			setState(217);
			match(CORRECT_ANSWER);
			setState(218);
			match(TRUE_OR_FALSE);
			setState(219);
			match(NEWLINE);
			setState(221);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FEEDBACK) {
				{
				setState(220);
				feedback();
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
	public static class PhraseContext extends ParserRuleContext {
		public List<WordsContext> words() {
			return getRuleContexts(WordsContext.class);
		}
		public WordsContext words(int i) {
			return getRuleContext(WordsContext.class,i);
		}
		public TerminalNode PUNCTUATION_MARKS() { return getToken(ExamParser.PUNCTUATION_MARKS, 0); }
		public PhraseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_phrase; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterPhrase(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitPhrase(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitPhrase(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PhraseContext phrase() throws RecognitionException {
		PhraseContext _localctx = new PhraseContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_phrase);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(223);
				words();
				}
				}
				setState(226); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NUMS || _la==WORD );
			setState(229);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PUNCTUATION_MARKS) {
				{
				setState(228);
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
	public static class OptionContext extends ParserRuleContext {
		public TerminalNode ALPHABETICAL_OPTION() { return getToken(ExamParser.ALPHABETICAL_OPTION, 0); }
		public PhraseContext phrase() {
			return getRuleContext(PhraseContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(ExamParser.NEWLINE, 0); }
		public OptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_option; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OptionContext option() throws RecognitionException {
		OptionContext _localctx = new OptionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_option);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231);
			match(ALPHABETICAL_OPTION);
			setState(232);
			phrase();
			setState(233);
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
	public static class FeedbackContext extends ParserRuleContext {
		public TerminalNode FEEDBACK() { return getToken(ExamParser.FEEDBACK, 0); }
		public PhraseContext phrase() {
			return getRuleContext(PhraseContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(ExamParser.NEWLINE, 0); }
		public FeedbackContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_feedback; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterFeedback(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitFeedback(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitFeedback(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FeedbackContext feedback() throws RecognitionException {
		FeedbackContext _localctx = new FeedbackContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_feedback);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
			match(FEEDBACK);
			setState(237);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NUMS || _la==WORD) {
				{
				setState(236);
				phrase();
				}
			}

			setState(240);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NEWLINE) {
				{
				setState(239);
				match(NEWLINE);
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
		public TerminalNode NONE() { return getToken(ExamParser.NONE, 0); }
		public TerminalNode ON_SUBMISSION() { return getToken(ExamParser.ON_SUBMISSION, 0); }
		public TerminalNode AFTER_CLOSING() { return getToken(ExamParser.AFTER_CLOSING, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(242);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 44L) != 0)) ) {
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
		public TerminalNode WORD() { return getToken(ExamParser.WORD, 0); }
		public TerminalNode NUMS() { return getToken(ExamParser.NUMS, 0); }
		public WordsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_words; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterWords(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitWords(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitWords(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WordsContext words() throws RecognitionException {
		WordsContext _localctx = new WordsContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_words);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(244);
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
		"\u0004\u00011\u00f7\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0001\u0000\u0001\u0000\u0001\u0000\u0004\u0000"+
		",\b\u0000\u000b\u0000\f\u0000-\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0003\u0002H\b\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003P\b"+
		"\u0003\u0001\u0003\u0001\u0003\u0004\u0003T\b\u0003\u000b\u0003\f\u0003"+
		"U\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0003\u0004^\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0004\u0005f\b\u0005\u000b\u0005\f\u0005g\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0004\u0005o\b"+
		"\u0005\u000b\u0005\f\u0005p\u0001\u0005\u0001\u0005\u0003\u0005u\b\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0004\b\u0087\b\b\u000b\b\f\b\u0088\u0001\b"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003\b\u0092\b\b\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0003\t\u009f\b\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003"+
		"\n\u00af\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0004\u000b\u00b7\b\u000b\u000b\u000b\f\u000b\u00b8\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0003\u000b\u00c3\b\u000b\u0001\f\u0003\f\u00c6\b\f"+
		"\u0001\f\u0001\f\u0003\f\u00ca\b\f\u0001\r\u0004\r\u00cd\b\r\u000b\r\f"+
		"\r\u00ce\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0003\u000e\u00de\b\u000e\u0001\u000f\u0004\u000f\u00e1\b"+
		"\u000f\u000b\u000f\f\u000f\u00e2\u0001\u000f\u0003\u000f\u00e6\b\u000f"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011"+
		"\u0003\u0011\u00ee\b\u0011\u0001\u0011\u0003\u0011\u00f1\b\u0011\u0001"+
		"\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0000\u0000\u0014"+
		"\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a"+
		"\u001c\u001e \"$&\u0000\u0002\u0002\u0000\u0002\u0003\u0005\u0005\u0002"+
		"\u0000\u001d\u001d00\u00fc\u0000(\u0001\u0000\u0000\u0000\u0002/\u0001"+
		"\u0000\u0000\u0000\u0004=\u0001\u0000\u0000\u0000\u0006K\u0001\u0000\u0000"+
		"\u0000\b]\u0001\u0000\u0000\u0000\n_\u0001\u0000\u0000\u0000\fv\u0001"+
		"\u0000\u0000\u0000\u000e}\u0001\u0000\u0000\u0000\u0010\u0081\u0001\u0000"+
		"\u0000\u0000\u0012\u0093\u0001\u0000\u0000\u0000\u0014\u00a0\u0001\u0000"+
		"\u0000\u0000\u0016\u00b0\u0001\u0000\u0000\u0000\u0018\u00c5\u0001\u0000"+
		"\u0000\u0000\u001a\u00cc\u0001\u0000\u0000\u0000\u001c\u00d2\u0001\u0000"+
		"\u0000\u0000\u001e\u00e0\u0001\u0000\u0000\u0000 \u00e7\u0001\u0000\u0000"+
		"\u0000\"\u00eb\u0001\u0000\u0000\u0000$\u00f2\u0001\u0000\u0000\u0000"+
		"&\u00f4\u0001\u0000\u0000\u0000()\u0003\u0002\u0001\u0000)+\u0003\u0004"+
		"\u0002\u0000*,\u0003\u0006\u0003\u0000+*\u0001\u0000\u0000\u0000,-\u0001"+
		"\u0000\u0000\u0000-+\u0001\u0000\u0000\u0000-.\u0001\u0000\u0000\u0000"+
		".\u0001\u0001\u0000\u0000\u0000/0\u0005\u0007\u0000\u000001\u0003\u001e"+
		"\u000f\u000012\u0005 \u0000\u000023\u0005\b\u0000\u000034\u0005\u001b"+
		"\u0000\u000045\u0005&\u0000\u000056\u0005\u001c\u0000\u000067\u0005 \u0000"+
		"\u000078\u0005\t\u0000\u000089\u0005\u001b\u0000\u00009:\u0005&\u0000"+
		"\u0000:;\u0005\u001c\u0000\u0000;<\u0005 \u0000\u0000<\u0003\u0001\u0000"+
		"\u0000\u0000=>\u0005\n\u0000\u0000>?\u0005 \u0000\u0000?@\u0005\u000b"+
		"\u0000\u0000@A\u0003$\u0012\u0000AB\u0005 \u0000\u0000BC\u0005\f\u0000"+
		"\u0000CD\u0003$\u0012\u0000DE\u0005 \u0000\u0000EG\u0005\r\u0000\u0000"+
		"FH\u0003\u001e\u000f\u0000GF\u0001\u0000\u0000\u0000GH\u0001\u0000\u0000"+
		"\u0000HI\u0001\u0000\u0000\u0000IJ\u0005 \u0000\u0000J\u0005\u0001\u0000"+
		"\u0000\u0000KL\u0005\u000e\u0000\u0000LM\u0005 \u0000\u0000MO\u0005\r"+
		"\u0000\u0000NP\u0003\u001e\u000f\u0000ON\u0001\u0000\u0000\u0000OP\u0001"+
		"\u0000\u0000\u0000PQ\u0001\u0000\u0000\u0000QS\u0005 \u0000\u0000RT\u0003"+
		"\b\u0004\u0000SR\u0001\u0000\u0000\u0000TU\u0001\u0000\u0000\u0000US\u0001"+
		"\u0000\u0000\u0000UV\u0001\u0000\u0000\u0000V\u0007\u0001\u0000\u0000"+
		"\u0000W^\u0003\n\u0005\u0000X^\u0003\u0010\b\u0000Y^\u0003\u0012\t\u0000"+
		"Z^\u0003\u0014\n\u0000[^\u0003\u0016\u000b\u0000\\^\u0003\u001c\u000e"+
		"\u0000]W\u0001\u0000\u0000\u0000]X\u0001\u0000\u0000\u0000]Y\u0001\u0000"+
		"\u0000\u0000]Z\u0001\u0000\u0000\u0000][\u0001\u0000\u0000\u0000]\\\u0001"+
		"\u0000\u0000\u0000^\t\u0001\u0000\u0000\u0000_`\u0005\u000f\u0000\u0000"+
		"`a\u0005 \u0000\u0000ab\u0003\u001e\u000f\u0000bc\u0005 \u0000\u0000c"+
		"e\u0003\f\u0006\u0000df\u0003\f\u0006\u0000ed\u0001\u0000\u0000\u0000"+
		"fg\u0001\u0000\u0000\u0000ge\u0001\u0000\u0000\u0000gh\u0001\u0000\u0000"+
		"\u0000hi\u0001\u0000\u0000\u0000ij\u0005\u0015\u0000\u0000jk\u0005,\u0000"+
		"\u0000kl\u0005 \u0000\u0000ln\u0005\u0017\u0000\u0000mo\u0003\u000e\u0007"+
		"\u0000nm\u0001\u0000\u0000\u0000op\u0001\u0000\u0000\u0000pn\u0001\u0000"+
		"\u0000\u0000pq\u0001\u0000\u0000\u0000qr\u0001\u0000\u0000\u0000rt\u0005"+
		" \u0000\u0000su\u0003\"\u0011\u0000ts\u0001\u0000\u0000\u0000tu\u0001"+
		"\u0000\u0000\u0000u\u000b\u0001\u0000\u0000\u0000vw\u0005-\u0000\u0000"+
		"wx\u0003\u001e\u000f\u0000xy\u0005+\u0000\u0000yz\u0005.\u0000\u0000z"+
		"{\u0003\u001e\u000f\u0000{|\u0005 \u0000\u0000|\r\u0001\u0000\u0000\u0000"+
		"}~\u0005-\u0000\u0000~\u007f\u0005$\u0000\u0000\u007f\u0080\u0005.\u0000"+
		"\u0000\u0080\u000f\u0001\u0000\u0000\u0000\u0081\u0082\u0005\u0010\u0000"+
		"\u0000\u0082\u0083\u0005 \u0000\u0000\u0083\u0084\u0003\u001e\u000f\u0000"+
		"\u0084\u0086\u0005 \u0000\u0000\u0085\u0087\u0003 \u0010\u0000\u0086\u0085"+
		"\u0001\u0000\u0000\u0000\u0087\u0088\u0001\u0000\u0000\u0000\u0088\u0086"+
		"\u0001\u0000\u0000\u0000\u0088\u0089\u0001\u0000\u0000\u0000\u0089\u008a"+
		"\u0001\u0000\u0000\u0000\u008a\u008b\u0005\u0016\u0000\u0000\u008b\u008c"+
		"\u0005,\u0000\u0000\u008c\u008d\u0005 \u0000\u0000\u008d\u008e\u0005\u0017"+
		"\u0000\u0000\u008e\u008f\u0005\u001e\u0000\u0000\u008f\u0091\u0005 \u0000"+
		"\u0000\u0090\u0092\u0003\"\u0011\u0000\u0091\u0090\u0001\u0000\u0000\u0000"+
		"\u0091\u0092\u0001\u0000\u0000\u0000\u0092\u0011\u0001\u0000\u0000\u0000"+
		"\u0093\u0094\u0005\u0011\u0000\u0000\u0094\u0095\u0005 \u0000\u0000\u0095"+
		"\u0096\u0003\u001e\u000f\u0000\u0096\u0097\u0005 \u0000\u0000\u0097\u0098"+
		"\u0005\u0016\u0000\u0000\u0098\u0099\u0005,\u0000\u0000\u0099\u009a\u0005"+
		" \u0000\u0000\u009a\u009b\u0005\u0017\u0000\u0000\u009b\u009c\u0003\u001e"+
		"\u000f\u0000\u009c\u009e\u0005 \u0000\u0000\u009d\u009f\u0003\"\u0011"+
		"\u0000\u009e\u009d\u0001\u0000\u0000\u0000\u009e\u009f\u0001\u0000\u0000"+
		"\u0000\u009f\u0013\u0001\u0000\u0000\u0000\u00a0\u00a1\u0005\u0012\u0000"+
		"\u0000\u00a1\u00a2\u0005 \u0000\u0000\u00a2\u00a3\u0003\u001e\u000f\u0000"+
		"\u00a3\u00a4\u0005 \u0000\u0000\u00a4\u00a5\u0005\u0016\u0000\u0000\u00a5"+
		"\u00a6\u0005,\u0000\u0000\u00a6\u00a7\u0005 \u0000\u0000\u00a7\u00a8\u0005"+
		"\u0017\u0000\u0000\u00a8\u00a9\u0005,\u0000\u0000\u00a9\u00aa\u0005 \u0000"+
		"\u0000\u00aa\u00ab\u0005\u0019\u0000\u0000\u00ab\u00ac\u0005,\u0000\u0000"+
		"\u00ac\u00ae\u0005 \u0000\u0000\u00ad\u00af\u0003\"\u0011\u0000\u00ae"+
		"\u00ad\u0001\u0000\u0000\u0000\u00ae\u00af\u0001\u0000\u0000\u0000\u00af"+
		"\u0015\u0001\u0000\u0000\u0000\u00b0\u00b1\u0005\u0013\u0000\u0000\u00b1"+
		"\u00b2\u0005 \u0000\u0000\u00b2\u00b3\u0003\u0018\f\u0000\u00b3\u00b4"+
		"\u0005 \u0000\u0000\u00b4\u00b6\u0005\u001a\u0000\u0000\u00b5\u00b7\u0003"+
		"\u001a\r\u0000\u00b6\u00b5\u0001\u0000\u0000\u0000\u00b7\u00b8\u0001\u0000"+
		"\u0000\u0000\u00b8\u00b6\u0001\u0000\u0000\u0000\u00b8\u00b9\u0001\u0000"+
		"\u0000\u0000\u00b9\u00ba\u0001\u0000\u0000\u0000\u00ba\u00bb\u0005 \u0000"+
		"\u0000\u00bb\u00bc\u0005\u0016\u0000\u0000\u00bc\u00bd\u0005,\u0000\u0000"+
		"\u00bd\u00be\u0005 \u0000\u0000\u00be\u00bf\u0005\u0017\u0000\u0000\u00bf"+
		"\u00c0\u0003\u001e\u000f\u0000\u00c0\u00c2\u0005 \u0000\u0000\u00c1\u00c3"+
		"\u0003\"\u0011\u0000\u00c2\u00c1\u0001\u0000\u0000\u0000\u00c2\u00c3\u0001"+
		"\u0000\u0000\u0000\u00c3\u0017\u0001\u0000\u0000\u0000\u00c4\u00c6\u0003"+
		"\u001e\u000f\u0000\u00c5\u00c4\u0001\u0000\u0000\u0000\u00c5\u00c6\u0001"+
		"\u0000\u0000\u0000\u00c6\u00c7\u0001\u0000\u0000\u0000\u00c7\u00c9\u0005"+
		"\u0004\u0000\u0000\u00c8\u00ca\u0003\u001e\u000f\u0000\u00c9\u00c8\u0001"+
		"\u0000\u0000\u0000\u00c9\u00ca\u0001\u0000\u0000\u0000\u00ca\u0019\u0001"+
		"\u0000\u0000\u0000\u00cb\u00cd\u0003&\u0013\u0000\u00cc\u00cb\u0001\u0000"+
		"\u0000\u0000\u00cd\u00ce\u0001\u0000\u0000\u0000\u00ce\u00cc\u0001\u0000"+
		"\u0000\u0000\u00ce\u00cf\u0001\u0000\u0000\u0000\u00cf\u00d0\u0001\u0000"+
		"\u0000\u0000\u00d0\u00d1\u0005)\u0000\u0000\u00d1\u001b\u0001\u0000\u0000"+
		"\u0000\u00d2\u00d3\u0005\u0014\u0000\u0000\u00d3\u00d4\u0005 \u0000\u0000"+
		"\u00d4\u00d5\u0003\u001e\u000f\u0000\u00d5\u00d6\u0005 \u0000\u0000\u00d6"+
		"\u00d7\u0005\u0016\u0000\u0000\u00d7\u00d8\u0005,\u0000\u0000\u00d8\u00d9"+
		"\u0005 \u0000\u0000\u00d9\u00da\u0005\u0017\u0000\u0000\u00da\u00db\u0005"+
		"\u0001\u0000\u0000\u00db\u00dd\u0005 \u0000\u0000\u00dc\u00de\u0003\""+
		"\u0011\u0000\u00dd\u00dc\u0001\u0000\u0000\u0000\u00dd\u00de\u0001\u0000"+
		"\u0000\u0000\u00de\u001d\u0001\u0000\u0000\u0000\u00df\u00e1\u0003&\u0013"+
		"\u0000\u00e0\u00df\u0001\u0000\u0000\u0000\u00e1\u00e2\u0001\u0000\u0000"+
		"\u0000\u00e2\u00e0\u0001\u0000\u0000\u0000\u00e2\u00e3\u0001\u0000\u0000"+
		"\u0000\u00e3\u00e5\u0001\u0000\u0000\u0000\u00e4\u00e6\u0005\"\u0000\u0000"+
		"\u00e5\u00e4\u0001\u0000\u0000\u0000\u00e5\u00e6\u0001\u0000\u0000\u0000"+
		"\u00e6\u001f\u0001\u0000\u0000\u0000\u00e7\u00e8\u0005.\u0000\u0000\u00e8"+
		"\u00e9\u0003\u001e\u000f\u0000\u00e9\u00ea\u0005 \u0000\u0000\u00ea!\u0001"+
		"\u0000\u0000\u0000\u00eb\u00ed\u0005\u0018\u0000\u0000\u00ec\u00ee\u0003"+
		"\u001e\u000f\u0000\u00ed\u00ec\u0001\u0000\u0000\u0000\u00ed\u00ee\u0001"+
		"\u0000\u0000\u0000\u00ee\u00f0\u0001\u0000\u0000\u0000\u00ef\u00f1\u0005"+
		" \u0000\u0000\u00f0\u00ef\u0001\u0000\u0000\u0000\u00f0\u00f1\u0001\u0000"+
		"\u0000\u0000\u00f1#\u0001\u0000\u0000\u0000\u00f2\u00f3\u0007\u0000\u0000"+
		"\u0000\u00f3%\u0001\u0000\u0000\u0000\u00f4\u00f5\u0007\u0001\u0000\u0000"+
		"\u00f5\'\u0001\u0000\u0000\u0000\u0016-GOU]gpt\u0088\u0091\u009e\u00ae"+
		"\u00b8\u00c2\u00c5\u00c9\u00ce\u00dd\u00e2\u00e5\u00ed\u00f0";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}