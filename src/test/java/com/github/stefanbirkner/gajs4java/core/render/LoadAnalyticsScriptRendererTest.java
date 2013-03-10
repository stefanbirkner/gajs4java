package com.github.stefanbirkner.gajs4java.core.render;

import static com.github.stefanbirkner.gajs4java.core.model.AnalyticsScript.STANDARD;
import static com.github.stefanbirkner.gajs4java.core.model.AnalyticsScript.WITH_REMARKETING;
import static com.github.stefanbirkner.gajs4java.core.model.Protocol.DECIDE_BY_JAVASCRIPT;
import static com.github.stefanbirkner.gajs4java.core.model.Protocol.DECIDE_BY_RENDERER;
import static com.github.stefanbirkner.gajs4java.core.model.Protocol.HTTP;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.rules.ExpectedException.none;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.github.stefanbirkner.gajs4java.core.model.AnalyticsScript;
import com.github.stefanbirkner.gajs4java.core.model.Protocol;

public class LoadAnalyticsScriptRendererTest {
	private final LoadAnalyticsScriptRenderer renderer = new LoadAnalyticsScriptRenderer();

	@Rule
	public final ExpectedException thrown = none();

	@Test
	public void rendersStandardSnippet() throws IOException {
		assertSnippetForProtocolAndScript("ga.js.javascript.snippet",
				DECIDE_BY_JAVASCRIPT, STANDARD);
	}

	@Test
	public void rendersSnippetWithRemarketingSupport() throws IOException {
		assertSnippetForProtocolAndScript("remarketing.snippet",
				DECIDE_BY_JAVASCRIPT, WITH_REMARKETING);
	}

	@Test
	public void rendersHttpSnippet() throws IOException {
		assertSnippetForProtocolAndScript("ga.js.http.snippet", HTTP, STANDARD);
	}

	@Test
	public void failsToRenderDecideOnServer() throws IOException {
		thrown.expect(IllegalArgumentException.class);
		renderer.writeScriptLoadingCodeToWriter(new StringWriter(),
				DECIDE_BY_RENDERER, STANDARD);
	}

	private void assertSnippetForProtocolAndScript(String snippetName,
			Protocol protocol, AnalyticsScript script) throws IOException {
		StringWriter w = new StringWriter();
		renderer.writeScriptLoadingCodeToWriter(w, protocol, script);
		assertEquals(w.toString(), snippetWithName(snippetName));
		assertThat(w.toString(), is(equalTo(snippetWithName(snippetName))));
	}

	private String snippetWithName(String name) throws IOException {
		InputStream is = getClass().getResourceAsStream(name);
		return IOUtils.toString(is);
	}
}
