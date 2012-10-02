package com.github.stefanbirkner.gajs4java.core.model;

import static com.github.stefanbirkner.gajs4java.core.model.CommandForTrackerUtil.wrapCommandsForDefaultTracker;
import static com.github.stefanbirkner.gajs4java.core.model.CommandForTrackerUtil.wrapCommandsForNamedTracker;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.hamcrest.Matcher;
import org.junit.Test;

public class CommandForTrackerUtilTest {
	private static final String DUMMY_TRACKER_NAME = "dummy tracker name";
	private final SimpleCommand firstCommand = new SimpleCommand("firstMethod",
			"argument of first command");
	private final SimpleCommand secondCommand = new SimpleCommand(
			"secondMethod", "argument of second command");

	@Test
	public void wrapsCommandsForDefaultTracker() {
		List<CommandForTracker> wrappedCommands = wrapCommandsForDefaultTracker(
				firstCommand, secondCommand);
		assertThat(
				wrappedCommands,
				contains(wrapperOfCommandForDefaultTracker(firstCommand),
						wrapperOfCommandForDefaultTracker(secondCommand)));
	}

	private Matcher<Object> wrapperOfCommandForDefaultTracker(Command command) {
		return allOf(hasProperty("forDefaultTracker", is(true)),
				hasProperty("methodName", equalTo(command.getMethodName())),
				hasProperty("arguments", equalTo(command.getArguments())));
	}

	@Test
	public void wrapsCommandsForNamedTracker() {
		List<CommandForTracker> wrappedCommands = wrapCommandsForNamedTracker(
				DUMMY_TRACKER_NAME, firstCommand, secondCommand);
		assertThat(
				wrappedCommands,
				contains(wrapperOfCommandForNamedTracker(firstCommand),
						wrapperOfCommandForNamedTracker(secondCommand)));
	}

	private Matcher<Object> wrapperOfCommandForNamedTracker(Command command) {
		return allOf(hasProperty("trackerName", equalTo(DUMMY_TRACKER_NAME)),
				hasProperty("methodName", equalTo(command.getMethodName())),
				hasProperty("arguments", equalTo(command.getArguments())));
	}
}
