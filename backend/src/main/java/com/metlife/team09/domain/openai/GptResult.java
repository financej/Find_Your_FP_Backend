package com.metlife.team09.domain.openai;

import com.metlife.team09.domain.openai.enums.ConversationType;

public interface GptResult {
	public String getResult(ConversationType type, String conversation);
}
