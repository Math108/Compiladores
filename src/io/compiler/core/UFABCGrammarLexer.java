// Generated from UFABCGrammar.g4 by ANTLR 4.13.2
package io.compiler.core;

	import java.util.ArrayList;
	import java.util.Stack;
	import java.util.HashMap;
	import io.compiler.types.*;
	import io.compiler.core.exceptions.*;
	import io.compiler.core.ast.*;
   import io.compiler.runtime.*;


import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class UFABCGrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		OP_SUM=18, OPLOG=19, OPNOT=20, OP_SUB=21, OP_MUL=22, OP_DIV=23, OP_AT=24, 
		OPREL=25, ID=26, NUM=27, VIRG=28, PV=29, AP=30, FP=31, DP=32, TEXTO=33, 
		WS=34;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
			"OP_SUM", "OPLOG", "OPNOT", "OP_SUB", "OP_MUL", "OP_DIV", "OP_AT", "OPREL", 
			"ID", "NUM", "VIRG", "PV", "AP", "FP", "DP", "TEXTO", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'programa'", "'inicio'", "'fim'", "'fimprog'", "'declare'", "'number'", 
			"'text'", "'real number'", "'se'", "'entao'", "'senao'", "'fimse'", "'enquanto'", 
			"'faca'", "'fimWhile'", "'leia'", "'escreva'", "'+'", null, "'!'", "'-'", 
			"'*'", "'/'", "':='", null, null, null, "','", "';'", "'('", "')'", "':'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, "OP_SUM", "OPLOG", "OPNOT", "OP_SUB", 
			"OP_MUL", "OP_DIV", "OP_AT", "OPREL", "ID", "NUM", "VIRG", "PV", "AP", 
			"FP", "DP", "TEXTO", "WS"
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


	    private HashMap<String,Var> symbolTable = new HashMap<String, Var>();
	    private ArrayList<Var> currentDecl = new ArrayList<Var>();
	    private Types currentType;
	    private Types leftType=null, rightType=null;
	    private Program program = new Program();
	    private String strExpr = "";
	    private IfCommand currentIfCommand;
	    private WhileCommand currentWhileCommand;
	    private DoWhileCommand currentDoWhileCommand;
	    
	    private Stack<ArrayList<Command>> stack = new Stack<ArrayList<Command>>();

	    private Stack<AbstractExpression> evalStack = new Stack<AbstractExpression>();
		 private AbstractExpression topo = null;
		
	    public double generateValue(){
	       if (topo == null){
	          if (!(evalStack.isEmpty())) {
	          topo = evalStack.pop();
	          }
	       }
	       return topo.evaluate();
	    }
	    
	    public void updateType(){
	    	for(Var v: currentDecl){
	    	   v.setType(currentType);
	    	   symbolTable.put(v.getId(), v);
	    	}
	    }
	    public void exibirVar(){
	        for (String id: symbolTable.keySet()){
	        	System.out.println(symbolTable.get(id));
	        }
	    }
	    
	    public Program getProgram(){
	    	return this.program;
	    	}
	    
	    public boolean isDeclared(String id){
	    	return symbolTable.get(id) != null;
	    }


	public UFABCGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "UFABCGrammar.g4"; }

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
		"\u0004\u0000\"\u0107\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002"+
		"\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002"+
		"\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002"+
		"\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002"+
		"\u001e\u0007\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007"+
		"!\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0003"+
		"\u0012\u00c1\b\u0012\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001"+
		"\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0003\u0018\u00d8"+
		"\b\u0018\u0001\u0019\u0001\u0019\u0005\u0019\u00dc\b\u0019\n\u0019\f\u0019"+
		"\u00df\t\u0019\u0001\u001a\u0003\u001a\u00e2\b\u001a\u0001\u001a\u0004"+
		"\u001a\u00e5\b\u001a\u000b\u001a\f\u001a\u00e6\u0001\u001a\u0001\u001a"+
		"\u0004\u001a\u00eb\b\u001a\u000b\u001a\f\u001a\u00ec\u0003\u001a\u00ef"+
		"\b\u001a\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0001\u001d\u0001"+
		"\u001d\u0001\u001e\u0001\u001e\u0001\u001f\u0001\u001f\u0001 \u0001 \u0005"+
		" \u00fd\b \n \f \u0100\t \u0001 \u0001 \u0001!\u0001!\u0001!\u0001!\u0000"+
		"\u0000\"\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b"+
		"\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b"+
		"\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014)\u0015+\u0016"+
		"-\u0017/\u00181\u00193\u001a5\u001b7\u001c9\u001d;\u001e=\u001f? A!C\""+
		"\u0001\u0000\u0006\u0002\u0000<<>>\u0001\u0000az\u0003\u000009AZaz\u0001"+
		"\u000009\u0005\u0000  ,.09AZaz\u0003\u0000\t\n\r\r  \u0111\u0000\u0001"+
		"\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005"+
		"\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001"+
		"\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000"+
		"\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000"+
		"\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000"+
		"\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000"+
		"\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000"+
		"\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000"+
		"\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000"+
		"\'\u0001\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001"+
		"\u0000\u0000\u0000\u0000-\u0001\u0000\u0000\u0000\u0000/\u0001\u0000\u0000"+
		"\u0000\u00001\u0001\u0000\u0000\u0000\u00003\u0001\u0000\u0000\u0000\u0000"+
		"5\u0001\u0000\u0000\u0000\u00007\u0001\u0000\u0000\u0000\u00009\u0001"+
		"\u0000\u0000\u0000\u0000;\u0001\u0000\u0000\u0000\u0000=\u0001\u0000\u0000"+
		"\u0000\u0000?\u0001\u0000\u0000\u0000\u0000A\u0001\u0000\u0000\u0000\u0000"+
		"C\u0001\u0000\u0000\u0000\u0001E\u0001\u0000\u0000\u0000\u0003N\u0001"+
		"\u0000\u0000\u0000\u0005U\u0001\u0000\u0000\u0000\u0007Y\u0001\u0000\u0000"+
		"\u0000\ta\u0001\u0000\u0000\u0000\u000bi\u0001\u0000\u0000\u0000\rp\u0001"+
		"\u0000\u0000\u0000\u000fu\u0001\u0000\u0000\u0000\u0011\u0081\u0001\u0000"+
		"\u0000\u0000\u0013\u0084\u0001\u0000\u0000\u0000\u0015\u008a\u0001\u0000"+
		"\u0000\u0000\u0017\u0090\u0001\u0000\u0000\u0000\u0019\u0096\u0001\u0000"+
		"\u0000\u0000\u001b\u009f\u0001\u0000\u0000\u0000\u001d\u00a4\u0001\u0000"+
		"\u0000\u0000\u001f\u00ad\u0001\u0000\u0000\u0000!\u00b2\u0001\u0000\u0000"+
		"\u0000#\u00ba\u0001\u0000\u0000\u0000%\u00c0\u0001\u0000\u0000\u0000\'"+
		"\u00c2\u0001\u0000\u0000\u0000)\u00c4\u0001\u0000\u0000\u0000+\u00c6\u0001"+
		"\u0000\u0000\u0000-\u00c8\u0001\u0000\u0000\u0000/\u00ca\u0001\u0000\u0000"+
		"\u00001\u00d7\u0001\u0000\u0000\u00003\u00d9\u0001\u0000\u0000\u00005"+
		"\u00e1\u0001\u0000\u0000\u00007\u00f0\u0001\u0000\u0000\u00009\u00f2\u0001"+
		"\u0000\u0000\u0000;\u00f4\u0001\u0000\u0000\u0000=\u00f6\u0001\u0000\u0000"+
		"\u0000?\u00f8\u0001\u0000\u0000\u0000A\u00fa\u0001\u0000\u0000\u0000C"+
		"\u0103\u0001\u0000\u0000\u0000EF\u0005p\u0000\u0000FG\u0005r\u0000\u0000"+
		"GH\u0005o\u0000\u0000HI\u0005g\u0000\u0000IJ\u0005r\u0000\u0000JK\u0005"+
		"a\u0000\u0000KL\u0005m\u0000\u0000LM\u0005a\u0000\u0000M\u0002\u0001\u0000"+
		"\u0000\u0000NO\u0005i\u0000\u0000OP\u0005n\u0000\u0000PQ\u0005i\u0000"+
		"\u0000QR\u0005c\u0000\u0000RS\u0005i\u0000\u0000ST\u0005o\u0000\u0000"+
		"T\u0004\u0001\u0000\u0000\u0000UV\u0005f\u0000\u0000VW\u0005i\u0000\u0000"+
		"WX\u0005m\u0000\u0000X\u0006\u0001\u0000\u0000\u0000YZ\u0005f\u0000\u0000"+
		"Z[\u0005i\u0000\u0000[\\\u0005m\u0000\u0000\\]\u0005p\u0000\u0000]^\u0005"+
		"r\u0000\u0000^_\u0005o\u0000\u0000_`\u0005g\u0000\u0000`\b\u0001\u0000"+
		"\u0000\u0000ab\u0005d\u0000\u0000bc\u0005e\u0000\u0000cd\u0005c\u0000"+
		"\u0000de\u0005l\u0000\u0000ef\u0005a\u0000\u0000fg\u0005r\u0000\u0000"+
		"gh\u0005e\u0000\u0000h\n\u0001\u0000\u0000\u0000ij\u0005n\u0000\u0000"+
		"jk\u0005u\u0000\u0000kl\u0005m\u0000\u0000lm\u0005b\u0000\u0000mn\u0005"+
		"e\u0000\u0000no\u0005r\u0000\u0000o\f\u0001\u0000\u0000\u0000pq\u0005"+
		"t\u0000\u0000qr\u0005e\u0000\u0000rs\u0005x\u0000\u0000st\u0005t\u0000"+
		"\u0000t\u000e\u0001\u0000\u0000\u0000uv\u0005r\u0000\u0000vw\u0005e\u0000"+
		"\u0000wx\u0005a\u0000\u0000xy\u0005l\u0000\u0000yz\u0005 \u0000\u0000"+
		"z{\u0005n\u0000\u0000{|\u0005u\u0000\u0000|}\u0005m\u0000\u0000}~\u0005"+
		"b\u0000\u0000~\u007f\u0005e\u0000\u0000\u007f\u0080\u0005r\u0000\u0000"+
		"\u0080\u0010\u0001\u0000\u0000\u0000\u0081\u0082\u0005s\u0000\u0000\u0082"+
		"\u0083\u0005e\u0000\u0000\u0083\u0012\u0001\u0000\u0000\u0000\u0084\u0085"+
		"\u0005e\u0000\u0000\u0085\u0086\u0005n\u0000\u0000\u0086\u0087\u0005t"+
		"\u0000\u0000\u0087\u0088\u0005a\u0000\u0000\u0088\u0089\u0005o\u0000\u0000"+
		"\u0089\u0014\u0001\u0000\u0000\u0000\u008a\u008b\u0005s\u0000\u0000\u008b"+
		"\u008c\u0005e\u0000\u0000\u008c\u008d\u0005n\u0000\u0000\u008d\u008e\u0005"+
		"a\u0000\u0000\u008e\u008f\u0005o\u0000\u0000\u008f\u0016\u0001\u0000\u0000"+
		"\u0000\u0090\u0091\u0005f\u0000\u0000\u0091\u0092\u0005i\u0000\u0000\u0092"+
		"\u0093\u0005m\u0000\u0000\u0093\u0094\u0005s\u0000\u0000\u0094\u0095\u0005"+
		"e\u0000\u0000\u0095\u0018\u0001\u0000\u0000\u0000\u0096\u0097\u0005e\u0000"+
		"\u0000\u0097\u0098\u0005n\u0000\u0000\u0098\u0099\u0005q\u0000\u0000\u0099"+
		"\u009a\u0005u\u0000\u0000\u009a\u009b\u0005a\u0000\u0000\u009b\u009c\u0005"+
		"n\u0000\u0000\u009c\u009d\u0005t\u0000\u0000\u009d\u009e\u0005o\u0000"+
		"\u0000\u009e\u001a\u0001\u0000\u0000\u0000\u009f\u00a0\u0005f\u0000\u0000"+
		"\u00a0\u00a1\u0005a\u0000\u0000\u00a1\u00a2\u0005c\u0000\u0000\u00a2\u00a3"+
		"\u0005a\u0000\u0000\u00a3\u001c\u0001\u0000\u0000\u0000\u00a4\u00a5\u0005"+
		"f\u0000\u0000\u00a5\u00a6\u0005i\u0000\u0000\u00a6\u00a7\u0005m\u0000"+
		"\u0000\u00a7\u00a8\u0005W\u0000\u0000\u00a8\u00a9\u0005h\u0000\u0000\u00a9"+
		"\u00aa\u0005i\u0000\u0000\u00aa\u00ab\u0005l\u0000\u0000\u00ab\u00ac\u0005"+
		"e\u0000\u0000\u00ac\u001e\u0001\u0000\u0000\u0000\u00ad\u00ae\u0005l\u0000"+
		"\u0000\u00ae\u00af\u0005e\u0000\u0000\u00af\u00b0\u0005i\u0000\u0000\u00b0"+
		"\u00b1\u0005a\u0000\u0000\u00b1 \u0001\u0000\u0000\u0000\u00b2\u00b3\u0005"+
		"e\u0000\u0000\u00b3\u00b4\u0005s\u0000\u0000\u00b4\u00b5\u0005c\u0000"+
		"\u0000\u00b5\u00b6\u0005r\u0000\u0000\u00b6\u00b7\u0005e\u0000\u0000\u00b7"+
		"\u00b8\u0005v\u0000\u0000\u00b8\u00b9\u0005a\u0000\u0000\u00b9\"\u0001"+
		"\u0000\u0000\u0000\u00ba\u00bb\u0005+\u0000\u0000\u00bb$\u0001\u0000\u0000"+
		"\u0000\u00bc\u00bd\u0005|\u0000\u0000\u00bd\u00c1\u0005|\u0000\u0000\u00be"+
		"\u00bf\u0005&\u0000\u0000\u00bf\u00c1\u0005&\u0000\u0000\u00c0\u00bc\u0001"+
		"\u0000\u0000\u0000\u00c0\u00be\u0001\u0000\u0000\u0000\u00c1&\u0001\u0000"+
		"\u0000\u0000\u00c2\u00c3\u0005!\u0000\u0000\u00c3(\u0001\u0000\u0000\u0000"+
		"\u00c4\u00c5\u0005-\u0000\u0000\u00c5*\u0001\u0000\u0000\u0000\u00c6\u00c7"+
		"\u0005*\u0000\u0000\u00c7,\u0001\u0000\u0000\u0000\u00c8\u00c9\u0005/"+
		"\u0000\u0000\u00c9.\u0001\u0000\u0000\u0000\u00ca\u00cb\u0005:\u0000\u0000"+
		"\u00cb\u00cc\u0005=\u0000\u0000\u00cc0\u0001\u0000\u0000\u0000\u00cd\u00d8"+
		"\u0007\u0000\u0000\u0000\u00ce\u00cf\u0005>\u0000\u0000\u00cf\u00d8\u0005"+
		"=\u0000\u0000\u00d0\u00d1\u0005<\u0000\u0000\u00d1\u00d2\u0005=\u0000"+
		"\u0000\u00d2\u00d8\u0005 \u0000\u0000\u00d3\u00d4\u0005<\u0000\u0000\u00d4"+
		"\u00d8\u0005>\u0000\u0000\u00d5\u00d6\u0005=\u0000\u0000\u00d6\u00d8\u0005"+
		"=\u0000\u0000\u00d7\u00cd\u0001\u0000\u0000\u0000\u00d7\u00ce\u0001\u0000"+
		"\u0000\u0000\u00d7\u00d0\u0001\u0000\u0000\u0000\u00d7\u00d3\u0001\u0000"+
		"\u0000\u0000\u00d7\u00d5\u0001\u0000\u0000\u0000\u00d82\u0001\u0000\u0000"+
		"\u0000\u00d9\u00dd\u0007\u0001\u0000\u0000\u00da\u00dc\u0007\u0002\u0000"+
		"\u0000\u00db\u00da\u0001\u0000\u0000\u0000\u00dc\u00df\u0001\u0000\u0000"+
		"\u0000\u00dd\u00db\u0001\u0000\u0000\u0000\u00dd\u00de\u0001\u0000\u0000"+
		"\u0000\u00de4\u0001\u0000\u0000\u0000\u00df\u00dd\u0001\u0000\u0000\u0000"+
		"\u00e0\u00e2\u0005-\u0000\u0000\u00e1\u00e0\u0001\u0000\u0000\u0000\u00e1"+
		"\u00e2\u0001\u0000\u0000\u0000\u00e2\u00e4\u0001\u0000\u0000\u0000\u00e3"+
		"\u00e5\u0007\u0003\u0000\u0000\u00e4\u00e3\u0001\u0000\u0000\u0000\u00e5"+
		"\u00e6\u0001\u0000\u0000\u0000\u00e6\u00e4\u0001\u0000\u0000\u0000\u00e6"+
		"\u00e7\u0001\u0000\u0000\u0000\u00e7\u00ee\u0001\u0000\u0000\u0000\u00e8"+
		"\u00ea\u0005.\u0000\u0000\u00e9\u00eb\u0007\u0003\u0000\u0000\u00ea\u00e9"+
		"\u0001\u0000\u0000\u0000\u00eb\u00ec\u0001\u0000\u0000\u0000\u00ec\u00ea"+
		"\u0001\u0000\u0000\u0000\u00ec\u00ed\u0001\u0000\u0000\u0000\u00ed\u00ef"+
		"\u0001\u0000\u0000\u0000\u00ee\u00e8\u0001\u0000\u0000\u0000\u00ee\u00ef"+
		"\u0001\u0000\u0000\u0000\u00ef6\u0001\u0000\u0000\u0000\u00f0\u00f1\u0005"+
		",\u0000\u0000\u00f18\u0001\u0000\u0000\u0000\u00f2\u00f3\u0005;\u0000"+
		"\u0000\u00f3:\u0001\u0000\u0000\u0000\u00f4\u00f5\u0005(\u0000\u0000\u00f5"+
		"<\u0001\u0000\u0000\u0000\u00f6\u00f7\u0005)\u0000\u0000\u00f7>\u0001"+
		"\u0000\u0000\u0000\u00f8\u00f9\u0005:\u0000\u0000\u00f9@\u0001\u0000\u0000"+
		"\u0000\u00fa\u00fe\u0005\"\u0000\u0000\u00fb\u00fd\u0007\u0004\u0000\u0000"+
		"\u00fc\u00fb\u0001\u0000\u0000\u0000\u00fd\u0100\u0001\u0000\u0000\u0000"+
		"\u00fe\u00fc\u0001\u0000\u0000\u0000\u00fe\u00ff\u0001\u0000\u0000\u0000"+
		"\u00ff\u0101\u0001\u0000\u0000\u0000\u0100\u00fe\u0001\u0000\u0000\u0000"+
		"\u0101\u0102\u0005\"\u0000\u0000\u0102B\u0001\u0000\u0000\u0000\u0103"+
		"\u0104\u0007\u0005\u0000\u0000\u0104\u0105\u0001\u0000\u0000\u0000\u0105"+
		"\u0106\u0006!\u0000\u0000\u0106D\u0001\u0000\u0000\u0000\u000b\u0000\u00c0"+
		"\u00d7\u00db\u00dd\u00e1\u00e6\u00ec\u00ee\u00fc\u00fe\u0001\u0006\u0000"+
		"\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}