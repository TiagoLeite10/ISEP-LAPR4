// Generated from C:/Users/Gabriel/Desktop/Escola/Engenharia Informática/2º Ano/2º Semetre/Projeto Integrador/sem4pi-22-23-20/antlr4\Resolution.g4 by ANTLR 4.12.0

package eapli.base.takenexammanagement.lprog;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class ResolutionLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		TRUE_OR_FALSE=1, IDENTIFIER=2, SECTION=3, ANSWER=4, NUMS=5, CHAR=6, NUM=7, 
		NEWLINE=8, SPECIAL_CHAR=9, PUNCTUATION_MARKS=10, DOT=11, DASH=12, SLASH=13, 
		SPACE=14, COMMA=15, TWO_DOTS=16, UNDERSCORE=17, RIGHT_PARENTHESES=18, 
		FLOAT=19, NUMERICAL_OPTION=20, ALPHABETICAL_OPTION=21, WORD=22, WS=23;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"TRUE_OR_FALSE", "IDENTIFIER", "SECTION", "ANSWER", "NUMS", "CHAR", "NUM", 
			"NEWLINE", "SPECIAL_CHAR", "PUNCTUATION_MARKS", "DOT", "DASH", "SLASH", 
			"SPACE", "COMMA", "TWO_DOTS", "UNDERSCORE", "RIGHT_PARENTHESES", "FLOAT", 
			"NUMERICAL_OPTION", "ALPHABETICAL_OPTION", "WORD", "WS"
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


	public ResolutionLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Resolution.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u0017\u0091\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0003\u00009\b\u0000\u0001\u0001\u0001\u0001\u0003\u0001"+
		"=\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0004\u0004\u0004S\b\u0004\u000b\u0004"+
		"\f\u0004T\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0007"+
		"\u0004\u0007\\\b\u0007\u000b\u0007\f\u0007]\u0001\b\u0001\b\u0001\t\u0001"+
		"\t\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\r\u0001"+
		"\r\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010"+
		"\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0013\u0001\u0013\u0003\u0013z\b\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u0081\b\u0014\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0004\u0015\u0087\b\u0015\u000b\u0015"+
		"\f\u0015\u0088\u0001\u0016\u0004\u0016\u008c\b\u0016\u000b\u0016\f\u0016"+
		"\u008d\u0001\u0016\u0001\u0016\u0000\u0000\u0017\u0001\u0001\u0003\u0002"+
		"\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013"+
		"\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011"+
		"#\u0012%\u0013\'\u0014)\u0015+\u0016-\u0017\u0001\u0000\b\u0002\u0000"+
		"AZaz\u0001\u000009\u0002\u0000\n\n\r\r\u0005\u0000\"\"%%\'\'\u00ba\u00ba"+
		"\u00c2\u00c2\u0003\u0000!!..??\u0001\u000019\u0001\u0000az\u0003\u0000"+
		"\t\n\r\r  \u009b\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001"+
		"\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001"+
		"\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000"+
		"\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000"+
		"\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000"+
		"\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000"+
		"\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000"+
		"\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000"+
		"\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000"+
		"%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000)\u0001"+
		"\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000\u0000-\u0001\u0000\u0000"+
		"\u0000\u00018\u0001\u0000\u0000\u0000\u0003:\u0001\u0000\u0000\u0000\u0005"+
		">\u0001\u0000\u0000\u0000\u0007H\u0001\u0000\u0000\u0000\tR\u0001\u0000"+
		"\u0000\u0000\u000bV\u0001\u0000\u0000\u0000\rX\u0001\u0000\u0000\u0000"+
		"\u000f[\u0001\u0000\u0000\u0000\u0011_\u0001\u0000\u0000\u0000\u0013a"+
		"\u0001\u0000\u0000\u0000\u0015c\u0001\u0000\u0000\u0000\u0017e\u0001\u0000"+
		"\u0000\u0000\u0019g\u0001\u0000\u0000\u0000\u001bi\u0001\u0000\u0000\u0000"+
		"\u001dk\u0001\u0000\u0000\u0000\u001fm\u0001\u0000\u0000\u0000!o\u0001"+
		"\u0000\u0000\u0000#q\u0001\u0000\u0000\u0000%s\u0001\u0000\u0000\u0000"+
		"\'w\u0001\u0000\u0000\u0000)}\u0001\u0000\u0000\u0000+\u0086\u0001\u0000"+
		"\u0000\u0000-\u008b\u0001\u0000\u0000\u0000/0\u0005t\u0000\u000001\u0005"+
		"r\u0000\u000012\u0005u\u0000\u000029\u0005e\u0000\u000034\u0005f\u0000"+
		"\u000045\u0005a\u0000\u000056\u0005l\u0000\u000067\u0005s\u0000\u0000"+
		"79\u0005e\u0000\u00008/\u0001\u0000\u0000\u000083\u0001\u0000\u0000\u0000"+
		"9\u0002\u0001\u0000\u0000\u0000:<\u0003\u001f\u000f\u0000;=\u0003\u001b"+
		"\r\u0000<;\u0001\u0000\u0000\u0000<=\u0001\u0000\u0000\u0000=\u0004\u0001"+
		"\u0000\u0000\u0000>?\u0005S\u0000\u0000?@\u0005e\u0000\u0000@A\u0005c"+
		"\u0000\u0000AB\u0005t\u0000\u0000BC\u0005i\u0000\u0000CD\u0005o\u0000"+
		"\u0000DE\u0005n\u0000\u0000EF\u0001\u0000\u0000\u0000FG\u0003\u0003\u0001"+
		"\u0000G\u0006\u0001\u0000\u0000\u0000HI\u0005A\u0000\u0000IJ\u0005n\u0000"+
		"\u0000JK\u0005s\u0000\u0000KL\u0005w\u0000\u0000LM\u0005e\u0000\u0000"+
		"MN\u0005r\u0000\u0000NO\u0001\u0000\u0000\u0000OP\u0003\u0003\u0001\u0000"+
		"P\b\u0001\u0000\u0000\u0000QS\u0003\r\u0006\u0000RQ\u0001\u0000\u0000"+
		"\u0000ST\u0001\u0000\u0000\u0000TR\u0001\u0000\u0000\u0000TU\u0001\u0000"+
		"\u0000\u0000U\n\u0001\u0000\u0000\u0000VW\u0007\u0000\u0000\u0000W\f\u0001"+
		"\u0000\u0000\u0000XY\u0007\u0001\u0000\u0000Y\u000e\u0001\u0000\u0000"+
		"\u0000Z\\\u0007\u0002\u0000\u0000[Z\u0001\u0000\u0000\u0000\\]\u0001\u0000"+
		"\u0000\u0000][\u0001\u0000\u0000\u0000]^\u0001\u0000\u0000\u0000^\u0010"+
		"\u0001\u0000\u0000\u0000_`\u0007\u0003\u0000\u0000`\u0012\u0001\u0000"+
		"\u0000\u0000ab\u0007\u0004\u0000\u0000b\u0014\u0001\u0000\u0000\u0000"+
		"cd\u0005.\u0000\u0000d\u0016\u0001\u0000\u0000\u0000ef\u0005-\u0000\u0000"+
		"f\u0018\u0001\u0000\u0000\u0000gh\u0005/\u0000\u0000h\u001a\u0001\u0000"+
		"\u0000\u0000ij\u0005 \u0000\u0000j\u001c\u0001\u0000\u0000\u0000kl\u0005"+
		",\u0000\u0000l\u001e\u0001\u0000\u0000\u0000mn\u0005:\u0000\u0000n \u0001"+
		"\u0000\u0000\u0000op\u0005_\u0000\u0000p\"\u0001\u0000\u0000\u0000qr\u0005"+
		")\u0000\u0000r$\u0001\u0000\u0000\u0000st\u0003\t\u0004\u0000tu\u0003"+
		"\u0015\n\u0000uv\u0003\t\u0004\u0000v&\u0001\u0000\u0000\u0000wy\u0007"+
		"\u0005\u0000\u0000xz\u0003\r\u0006\u0000yx\u0001\u0000\u0000\u0000yz\u0001"+
		"\u0000\u0000\u0000z{\u0001\u0000\u0000\u0000{|\u0003#\u0011\u0000|(\u0001"+
		"\u0000\u0000\u0000}~\u0007\u0006\u0000\u0000~\u0080\u0003#\u0011\u0000"+
		"\u007f\u0081\u0003\u001b\r\u0000\u0080\u007f\u0001\u0000\u0000\u0000\u0080"+
		"\u0081\u0001\u0000\u0000\u0000\u0081*\u0001\u0000\u0000\u0000\u0082\u0087"+
		"\u0003\u000b\u0005\u0000\u0083\u0087\u0003\u0011\b\u0000\u0084\u0087\u0003"+
		"\u001b\r\u0000\u0085\u0087\u0003\u001d\u000e\u0000\u0086\u0082\u0001\u0000"+
		"\u0000\u0000\u0086\u0083\u0001\u0000\u0000\u0000\u0086\u0084\u0001\u0000"+
		"\u0000\u0000\u0086\u0085\u0001\u0000\u0000\u0000\u0087\u0088\u0001\u0000"+
		"\u0000\u0000\u0088\u0086\u0001\u0000\u0000\u0000\u0088\u0089\u0001\u0000"+
		"\u0000\u0000\u0089,\u0001\u0000\u0000\u0000\u008a\u008c\u0007\u0007\u0000"+
		"\u0000\u008b\u008a\u0001\u0000\u0000\u0000\u008c\u008d\u0001\u0000\u0000"+
		"\u0000\u008d\u008b\u0001\u0000\u0000\u0000\u008d\u008e\u0001\u0000\u0000"+
		"\u0000\u008e\u008f\u0001\u0000\u0000\u0000\u008f\u0090\u0006\u0016\u0000"+
		"\u0000\u0090.\u0001\u0000\u0000\u0000\n\u00008<T]y\u0080\u0086\u0088\u008d"+
		"\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}