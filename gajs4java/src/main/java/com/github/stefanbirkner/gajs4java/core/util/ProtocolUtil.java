package com.github.stefanbirkner.gajs4java.core.util;

import static com.github.stefanbirkner.gajs4java.core.model.Protocol.HTTP;
import static com.github.stefanbirkner.gajs4java.core.model.Protocol.HTTPS;

import javax.servlet.ServletRequest;

import com.github.stefanbirkner.gajs4java.core.model.Protocol;

public class ProtocolUtil {
	private ProtocolUtil() {
	}

	public static Protocol finalProtocolForRequestAndBaseProtocol(
			ServletRequest request, Protocol protocol) {
		if (protocol == Protocol.DECIDE_BY_RENDERER)
			return protocolForRequest(request);
		else
			return protocol;
	}

	private static Protocol protocolForRequest(ServletRequest request) {
		String scheme = request.getScheme();
		if (scheme.equals("http"))
			return HTTP;
		else if (scheme.equals("https"))
			return HTTPS;
		else
			throw new IllegalArgumentException("The scheme '" + scheme
					+ "' is not supported. Only http and https are allowed.");
	}
}
