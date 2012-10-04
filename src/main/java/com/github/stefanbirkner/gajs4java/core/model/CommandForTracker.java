package com.github.stefanbirkner.gajs4java.core.model;

import java.util.List;

public class CommandForTracker {
	private final Command command;
	private final String trackerName;

	public CommandForTracker(Command command) {
		this(command, null);
	}

	public CommandForTracker(Command command, String trackerName) {
		this.command = command;
		this.trackerName = trackerName;
	}

	public boolean isForDefaultTracker() {
		return trackerName == null;
	}

	public String getTrackerName() {
		return trackerName;
	}

	public String getMethodName() {
		return command.getMethodName();
	}

	public List<Object> getArguments() {
		return command.getArguments();
	}
}
