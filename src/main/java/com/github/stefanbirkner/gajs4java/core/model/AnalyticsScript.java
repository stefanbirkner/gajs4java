package com.github.stefanbirkner.gajs4java.core.model;

public enum AnalyticsScript {
	STANDARD {
		@Override
		public String getFirstPartOfUrlForHttp() {
			return "http://www";
		}

		@Override
		public String getFirstPartOfUrlForHttps() {
			return "https://ssl";
		}

		@Override
		public String getSecondPartOfUrl() {
			return ".google-analytics.com/ga.js";
		}
	},
	WITH_REMARKETING { // http://support.google.com/analytics/bin/answer.py?hl=en&answer=2444872
		@Override
		public String getFirstPartOfUrlForHttp() {
			return "http://";
		}

		@Override
		public String getFirstPartOfUrlForHttps() {
			return "https://";
		}

		@Override
		public String getSecondPartOfUrl() {
			return "stats.g.doubleclick.net/dc.js";
		}
	};

	public abstract String getFirstPartOfUrlForHttp();

	public abstract String getFirstPartOfUrlForHttps();

	public abstract String getSecondPartOfUrl();
}
