package com.github.stefanbirkner.gajs4java.core.render;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import com.github.stefanbirkner.gajs4java.core.model.CommandForTracker;

public class GaqRenderer {
	private static final GaqPushRenderer GAQ_PUSH_RENDERER = new GaqPushRenderer();

	public void writeGaqSnippetWithCommandsToWriter(
			List<CommandForTracker> commands, Writer writer) throws IOException {
		writer.write("  var _gaq = _gaq || [];\n");
		for (CommandForTracker command : commands) {
			writer.write("  ");
			GAQ_PUSH_RENDERER.writeCodeForCommandToWriter(command, writer);
			writer.write("\n");
		}
	}
}
