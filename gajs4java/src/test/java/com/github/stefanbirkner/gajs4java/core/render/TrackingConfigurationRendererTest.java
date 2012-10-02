package com.github.stefanbirkner.gajs4java.core.render;

import static com.github.stefanbirkner.gajs4java.core.render.SnippetReader.snippetWithName;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.StringWriter;

import org.junit.Test;

import com.github.stefanbirkner.gajs4java.core.model.CommandForTracker;
import com.github.stefanbirkner.gajs4java.core.model.SetAccount;
import com.github.stefanbirkner.gajs4java.core.model.TrackPageview;
import com.github.stefanbirkner.gajs4java.core.model.TrackingConfiguration;

public class TrackingConfigurationRendererTest {
	private final TrackingConfigurationRenderer renderer = new TrackingConfigurationRenderer();

	@Test
	public void rendersTrackingConfigurationWithJavaScriptProtocolDecision()
			throws Exception {
		CommandForTracker setAccount = new CommandForTracker(new SetAccount(
				"UA-XXXXX-X"));
		CommandForTracker trackPageview = new CommandForTracker(
				new TrackPageview());
		TrackingConfiguration configuration = new TrackingConfiguration(asList(
				setAccount, trackPageview));
		StringWriter writer = new StringWriter();
		renderer.writeTrackingConfigurationToWriter(configuration, writer);
		assertThat(writer.toString(),
				is(equalTo(snippetWithName("complete.javascript.snippet"))));
	}
}
