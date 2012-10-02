package com.github.stefanbirkner.gajs4java.core.util;

import static com.github.stefanbirkner.gajs4java.core.model.Protocol.DECIDE_BY_RENDERER;
import static com.github.stefanbirkner.gajs4java.core.model.Protocol.HTTPS;
import static com.github.stefanbirkner.gajs4java.core.util.ProtocolUtil.finalProtocolForRequestAndBaseProtocol;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.rules.ExpectedException.none;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ProtocolUtilTest {
	@Rule
	public final ExpectedException thrown = none();

	@Test
	public void determinesOriginalProtocol() {
		HttpServletRequest request = requestWithScheme("http");
		assertThat(finalProtocolForRequestAndBaseProtocol(request, HTTPS),
				is(HTTPS));
	}

	@Test
	public void determinesHttpsBasedOnScheme() {
		HttpServletRequest request = requestWithScheme("https");
		assertThat(
				finalProtocolForRequestAndBaseProtocol(request,
						DECIDE_BY_RENDERER), is(HTTPS));
	}

	@Test
	public void failsForUnkownScheme() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("The scheme 'ftp' is not supported. Only http and https are allowed.");
		HttpServletRequest request = requestWithScheme("ftp");
		finalProtocolForRequestAndBaseProtocol(request, DECIDE_BY_RENDERER);
	}

	private HttpServletRequest requestWithScheme(String scheme) {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getScheme()).thenReturn(scheme);
		return request;
	}
}
