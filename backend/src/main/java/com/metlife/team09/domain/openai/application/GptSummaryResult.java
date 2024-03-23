package com.metlife.team09.domain.openai.application;

import java.util.List;

import com.azure.ai.openai.models.ChatCompletions;
import com.azure.ai.openai.models.ChatRequestMessage;
import com.metlife.team09.domain.openai.enums.ConversationType;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GptSummaryResult implements GptResult{
	private final OpenAiUtil openAiUtil;
	@Override
	public String getResult(ConversationType type, String conversation) {
		
		// Open-AI에 보낼 채팅메시지 생성
		List<ChatRequestMessage> chatMessages = openAiUtil.generateChatMessages(type, conversation);

		// 연동된 AI 모델에 요약요청 전송
		ChatCompletions chatCompletions = openAiUtil.sendChat(chatMessages);

		// AI 모델의 응답을 String 메시지화 하여 응답
		return openAiUtil.getMessages(chatCompletions);
	}
}
