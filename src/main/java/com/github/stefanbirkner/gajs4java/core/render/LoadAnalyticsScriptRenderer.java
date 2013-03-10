package com.github.stefanbirkner.gajs4java.core.render;

import java.io.IOException;
import java.io.Writer;

import com.github.stefanbirkner.gajs4java.core.model.Protocol;

public class LoadAnalyticsScriptRenderer {
	private static final String FIRST_LINE = "  (function() {\n";
	private static final String SECOND_LINE = "    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;\n";
	private static final String THIRD_LINE_CLIENT = "    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';\n";
	private static final String THIRD_LINE_HTTP = "    ga.src = 'http://www.google-analytics.com/ga.js';\n";
	private static final String THIRD_LINE_HTTPS = "    ga.src = 'https://ssl.google-analytics.com/ga.js';\n";
	private static final String FOURTH_LINE = "    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);\n";
	private static final String FIFTH_LINE = "  })();";

	public void writeGaJsInsertStatementToWriter(Writer w, Protocol protocol)
			throws IOException {
		w.write(FIRST_LINE);
		w.write(SECOND_LINE);
		w.write(thirdLineForProtocol(protocol));
		w.write(FOURTH_LINE);
		w.write(FIFTH_LINE);
	}

	private String thirdLineForProtocol(Protocol protocol) {
		switch (protocol) {
		case HTTP:
			return THIRD_LINE_HTTP;
		case HTTPS:
			return THIRD_LINE_HTTPS;
		case DECIDE_BY_JAVASCRIPT:
			return THIRD_LINE_CLIENT;
		case DECIDE_BY_RENDERER:
			throw new IllegalArgumentException(
					protocol
							+ " is not supported. Please use writeGaJsInsertStatementToWriterUsingRequest(Writer, HttpServletRequest).");
		default:
			throw new IllegalArgumentException("The protocol " + protocol
					+ " is not supported.");
		}
	}
}
