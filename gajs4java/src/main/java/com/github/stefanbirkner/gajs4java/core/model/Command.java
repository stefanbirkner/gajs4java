package com.github.stefanbirkner.gajs4java.core.model;

import java.util.List;

public interface Command {
	String getMethodName();

	List<Object> getArguments();
}
