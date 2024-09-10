package io.compiler.core.ast;

public class WriteCommand extends Command {
	private String content;

	@Override
	public String generateTarget() {
		return "System.out.println(" + content + ");\n";
	}

	@Override
	public String generateTargetPython(int n) {
		//System.out.println("n no Write: " + n);
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < n; i++) {
            str.append("    ");
        }
		return str.append("print(" + content + ")\n").toString();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public WriteCommand(String content) {
		super();
		this.content = content;
	}

	public WriteCommand() {
		super();
	}

}
