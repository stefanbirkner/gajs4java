package com.github.stefanbirkner.gajs4java.core.render;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import com.github.stefanbirkner.gajs4java.core.model.CommandForTracker;

public class GaqPushRenderer {
	public void writeCodeForCommandToWriter(CommandForTracker command,
			Writer writer) throws IOException {
		writer.write("_gaq.push([");
		writeMethodNameToWriter(command, writer);
		writeArgumentsToWriter(command.getArguments(), writer);
		writer.write("]);");
	}

	private void writeMethodNameToWriter(CommandForTracker command,
			Writer writer) throws IOException {
		writer.write("'");
		if (!command.isForDefaultTracker()) {
			writer.write(command.getTrackerName());
			writer.write(".");
		}
		writer.write(command.getMethodName());
		writer.write("'");
	}

	private void writeArgumentsToWriter(List<Object> arguments, Writer writer)
			throws IOException {
		for (Object argument : arguments)
			writeArgumentToWriter(argument, writer);
	}

	private void writeArgumentToWriter(Object argument, Writer writer)
			throws IOException {
		if ((argument instanceof Boolean) || (argument instanceof Long)
				|| (argument instanceof Integer) || (argument instanceof Short))
			writePlainArgumentToWriter(argument, writer);
		else
			writeArgumentAsStringToWriter(argument, writer);
	}

	private void writePlainArgumentToWriter(Object argument, Writer writer)
			throws IOException {
		writer.write(", ");
		writer.write(argument.toString());
	}

	private void writeArgumentAsStringToWriter(Object argument, Writer writer)
			throws IOException {
		writer.write(", '");
		writer.write(argument.toString());
		writer.write("'");
	}
}
