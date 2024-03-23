package com.metlife.team09.domain.openai.enums;

public enum ConversationType {

	SUMMARY("요약");

	private final String requestContent;

	ConversationType(String requestContent) {
		this.requestContent = requestContent;
	}

	public String getRequestContent() {
		return this.requestContent;
	}
}
