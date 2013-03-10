package com.github.stefanbirkner.gajs4java.core.model;

import static com.github.stefanbirkner.gajs4java.core.model.AnalyticsScript.STANDARD;
import static com.github.stefanbirkner.gajs4java.core.model.Protocol.DECIDE_BY_JAVASCRIPT;

import java.util.List;

public class TrackingConfiguration {
	private final List<CommandForTracker> commands;
	private final Protocol protocol;
	private final AnalyticsScript analyticsScript;

	public TrackingConfiguration(List<CommandForTracker> commands) {
		this(commands, DECIDE_BY_JAVASCRIPT, STANDARD);
	}

	public TrackingConfiguration(List<CommandForTracker> commands,
			Protocol protocol, AnalyticsScript analyticsScript) {
		this.commands = commands;
		this.protocol = protocol;
		this.analyticsScript = analyticsScript;
	}

	public List<CommandForTracker> getCommands() {
		return commands;
	}

	public Protocol getProtocol() {
		return protocol;
	}

	public AnalyticsScript getAnalyticsScript() {
		return analyticsScript;
	}
}
