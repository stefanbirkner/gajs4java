package com.github.stefanbirkner.gajs4java.taglib;

import static com.github.stefanbirkner.gajs4java.core.model.Protocol.DECIDE_BY_JAVASCRIPT;
import static com.github.stefanbirkner.gajs4java.core.model.Protocol.DECIDE_BY_RENDERER;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.github.stefanbirkner.gajs4java.core.model.Protocol;
import com.github.stefanbirkner.gajs4java.core.render.InsertGaJsRenderer;

public class InsertGaJsTag extends SimpleTagSupport {
	private static final InsertGaJsRenderer RENDERER = new InsertGaJsRenderer();
	private Protocol protocol = DECIDE_BY_JAVASCRIPT;

	public void setProtocol(Protocol protocol) {
		this.protocol = protocol;
	}

	@Override
	public void doTag() throws IOException {
		Writer w = getJspContext().getOut();
		if (protocol == DECIDE_BY_RENDERER)
			RENDERER.writeGaJsInsertStatementToWriterUsingRequest(w,
					((PageContext) getJspContext()).getRequest());
		else
			RENDERER.writeGaJsInsertStatementToWriter(w, protocol);
	}
}
