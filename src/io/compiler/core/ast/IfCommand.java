package io.compiler.core.ast;

import java.util.List;

public class IfCommand extends Command {

	private String expression;
	private List<Command> trueList;
	private List<Command> falseList;

	public IfCommand() {
		super();
	}

	public IfCommand(String expression, List<Command> trueList, List<Command> falseList) {
		super();
		this.expression = expression;
		this.trueList = trueList;
		this.falseList = falseList;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public List<Command> getTrueList() {
		return trueList;
	}

	public void setTrueList(List<Command> trueList) {
		this.trueList = trueList;
	}

	public List<Command> getFalseList() {
		return falseList;
	}

	public void setFalseList(List<Command> falseList) {
		this.falseList = falseList;
	}

	@Override
	public String generateTarget() {
		StringBuilder str = new StringBuilder();
		str.append("if (" + expression + ") {");
		for (Command cmd : trueList) {
			str.append(cmd.generateTarget());
		}
		str.append("}");

		if (!falseList.isEmpty()) {
			str.append("else {");
			for (Command cmd : falseList) {
				str.append(cmd.generateTarget());
			}
			str.append("}");
		}
		return str.toString();
	}

	@Override
	public String generateTargetPython(int n) {
		System.out.println("n no If: " + n);
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < n; i++) {
            str.append("    ");
        }
		str.append("if ").append(expression).append(":\n");
		for (Command cmd : trueList) {
			str.append(cmd.generateTargetPython(n+1));
		}

		if (!falseList.isEmpty()) {
			for(int i = 0; i < n; i++) {
				str.append("    ");
			}
			str.append("else:\n");
			for (Command cmd : falseList) {
				str.append(cmd.generateTargetPython(n+1));
			}
		}

		return str.toString();
	}

}
