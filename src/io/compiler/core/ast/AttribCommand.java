package io.compiler.core.ast;

public class AttribCommand extends Command {

    private String id;
    private String expression;

    public AttribCommand(String id, String expression) {
        this.id = id;
        this.expression = expression;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public String generateTarget() {
        // Gera o código de atribuição na linguagem de destino
        return id + " = " + expression + ";\n";
    }

    @Override
    public String generateTargetPython(int n) {
        // Gera o código de atribuição na linguagem Python
        System.out.println("n no Attrib: " + n);
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < (n); i++) {
            str.append("    ");
        }
        return str.append(id + " = " + expression + "\n").toString();
    }
}
