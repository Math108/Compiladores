package io.compiler.core.ast;

import io.compiler.types.Types;
import io.compiler.types.Var;

public class ReadCommand extends Command {

	private Var var;

	public ReadCommand() {
		super();
	}

	public ReadCommand(Var var) {
		super();
		this.var = var;
	}

	public Var getVar() {
		return var;
	}

	public void setVar(Var var) {
		this.var = var;
	}

	@Override
	public String generateTarget() {
		String readMethod;
		if(var.getType() == Types.NUMBER) {
			readMethod = "_scTrx.nextInt();";
		} else if (var.getType() == Types.REALNUMBER) {
			readMethod = "_scTrx.nextDouble();";
		} else {
			readMethod = "_scTrx.nextLine();";
		}

		return var.getId() + " = " + readMethod + "\n";
	}

	@Override
	public String generateTargetPython(int n) {
		//System.out.println("n no Read: " + n);
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < n; i++) {
            str.append("    ");
        }
		String typeConversion = "";
		if (var.getType() == Types.NUMBER) {
			typeConversion = "int(input())";
		} else if (var.getType() == Types.REALNUMBER){
			typeConversion = "float(input())";
		} else {
			typeConversion = "input()";
		}

		return str.append(var.getId() + " = " + typeConversion + "\n").toString();
	}

}
