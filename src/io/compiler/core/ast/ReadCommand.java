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
		return var.getId() + " = " + ((var.getType() == Types.NUMBER) ? "_scTrx.nextInt();" : "_scTrx.nextLine();")
				+ "\n";
	}

	@Override
	public String generateTargetPython(int n) {
		System.out.println("n no Read: " + n);
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < n; i++) {
            str.append("    ");
        }
		String typeConversion = "";
		if (var.getType() == Types.NUMBER) {
			typeConversion = "int(input())";
		} else {
			typeConversion = "input()";
		}

		return str.append(var.getId() + " = " + typeConversion + "\n").toString();
	}

}
