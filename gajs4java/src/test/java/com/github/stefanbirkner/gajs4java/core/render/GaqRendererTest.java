package com.github.stefanbirkner.gajs4java.core.render;

import static com.github.stefanbirkner.gajs4java.core.model.CommandForTrackerUtil.wrapCommandsForDefaultTracker;
import static com.github.stefanbirkner.gajs4java.core.render.SnippetReader.snippetWithName;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.StringWriter;
import java.util.List;

import org.junit.Test;

import com.github.stefanbirkner.gajs4java.core.model.CommandForTracker;
import com.github.stefanbirkner.gajs4java.core.model.SetAccount;
import com.github.stefanbirkner.gajs4java.core.model.TrackPageview;

public class GaqRendererTest {
	private final GaqRenderer renderer = new GaqRenderer();

	@Test
	public void renderesGaqSnippet() throws Exception {
		List<CommandForTracker> commands = wrapCommandsForDefaultTracker(
				new SetAccount("UA-XXXXX-X"), new TrackPageview());
		StringWriter writer = new StringWriter();
		renderer.writeGaqSnippetWithCommandsToWriter(commands, writer);
		assertThat(writer.toString(),
				is(equalTo(snippetWithName("gaq.snippet"))));
	}
}
