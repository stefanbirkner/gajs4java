package com.github.stefanbirkner.gajs4java.core.render;

import java.io.IOException;
import java.io.Writer;

import com.github.stefanbirkner.gajs4java.core.model.AnalyticsScript;
import com.github.stefanbirkner.gajs4java.core.model.Protocol;

public class LoadAnalyticsScriptRenderer {
	private static final String FIRST_LINE = "  (function() {\n";
	private static final String SECOND_LINE = "    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;\n";
	private static final String THIRD_LINE_PREFIX = "    ga.src = ";
	private static final String THIRD_LINE_CLIENT = THIRD_LINE_PREFIX
			+ "('https:' == document.location.protocol ? '%s' : '%s') + '%s';\n";
	private static final String FOURTH_LINE = "    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);\n";
	private static final String FIFTH_LINE = "  })();";

	public void writeScriptLoadingCodeToWriter(Writer w, Protocol protocol,
			AnalyticsScript script) throws IOException {
		w.write(FIRST_LINE);
		w.write(SECOND_LINE);
		w.write(thirdLineForProtocolAndAnalyticsScript(protocol, script));
		w.write(FOURTH_LINE);
		w.write(FIFTH_LINE);
	}

	private String thirdLineForProtocolAndAnalyticsScript(Protocol protocol,
			AnalyticsScript script) {
		switch (protocol) {
		case HTTP:
			return thirdLineWithUrlParts(script.getFirstPartOfUrlForHttp(),
					script.getSecondPartOfUrl());
		case HTTPS:
			return thirdLineWithUrlParts(script.getFirstPartOfUrlForHttps(),
					script.getSecondPartOfUrl());
		case DECIDE_BY_JAVASCRIPT:
			return String.format(THIRD_LINE_CLIENT,
					script.getFirstPartOfUrlForHttps(),
					script.getFirstPartOfUrlForHttp(),
					script.getSecondPartOfUrl());
		case DECIDE_BY_RENDERER:
			throw new IllegalArgumentException(
					protocol
							+ " is not supported. Please choose the protocol before calling the renderer.");
		default:
			throw new IllegalArgumentException("The protocol " + protocol
					+ " is not supported.");
		}
	}

	private String thirdLineWithUrlParts(String firstPart, String secondPart) {
		return THIRD_LINE_PREFIX + "'" + firstPart + secondPart + "';\n";
	}
}
