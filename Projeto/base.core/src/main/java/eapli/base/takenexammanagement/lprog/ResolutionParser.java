// Generated from C:/Users/Gabriel/Desktop/Escola/Engenharia Informática/2º Ano/2º Semetre/Projeto Integrador/sem4pi-22-23-20/antlr4\Resolution.g4 by ANTLR 4.12.0

package eapli.base.takenexammanagement.lprog;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class ResolutionParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		TRUE_OR_FALSE=1, IDENTIFIER=2, SECTION=3, ANSWER=4, NUMS=5, CHAR=6, NUM=7, 
		NEWLINE=8, SPECIAL_CHAR=9, PUNCTUATION_MARKS=10, DOT=11, DASH=12, SLASH=13, 
		SPACE=14, COMMA=15, TWO_DOTS=16, UNDERSCORE=17, RIGHT_PARENTHESES=18, 
		FLOAT=19, NUMERICAL_OPTION=20, ALPHABETICAL_OPTION=21, WORD=22, WS=23;
	public static final int
		RULE_resolution = 0, RULE_section = 1, RULE_answer = 2, RULE_answerToMatching = 3, 
		RULE_matchingOption = 4, RULE_answerToMultipleChoice = 5, RULE_answerToShortAnswer = 6, 
		RULE_answerToNumerical = 7, RULE_answerToSelectMissingWords = 8, RULE_answerToTrueFalse = 9, 
		RULE_phrase = 10, RULE_words = 11;
	private static String[] makeRuleNames() {
		return new String[] {
			"resolution", "section", "answer", "answerToMatching", "matchingOption", 
			"answerToMultipleChoice", "answerToShortAnswer", "answerToNumerical", 
			"answerToSelectMissingWords", "answerToTrueFalse", "phrase", "words"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, "'.'", 
			"'-'", "'/'", "' '", "','", "':'", "'_'", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "TRUE_OR_FALSE", "IDENTIFIER", "SECTION", "ANSWER", "NUMS", "CHAR", 
			"NUM", "NEWLINE", "SPECIAL_CHAR", "PUNCTUATION_MARKS", "DOT", "DASH", 
			"SLASH", "SPACE", "COMMA", "TWO_DOTS", "UNDERSCORE", "RIGHT_PARENTHESES", 
			"FLOAT", "NUMERICAL_OPTION", "ALPHABETICAL_OPTION", "WORD", "WS"
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
	public String getGrammarFileName() { return "Resolution.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ResolutionParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ResolutionContext extends ParserRuleContext {
		public List<SectionContext> section() {
			return getRuleContexts(SectionContext.class);
		}
		public SectionContext section(int i) {
			return getRuleContext(SectionContext.class,i);
		}
		public ResolutionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_resolution; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ResolutionListener ) ((ResolutionListener)listener).enterResolution(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ResolutionListener ) ((ResolutionListener)listener).exitResolution(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ResolutionVisitor ) return ((ResolutionVisitor<? extends T>)visitor).visitResolution(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ResolutionContext resolution() throws RecognitionException {
		ResolutionContext _localctx = new ResolutionContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_resolution);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(25); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(24);
				section();
				}
				}
				setState(27); 
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
	public static class SectionContext extends ParserRuleContext {
		public TerminalNode SECTION() { return getToken(ResolutionParser.SECTION, 0); }
		public TerminalNode NEWLINE() { return getToken(ResolutionParser.NEWLINE, 0); }
		public List<AnswerContext> answer() {
			return getRuleContexts(AnswerContext.class);
		}
		public AnswerContext answer(int i) {
			return getRuleContext(AnswerContext.class,i);
		}
		public SectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_section; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ResolutionListener ) ((ResolutionListener)listener).enterSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ResolutionListener ) ((ResolutionListener)listener).exitSection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ResolutionVisitor ) return ((ResolutionVisitor<? extends T>)visitor).visitSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SectionContext section() throws RecognitionException {
		SectionContext _localctx = new SectionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_section);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29);
			match(SECTION);
			setState(30);
			match(NEWLINE);
			setState(32); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(31);
				answer();
				}
				}
				setState(34); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ANSWER );
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
	public static class AnswerContext extends ParserRuleContext {
		public AnswerToMatchingContext answerToMatching() {
			return getRuleContext(AnswerToMatchingContext.class,0);
		}
		public AnswerToMultipleChoiceContext answerToMultipleChoice() {
			return getRuleContext(AnswerToMultipleChoiceContext.class,0);
		}
		public AnswerToShortAnswerContext answerToShortAnswer() {
			return getRuleContext(AnswerToShortAnswerContext.class,0);
		}
		public AnswerToNumericalContext answerToNumerical() {
			return getRuleContext(AnswerToNumericalContext.class,0);
		}
		public AnswerToSelectMissingWordsContext answerToSelectMissingWords() {
			return getRuleContext(AnswerToSelectMissingWordsContext.class,0);
		}
		public AnswerToTrueFalseContext answerToTrueFalse() {
			return getRuleContext(AnswerToTrueFalseContext.class,0);
		}
		public AnswerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_answer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ResolutionListener ) ((ResolutionListener)listener).enterAnswer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ResolutionListener ) ((ResolutionListener)listener).exitAnswer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ResolutionVisitor ) return ((ResolutionVisitor<? extends T>)visitor).visitAnswer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnswerContext answer() throws RecognitionException {
		AnswerContext _localctx = new AnswerContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_answer);
		try {
			setState(42);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(36);
				answerToMatching();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(37);
				answerToMultipleChoice();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(38);
				answerToShortAnswer();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(39);
				answerToNumerical();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(40);
				answerToSelectMissingWords();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(41);
				answerToTrueFalse();
				}
				break;
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
	public static class AnswerToMatchingContext extends ParserRuleContext {
		public TerminalNode ANSWER() { return getToken(ResolutionParser.ANSWER, 0); }
		public List<MatchingOptionContext> matchingOption() {
			return getRuleContexts(MatchingOptionContext.class);
		}
		public MatchingOptionContext matchingOption(int i) {
			return getRuleContext(MatchingOptionContext.class,i);
		}
		public TerminalNode NEWLINE() { return getToken(ResolutionParser.NEWLINE, 0); }
		public AnswerToMatchingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_answerToMatching; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ResolutionListener ) ((ResolutionListener)listener).enterAnswerToMatching(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ResolutionListener ) ((ResolutionListener)listener).exitAnswerToMatching(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ResolutionVisitor ) return ((ResolutionVisitor<? extends T>)visitor).visitAnswerToMatching(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnswerToMatchingContext answerToMatching() throws RecognitionException {
		AnswerToMatchingContext _localctx = new AnswerToMatchingContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_answerToMatching);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			match(ANSWER);
			setState(48);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NUMERICAL_OPTION) {
				{
				{
				setState(45);
				matchingOption();
				}
				}
				setState(50);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(52);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NEWLINE) {
				{
				setState(51);
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
	public static class MatchingOptionContext extends ParserRuleContext {
		public TerminalNode NUMERICAL_OPTION() { return getToken(ResolutionParser.NUMERICAL_OPTION, 0); }
		public TerminalNode DASH() { return getToken(ResolutionParser.DASH, 0); }
		public TerminalNode ALPHABETICAL_OPTION() { return getToken(ResolutionParser.ALPHABETICAL_OPTION, 0); }
		public TerminalNode SPACE() { return getToken(ResolutionParser.SPACE, 0); }
		public MatchingOptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matchingOption; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ResolutionListener ) ((ResolutionListener)listener).enterMatchingOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ResolutionListener ) ((ResolutionListener)listener).exitMatchingOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ResolutionVisitor ) return ((ResolutionVisitor<? extends T>)visitor).visitMatchingOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchingOptionContext matchingOption() throws RecognitionException {
		MatchingOptionContext _localctx = new MatchingOptionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_matchingOption);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			match(NUMERICAL_OPTION);
			setState(55);
			match(DASH);
			setState(60);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EOF:
			case SECTION:
			case ANSWER:
			case NEWLINE:
			case SPACE:
			case NUMERICAL_OPTION:
				{
				setState(57);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SPACE) {
					{
					setState(56);
					match(SPACE);
					}
				}

				}
				break;
			case ALPHABETICAL_OPTION:
				{
				setState(59);
				match(ALPHABETICAL_OPTION);
				}
				break;
			default:
				throw new NoViableAltException(this);
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
	public static class AnswerToMultipleChoiceContext extends ParserRuleContext {
		public TerminalNode ANSWER() { return getToken(ResolutionParser.ANSWER, 0); }
		public TerminalNode CHAR() { return getToken(ResolutionParser.CHAR, 0); }
		public TerminalNode NEWLINE() { return getToken(ResolutionParser.NEWLINE, 0); }
		public AnswerToMultipleChoiceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_answerToMultipleChoice; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ResolutionListener ) ((ResolutionListener)listener).enterAnswerToMultipleChoice(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ResolutionListener ) ((ResolutionListener)listener).exitAnswerToMultipleChoice(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ResolutionVisitor ) return ((ResolutionVisitor<? extends T>)visitor).visitAnswerToMultipleChoice(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnswerToMultipleChoiceContext answerToMultipleChoice() throws RecognitionException {
		AnswerToMultipleChoiceContext _localctx = new AnswerToMultipleChoiceContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_answerToMultipleChoice);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			match(ANSWER);
			setState(64);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CHAR) {
				{
				setState(63);
				match(CHAR);
				}
			}

			setState(67);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NEWLINE) {
				{
				setState(66);
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
	public static class AnswerToShortAnswerContext extends ParserRuleContext {
		public TerminalNode ANSWER() { return getToken(ResolutionParser.ANSWER, 0); }
		public PhraseContext phrase() {
			return getRuleContext(PhraseContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(ResolutionParser.NEWLINE, 0); }
		public AnswerToShortAnswerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_answerToShortAnswer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ResolutionListener ) ((ResolutionListener)listener).enterAnswerToShortAnswer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ResolutionListener ) ((ResolutionListener)listener).exitAnswerToShortAnswer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ResolutionVisitor ) return ((ResolutionVisitor<? extends T>)visitor).visitAnswerToShortAnswer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnswerToShortAnswerContext answerToShortAnswer() throws RecognitionException {
		AnswerToShortAnswerContext _localctx = new AnswerToShortAnswerContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_answerToShortAnswer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			match(ANSWER);
			setState(71);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NUMS || _la==WORD) {
				{
				setState(70);
				phrase();
				}
			}

			setState(74);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NEWLINE) {
				{
				setState(73);
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
	public static class AnswerToNumericalContext extends ParserRuleContext {
		public TerminalNode ANSWER() { return getToken(ResolutionParser.ANSWER, 0); }
		public TerminalNode FLOAT() { return getToken(ResolutionParser.FLOAT, 0); }
		public TerminalNode NEWLINE() { return getToken(ResolutionParser.NEWLINE, 0); }
		public AnswerToNumericalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_answerToNumerical; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ResolutionListener ) ((ResolutionListener)listener).enterAnswerToNumerical(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ResolutionListener ) ((ResolutionListener)listener).exitAnswerToNumerical(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ResolutionVisitor ) return ((ResolutionVisitor<? extends T>)visitor).visitAnswerToNumerical(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnswerToNumericalContext answerToNumerical() throws RecognitionException {
		AnswerToNumericalContext _localctx = new AnswerToNumericalContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_answerToNumerical);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			match(ANSWER);
			setState(78);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FLOAT) {
				{
				setState(77);
				match(FLOAT);
				}
			}

			setState(81);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NEWLINE) {
				{
				setState(80);
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
	public static class AnswerToSelectMissingWordsContext extends ParserRuleContext {
		public TerminalNode ANSWER() { return getToken(ResolutionParser.ANSWER, 0); }
		public PhraseContext phrase() {
			return getRuleContext(PhraseContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(ResolutionParser.NEWLINE, 0); }
		public AnswerToSelectMissingWordsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_answerToSelectMissingWords; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ResolutionListener ) ((ResolutionListener)listener).enterAnswerToSelectMissingWords(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ResolutionListener ) ((ResolutionListener)listener).exitAnswerToSelectMissingWords(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ResolutionVisitor ) return ((ResolutionVisitor<? extends T>)visitor).visitAnswerToSelectMissingWords(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnswerToSelectMissingWordsContext answerToSelectMissingWords() throws RecognitionException {
		AnswerToSelectMissingWordsContext _localctx = new AnswerToSelectMissingWordsContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_answerToSelectMissingWords);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			match(ANSWER);
			setState(85);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NUMS || _la==WORD) {
				{
				setState(84);
				phrase();
				}
			}

			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NEWLINE) {
				{
				setState(87);
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
	public static class AnswerToTrueFalseContext extends ParserRuleContext {
		public TerminalNode ANSWER() { return getToken(ResolutionParser.ANSWER, 0); }
		public TerminalNode TRUE_OR_FALSE() { return getToken(ResolutionParser.TRUE_OR_FALSE, 0); }
		public TerminalNode NEWLINE() { return getToken(ResolutionParser.NEWLINE, 0); }
		public AnswerToTrueFalseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_answerToTrueFalse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ResolutionListener ) ((ResolutionListener)listener).enterAnswerToTrueFalse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ResolutionListener ) ((ResolutionListener)listener).exitAnswerToTrueFalse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ResolutionVisitor ) return ((ResolutionVisitor<? extends T>)visitor).visitAnswerToTrueFalse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnswerToTrueFalseContext answerToTrueFalse() throws RecognitionException {
		AnswerToTrueFalseContext _localctx = new AnswerToTrueFalseContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_answerToTrueFalse);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			match(ANSWER);
			setState(92);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==TRUE_OR_FALSE) {
				{
				setState(91);
				match(TRUE_OR_FALSE);
				}
			}

			setState(95);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NEWLINE) {
				{
				setState(94);
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
	public static class PhraseContext extends ParserRuleContext {
		public List<WordsContext> words() {
			return getRuleContexts(WordsContext.class);
		}
		public WordsContext words(int i) {
			return getRuleContext(WordsContext.class,i);
		}
		public TerminalNode PUNCTUATION_MARKS() { return getToken(ResolutionParser.PUNCTUATION_MARKS, 0); }
		public PhraseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_phrase; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ResolutionListener ) ((ResolutionListener)listener).enterPhrase(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ResolutionListener ) ((ResolutionListener)listener).exitPhrase(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ResolutionVisitor ) return ((ResolutionVisitor<? extends T>)visitor).visitPhrase(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PhraseContext phrase() throws RecognitionException {
		PhraseContext _localctx = new PhraseContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_phrase);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(97);
				words();
				}
				}
				setState(100); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NUMS || _la==WORD );
			setState(103);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PUNCTUATION_MARKS) {
				{
				setState(102);
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
	public static class WordsContext extends ParserRuleContext {
		public TerminalNode WORD() { return getToken(ResolutionParser.WORD, 0); }
		public TerminalNode NUMS() { return getToken(ResolutionParser.NUMS, 0); }
		public WordsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_words; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ResolutionListener ) ((ResolutionListener)listener).enterWords(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ResolutionListener ) ((ResolutionListener)listener).exitWords(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ResolutionVisitor ) return ((ResolutionVisitor<? extends T>)visitor).visitWords(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WordsContext words() throws RecognitionException {
		WordsContext _localctx = new WordsContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_words);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
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
		"\u0004\u0001\u0017l\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0001"+
		"\u0000\u0004\u0000\u001a\b\u0000\u000b\u0000\f\u0000\u001b\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0004\u0001!\b\u0001\u000b\u0001\f\u0001\"\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003"+
		"\u0002+\b\u0002\u0001\u0003\u0001\u0003\u0005\u0003/\b\u0003\n\u0003\f"+
		"\u00032\t\u0003\u0001\u0003\u0003\u00035\b\u0003\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0003\u0004:\b\u0004\u0001\u0004\u0003\u0004=\b\u0004\u0001"+
		"\u0005\u0001\u0005\u0003\u0005A\b\u0005\u0001\u0005\u0003\u0005D\b\u0005"+
		"\u0001\u0006\u0001\u0006\u0003\u0006H\b\u0006\u0001\u0006\u0003\u0006"+
		"K\b\u0006\u0001\u0007\u0001\u0007\u0003\u0007O\b\u0007\u0001\u0007\u0003"+
		"\u0007R\b\u0007\u0001\b\u0001\b\u0003\bV\b\b\u0001\b\u0003\bY\b\b\u0001"+
		"\t\u0001\t\u0003\t]\b\t\u0001\t\u0003\t`\b\t\u0001\n\u0004\nc\b\n\u000b"+
		"\n\f\nd\u0001\n\u0003\nh\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0000"+
		"\u0000\f\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0000"+
		"\u0001\u0002\u0000\u0005\u0005\u0016\u0016v\u0000\u0019\u0001\u0000\u0000"+
		"\u0000\u0002\u001d\u0001\u0000\u0000\u0000\u0004*\u0001\u0000\u0000\u0000"+
		"\u0006,\u0001\u0000\u0000\u0000\b6\u0001\u0000\u0000\u0000\n>\u0001\u0000"+
		"\u0000\u0000\fE\u0001\u0000\u0000\u0000\u000eL\u0001\u0000\u0000\u0000"+
		"\u0010S\u0001\u0000\u0000\u0000\u0012Z\u0001\u0000\u0000\u0000\u0014b"+
		"\u0001\u0000\u0000\u0000\u0016i\u0001\u0000\u0000\u0000\u0018\u001a\u0003"+
		"\u0002\u0001\u0000\u0019\u0018\u0001\u0000\u0000\u0000\u001a\u001b\u0001"+
		"\u0000\u0000\u0000\u001b\u0019\u0001\u0000\u0000\u0000\u001b\u001c\u0001"+
		"\u0000\u0000\u0000\u001c\u0001\u0001\u0000\u0000\u0000\u001d\u001e\u0005"+
		"\u0003\u0000\u0000\u001e \u0005\b\u0000\u0000\u001f!\u0003\u0004\u0002"+
		"\u0000 \u001f\u0001\u0000\u0000\u0000!\"\u0001\u0000\u0000\u0000\" \u0001"+
		"\u0000\u0000\u0000\"#\u0001\u0000\u0000\u0000#\u0003\u0001\u0000\u0000"+
		"\u0000$+\u0003\u0006\u0003\u0000%+\u0003\n\u0005\u0000&+\u0003\f\u0006"+
		"\u0000\'+\u0003\u000e\u0007\u0000(+\u0003\u0010\b\u0000)+\u0003\u0012"+
		"\t\u0000*$\u0001\u0000\u0000\u0000*%\u0001\u0000\u0000\u0000*&\u0001\u0000"+
		"\u0000\u0000*\'\u0001\u0000\u0000\u0000*(\u0001\u0000\u0000\u0000*)\u0001"+
		"\u0000\u0000\u0000+\u0005\u0001\u0000\u0000\u0000,0\u0005\u0004\u0000"+
		"\u0000-/\u0003\b\u0004\u0000.-\u0001\u0000\u0000\u0000/2\u0001\u0000\u0000"+
		"\u00000.\u0001\u0000\u0000\u000001\u0001\u0000\u0000\u000014\u0001\u0000"+
		"\u0000\u000020\u0001\u0000\u0000\u000035\u0005\b\u0000\u000043\u0001\u0000"+
		"\u0000\u000045\u0001\u0000\u0000\u00005\u0007\u0001\u0000\u0000\u0000"+
		"67\u0005\u0014\u0000\u00007<\u0005\f\u0000\u00008:\u0005\u000e\u0000\u0000"+
		"98\u0001\u0000\u0000\u00009:\u0001\u0000\u0000\u0000:=\u0001\u0000\u0000"+
		"\u0000;=\u0005\u0015\u0000\u0000<9\u0001\u0000\u0000\u0000<;\u0001\u0000"+
		"\u0000\u0000=\t\u0001\u0000\u0000\u0000>@\u0005\u0004\u0000\u0000?A\u0005"+
		"\u0006\u0000\u0000@?\u0001\u0000\u0000\u0000@A\u0001\u0000\u0000\u0000"+
		"AC\u0001\u0000\u0000\u0000BD\u0005\b\u0000\u0000CB\u0001\u0000\u0000\u0000"+
		"CD\u0001\u0000\u0000\u0000D\u000b\u0001\u0000\u0000\u0000EG\u0005\u0004"+
		"\u0000\u0000FH\u0003\u0014\n\u0000GF\u0001\u0000\u0000\u0000GH\u0001\u0000"+
		"\u0000\u0000HJ\u0001\u0000\u0000\u0000IK\u0005\b\u0000\u0000JI\u0001\u0000"+
		"\u0000\u0000JK\u0001\u0000\u0000\u0000K\r\u0001\u0000\u0000\u0000LN\u0005"+
		"\u0004\u0000\u0000MO\u0005\u0013\u0000\u0000NM\u0001\u0000\u0000\u0000"+
		"NO\u0001\u0000\u0000\u0000OQ\u0001\u0000\u0000\u0000PR\u0005\b\u0000\u0000"+
		"QP\u0001\u0000\u0000\u0000QR\u0001\u0000\u0000\u0000R\u000f\u0001\u0000"+
		"\u0000\u0000SU\u0005\u0004\u0000\u0000TV\u0003\u0014\n\u0000UT\u0001\u0000"+
		"\u0000\u0000UV\u0001\u0000\u0000\u0000VX\u0001\u0000\u0000\u0000WY\u0005"+
		"\b\u0000\u0000XW\u0001\u0000\u0000\u0000XY\u0001\u0000\u0000\u0000Y\u0011"+
		"\u0001\u0000\u0000\u0000Z\\\u0005\u0004\u0000\u0000[]\u0005\u0001\u0000"+
		"\u0000\\[\u0001\u0000\u0000\u0000\\]\u0001\u0000\u0000\u0000]_\u0001\u0000"+
		"\u0000\u0000^`\u0005\b\u0000\u0000_^\u0001\u0000\u0000\u0000_`\u0001\u0000"+
		"\u0000\u0000`\u0013\u0001\u0000\u0000\u0000ac\u0003\u0016\u000b\u0000"+
		"ba\u0001\u0000\u0000\u0000cd\u0001\u0000\u0000\u0000db\u0001\u0000\u0000"+
		"\u0000de\u0001\u0000\u0000\u0000eg\u0001\u0000\u0000\u0000fh\u0005\n\u0000"+
		"\u0000gf\u0001\u0000\u0000\u0000gh\u0001\u0000\u0000\u0000h\u0015\u0001"+
		"\u0000\u0000\u0000ij\u0007\u0000\u0000\u0000j\u0017\u0001\u0000\u0000"+
		"\u0000\u0013\u001b\"*049<@CGJNQUX\\_dg";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}