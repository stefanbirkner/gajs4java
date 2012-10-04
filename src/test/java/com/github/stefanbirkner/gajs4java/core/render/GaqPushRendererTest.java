package com.github.stefanbirkner.gajs4java.core.render;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.Test;

import com.github.stefanbirkner.gajs4java.core.model.Command;
import com.github.stefanbirkner.gajs4java.core.model.CommandForTracker;
import com.github.stefanbirkner.gajs4java.core.model.SimpleCommand;

public class GaqPushRendererTest {
	private static final String DUMMY_METHOD_NAME = "_dummyMethod";
	private final GaqPushRenderer renderer = new GaqPushRenderer();

	@Test
	public void rendersCommandWithoutArguments() throws Exception {
		CommandForTracker command = commandForDefaultTracker(DUMMY_METHOD_NAME);
		assertThat(outputForCommand(command), is(equalTo("_gaq.push(['"
				+ DUMMY_METHOD_NAME + "']);")));
	}

	@Test
	public void renderCommandWithStringArguments() throws Exception {
		CommandForTracker command = commandForDefaultTracker(DUMMY_METHOD_NAME,
				"first", "second");
		assertThat(outputForCommand(command), is(equalTo("_gaq.push(['"
				+ DUMMY_METHOD_NAME + "', 'first', 'second']);")));
	}

	@Test
	public void rendersCommandWithBooleanArguments() throws Exception {
		CommandForTracker command = commandForDefaultTracker(DUMMY_METHOD_NAME,
				true, false);
		assertThat(outputForCommand(command), is(equalTo("_gaq.push(['"
				+ DUMMY_METHOD_NAME + "', true, false]);")));
	}

	@Test
	public void rendersCommandWithLongArgument() throws Exception {
		CommandForTracker command = commandForDefaultTracker(DUMMY_METHOD_NAME,
				1L);
		assertThat(outputForCommand(command), is(equalTo("_gaq.push(['"
				+ DUMMY_METHOD_NAME + "', 1]);")));
	}

	@Test
	public void rendersCommandWithIntegerArgument() throws Exception {
		CommandForTracker command = commandForDefaultTracker(DUMMY_METHOD_NAME,
				1);
		assertThat(outputForCommand(command), is(equalTo("_gaq.push(['"
				+ DUMMY_METHOD_NAME + "', 1]);")));
	}

	@Test
	public void rendersCommandWithShortArgument() throws Exception {
		CommandForTracker command = commandForDefaultTracker(DUMMY_METHOD_NAME,
				(short) 1);
		assertThat(outputForCommand(command), is(equalTo("_gaq.push(['"
				+ DUMMY_METHOD_NAME + "', 1]);")));
	}

	@Test
	public void rendersCommandForNamedTracker() throws Exception {
		CommandForTracker command = new CommandForTracker(new SimpleCommand(
				"_dummyMethod"), "dummyTrackerName");
		assertThat(outputForCommand(command),
				is(equalTo("_gaq.push(['dummyTrackerName._dummyMethod']);")));
	}

	private CommandForTracker commandForDefaultTracker(String methodName,
			Object... arguments) {
		Command command = new SimpleCommand(methodName, arguments);
		return new CommandForTracker(command);
	}

	private String outputForCommand(CommandForTracker commandForTracker)
			throws IOException {
		StringWriter writer = new StringWriter();
		renderer.writeCodeForCommandToWriter(commandForTracker, writer);
		return writer.toString();
	}
}
