grammar UFABCGrammar;

@header {
	import java.util.ArrayList;
	import java.util.Stack;
	import java.util.HashMap;
	import io.compiler.types.*;
	import io.compiler.core.exceptions.*;
	import io.compiler.core.ast.*;
   import io.compiler.runtime.*;

}

@members {
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
}
 
programa	: 'programa' ID  { program.setName(_input.LT(-1).getText());
                               stack.push(new ArrayList<Command>()); 
                             }
               declaravar+
               'inicio'
               comando+
               'fim'
               'fimprog'
               
               {
                  program.setSymbolTable(symbolTable);
                  program.setCommandList(stack.pop());

                  for (Var var : symbolTable.values()) {
                     if (!var.isUtilized()) {
                        System.out.println("WARNING: Variable " + var.getId() + " was declared but never used");
                     }
                  }
               }
			;
						
declaravar	: 'declare' { currentDecl.clear(); } 
               ID  { 
                  String varId = _input.LT(-1).getText();
                  if (isDeclared(varId)) {
                     throw new UFABCSemanticException("Variable already declared: " + varId);
                  }
                  currentDecl.add(new Var(_input.LT(-1).getText()));
               }
               ( VIRG ID                
              		 { String varId2 = _input.LT(-1).getText();
                     if (isDeclared(varId2) || currentDecl.stream().anyMatch(v->v.getId().equals(varId2))) {
                        throw new UFABCSemanticException("Variable already declared: " + varId2);
                     }
                     currentDecl.add(new Var(_input.LT(-1).getText()));}
               )*
               DP 
               (
               'number' {currentType = Types.NUMBER;}
               |
               'text' {currentType = Types.TEXT;}
               |
               'real number' {currentType = Types.REALNUMBER;}
               ) 
               
               { updateType(); } 
               PV
			;
			
comando     :  cmdAttrib
               |  cmdLeitura
               |  cmdEscrita
               |  cmdIF
               |  cmdWhile
               |  cmdDoWhile
			;
			
cmdIF		: 'se'  { stack.push(new ArrayList<Command>());
                      strExpr = "";
                      currentIfCommand = new IfCommand();
                    } 
               AP
               expr
               (OPREL | OPLOG) { strExpr += _input.LT(-1).getText(); }
               expr 
               FP  { currentIfCommand.setExpression(strExpr); 
                  strExpr = "";}
               'entao'  
               comando+                
               { 
                  currentIfCommand.setTrueList(stack.pop());                            
               }  
               ( 'senao'  
                  { stack.push(new ArrayList<Command>()); }
                 comando+
                 {
                   currentIfCommand.setFalseList(stack.pop());
                 }  
               )? 
               'fimse' 
               {
               	   stack.peek().add(currentIfCommand);
                     strExpr = "";
               }  			   
			;

cmdWhile    :  'enquanto' {   stack.push (new ArrayList<Command>());
                              strExpr = "";
                              currentWhileCommand = new WhileCommand();
                              }
               AP
               exprLog 
               FP { currentWhileCommand.setExpression(strExpr);
                  strExpr = "";}
               'faca'
               comando+ 
               {
                  currentWhileCommand.setCommandList(stack.pop());
               }
               'fimWhile' 
               {
                  stack.peek().add(currentWhileCommand);
                  strExpr = "";
               }
            ;

cmdDoWhile  :  'faca' { stack.push (new ArrayList<Command>());
                        strExpr = "";
                        currentDoWhileCommand = new DoWhileCommand();
                        }
               comando+ {
                  currentDoWhileCommand.setCommandList(stack.pop());
               }
               'enquanto'
               AP
               exprLog
               FP { currentDoWhileCommand.setExpression(strExpr);
                  strExpr = "";
               }
               PV
               'fimWhile'
               {
                  stack.peek().add(currentDoWhileCommand);
                  strExpr = "";
               }
            ;
			
cmdAttrib   : ID {      strExpr = "";
                        if (!isDeclared(_input.LT(-1).getText())) {
                       throw new UFABCSemanticException("Undeclared Variable: "+_input.LT(-1).getText());
                   }
                   symbolTable.get(_input.LT(-1).getText()).setInitialized(true);
                   leftType = symbolTable.get(_input.LT(-1).getText()).getType();
                   String varId = _input.LT(-1).getText();
                 }
              OP_AT 
              expr 
              {
                  AttribCommand attribCommand = new AttribCommand (varId, strExpr);
                  stack.peek().add(attribCommand);
              }
              PV
              
              {
                 //System.out.println("Left  Side Expression Type = "+leftType);
                 //System.out.println("Right Side Expression Type = "+rightType);
                 if (leftType.getValue() < rightType.getValue()){
                    throw new UFABCSemanticException("Type Mismatching on Assignment");
                 }

                 Var var = symbolTable.get(varId);
                 double varValue = generateValue();
                 var.setVarValue(varValue);

                 System.out.println("Log: Valor atribuido a variavel " + varId + ": " + varValue); // Adicionado para imprimir o valor
                 strExpr = "";

                 topo = null;
               
              }
			;			
			
cmdLeitura  : 'leia' AP
               ID { if (!isDeclared(_input.LT(-1).getText())) {
                       throw new UFABCSemanticException("Undeclared Variable: "+_input.LT(-1).getText());
                    }
                    symbolTable.get(_input.LT(-1).getText()).setInitialized(true);
                    Command cmdRead = new ReadCommand(symbolTable.get(_input.LT(-1).getText()));
                    stack.peek().add(cmdRead);
                  } 
               FP
               PV 
			;
			
cmdEscrita  : 'escreva' AP
              ( termo  { Command cmdWrite = new WriteCommand(_input.LT(-1).getText());
                         stack.peek().add(cmdWrite);
                       } 
              ) 
              FP PV {rightType = null;}
			;			

exprLog     : exprRel (OPLOG {strExpr += _input.LT(-1).getText();} exprLog)?
            | OPNOT {strExpr += _input.LT(-1).getText() + "(";} exprLog {strExpr += ")";}
            | AP exprLog FP {strExpr = "(" + strExpr + ")";}
            ;

exprRel     : expr (OPREL { strExpr += _input.LT(-1).getText();} expr)?
            ;            

expr       : exprl ( (OP_SUM | OP_SUB) { 
                     BinaryExpression bin = new BinaryExpression(_input.LT(-1).getText().charAt(0));
                     bin.setLeft(evalStack.pop());
                     evalStack.push(bin);

                     strExpr += _input.LT(-1).getText();
                     }  exprl {
                        AbstractExpression topo = evalStack.pop(); // desempilhei o termo
                        BinaryExpression root = (BinaryExpression) evalStack.pop(); // preciso do componente binário
                        root.setRight(topo);
                        evalStack.push(root);

                        System.out.println("Log: Valor da expressao " + strExpr + ": " + generateValue()); // Adicionado para imprimir o valor
                     })* 
            ;

exprl       : termo ( (OP_MUL | OP_DIV) { 
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
                     strExpr += _input.LT(-1).getText();}
                     termo {
                        bin.setRight(evalStack.pop());
                        evalStack.push(bin);
                     })* 
            ;
			
termo		: ID  {     
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
			| NUM    {  
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
			| TEXTO  {  
                     //strExpr += _input.LT(-1).getText();
                     if (rightType == null) {
			 				rightType = Types.TEXT;
			 				//System.out.println("Encontrei pela 1a vez um texto ="+ rightType);
			            }
			            else{
			                if (rightType.getValue() < Types.TEXT.getValue()){			                    
			                	rightType = Types.TEXT;
			                	//System.out.println("Mudei o tipo para TEXT = "+rightType);
			                	
			                }
			            }
			         }
         | AP {strExpr += "(";} expr FP {strExpr += ")";}
			;
			
OP_SUM		: '+' ;

OPLOG       : '||' | '&&' ;

OPNOT       : '!' ;

OP_SUB		: '-' ;

OP_MUL		: '*' ;

OP_DIV		: '/' ;
			
OP_AT	    : ':='
		    ;
		    
OPREL       : '>' | '<' | '>=' | '<= ' | '<>' | '=='
			;		    			
			
ID			: [a-z] ( [a-z] | [A-Z] | [0-9] )*		
			;
			
NUM			: '-'?[0-9]+ ('.' [0-9]+ )?
			;			
			
VIRG		: ','
			;
						
PV			: ';'
            ;			
            
AP			: '('
			;            
						
FP			: ')'
			;
									
DP			: ':'
		    ;
		    
TEXTO       : '"' ( [a-z] | [A-Z] | [0-9] | ',' | '.' | ' ' | '-' )* '"'
			;		    
		    			
WS			: (' ' | '\n' | '\r' | '\t' ) -> skip
			;