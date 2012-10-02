package com.github.stefanbirkner.gajs4java.core.model;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;

public class CommandForTrackerTest {
	private static final String DUMMY_NAME = "dummy";
	private final Command command = mock(Command.class);

	@Test
	public void isForDefaultTrackerWithoutName() {
		CommandForTracker commandForTracker = new CommandForTracker(command);
		assertThat(commandForTracker,
				hasProperty("forDefaultTracker", is(true)));
	}

	@Test
	public void isNotForDefaultTrackerWithName() {
		CommandForTracker commandForTracker = new CommandForTracker(command,
				DUMMY_NAME);
		assertThat(commandForTracker,
				hasProperty("forDefaultTracker", is(false)));
	}

	@Test
	public void usesMethodNameOfCommand() {
		when(command.getMethodName()).thenReturn(DUMMY_NAME);
		CommandForTracker commandForTracker = new CommandForTracker(command);
		assertThat(commandForTracker,
				hasProperty("methodName", is(equalTo(DUMMY_NAME))));
	}

	@Test
	public void usesArgumentsOfCommand() {
		List<Object> arguments = asList("first argument", new Object());
		when(command.getArguments()).thenReturn(arguments);
		CommandForTracker commandForTracker = new CommandForTracker(command);
		assertThat(commandForTracker,
				hasProperty("arguments", is(equalTo(arguments))));
	}
}
