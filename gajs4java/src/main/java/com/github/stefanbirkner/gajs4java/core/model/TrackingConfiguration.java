package com.github.stefanbirkner.gajs4java.core.model;

import static com.github.stefanbirkner.gajs4java.core.model.Protocol.DECIDE_BY_JAVASCRIPT;

import java.util.List;

public class TrackingConfiguration {
	private final List<CommandForTracker> commands;
	private final Protocol protocol;

	public TrackingConfiguration(List<CommandForTracker> commands) {
		this(commands, DECIDE_BY_JAVASCRIPT);
	}

	public TrackingConfiguration(List<CommandForTracker> commands,
			Protocol protocol) {
		this.commands = commands;
		this.protocol = protocol;
	}

	public List<CommandForTracker> getCommands() {
		return commands;
	}

	public Protocol getProtocol() {
		return protocol;
	}
}
