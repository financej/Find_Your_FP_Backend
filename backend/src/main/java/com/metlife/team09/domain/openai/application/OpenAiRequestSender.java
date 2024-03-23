package com.metlife.team09.domain.openai.application;

import com.metlife.team09.domain.openai.enums.ConversationType;

public interface OpenAiRequestSender {
	public String getResult(ConversationType type, String conversation);
}
