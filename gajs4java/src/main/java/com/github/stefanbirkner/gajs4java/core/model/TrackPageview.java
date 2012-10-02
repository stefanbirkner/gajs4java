package com.github.stefanbirkner.gajs4java.core.model;

public class TrackPageview extends SimpleCommand {
	private static final String METHOD_NAME = "_trackPageview";

	public TrackPageview() {
		super(METHOD_NAME);
	}

	public TrackPageview(String pageUrl) {
		super(METHOD_NAME, pageUrl);
	}
}
