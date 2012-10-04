package com.github.stefanbirkner.gajs4java.core.model;

import java.util.ArrayList;
import java.util.List;

public class CommandForTrackerUtil {
	private CommandForTrackerUtil() {
	}

	public static List<CommandForTracker> wrapCommandsForDefaultTracker(
			Command... commands) {
		List<CommandForTracker> wrappedCommands = new ArrayList<CommandForTracker>();
		for (Command command : commands)
			wrappedCommands.add(new CommandForTracker(command));
		return wrappedCommands;
	}

	public static List<CommandForTracker> wrapCommandsForNamedTracker(
			String trackerName, Command... commands) {
		List<CommandForTracker> wrappedCommands = new ArrayList<CommandForTracker>();
		for (Command command : commands)
			wrappedCommands.add(new CommandForTracker(command, trackerName));
		return wrappedCommands;
	}
}
