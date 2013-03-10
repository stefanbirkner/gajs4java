package com.github.stefanbirkner.gajs4java.core.render;

import static com.github.stefanbirkner.gajs4java.core.model.Protocol.DECIDE_BY_JAVASCRIPT;
import static com.github.stefanbirkner.gajs4java.core.model.Protocol.DECIDE_BY_RENDERER;
import static com.github.stefanbirkner.gajs4java.core.model.Protocol.HTTP;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.rules.ExpectedException.none;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.github.stefanbirkner.gajs4java.core.model.Protocol;

public class LoadAnalyticsScriptRendererTest {
	private final LoadAnalyticsScriptRenderer renderer = new LoadAnalyticsScriptRenderer();

	@Rule
	public final ExpectedException thrown = none();

	@Test
	public void rendersStandardSnippet() throws IOException {
		assertSnippetForProtocol("ga.js.javascript.snippet",
				DECIDE_BY_JAVASCRIPT);
	}

	@Test
	public void rendersHttpSnippet() throws IOException {
		assertSnippetForProtocol("ga.js.http.snippet", HTTP);
	}

	@Test
	public void failsToRenderDecideOnServer() throws IOException {
		thrown.expect(IllegalArgumentException.class);
		renderer.writeGaJsInsertStatementToWriter(new StringWriter(),
				DECIDE_BY_RENDERER);
	}

	private void assertSnippetForProtocol(String snippetName, Protocol protocol)
			throws IOException {
		StringWriter w = new StringWriter();
		renderer.writeGaJsInsertStatementToWriter(w, protocol);
		assertThat(w.toString(), is(equalTo(snippetWithName(snippetName))));
	}

	private String snippetWithName(String name) throws IOException {
		InputStream is = getClass().getResourceAsStream(name);
		return IOUtils.toString(is);
	}
}
