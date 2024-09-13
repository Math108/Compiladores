package io.compiler.core.ast;

public abstract class Command {

	public abstract String generateTarget();
	public abstract String generateTargetPython(int n);

	public static String pythonify(String input) {
        // Substitui ! por not
        String result = input.replace("!", "not");

        // Substitui || por or
        result = result.replace("||", " or ");

        // Substitui && por and
        result = result.replace("&&", " and ");

        return result;
    }
}
