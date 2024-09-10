package io.compiler.core.ast;

import java.util.HashMap;
import java.util.List;

import io.compiler.types.Types;
import io.compiler.types.Var;

public class Program {
	private String name;
	private HashMap<String, Var> symbolTable;
	private List<Command> commandList;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public HashMap<String, Var> getSymbolTable() {
		return symbolTable;
	}
	public void setSymbolTable(HashMap<String, Var> symbolTable) {
		this.symbolTable = symbolTable;
	}
	public List<Command> getCommandList() {
		return commandList;
	}
	public void setCommandList(List<Command> commandList) {
		this.commandList = commandList;
	}
	
	public String generateTarget() {
		StringBuilder str = new StringBuilder();
		str.append("import java.util.Scanner;\n");
		str.append("public class "+name+"{ \n");
		str.append("   public static void main(String args[]){ \n");
		str.append("   Scanner _scTrx = new Scanner(System.in);\n");
		for (String varId: symbolTable.keySet()) {
			Var var = symbolTable.get(varId);
			if (var.getType() == Types.NUMBER) {
				str.append("    int ");
			}
			else if (var.getType() == Types.REALNUMBER){
				str.append("    double ");				
			}
			else {
				str.append("	String ");
			}
			str.append(var.getId()+";\n");
		}
		
		for(Command cmd: commandList) {
			str.append(cmd.generateTarget());
		}
		str.append("   }\n");
		str.append("}");
		return str.toString();
	}

	public String generateTargetPython(int n) {
		StringBuilder str = new StringBuilder();
		str.append("def main():\n");
		
		// Definir variáveis com base na tabela de símbolos
		for (String varId: symbolTable.keySet()) {
			Var var = symbolTable.get(varId);
			if (var.getType() == Types.NUMBER) {
				// Em Python, não precisamos declarar o tipo explicitamente
				str.append("    ").append(var.getId()).append(" = 0\n");  // Inicializando com 0
			} else if (var.getType() == Types.REALNUMBER){
				str.append("    ").append(var.getId()).append(" = 0.0\n");  // Inicializando doubles com 0.0
			} else {
				str.append("    ").append(var.getId()).append(" = ''\n");  // Inicializando strings com ''
			}
		}

		// Gerar comandos
		for (Command cmd: commandList) {
			str.append(cmd.generateTargetPython(n+1));
		}
		
		str.append("\nif __name__ == '__main__':\n");
		str.append("    main()\n");

		return str.toString();
	}
	
}
