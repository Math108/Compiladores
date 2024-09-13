package io.compiler.types;

public class Var {
	private String id;
	private Types type;
	private boolean initialized;
	private boolean utilized;
	private double varValue;
	private String varStringValue;
	
	public Var(String id, Types type) {
		super();
		this.id = id;
		this.type = type;
	}
	public String getVarStringValue() {
		return varStringValue;
	}
	public void setVarStringValue(String varStringValue) {
		this.varStringValue = varStringValue;
	}
	public double getVarValue() {
		return varValue;
	}
	public void setVarValue(double value) {
		this.varValue = value;
	}
	public boolean isUtilized() {
		return utilized;
	}
	public void setUtilized(boolean utilized) {
		this.utilized = utilized;
	}
	public Var(String id) {
		super();
		this.id = id;
	}
	public Var() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Types getType() {
		return type;
	}
	public void setType(Types type) {
		this.type = type;
	}
	public boolean isInitialized() {
		return initialized;
	}
	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}
	@Override
	public String toString() {
		return "Var [id=" + id + ", type=" + type + ", initialized=" + initialized + "]";
	}
	
	

}
