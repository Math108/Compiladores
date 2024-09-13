package io.compiler.runtime;

public class UnaryExpression extends AbstractExpression {

	private double value;

	public UnaryExpression() {
		super();
	}

	public UnaryExpression(double value) {
		super();
		this.value = value;
	}

	@Override
	public double evaluate() {
		return value;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

}