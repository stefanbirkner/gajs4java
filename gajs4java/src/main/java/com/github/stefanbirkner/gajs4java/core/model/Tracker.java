package com.github.stefanbirkner.gajs4java.core.model;

import static java.util.Arrays.asList;

import java.util.List;

public class Tracker {
	private final String name;
	private final List<Command> commands;

	// Vielleicht sollten die Commands raus in irgendwas wie TrackerWithCommands
	public Tracker(Command... commands) {
		this(null, commands);
	}

	public Tracker(String name, Command... commands) {
		this.name = name;
		this.commands = asList(commands);
	}

}
