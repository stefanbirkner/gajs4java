package com.github.stefanbirkner.gajs4java.core.render;

import java.io.IOException;
import java.io.Writer;

import com.github.stefanbirkner.gajs4java.core.model.TrackingConfiguration;

public class TrackingConfigurationRenderer {
	private static final GaqRenderer GAQ_RENDERER = new GaqRenderer();
	private static final LoadAnalyticsScriptRenderer LOAD_ANALYTICS_SCRIPT_RENDERER = new LoadAnalyticsScriptRenderer();
	private static final String START_TAG = "<script type=\"text/javascript\">\n";
	private static final String EMPTY_LINE = "\n";
	private static final String END_TAG = "\n</script>";

	public void writeTrackingConfigurationToWriter(
			TrackingConfiguration configuration, Writer writer)
			throws IOException {
		writer.write(START_TAG);
		writer.write(EMPTY_LINE);
		GAQ_RENDERER.writeGaqSnippetWithCommandsToWriter(
				configuration.getCommands(), writer);
		writer.write(EMPTY_LINE);
		LOAD_ANALYTICS_SCRIPT_RENDERER
				.writeScriptLoadingCodeToWriter(writer,
						configuration.getProtocol(),
						configuration.getAnalyticsScript());
		writer.write(EMPTY_LINE);
		writer.write(END_TAG);
	}
}
