package com.github.stefanbirkner.gajs4java.core.model;

import static java.util.Arrays.asList;

import java.util.List;


public class SimpleCommand implements Command {
	private final String methodName;
	private final List<Object> arguments;

	public SimpleCommand(String methodName, Object... arguments) {
		this.methodName = methodName;
		this.arguments = asList(arguments);
	}

	@Override
	public String getMethodName() {
		return methodName;
	}

	@Override
	public List<Object> getArguments() {
		return arguments;
	}
}
