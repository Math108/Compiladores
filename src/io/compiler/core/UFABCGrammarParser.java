// Generated from UFABCGrammar.g4 by ANTLR 4.13.2
package io.compiler.core;

	import java.util.ArrayList;
	import java.util.Stack;
	import java.util.HashMap;
	import io.compiler.types.*;
	import io.compiler.core.exceptions.*;
	import io.compiler.core.ast.*;
   import io.compiler.runtime.*;


import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class UFABCGrammarParser extends Parser {
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
	public static final int
		RULE_programa = 0, RULE_declaravar = 1, RULE_comando = 2, RULE_cmdIF = 3, 
		RULE_cmdWhile = 4, RULE_cmdDoWhile = 5, RULE_cmdAttrib = 6, RULE_cmdLeitura = 7, 
		RULE_cmdEscrita = 8, RULE_exprLog = 9, RULE_exprRel = 10, RULE_expr = 11, 
		RULE_exprl = 12, RULE_termo = 13;
	private static String[] makeRuleNames() {
		return new String[] {
			"programa", "declaravar", "comando", "cmdIF", "cmdWhile", "cmdDoWhile", 
			"cmdAttrib", "cmdLeitura", "cmdEscrita", "exprLog", "exprRel", "expr", 
			"exprl", "termo"
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

	@Override
	public String getGrammarFileName() { return "UFABCGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


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

	public UFABCGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramaContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(UFABCGrammarParser.ID, 0); }
		public List<DeclaravarContext> declaravar() {
			return getRuleContexts(DeclaravarContext.class);
		}
		public DeclaravarContext declaravar(int i) {
			return getRuleContext(DeclaravarContext.class,i);
		}
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
		public ProgramaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programa; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).enterPrograma(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).exitPrograma(this);
		}
	}

	public final ProgramaContext programa() throws RecognitionException {
		ProgramaContext _localctx = new ProgramaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_programa);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			match(T__0);
			setState(29);
			match(ID);
			 program.setName(_input.LT(-1).getText());
			                               stack.push(new ArrayList<Command>()); 
			                             
			setState(32); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(31);
				declaravar();
				}
				}
				setState(34); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__4 );
			setState(36);
			match(T__1);
			setState(38); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(37);
				comando();
				}
				}
				setState(40); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 67330560L) != 0) );
			setState(42);
			match(T__2);
			setState(43);
			match(T__3);

			                  program.setSymbolTable(symbolTable);
			                  program.setCommandList(stack.pop());

			                  for (Var var : symbolTable.values()) {
			                     if (!var.isUtilized()) {
			                        System.out.println("WARNING: Variable " + var.getId() + " was declared but never used");
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
	public static class DeclaravarContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(UFABCGrammarParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(UFABCGrammarParser.ID, i);
		}
		public TerminalNode DP() { return getToken(UFABCGrammarParser.DP, 0); }
		public TerminalNode PV() { return getToken(UFABCGrammarParser.PV, 0); }
		public List<TerminalNode> VIRG() { return getTokens(UFABCGrammarParser.VIRG); }
		public TerminalNode VIRG(int i) {
			return getToken(UFABCGrammarParser.VIRG, i);
		}
		public DeclaravarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaravar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).enterDeclaravar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).exitDeclaravar(this);
		}
	}

	public final DeclaravarContext declaravar() throws RecognitionException {
		DeclaravarContext _localctx = new DeclaravarContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declaravar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			match(T__4);
			 currentDecl.clear(); 
			setState(48);
			match(ID);
			 
			                  String varId = _input.LT(-1).getText();
			                  if (isDeclared(varId)) {
			                     throw new UFABCSemanticException("Variable already declared: " + varId);
			                  }
			                  currentDecl.add(new Var(_input.LT(-1).getText()));
			               
			setState(55);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIRG) {
				{
				{
				setState(50);
				match(VIRG);
				setState(51);
				match(ID);
				 String varId2 = _input.LT(-1).getText();
				                     if (isDeclared(varId2) || currentDecl.stream().anyMatch(v->v.getId().equals(varId2))) {
				                        throw new UFABCSemanticException("Variable already declared: " + varId2);
				                     }
				                     currentDecl.add(new Var(_input.LT(-1).getText()));
				}
				}
				setState(57);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(58);
			match(DP);
			setState(65);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				{
				setState(59);
				match(T__5);
				currentType = Types.NUMBER;
				}
				break;
			case T__6:
				{
				setState(61);
				match(T__6);
				currentType = Types.TEXT;
				}
				break;
			case T__7:
				{
				setState(63);
				match(T__7);
				currentType = Types.REALNUMBER;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			 updateType(); 
			setState(68);
			match(PV);
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
	public static class ComandoContext extends ParserRuleContext {
		public CmdAttribContext cmdAttrib() {
			return getRuleContext(CmdAttribContext.class,0);
		}
		public CmdLeituraContext cmdLeitura() {
			return getRuleContext(CmdLeituraContext.class,0);
		}
		public CmdEscritaContext cmdEscrita() {
			return getRuleContext(CmdEscritaContext.class,0);
		}
		public CmdIFContext cmdIF() {
			return getRuleContext(CmdIFContext.class,0);
		}
		public CmdWhileContext cmdWhile() {
			return getRuleContext(CmdWhileContext.class,0);
		}
		public CmdDoWhileContext cmdDoWhile() {
			return getRuleContext(CmdDoWhileContext.class,0);
		}
		public ComandoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comando; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).enterComando(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).exitComando(this);
		}
	}

	public final ComandoContext comando() throws RecognitionException {
		ComandoContext _localctx = new ComandoContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_comando);
		try {
			setState(76);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(70);
				cmdAttrib();
				}
				break;
			case T__15:
				enterOuterAlt(_localctx, 2);
				{
				setState(71);
				cmdLeitura();
				}
				break;
			case T__16:
				enterOuterAlt(_localctx, 3);
				{
				setState(72);
				cmdEscrita();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 4);
				{
				setState(73);
				cmdIF();
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 5);
				{
				setState(74);
				cmdWhile();
				}
				break;
			case T__13:
				enterOuterAlt(_localctx, 6);
				{
				setState(75);
				cmdDoWhile();
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
	public static class CmdIFContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(UFABCGrammarParser.AP, 0); }
		public ExprLogContext exprLog() {
			return getRuleContext(ExprLogContext.class,0);
		}
		public TerminalNode FP() { return getToken(UFABCGrammarParser.FP, 0); }
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
		public CmdIFContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdIF; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).enterCmdIF(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).exitCmdIF(this);
		}
	}

	public final CmdIFContext cmdIF() throws RecognitionException {
		CmdIFContext _localctx = new CmdIFContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_cmdIF);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			match(T__8);
			 stack.push(new ArrayList<Command>());
			                      strExpr = "";
			                      currentIfCommand = new IfCommand();
			                    
			setState(80);
			match(AP);
			setState(81);
			exprLog();
			setState(82);
			match(FP);
			 currentIfCommand.setExpression(strExpr); 
			                  strExpr = "";
			setState(84);
			match(T__9);
			setState(86); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(85);
				comando();
				}
				}
				setState(88); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 67330560L) != 0) );
			 
			                  currentIfCommand.setTrueList(stack.pop());                            
			               
			setState(100);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(91);
				match(T__10);
				 stack.push(new ArrayList<Command>()); 
				setState(94); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(93);
					comando();
					}
					}
					setState(96); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 67330560L) != 0) );

				                   currentIfCommand.setFalseList(stack.pop());
				                 
				}
			}

			setState(102);
			match(T__11);

			               	   stack.peek().add(currentIfCommand);
			                     strExpr = "";
			               
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
	public static class CmdWhileContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(UFABCGrammarParser.AP, 0); }
		public ExprLogContext exprLog() {
			return getRuleContext(ExprLogContext.class,0);
		}
		public TerminalNode FP() { return getToken(UFABCGrammarParser.FP, 0); }
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
		public CmdWhileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdWhile; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).enterCmdWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).exitCmdWhile(this);
		}
	}

	public final CmdWhileContext cmdWhile() throws RecognitionException {
		CmdWhileContext _localctx = new CmdWhileContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_cmdWhile);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			match(T__12);
			   stack.push (new ArrayList<Command>());
			                              strExpr = "";
			                              currentWhileCommand = new WhileCommand();
			                              
			setState(107);
			match(AP);
			setState(108);
			exprLog();
			setState(109);
			match(FP);
			 currentWhileCommand.setExpression(strExpr);
			                  strExpr = "";
			setState(111);
			match(T__13);
			setState(113); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(112);
				comando();
				}
				}
				setState(115); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 67330560L) != 0) );

			                  currentWhileCommand.setCommandList(stack.pop());
			               
			setState(118);
			match(T__14);

			                  stack.peek().add(currentWhileCommand);
			                  strExpr = "";
			               
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
	public static class CmdDoWhileContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(UFABCGrammarParser.AP, 0); }
		public ExprLogContext exprLog() {
			return getRuleContext(ExprLogContext.class,0);
		}
		public TerminalNode FP() { return getToken(UFABCGrammarParser.FP, 0); }
		public TerminalNode PV() { return getToken(UFABCGrammarParser.PV, 0); }
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
		public CmdDoWhileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdDoWhile; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).enterCmdDoWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).exitCmdDoWhile(this);
		}
	}

	public final CmdDoWhileContext cmdDoWhile() throws RecognitionException {
		CmdDoWhileContext _localctx = new CmdDoWhileContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_cmdDoWhile);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			match(T__13);
			 stack.push (new ArrayList<Command>());
			                        strExpr = "";
			                        currentDoWhileCommand = new DoWhileCommand();
			                        
			setState(124); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(123);
					comando();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(126); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );

			                  currentDoWhileCommand.setCommandList(stack.pop());
			                  strExpr = "";
			               
			setState(129);
			match(T__12);
			setState(130);
			match(AP);
			setState(131);
			exprLog();
			setState(132);
			match(FP);
			 currentDoWhileCommand.setExpression(strExpr);
			                  strExpr = "";
			               
			setState(134);
			match(PV);
			setState(135);
			match(T__14);

			                  stack.peek().add(currentDoWhileCommand);
			                  strExpr = "";
			               
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
	public static class CmdAttribContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(UFABCGrammarParser.ID, 0); }
		public TerminalNode OP_AT() { return getToken(UFABCGrammarParser.OP_AT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode PV() { return getToken(UFABCGrammarParser.PV, 0); }
		public CmdAttribContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdAttrib; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).enterCmdAttrib(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).exitCmdAttrib(this);
		}
	}

	public final CmdAttribContext cmdAttrib() throws RecognitionException {
		CmdAttribContext _localctx = new CmdAttribContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_cmdAttrib);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			match(ID);
			      strExpr = "";
			                        if (!isDeclared(_input.LT(-1).getText())) {
			                       throw new UFABCSemanticException("Undeclared Variable: "+_input.LT(-1).getText());
			                   }
			                   symbolTable.get(_input.LT(-1).getText()).setInitialized(true);
			                   leftType = symbolTable.get(_input.LT(-1).getText()).getType();
			                   String varId = _input.LT(-1).getText();
			                 
			setState(140);
			match(OP_AT);
			setState(141);
			expr();

			                  AttribCommand attribCommand = new AttribCommand (varId, strExpr);
			                  stack.peek().add(attribCommand);
			              
			setState(143);
			match(PV);

			                 //System.out.println("Left  Side Expression Type = "+leftType);
			                 //System.out.println("Right Side Expression Type = "+rightType);
			                 if (leftType.getValue() < rightType.getValue()){
			                    throw new UFABCSemanticException("Type Mismatching on Assignment");
			                 }

			                 Var var = symbolTable.get(varId);

			                 if (var.getType() != Types.TEXT) {
			                     double varValue = generateValue();
			                     var.setVarValue(varValue);

			                     System.out.println("Log: Valor atribuido a variavel " + varId + ": " + varValue); // Adicionado para imprimir o valor
			                     

			                     topo = null;
			                 } else {
			                     var.setVarStringValue(strExpr);
			                     System.out.println("Log: Valor atribuido a variavel " + varId + ": " + strExpr);
			                 }
			                 strExpr = "";
			              
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
	public static class CmdLeituraContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(UFABCGrammarParser.AP, 0); }
		public TerminalNode ID() { return getToken(UFABCGrammarParser.ID, 0); }
		public TerminalNode FP() { return getToken(UFABCGrammarParser.FP, 0); }
		public TerminalNode PV() { return getToken(UFABCGrammarParser.PV, 0); }
		public CmdLeituraContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdLeitura; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).enterCmdLeitura(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).exitCmdLeitura(this);
		}
	}

	public final CmdLeituraContext cmdLeitura() throws RecognitionException {
		CmdLeituraContext _localctx = new CmdLeituraContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_cmdLeitura);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			match(T__15);
			setState(147);
			match(AP);
			setState(148);
			match(ID);
			 if (!isDeclared(_input.LT(-1).getText())) {
			                       throw new UFABCSemanticException("Undeclared Variable: "+_input.LT(-1).getText());
			                    }
			                    symbolTable.get(_input.LT(-1).getText()).setInitialized(true);
			                    Command cmdRead = new ReadCommand(symbolTable.get(_input.LT(-1).getText()));
			                    stack.peek().add(cmdRead);
			                  
			setState(150);
			match(FP);
			setState(151);
			match(PV);
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
	public static class CmdEscritaContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(UFABCGrammarParser.AP, 0); }
		public TerminalNode FP() { return getToken(UFABCGrammarParser.FP, 0); }
		public TerminalNode PV() { return getToken(UFABCGrammarParser.PV, 0); }
		public TermoContext termo() {
			return getRuleContext(TermoContext.class,0);
		}
		public CmdEscritaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdEscrita; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).enterCmdEscrita(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).exitCmdEscrita(this);
		}
	}

	public final CmdEscritaContext cmdEscrita() throws RecognitionException {
		CmdEscritaContext _localctx = new CmdEscritaContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_cmdEscrita);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
			match(T__16);
			setState(154);
			match(AP);
			{
			setState(155);
			termo();
			 Command cmdWrite = new WriteCommand(_input.LT(-1).getText());
			                         stack.peek().add(cmdWrite);
			                       
			}
			setState(158);
			match(FP);
			setState(159);
			match(PV);
			rightType = null;
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
	public static class ExprLogContext extends ParserRuleContext {
		public ExprRelContext exprRel() {
			return getRuleContext(ExprRelContext.class,0);
		}
		public TerminalNode OPLOG() { return getToken(UFABCGrammarParser.OPLOG, 0); }
		public ExprLogContext exprLog() {
			return getRuleContext(ExprLogContext.class,0);
		}
		public TerminalNode OPNOT() { return getToken(UFABCGrammarParser.OPNOT, 0); }
		public TerminalNode AP() { return getToken(UFABCGrammarParser.AP, 0); }
		public TerminalNode FP() { return getToken(UFABCGrammarParser.FP, 0); }
		public ExprLogContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprLog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).enterExprLog(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).exitExprLog(this);
		}
	}

	public final ExprLogContext exprLog() throws RecognitionException {
		ExprLogContext _localctx = new ExprLogContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_exprLog);
		int _la;
		try {
			setState(178);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(162);
				exprRel();
				setState(166);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OPLOG) {
					{
					setState(163);
					match(OPLOG);
					strExpr += _input.LT(-1).getText();
					setState(165);
					exprLog();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(168);
				match(OPNOT);
				strExpr += _input.LT(-1).getText() + "(";
				setState(170);
				exprLog();
				strExpr += ")";
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(173);
				match(AP);
				setState(174);
				exprLog();
				setState(175);
				match(FP);
				strExpr = "(" + strExpr + ")";
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
	public static class ExprRelContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OPREL() { return getToken(UFABCGrammarParser.OPREL, 0); }
		public ExprRelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprRel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).enterExprRel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).exitExprRel(this);
		}
	}

	public final ExprRelContext exprRel() throws RecognitionException {
		ExprRelContext _localctx = new ExprRelContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_exprRel);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			expr();
			setState(184);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPREL) {
				{
				setState(181);
				match(OPREL);
				 strExpr += _input.LT(-1).getText();
				setState(183);
				expr();
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
	public static class ExprContext extends ParserRuleContext {
		public List<ExprlContext> exprl() {
			return getRuleContexts(ExprlContext.class);
		}
		public ExprlContext exprl(int i) {
			return getRuleContext(ExprlContext.class,i);
		}
		public List<TerminalNode> OP_SUM() { return getTokens(UFABCGrammarParser.OP_SUM); }
		public TerminalNode OP_SUM(int i) {
			return getToken(UFABCGrammarParser.OP_SUM, i);
		}
		public List<TerminalNode> OP_SUB() { return getTokens(UFABCGrammarParser.OP_SUB); }
		public TerminalNode OP_SUB(int i) {
			return getToken(UFABCGrammarParser.OP_SUB, i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			exprl();
			setState(194);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP_SUM || _la==OP_SUB) {
				{
				{
				setState(187);
				_la = _input.LA(1);
				if ( !(_la==OP_SUM || _la==OP_SUB) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				 
				                     BinaryExpression bin = new BinaryExpression(_input.LT(-1).getText().charAt(0));
				                     bin.setLeft(evalStack.pop());
				                     evalStack.push(bin);

				                     strExpr += _input.LT(-1).getText();
				                     
				setState(189);
				exprl();

				                        AbstractExpression topo = evalStack.pop(); // desempilhei o termo
				                        BinaryExpression root = (BinaryExpression) evalStack.pop(); // preciso do componente binário
				                        root.setRight(topo);
				                        evalStack.push(root);

				                        System.out.println("Log: Valor da expressao " + strExpr + ": " + generateValue()); // Adicionado para imprimir o valor
				                     
				}
				}
				setState(196);
				_errHandler.sync(this);
				_la = _input.LA(1);
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
	public static class ExprlContext extends ParserRuleContext {
		public List<TermoContext> termo() {
			return getRuleContexts(TermoContext.class);
		}
		public TermoContext termo(int i) {
			return getRuleContext(TermoContext.class,i);
		}
		public List<TerminalNode> OP_MUL() { return getTokens(UFABCGrammarParser.OP_MUL); }
		public TerminalNode OP_MUL(int i) {
			return getToken(UFABCGrammarParser.OP_MUL, i);
		}
		public List<TerminalNode> OP_DIV() { return getTokens(UFABCGrammarParser.OP_DIV); }
		public TerminalNode OP_DIV(int i) {
			return getToken(UFABCGrammarParser.OP_DIV, i);
		}
		public ExprlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).enterExprl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).exitExprl(this);
		}
	}

	public final ExprlContext exprl() throws RecognitionException {
		ExprlContext _localctx = new ExprlContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_exprl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(197);
			termo();
			setState(205);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP_MUL || _la==OP_DIV) {
				{
				{
				setState(198);
				_la = _input.LA(1);
				if ( !(_la==OP_MUL || _la==OP_DIV) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				 
				                     BinaryExpression bin = new BinaryExpression(_input.LT(-1).getText().charAt(0));
				                     if (evalStack.peek() instanceof UnaryExpression) { // o que tem no topo é um operador "simples"
				                        bin.setLeft(evalStack.pop()); // desempilho já tornando ele filho da multiplicacao
				                     } else {
				                        BinaryExpression father = (BinaryExpression)evalStack.pop();
				                        if (father.getOperation() == '-' || father.getOperation() == '+'){
				                           bin.setLeft(father.getRight());
				                           father.setRight(bin);
				                        } else {
				                           bin.setLeft(father);
							                  evalStack.push(bin);
				                        }
				                     }
				                     strExpr += _input.LT(-1).getText();
				setState(200);
				termo();

				                        bin.setRight(evalStack.pop());
				                        evalStack.push(bin);
				                     
				}
				}
				setState(207);
				_errHandler.sync(this);
				_la = _input.LA(1);
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
	public static class TermoContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(UFABCGrammarParser.ID, 0); }
		public TerminalNode NUM() { return getToken(UFABCGrammarParser.NUM, 0); }
		public TerminalNode TEXTO() { return getToken(UFABCGrammarParser.TEXTO, 0); }
		public TerminalNode AP() { return getToken(UFABCGrammarParser.AP, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode FP() { return getToken(UFABCGrammarParser.FP, 0); }
		public TermoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).enterTermo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).exitTermo(this);
		}
	}

	public final TermoContext termo() throws RecognitionException {
		TermoContext _localctx = new TermoContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_termo);
		try {
			setState(220);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(208);
				match(ID);
				     
				                     if (!isDeclared(_input.LT(-1).getText())) {
				                       throw new UFABCSemanticException("Undeclared Variable: "+_input.LT(-1).getText());
				                     }
				                     Var var = symbolTable.get(_input.LT(-1).getText());
				                     var.setUtilized(true);
				                     if (!symbolTable.get(_input.LT(-1).getText()).isInitialized()){
				                       throw new UFABCSemanticException("Variable "+_input.LT(-1).getText()+" has no value assigned");
				                     }
				                     strExpr += var.getId();
				                     if (rightType == null){
				                       rightType = symbolTable.get(_input.LT(-1).getText()).getType();
				                       //System.out.println("Encontrei pela 1a vez uma variavel = "+rightType);
				                     }   
				                     else{
				                       if (symbolTable.get(_input.LT(-1).getText()).getType().getValue() > rightType.getValue()){
				                          rightType = symbolTable.get(_input.LT(-1).getText()).getType();
				                          //System.out.println("Ja havia tipo declarado e mudou para = "+rightType);
				                          
				                       }
				                     }
				                     UnaryExpression element = new UnaryExpression(symbolTable.get(_input.LT(-1).getText()).getVarValue());
				                     evalStack.push(element);
				                  
				}
				break;
			case NUM:
				enterOuterAlt(_localctx, 2);
				{
				setState(210);
				match(NUM);
				  
				                     strExpr += _input.LT(-1).getText();
				                     if (rightType == null) {
							 				rightType = Types.NUMBER;
							 				//System.out.println("Encontrei um numero pela 1a vez "+rightType);
							            }
							            else{
							                if (rightType.getValue() < Types.NUMBER.getValue()){			                    			                   
							                	rightType = Types.NUMBER;
							                	//System.out.println("Mudei o tipo para Number = "+rightType);
							                }
							            }

				                     UnaryExpression element = new UnaryExpression(Double.parseDouble(_input.LT(-1).getText()));
				                     evalStack.push(element);
							         
				}
				break;
			case TEXTO:
				enterOuterAlt(_localctx, 3);
				{
				setState(212);
				match(TEXTO);
				  
				                     strExpr += _input.LT(-1).getText();
				                     if (rightType == null) {
							 				   rightType = Types.TEXT;
							            }
							            else{
							                if (rightType.getValue() < Types.TEXT.getValue()){			                    
							                	rightType = Types.TEXT;
							                }
							            }
							         
				}
				break;
			case AP:
				enterOuterAlt(_localctx, 4);
				{
				setState(214);
				match(AP);
				strExpr += "(";
				setState(216);
				expr();
				setState(217);
				match(FP);
				strExpr += ")";
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

	public static final String _serializedATN =
		"\u0004\u0001\"\u00df\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0004\u0000!\b\u0000\u000b\u0000\f\u0000\"\u0001\u0000\u0001\u0000\u0004"+
		"\u0000\'\b\u0000\u000b\u0000\f\u0000(\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0005\u00016\b\u0001\n\u0001\f\u00019\t\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0003\u0001B\b\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0003\u0002M\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0004\u0003W\b\u0003"+
		"\u000b\u0003\f\u0003X\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0004\u0003_\b\u0003\u000b\u0003\f\u0003`\u0001\u0003\u0001\u0003\u0003"+
		"\u0003e\b\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0004\u0004r\b\u0004\u000b\u0004\f\u0004s\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0004\u0005"+
		"}\b\u0005\u000b\u0005\f\u0005~\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0003\t\u00a7\b\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0003\t\u00b3\b\t\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0003\n\u00b9\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0005\u000b\u00c1\b\u000b\n\u000b\f\u000b\u00c4\t\u000b"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0005\f\u00cc\b\f\n\f"+
		"\f\f\u00cf\t\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r"+
		"\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u00dd\b\r\u0001\r\u0000"+
		"\u0000\u000e\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016"+
		"\u0018\u001a\u0000\u0002\u0002\u0000\u0012\u0012\u0015\u0015\u0001\u0000"+
		"\u0016\u0017\u00e8\u0000\u001c\u0001\u0000\u0000\u0000\u0002.\u0001\u0000"+
		"\u0000\u0000\u0004L\u0001\u0000\u0000\u0000\u0006N\u0001\u0000\u0000\u0000"+
		"\bi\u0001\u0000\u0000\u0000\ny\u0001\u0000\u0000\u0000\f\u008a\u0001\u0000"+
		"\u0000\u0000\u000e\u0092\u0001\u0000\u0000\u0000\u0010\u0099\u0001\u0000"+
		"\u0000\u0000\u0012\u00b2\u0001\u0000\u0000\u0000\u0014\u00b4\u0001\u0000"+
		"\u0000\u0000\u0016\u00ba\u0001\u0000\u0000\u0000\u0018\u00c5\u0001\u0000"+
		"\u0000\u0000\u001a\u00dc\u0001\u0000\u0000\u0000\u001c\u001d\u0005\u0001"+
		"\u0000\u0000\u001d\u001e\u0005\u001a\u0000\u0000\u001e \u0006\u0000\uffff"+
		"\uffff\u0000\u001f!\u0003\u0002\u0001\u0000 \u001f\u0001\u0000\u0000\u0000"+
		"!\"\u0001\u0000\u0000\u0000\" \u0001\u0000\u0000\u0000\"#\u0001\u0000"+
		"\u0000\u0000#$\u0001\u0000\u0000\u0000$&\u0005\u0002\u0000\u0000%\'\u0003"+
		"\u0004\u0002\u0000&%\u0001\u0000\u0000\u0000\'(\u0001\u0000\u0000\u0000"+
		"(&\u0001\u0000\u0000\u0000()\u0001\u0000\u0000\u0000)*\u0001\u0000\u0000"+
		"\u0000*+\u0005\u0003\u0000\u0000+,\u0005\u0004\u0000\u0000,-\u0006\u0000"+
		"\uffff\uffff\u0000-\u0001\u0001\u0000\u0000\u0000./\u0005\u0005\u0000"+
		"\u0000/0\u0006\u0001\uffff\uffff\u000001\u0005\u001a\u0000\u000017\u0006"+
		"\u0001\uffff\uffff\u000023\u0005\u001c\u0000\u000034\u0005\u001a\u0000"+
		"\u000046\u0006\u0001\uffff\uffff\u000052\u0001\u0000\u0000\u000069\u0001"+
		"\u0000\u0000\u000075\u0001\u0000\u0000\u000078\u0001\u0000\u0000\u0000"+
		"8:\u0001\u0000\u0000\u000097\u0001\u0000\u0000\u0000:A\u0005 \u0000\u0000"+
		";<\u0005\u0006\u0000\u0000<B\u0006\u0001\uffff\uffff\u0000=>\u0005\u0007"+
		"\u0000\u0000>B\u0006\u0001\uffff\uffff\u0000?@\u0005\b\u0000\u0000@B\u0006"+
		"\u0001\uffff\uffff\u0000A;\u0001\u0000\u0000\u0000A=\u0001\u0000\u0000"+
		"\u0000A?\u0001\u0000\u0000\u0000BC\u0001\u0000\u0000\u0000CD\u0006\u0001"+
		"\uffff\uffff\u0000DE\u0005\u001d\u0000\u0000E\u0003\u0001\u0000\u0000"+
		"\u0000FM\u0003\f\u0006\u0000GM\u0003\u000e\u0007\u0000HM\u0003\u0010\b"+
		"\u0000IM\u0003\u0006\u0003\u0000JM\u0003\b\u0004\u0000KM\u0003\n\u0005"+
		"\u0000LF\u0001\u0000\u0000\u0000LG\u0001\u0000\u0000\u0000LH\u0001\u0000"+
		"\u0000\u0000LI\u0001\u0000\u0000\u0000LJ\u0001\u0000\u0000\u0000LK\u0001"+
		"\u0000\u0000\u0000M\u0005\u0001\u0000\u0000\u0000NO\u0005\t\u0000\u0000"+
		"OP\u0006\u0003\uffff\uffff\u0000PQ\u0005\u001e\u0000\u0000QR\u0003\u0012"+
		"\t\u0000RS\u0005\u001f\u0000\u0000ST\u0006\u0003\uffff\uffff\u0000TV\u0005"+
		"\n\u0000\u0000UW\u0003\u0004\u0002\u0000VU\u0001\u0000\u0000\u0000WX\u0001"+
		"\u0000\u0000\u0000XV\u0001\u0000\u0000\u0000XY\u0001\u0000\u0000\u0000"+
		"YZ\u0001\u0000\u0000\u0000Zd\u0006\u0003\uffff\uffff\u0000[\\\u0005\u000b"+
		"\u0000\u0000\\^\u0006\u0003\uffff\uffff\u0000]_\u0003\u0004\u0002\u0000"+
		"^]\u0001\u0000\u0000\u0000_`\u0001\u0000\u0000\u0000`^\u0001\u0000\u0000"+
		"\u0000`a\u0001\u0000\u0000\u0000ab\u0001\u0000\u0000\u0000bc\u0006\u0003"+
		"\uffff\uffff\u0000ce\u0001\u0000\u0000\u0000d[\u0001\u0000\u0000\u0000"+
		"de\u0001\u0000\u0000\u0000ef\u0001\u0000\u0000\u0000fg\u0005\f\u0000\u0000"+
		"gh\u0006\u0003\uffff\uffff\u0000h\u0007\u0001\u0000\u0000\u0000ij\u0005"+
		"\r\u0000\u0000jk\u0006\u0004\uffff\uffff\u0000kl\u0005\u001e\u0000\u0000"+
		"lm\u0003\u0012\t\u0000mn\u0005\u001f\u0000\u0000no\u0006\u0004\uffff\uffff"+
		"\u0000oq\u0005\u000e\u0000\u0000pr\u0003\u0004\u0002\u0000qp\u0001\u0000"+
		"\u0000\u0000rs\u0001\u0000\u0000\u0000sq\u0001\u0000\u0000\u0000st\u0001"+
		"\u0000\u0000\u0000tu\u0001\u0000\u0000\u0000uv\u0006\u0004\uffff\uffff"+
		"\u0000vw\u0005\u000f\u0000\u0000wx\u0006\u0004\uffff\uffff\u0000x\t\u0001"+
		"\u0000\u0000\u0000yz\u0005\u000e\u0000\u0000z|\u0006\u0005\uffff\uffff"+
		"\u0000{}\u0003\u0004\u0002\u0000|{\u0001\u0000\u0000\u0000}~\u0001\u0000"+
		"\u0000\u0000~|\u0001\u0000\u0000\u0000~\u007f\u0001\u0000\u0000\u0000"+
		"\u007f\u0080\u0001\u0000\u0000\u0000\u0080\u0081\u0006\u0005\uffff\uffff"+
		"\u0000\u0081\u0082\u0005\r\u0000\u0000\u0082\u0083\u0005\u001e\u0000\u0000"+
		"\u0083\u0084\u0003\u0012\t\u0000\u0084\u0085\u0005\u001f\u0000\u0000\u0085"+
		"\u0086\u0006\u0005\uffff\uffff\u0000\u0086\u0087\u0005\u001d\u0000\u0000"+
		"\u0087\u0088\u0005\u000f\u0000\u0000\u0088\u0089\u0006\u0005\uffff\uffff"+
		"\u0000\u0089\u000b\u0001\u0000\u0000\u0000\u008a\u008b\u0005\u001a\u0000"+
		"\u0000\u008b\u008c\u0006\u0006\uffff\uffff\u0000\u008c\u008d\u0005\u0018"+
		"\u0000\u0000\u008d\u008e\u0003\u0016\u000b\u0000\u008e\u008f\u0006\u0006"+
		"\uffff\uffff\u0000\u008f\u0090\u0005\u001d\u0000\u0000\u0090\u0091\u0006"+
		"\u0006\uffff\uffff\u0000\u0091\r\u0001\u0000\u0000\u0000\u0092\u0093\u0005"+
		"\u0010\u0000\u0000\u0093\u0094\u0005\u001e\u0000\u0000\u0094\u0095\u0005"+
		"\u001a\u0000\u0000\u0095\u0096\u0006\u0007\uffff\uffff\u0000\u0096\u0097"+
		"\u0005\u001f\u0000\u0000\u0097\u0098\u0005\u001d\u0000\u0000\u0098\u000f"+
		"\u0001\u0000\u0000\u0000\u0099\u009a\u0005\u0011\u0000\u0000\u009a\u009b"+
		"\u0005\u001e\u0000\u0000\u009b\u009c\u0003\u001a\r\u0000\u009c\u009d\u0006"+
		"\b\uffff\uffff\u0000\u009d\u009e\u0001\u0000\u0000\u0000\u009e\u009f\u0005"+
		"\u001f\u0000\u0000\u009f\u00a0\u0005\u001d\u0000\u0000\u00a0\u00a1\u0006"+
		"\b\uffff\uffff\u0000\u00a1\u0011\u0001\u0000\u0000\u0000\u00a2\u00a6\u0003"+
		"\u0014\n\u0000\u00a3\u00a4\u0005\u0013\u0000\u0000\u00a4\u00a5\u0006\t"+
		"\uffff\uffff\u0000\u00a5\u00a7\u0003\u0012\t\u0000\u00a6\u00a3\u0001\u0000"+
		"\u0000\u0000\u00a6\u00a7\u0001\u0000\u0000\u0000\u00a7\u00b3\u0001\u0000"+
		"\u0000\u0000\u00a8\u00a9\u0005\u0014\u0000\u0000\u00a9\u00aa\u0006\t\uffff"+
		"\uffff\u0000\u00aa\u00ab\u0003\u0012\t\u0000\u00ab\u00ac\u0006\t\uffff"+
		"\uffff\u0000\u00ac\u00b3\u0001\u0000\u0000\u0000\u00ad\u00ae\u0005\u001e"+
		"\u0000\u0000\u00ae\u00af\u0003\u0012\t\u0000\u00af\u00b0\u0005\u001f\u0000"+
		"\u0000\u00b0\u00b1\u0006\t\uffff\uffff\u0000\u00b1\u00b3\u0001\u0000\u0000"+
		"\u0000\u00b2\u00a2\u0001\u0000\u0000\u0000\u00b2\u00a8\u0001\u0000\u0000"+
		"\u0000\u00b2\u00ad\u0001\u0000\u0000\u0000\u00b3\u0013\u0001\u0000\u0000"+
		"\u0000\u00b4\u00b8\u0003\u0016\u000b\u0000\u00b5\u00b6\u0005\u0019\u0000"+
		"\u0000\u00b6\u00b7\u0006\n\uffff\uffff\u0000\u00b7\u00b9\u0003\u0016\u000b"+
		"\u0000\u00b8\u00b5\u0001\u0000\u0000\u0000\u00b8\u00b9\u0001\u0000\u0000"+
		"\u0000\u00b9\u0015\u0001\u0000\u0000\u0000\u00ba\u00c2\u0003\u0018\f\u0000"+
		"\u00bb\u00bc\u0007\u0000\u0000\u0000\u00bc\u00bd\u0006\u000b\uffff\uffff"+
		"\u0000\u00bd\u00be\u0003\u0018\f\u0000\u00be\u00bf\u0006\u000b\uffff\uffff"+
		"\u0000\u00bf\u00c1\u0001\u0000\u0000\u0000\u00c0\u00bb\u0001\u0000\u0000"+
		"\u0000\u00c1\u00c4\u0001\u0000\u0000\u0000\u00c2\u00c0\u0001\u0000\u0000"+
		"\u0000\u00c2\u00c3\u0001\u0000\u0000\u0000\u00c3\u0017\u0001\u0000\u0000"+
		"\u0000\u00c4\u00c2\u0001\u0000\u0000\u0000\u00c5\u00cd\u0003\u001a\r\u0000"+
		"\u00c6\u00c7\u0007\u0001\u0000\u0000\u00c7\u00c8\u0006\f\uffff\uffff\u0000"+
		"\u00c8\u00c9\u0003\u001a\r\u0000\u00c9\u00ca\u0006\f\uffff\uffff\u0000"+
		"\u00ca\u00cc\u0001\u0000\u0000\u0000\u00cb\u00c6\u0001\u0000\u0000\u0000"+
		"\u00cc\u00cf\u0001\u0000\u0000\u0000\u00cd\u00cb\u0001\u0000\u0000\u0000"+
		"\u00cd\u00ce\u0001\u0000\u0000\u0000\u00ce\u0019\u0001\u0000\u0000\u0000"+
		"\u00cf\u00cd\u0001\u0000\u0000\u0000\u00d0\u00d1\u0005\u001a\u0000\u0000"+
		"\u00d1\u00dd\u0006\r\uffff\uffff\u0000\u00d2\u00d3\u0005\u001b\u0000\u0000"+
		"\u00d3\u00dd\u0006\r\uffff\uffff\u0000\u00d4\u00d5\u0005!\u0000\u0000"+
		"\u00d5\u00dd\u0006\r\uffff\uffff\u0000\u00d6\u00d7\u0005\u001e\u0000\u0000"+
		"\u00d7\u00d8\u0006\r\uffff\uffff\u0000\u00d8\u00d9\u0003\u0016\u000b\u0000"+
		"\u00d9\u00da\u0005\u001f\u0000\u0000\u00da\u00db\u0006\r\uffff\uffff\u0000"+
		"\u00db\u00dd\u0001\u0000\u0000\u0000\u00dc\u00d0\u0001\u0000\u0000\u0000"+
		"\u00dc\u00d2\u0001\u0000\u0000\u0000\u00dc\u00d4\u0001\u0000\u0000\u0000"+
		"\u00dc\u00d6\u0001\u0000\u0000\u0000\u00dd\u001b\u0001\u0000\u0000\u0000"+
		"\u0010\"(7ALX`ds~\u00a6\u00b2\u00b8\u00c2\u00cd\u00dc";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}