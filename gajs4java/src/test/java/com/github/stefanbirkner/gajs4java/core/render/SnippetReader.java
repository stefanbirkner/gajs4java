package com.github.stefanbirkner.gajs4java.core.render;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

class SnippetReader {
	static String snippetWithName(String name) throws IOException {
		InputStream is = SnippetReader.class.getResourceAsStream(name);
		return IOUtils.toString(is);
	}
}
