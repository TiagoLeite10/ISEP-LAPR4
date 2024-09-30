// Generated from C:/Users/Gabriel/Desktop/Escola/Engenharia Informática/2º Ano/2º Semetre/Projeto Integrador/sem4pi-22-23-20/antlr4\ExamQuestion.g4 by ANTLR 4.12.0

package eapli.base.questionmanagement.lprog;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class ExamQuestionParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		TRUE_OR_FALSE=1, CHOSING_OPTION_SELECT_MISSING_WORDS=2, IDENTIFIER=3, 
		SECTION=4, MATCHING=5, MULTIPLE_CHOICE=6, SHORT_ANSWER=7, NUMERICAL=8, 
		SELECT_MISSING_WORDS=9, TRUE_FALSE=10, SCORE=11, SCORE_PER_LINE=12, CORRECT_ANSWER=13, 
		FEEDBACK=14, ERROR_NUMERICAL=15, POSSIBLE_CHOICES_SELECT_MISSING_WORDS=16, 
		NUMS=17, CHAR=18, NUM=19, NEWLINE=20, PUNCTUATION_MARKS=21, DOT=22, DASH=23, 
		SPACE=24, COMMA=25, TWO_DOTS=26, UNDERSCORE=27, RIGHT_PARENTHESES=28, 
		DIVISIVE=29, FLOAT=30, NUMERICAL_OPTION=31, ALPHABETICAL_OPTION=32, MATCHING_OPTION_CORRECTION=33, 
		WORD=34, SPECIAL_CHAR=35, WS=36;
	public static final int
		RULE_examQuestion = 0, RULE_question = 1, RULE_matching = 2, RULE_pair = 3, 
		RULE_matchingOptionCorrection = 4, RULE_multipleChoice = 5, RULE_shortAnswer = 6, 
		RULE_numerical = 7, RULE_selectMissingWords = 8, RULE_uncompleteText = 9, 
		RULE_possibleChoices = 10, RULE_trueFalse = 11, RULE_phrase = 12, RULE_option = 13, 
		RULE_feedback = 14, RULE_words = 15;
	private static String[] makeRuleNames() {
		return new String[] {
			"examQuestion", "question", "matching", "pair", "matchingOptionCorrection", 
			"multipleChoice", "shortAnswer", "numerical", "selectMissingWords", "uncompleteText", 
			"possibleChoices", "trueFalse", "phrase", "option", "feedback", "words"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, "'<option>'", null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, "'.'", 
			"'-'", "' '", "','", "':'", "'_'", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "TRUE_OR_FALSE", "CHOSING_OPTION_SELECT_MISSING_WORDS", "IDENTIFIER", 
			"SECTION", "MATCHING", "MULTIPLE_CHOICE", "SHORT_ANSWER", "NUMERICAL", 
			"SELECT_MISSING_WORDS", "TRUE_FALSE", "SCORE", "SCORE_PER_LINE", "CORRECT_ANSWER", 
			"FEEDBACK", "ERROR_NUMERICAL", "POSSIBLE_CHOICES_SELECT_MISSING_WORDS", 
			"NUMS", "CHAR", "NUM", "NEWLINE", "PUNCTUATION_MARKS", "DOT", "DASH", 
			"SPACE", "COMMA", "TWO_DOTS", "UNDERSCORE", "RIGHT_PARENTHESES", "DIVISIVE", 
			"FLOAT", "NUMERICAL_OPTION", "ALPHABETICAL_OPTION", "MATCHING_OPTION_CORRECTION", 
			"WORD", "SPECIAL_CHAR", "WS"
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
	public String getGrammarFileName() { return "ExamQuestion.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ExamQuestionParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExamQuestionContext extends ParserRuleContext {
		public List<QuestionContext> question() {
			return getRuleContexts(QuestionContext.class);
		}
		public QuestionContext question(int i) {
			return getRuleContext(QuestionContext.class,i);
		}
		public ExamQuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_examQuestion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamQuestionListener ) ((ExamQuestionListener)listener).enterExamQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamQuestionListener ) ((ExamQuestionListener)listener).exitExamQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamQuestionVisitor ) return ((ExamQuestionVisitor<? extends T>)visitor).visitExamQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExamQuestionContext examQuestion() throws RecognitionException {
		ExamQuestionContext _localctx = new ExamQuestionContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_examQuestion);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(32);
				question();
				}
				}
				setState(35); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 2016L) != 0) );
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
			if ( listener instanceof ExamQuestionListener ) ((ExamQuestionListener)listener).enterQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamQuestionListener ) ((ExamQuestionListener)listener).exitQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamQuestionVisitor ) return ((ExamQuestionVisitor<? extends T>)visitor).visitQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionContext question() throws RecognitionException {
		QuestionContext _localctx = new QuestionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_question);
		try {
			setState(43);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MATCHING:
				enterOuterAlt(_localctx, 1);
				{
				setState(37);
				matching();
				}
				break;
			case MULTIPLE_CHOICE:
				enterOuterAlt(_localctx, 2);
				{
				setState(38);
				multipleChoice();
				}
				break;
			case SHORT_ANSWER:
				enterOuterAlt(_localctx, 3);
				{
				setState(39);
				shortAnswer();
				}
				break;
			case NUMERICAL:
				enterOuterAlt(_localctx, 4);
				{
				setState(40);
				numerical();
				}
				break;
			case SELECT_MISSING_WORDS:
				enterOuterAlt(_localctx, 5);
				{
				setState(41);
				selectMissingWords();
				}
				break;
			case TRUE_FALSE:
				enterOuterAlt(_localctx, 6);
				{
				setState(42);
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
		public TerminalNode MATCHING() { return getToken(ExamQuestionParser.MATCHING, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(ExamQuestionParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ExamQuestionParser.NEWLINE, i);
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
		public TerminalNode SCORE_PER_LINE() { return getToken(ExamQuestionParser.SCORE_PER_LINE, 0); }
		public TerminalNode FLOAT() { return getToken(ExamQuestionParser.FLOAT, 0); }
		public TerminalNode CORRECT_ANSWER() { return getToken(ExamQuestionParser.CORRECT_ANSWER, 0); }
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
			if ( listener instanceof ExamQuestionListener ) ((ExamQuestionListener)listener).enterMatching(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamQuestionListener ) ((ExamQuestionListener)listener).exitMatching(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamQuestionVisitor ) return ((ExamQuestionVisitor<? extends T>)visitor).visitMatching(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchingContext matching() throws RecognitionException {
		MatchingContext _localctx = new MatchingContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_matching);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			match(MATCHING);
			setState(46);
			match(NEWLINE);
			setState(47);
			phrase();
			setState(48);
			match(NEWLINE);
			setState(49);
			pair();
			setState(51); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(50);
				pair();
				}
				}
				setState(53); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NUMERICAL_OPTION );
			setState(55);
			match(SCORE_PER_LINE);
			setState(56);
			match(FLOAT);
			setState(57);
			match(NEWLINE);
			setState(58);
			match(CORRECT_ANSWER);
			setState(60); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(59);
				matchingOptionCorrection();
				}
				}
				setState(62); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NUMERICAL_OPTION );
			setState(64);
			match(NEWLINE);
			setState(66);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FEEDBACK) {
				{
				setState(65);
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
		public TerminalNode NUMERICAL_OPTION() { return getToken(ExamQuestionParser.NUMERICAL_OPTION, 0); }
		public List<PhraseContext> phrase() {
			return getRuleContexts(PhraseContext.class);
		}
		public PhraseContext phrase(int i) {
			return getRuleContext(PhraseContext.class,i);
		}
		public TerminalNode DIVISIVE() { return getToken(ExamQuestionParser.DIVISIVE, 0); }
		public TerminalNode ALPHABETICAL_OPTION() { return getToken(ExamQuestionParser.ALPHABETICAL_OPTION, 0); }
		public TerminalNode NEWLINE() { return getToken(ExamQuestionParser.NEWLINE, 0); }
		public PairContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pair; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamQuestionListener ) ((ExamQuestionListener)listener).enterPair(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamQuestionListener ) ((ExamQuestionListener)listener).exitPair(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamQuestionVisitor ) return ((ExamQuestionVisitor<? extends T>)visitor).visitPair(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairContext pair() throws RecognitionException {
		PairContext _localctx = new PairContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_pair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			match(NUMERICAL_OPTION);
			setState(69);
			phrase();
			setState(70);
			match(DIVISIVE);
			setState(71);
			match(ALPHABETICAL_OPTION);
			setState(72);
			phrase();
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
	public static class MatchingOptionCorrectionContext extends ParserRuleContext {
		public TerminalNode NUMERICAL_OPTION() { return getToken(ExamQuestionParser.NUMERICAL_OPTION, 0); }
		public TerminalNode DASH() { return getToken(ExamQuestionParser.DASH, 0); }
		public TerminalNode ALPHABETICAL_OPTION() { return getToken(ExamQuestionParser.ALPHABETICAL_OPTION, 0); }
		public MatchingOptionCorrectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matchingOptionCorrection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamQuestionListener ) ((ExamQuestionListener)listener).enterMatchingOptionCorrection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamQuestionListener ) ((ExamQuestionListener)listener).exitMatchingOptionCorrection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamQuestionVisitor ) return ((ExamQuestionVisitor<? extends T>)visitor).visitMatchingOptionCorrection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchingOptionCorrectionContext matchingOptionCorrection() throws RecognitionException {
		MatchingOptionCorrectionContext _localctx = new MatchingOptionCorrectionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_matchingOptionCorrection);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			match(NUMERICAL_OPTION);
			setState(76);
			match(DASH);
			setState(77);
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
		public TerminalNode MULTIPLE_CHOICE() { return getToken(ExamQuestionParser.MULTIPLE_CHOICE, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(ExamQuestionParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ExamQuestionParser.NEWLINE, i);
		}
		public PhraseContext phrase() {
			return getRuleContext(PhraseContext.class,0);
		}
		public TerminalNode SCORE() { return getToken(ExamQuestionParser.SCORE, 0); }
		public TerminalNode FLOAT() { return getToken(ExamQuestionParser.FLOAT, 0); }
		public TerminalNode CORRECT_ANSWER() { return getToken(ExamQuestionParser.CORRECT_ANSWER, 0); }
		public TerminalNode CHAR() { return getToken(ExamQuestionParser.CHAR, 0); }
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
			if ( listener instanceof ExamQuestionListener ) ((ExamQuestionListener)listener).enterMultipleChoice(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamQuestionListener ) ((ExamQuestionListener)listener).exitMultipleChoice(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamQuestionVisitor ) return ((ExamQuestionVisitor<? extends T>)visitor).visitMultipleChoice(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultipleChoiceContext multipleChoice() throws RecognitionException {
		MultipleChoiceContext _localctx = new MultipleChoiceContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_multipleChoice);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			match(MULTIPLE_CHOICE);
			setState(80);
			match(NEWLINE);
			setState(81);
			phrase();
			setState(82);
			match(NEWLINE);
			setState(84); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(83);
				option();
				}
				}
				setState(86); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ALPHABETICAL_OPTION );
			setState(88);
			match(SCORE);
			setState(89);
			match(FLOAT);
			setState(90);
			match(NEWLINE);
			setState(91);
			match(CORRECT_ANSWER);
			setState(92);
			match(CHAR);
			setState(93);
			match(NEWLINE);
			setState(95);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FEEDBACK) {
				{
				setState(94);
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
		public TerminalNode SHORT_ANSWER() { return getToken(ExamQuestionParser.SHORT_ANSWER, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(ExamQuestionParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ExamQuestionParser.NEWLINE, i);
		}
		public List<PhraseContext> phrase() {
			return getRuleContexts(PhraseContext.class);
		}
		public PhraseContext phrase(int i) {
			return getRuleContext(PhraseContext.class,i);
		}
		public TerminalNode SCORE() { return getToken(ExamQuestionParser.SCORE, 0); }
		public TerminalNode FLOAT() { return getToken(ExamQuestionParser.FLOAT, 0); }
		public TerminalNode CORRECT_ANSWER() { return getToken(ExamQuestionParser.CORRECT_ANSWER, 0); }
		public FeedbackContext feedback() {
			return getRuleContext(FeedbackContext.class,0);
		}
		public ShortAnswerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shortAnswer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamQuestionListener ) ((ExamQuestionListener)listener).enterShortAnswer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamQuestionListener ) ((ExamQuestionListener)listener).exitShortAnswer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamQuestionVisitor ) return ((ExamQuestionVisitor<? extends T>)visitor).visitShortAnswer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ShortAnswerContext shortAnswer() throws RecognitionException {
		ShortAnswerContext _localctx = new ShortAnswerContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_shortAnswer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			match(SHORT_ANSWER);
			setState(98);
			match(NEWLINE);
			setState(99);
			phrase();
			setState(100);
			match(NEWLINE);
			setState(101);
			match(SCORE);
			setState(102);
			match(FLOAT);
			setState(103);
			match(NEWLINE);
			setState(104);
			match(CORRECT_ANSWER);
			setState(105);
			phrase();
			setState(106);
			match(NEWLINE);
			setState(108);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FEEDBACK) {
				{
				setState(107);
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
		public TerminalNode NUMERICAL() { return getToken(ExamQuestionParser.NUMERICAL, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(ExamQuestionParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ExamQuestionParser.NEWLINE, i);
		}
		public PhraseContext phrase() {
			return getRuleContext(PhraseContext.class,0);
		}
		public TerminalNode SCORE() { return getToken(ExamQuestionParser.SCORE, 0); }
		public List<TerminalNode> FLOAT() { return getTokens(ExamQuestionParser.FLOAT); }
		public TerminalNode FLOAT(int i) {
			return getToken(ExamQuestionParser.FLOAT, i);
		}
		public TerminalNode CORRECT_ANSWER() { return getToken(ExamQuestionParser.CORRECT_ANSWER, 0); }
		public TerminalNode ERROR_NUMERICAL() { return getToken(ExamQuestionParser.ERROR_NUMERICAL, 0); }
		public FeedbackContext feedback() {
			return getRuleContext(FeedbackContext.class,0);
		}
		public NumericalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numerical; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamQuestionListener ) ((ExamQuestionListener)listener).enterNumerical(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamQuestionListener ) ((ExamQuestionListener)listener).exitNumerical(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamQuestionVisitor ) return ((ExamQuestionVisitor<? extends T>)visitor).visitNumerical(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumericalContext numerical() throws RecognitionException {
		NumericalContext _localctx = new NumericalContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_numerical);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			match(NUMERICAL);
			setState(111);
			match(NEWLINE);
			setState(112);
			phrase();
			setState(113);
			match(NEWLINE);
			setState(114);
			match(SCORE);
			setState(115);
			match(FLOAT);
			setState(116);
			match(NEWLINE);
			setState(117);
			match(CORRECT_ANSWER);
			setState(118);
			match(FLOAT);
			setState(119);
			match(NEWLINE);
			setState(120);
			match(ERROR_NUMERICAL);
			setState(121);
			match(FLOAT);
			setState(122);
			match(NEWLINE);
			setState(124);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FEEDBACK) {
				{
				setState(123);
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
		public TerminalNode SELECT_MISSING_WORDS() { return getToken(ExamQuestionParser.SELECT_MISSING_WORDS, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(ExamQuestionParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ExamQuestionParser.NEWLINE, i);
		}
		public UncompleteTextContext uncompleteText() {
			return getRuleContext(UncompleteTextContext.class,0);
		}
		public TerminalNode POSSIBLE_CHOICES_SELECT_MISSING_WORDS() { return getToken(ExamQuestionParser.POSSIBLE_CHOICES_SELECT_MISSING_WORDS, 0); }
		public TerminalNode SCORE() { return getToken(ExamQuestionParser.SCORE, 0); }
		public TerminalNode FLOAT() { return getToken(ExamQuestionParser.FLOAT, 0); }
		public TerminalNode CORRECT_ANSWER() { return getToken(ExamQuestionParser.CORRECT_ANSWER, 0); }
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
			if ( listener instanceof ExamQuestionListener ) ((ExamQuestionListener)listener).enterSelectMissingWords(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamQuestionListener ) ((ExamQuestionListener)listener).exitSelectMissingWords(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamQuestionVisitor ) return ((ExamQuestionVisitor<? extends T>)visitor).visitSelectMissingWords(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectMissingWordsContext selectMissingWords() throws RecognitionException {
		SelectMissingWordsContext _localctx = new SelectMissingWordsContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_selectMissingWords);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			match(SELECT_MISSING_WORDS);
			setState(127);
			match(NEWLINE);
			setState(128);
			uncompleteText();
			setState(129);
			match(NEWLINE);
			setState(130);
			match(POSSIBLE_CHOICES_SELECT_MISSING_WORDS);
			setState(132); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(131);
				possibleChoices();
				}
				}
				setState(134); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NUMS || _la==WORD );
			setState(136);
			match(NEWLINE);
			setState(137);
			match(SCORE);
			setState(138);
			match(FLOAT);
			setState(139);
			match(NEWLINE);
			setState(140);
			match(CORRECT_ANSWER);
			setState(141);
			phrase();
			setState(142);
			match(NEWLINE);
			setState(144);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FEEDBACK) {
				{
				setState(143);
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
		public TerminalNode CHOSING_OPTION_SELECT_MISSING_WORDS() { return getToken(ExamQuestionParser.CHOSING_OPTION_SELECT_MISSING_WORDS, 0); }
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
			if ( listener instanceof ExamQuestionListener ) ((ExamQuestionListener)listener).enterUncompleteText(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamQuestionListener ) ((ExamQuestionListener)listener).exitUncompleteText(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamQuestionVisitor ) return ((ExamQuestionVisitor<? extends T>)visitor).visitUncompleteText(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UncompleteTextContext uncompleteText() throws RecognitionException {
		UncompleteTextContext _localctx = new UncompleteTextContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_uncompleteText);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NUMS || _la==WORD) {
				{
				setState(146);
				phrase();
				}
			}

			setState(149);
			match(CHOSING_OPTION_SELECT_MISSING_WORDS);
			setState(151);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NUMS || _la==WORD) {
				{
				setState(150);
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
		public TerminalNode UNDERSCORE() { return getToken(ExamQuestionParser.UNDERSCORE, 0); }
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
			if ( listener instanceof ExamQuestionListener ) ((ExamQuestionListener)listener).enterPossibleChoices(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamQuestionListener ) ((ExamQuestionListener)listener).exitPossibleChoices(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamQuestionVisitor ) return ((ExamQuestionVisitor<? extends T>)visitor).visitPossibleChoices(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PossibleChoicesContext possibleChoices() throws RecognitionException {
		PossibleChoicesContext _localctx = new PossibleChoicesContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_possibleChoices);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(153);
				words();
				}
				}
				setState(156); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NUMS || _la==WORD );
			setState(158);
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
		public TerminalNode TRUE_FALSE() { return getToken(ExamQuestionParser.TRUE_FALSE, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(ExamQuestionParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ExamQuestionParser.NEWLINE, i);
		}
		public PhraseContext phrase() {
			return getRuleContext(PhraseContext.class,0);
		}
		public TerminalNode SCORE() { return getToken(ExamQuestionParser.SCORE, 0); }
		public TerminalNode FLOAT() { return getToken(ExamQuestionParser.FLOAT, 0); }
		public TerminalNode CORRECT_ANSWER() { return getToken(ExamQuestionParser.CORRECT_ANSWER, 0); }
		public TerminalNode TRUE_OR_FALSE() { return getToken(ExamQuestionParser.TRUE_OR_FALSE, 0); }
		public FeedbackContext feedback() {
			return getRuleContext(FeedbackContext.class,0);
		}
		public TrueFalseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_trueFalse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamQuestionListener ) ((ExamQuestionListener)listener).enterTrueFalse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamQuestionListener ) ((ExamQuestionListener)listener).exitTrueFalse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamQuestionVisitor ) return ((ExamQuestionVisitor<? extends T>)visitor).visitTrueFalse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TrueFalseContext trueFalse() throws RecognitionException {
		TrueFalseContext _localctx = new TrueFalseContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_trueFalse);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160);
			match(TRUE_FALSE);
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
			match(TRUE_OR_FALSE);
			setState(169);
			match(NEWLINE);
			setState(171);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FEEDBACK) {
				{
				setState(170);
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
		public TerminalNode PUNCTUATION_MARKS() { return getToken(ExamQuestionParser.PUNCTUATION_MARKS, 0); }
		public PhraseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_phrase; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamQuestionListener ) ((ExamQuestionListener)listener).enterPhrase(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamQuestionListener ) ((ExamQuestionListener)listener).exitPhrase(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamQuestionVisitor ) return ((ExamQuestionVisitor<? extends T>)visitor).visitPhrase(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PhraseContext phrase() throws RecognitionException {
		PhraseContext _localctx = new PhraseContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_phrase);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(173);
				words();
				}
				}
				setState(176); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NUMS || _la==WORD );
			setState(179);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PUNCTUATION_MARKS) {
				{
				setState(178);
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
		public TerminalNode ALPHABETICAL_OPTION() { return getToken(ExamQuestionParser.ALPHABETICAL_OPTION, 0); }
		public PhraseContext phrase() {
			return getRuleContext(PhraseContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(ExamQuestionParser.NEWLINE, 0); }
		public OptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_option; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamQuestionListener ) ((ExamQuestionListener)listener).enterOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamQuestionListener ) ((ExamQuestionListener)listener).exitOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamQuestionVisitor ) return ((ExamQuestionVisitor<? extends T>)visitor).visitOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OptionContext option() throws RecognitionException {
		OptionContext _localctx = new OptionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_option);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
			match(ALPHABETICAL_OPTION);
			setState(182);
			phrase();
			setState(183);
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
		public TerminalNode FEEDBACK() { return getToken(ExamQuestionParser.FEEDBACK, 0); }
		public PhraseContext phrase() {
			return getRuleContext(PhraseContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(ExamQuestionParser.NEWLINE, 0); }
		public FeedbackContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_feedback; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamQuestionListener ) ((ExamQuestionListener)listener).enterFeedback(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamQuestionListener ) ((ExamQuestionListener)listener).exitFeedback(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamQuestionVisitor ) return ((ExamQuestionVisitor<? extends T>)visitor).visitFeedback(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FeedbackContext feedback() throws RecognitionException {
		FeedbackContext _localctx = new FeedbackContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_feedback);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			match(FEEDBACK);
			setState(187);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NUMS || _la==WORD) {
				{
				setState(186);
				phrase();
				}
			}

			setState(190);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NEWLINE) {
				{
				setState(189);
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
	public static class WordsContext extends ParserRuleContext {
		public TerminalNode WORD() { return getToken(ExamQuestionParser.WORD, 0); }
		public TerminalNode NUMS() { return getToken(ExamQuestionParser.NUMS, 0); }
		public WordsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_words; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamQuestionListener ) ((ExamQuestionListener)listener).enterWords(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamQuestionListener ) ((ExamQuestionListener)listener).exitWords(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamQuestionVisitor ) return ((ExamQuestionVisitor<? extends T>)visitor).visitWords(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WordsContext words() throws RecognitionException {
		WordsContext _localctx = new WordsContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_words);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(192);
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
		"\u0004\u0001$\u00c3\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0001\u0000\u0004\u0000\"\b\u0000\u000b\u0000\f\u0000#\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001,\b"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0004\u00024\b\u0002\u000b\u0002\f\u00025\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0004\u0002=\b\u0002\u000b\u0002"+
		"\f\u0002>\u0001\u0002\u0001\u0002\u0003\u0002C\b\u0002\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0004\u0005U\b\u0005\u000b\u0005\f\u0005"+
		"V\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0003\u0005`\b\u0005\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0003\u0006m\b\u0006\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0003\u0007}\b\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b"+
		"\u0004\b\u0085\b\b\u000b\b\f\b\u0086\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0003\b\u0091\b\b\u0001\t\u0003\t\u0094\b\t"+
		"\u0001\t\u0001\t\u0003\t\u0098\b\t\u0001\n\u0004\n\u009b\b\n\u000b\n\f"+
		"\n\u009c\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0003\u000b\u00ac\b\u000b\u0001\f\u0004\f\u00af\b\f\u000b"+
		"\f\f\f\u00b0\u0001\f\u0003\f\u00b4\b\f\u0001\r\u0001\r\u0001\r\u0001\r"+
		"\u0001\u000e\u0001\u000e\u0003\u000e\u00bc\b\u000e\u0001\u000e\u0003\u000e"+
		"\u00bf\b\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0000\u0000\u0010\u0000"+
		"\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c"+
		"\u001e\u0000\u0001\u0002\u0000\u0011\u0011\"\"\u00c9\u0000!\u0001\u0000"+
		"\u0000\u0000\u0002+\u0001\u0000\u0000\u0000\u0004-\u0001\u0000\u0000\u0000"+
		"\u0006D\u0001\u0000\u0000\u0000\bK\u0001\u0000\u0000\u0000\nO\u0001\u0000"+
		"\u0000\u0000\fa\u0001\u0000\u0000\u0000\u000en\u0001\u0000\u0000\u0000"+
		"\u0010~\u0001\u0000\u0000\u0000\u0012\u0093\u0001\u0000\u0000\u0000\u0014"+
		"\u009a\u0001\u0000\u0000\u0000\u0016\u00a0\u0001\u0000\u0000\u0000\u0018"+
		"\u00ae\u0001\u0000\u0000\u0000\u001a\u00b5\u0001\u0000\u0000\u0000\u001c"+
		"\u00b9\u0001\u0000\u0000\u0000\u001e\u00c0\u0001\u0000\u0000\u0000 \""+
		"\u0003\u0002\u0001\u0000! \u0001\u0000\u0000\u0000\"#\u0001\u0000\u0000"+
		"\u0000#!\u0001\u0000\u0000\u0000#$\u0001\u0000\u0000\u0000$\u0001\u0001"+
		"\u0000\u0000\u0000%,\u0003\u0004\u0002\u0000&,\u0003\n\u0005\u0000\',"+
		"\u0003\f\u0006\u0000(,\u0003\u000e\u0007\u0000),\u0003\u0010\b\u0000*"+
		",\u0003\u0016\u000b\u0000+%\u0001\u0000\u0000\u0000+&\u0001\u0000\u0000"+
		"\u0000+\'\u0001\u0000\u0000\u0000+(\u0001\u0000\u0000\u0000+)\u0001\u0000"+
		"\u0000\u0000+*\u0001\u0000\u0000\u0000,\u0003\u0001\u0000\u0000\u0000"+
		"-.\u0005\u0005\u0000\u0000./\u0005\u0014\u0000\u0000/0\u0003\u0018\f\u0000"+
		"01\u0005\u0014\u0000\u000013\u0003\u0006\u0003\u000024\u0003\u0006\u0003"+
		"\u000032\u0001\u0000\u0000\u000045\u0001\u0000\u0000\u000053\u0001\u0000"+
		"\u0000\u000056\u0001\u0000\u0000\u000067\u0001\u0000\u0000\u000078\u0005"+
		"\f\u0000\u000089\u0005\u001e\u0000\u00009:\u0005\u0014\u0000\u0000:<\u0005"+
		"\r\u0000\u0000;=\u0003\b\u0004\u0000<;\u0001\u0000\u0000\u0000=>\u0001"+
		"\u0000\u0000\u0000><\u0001\u0000\u0000\u0000>?\u0001\u0000\u0000\u0000"+
		"?@\u0001\u0000\u0000\u0000@B\u0005\u0014\u0000\u0000AC\u0003\u001c\u000e"+
		"\u0000BA\u0001\u0000\u0000\u0000BC\u0001\u0000\u0000\u0000C\u0005\u0001"+
		"\u0000\u0000\u0000DE\u0005\u001f\u0000\u0000EF\u0003\u0018\f\u0000FG\u0005"+
		"\u001d\u0000\u0000GH\u0005 \u0000\u0000HI\u0003\u0018\f\u0000IJ\u0005"+
		"\u0014\u0000\u0000J\u0007\u0001\u0000\u0000\u0000KL\u0005\u001f\u0000"+
		"\u0000LM\u0005\u0017\u0000\u0000MN\u0005 \u0000\u0000N\t\u0001\u0000\u0000"+
		"\u0000OP\u0005\u0006\u0000\u0000PQ\u0005\u0014\u0000\u0000QR\u0003\u0018"+
		"\f\u0000RT\u0005\u0014\u0000\u0000SU\u0003\u001a\r\u0000TS\u0001\u0000"+
		"\u0000\u0000UV\u0001\u0000\u0000\u0000VT\u0001\u0000\u0000\u0000VW\u0001"+
		"\u0000\u0000\u0000WX\u0001\u0000\u0000\u0000XY\u0005\u000b\u0000\u0000"+
		"YZ\u0005\u001e\u0000\u0000Z[\u0005\u0014\u0000\u0000[\\\u0005\r\u0000"+
		"\u0000\\]\u0005\u0012\u0000\u0000]_\u0005\u0014\u0000\u0000^`\u0003\u001c"+
		"\u000e\u0000_^\u0001\u0000\u0000\u0000_`\u0001\u0000\u0000\u0000`\u000b"+
		"\u0001\u0000\u0000\u0000ab\u0005\u0007\u0000\u0000bc\u0005\u0014\u0000"+
		"\u0000cd\u0003\u0018\f\u0000de\u0005\u0014\u0000\u0000ef\u0005\u000b\u0000"+
		"\u0000fg\u0005\u001e\u0000\u0000gh\u0005\u0014\u0000\u0000hi\u0005\r\u0000"+
		"\u0000ij\u0003\u0018\f\u0000jl\u0005\u0014\u0000\u0000km\u0003\u001c\u000e"+
		"\u0000lk\u0001\u0000\u0000\u0000lm\u0001\u0000\u0000\u0000m\r\u0001\u0000"+
		"\u0000\u0000no\u0005\b\u0000\u0000op\u0005\u0014\u0000\u0000pq\u0003\u0018"+
		"\f\u0000qr\u0005\u0014\u0000\u0000rs\u0005\u000b\u0000\u0000st\u0005\u001e"+
		"\u0000\u0000tu\u0005\u0014\u0000\u0000uv\u0005\r\u0000\u0000vw\u0005\u001e"+
		"\u0000\u0000wx\u0005\u0014\u0000\u0000xy\u0005\u000f\u0000\u0000yz\u0005"+
		"\u001e\u0000\u0000z|\u0005\u0014\u0000\u0000{}\u0003\u001c\u000e\u0000"+
		"|{\u0001\u0000\u0000\u0000|}\u0001\u0000\u0000\u0000}\u000f\u0001\u0000"+
		"\u0000\u0000~\u007f\u0005\t\u0000\u0000\u007f\u0080\u0005\u0014\u0000"+
		"\u0000\u0080\u0081\u0003\u0012\t\u0000\u0081\u0082\u0005\u0014\u0000\u0000"+
		"\u0082\u0084\u0005\u0010\u0000\u0000\u0083\u0085\u0003\u0014\n\u0000\u0084"+
		"\u0083\u0001\u0000\u0000\u0000\u0085\u0086\u0001\u0000\u0000\u0000\u0086"+
		"\u0084\u0001\u0000\u0000\u0000\u0086\u0087\u0001\u0000\u0000\u0000\u0087"+
		"\u0088\u0001\u0000\u0000\u0000\u0088\u0089\u0005\u0014\u0000\u0000\u0089"+
		"\u008a\u0005\u000b\u0000\u0000\u008a\u008b\u0005\u001e\u0000\u0000\u008b"+
		"\u008c\u0005\u0014\u0000\u0000\u008c\u008d\u0005\r\u0000\u0000\u008d\u008e"+
		"\u0003\u0018\f\u0000\u008e\u0090\u0005\u0014\u0000\u0000\u008f\u0091\u0003"+
		"\u001c\u000e\u0000\u0090\u008f\u0001\u0000\u0000\u0000\u0090\u0091\u0001"+
		"\u0000\u0000\u0000\u0091\u0011\u0001\u0000\u0000\u0000\u0092\u0094\u0003"+
		"\u0018\f\u0000\u0093\u0092\u0001\u0000\u0000\u0000\u0093\u0094\u0001\u0000"+
		"\u0000\u0000\u0094\u0095\u0001\u0000\u0000\u0000\u0095\u0097\u0005\u0002"+
		"\u0000\u0000\u0096\u0098\u0003\u0018\f\u0000\u0097\u0096\u0001\u0000\u0000"+
		"\u0000\u0097\u0098\u0001\u0000\u0000\u0000\u0098\u0013\u0001\u0000\u0000"+
		"\u0000\u0099\u009b\u0003\u001e\u000f\u0000\u009a\u0099\u0001\u0000\u0000"+
		"\u0000\u009b\u009c\u0001\u0000\u0000\u0000\u009c\u009a\u0001\u0000\u0000"+
		"\u0000\u009c\u009d\u0001\u0000\u0000\u0000\u009d\u009e\u0001\u0000\u0000"+
		"\u0000\u009e\u009f\u0005\u001b\u0000\u0000\u009f\u0015\u0001\u0000\u0000"+
		"\u0000\u00a0\u00a1\u0005\n\u0000\u0000\u00a1\u00a2\u0005\u0014\u0000\u0000"+
		"\u00a2\u00a3\u0003\u0018\f\u0000\u00a3\u00a4\u0005\u0014\u0000\u0000\u00a4"+
		"\u00a5\u0005\u000b\u0000\u0000\u00a5\u00a6\u0005\u001e\u0000\u0000\u00a6"+
		"\u00a7\u0005\u0014\u0000\u0000\u00a7\u00a8\u0005\r\u0000\u0000\u00a8\u00a9"+
		"\u0005\u0001\u0000\u0000\u00a9\u00ab\u0005\u0014\u0000\u0000\u00aa\u00ac"+
		"\u0003\u001c\u000e\u0000\u00ab\u00aa\u0001\u0000\u0000\u0000\u00ab\u00ac"+
		"\u0001\u0000\u0000\u0000\u00ac\u0017\u0001\u0000\u0000\u0000\u00ad\u00af"+
		"\u0003\u001e\u000f\u0000\u00ae\u00ad\u0001\u0000\u0000\u0000\u00af\u00b0"+
		"\u0001\u0000\u0000\u0000\u00b0\u00ae\u0001\u0000\u0000\u0000\u00b0\u00b1"+
		"\u0001\u0000\u0000\u0000\u00b1\u00b3\u0001\u0000\u0000\u0000\u00b2\u00b4"+
		"\u0005\u0015\u0000\u0000\u00b3\u00b2\u0001\u0000\u0000\u0000\u00b3\u00b4"+
		"\u0001\u0000\u0000\u0000\u00b4\u0019\u0001\u0000\u0000\u0000\u00b5\u00b6"+
		"\u0005 \u0000\u0000\u00b6\u00b7\u0003\u0018\f\u0000\u00b7\u00b8\u0005"+
		"\u0014\u0000\u0000\u00b8\u001b\u0001\u0000\u0000\u0000\u00b9\u00bb\u0005"+
		"\u000e\u0000\u0000\u00ba\u00bc\u0003\u0018\f\u0000\u00bb\u00ba\u0001\u0000"+
		"\u0000\u0000\u00bb\u00bc\u0001\u0000\u0000\u0000\u00bc\u00be\u0001\u0000"+
		"\u0000\u0000\u00bd\u00bf\u0005\u0014\u0000\u0000\u00be\u00bd\u0001\u0000"+
		"\u0000\u0000\u00be\u00bf\u0001\u0000\u0000\u0000\u00bf\u001d\u0001\u0000"+
		"\u0000\u0000\u00c0\u00c1\u0007\u0000\u0000\u0000\u00c1\u001f\u0001\u0000"+
		"\u0000\u0000\u0013#+5>BV_l|\u0086\u0090\u0093\u0097\u009c\u00ab\u00b0"+
		"\u00b3\u00bb\u00be";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}