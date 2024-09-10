package io.compiler.core.ast;

import java.util.List;

public class DoWhileCommand extends Command {

    private String expression;
    private List<Command> commandList;

    public DoWhileCommand() {
        super();
    }

    public DoWhileCommand(String expression, List<Command> commandList) {
        super();
        this.expression = expression;
        this.commandList = commandList;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public List<Command> getCommandList() {
        return commandList;
    }

    public void setCommandList(List<Command> commandList) {
        this.commandList = commandList;
    }

    @Override
    public String generateTarget() {
        StringBuilder str = new StringBuilder();
        str.append("do {");
        for (Command cmd : commandList) {
            str.append(cmd.generateTarget());
        }
        str.append("}");
        str.append("while (" + expression + ");");
        return str.toString();
    }

    @Override
    public String generateTargetPython(int n) {
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < n; i++) {
            str.append("    ");
        }
        str.append("while True:\n");

        for (Command cmd : commandList) {
            str.append(cmd.generateTargetPython(n+1));
        }

        for(int i = 0; i < n + 1; i++) {
            str.append("    ");
        }
        str.append("if not (").append(expression).append("):\n");

        for(int i = 0; i < n + 2; i++) {
            str.append("    ");
        }
        str.append("break\n");
        return str.toString();
    }

    @Override
    public String toString() {
        return "DoWhileCommand [expression=" + expression + ", commandList=" + commandList + "]";
    }
}
