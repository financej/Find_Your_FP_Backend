package com.metlife.team09.domain.openai.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.models.ChatChoice;
import com.azure.ai.openai.models.ChatCompletions;
import com.azure.ai.openai.models.ChatCompletionsOptions;
import com.azure.ai.openai.models.ChatRequestMessage;
import com.azure.ai.openai.models.ChatRequestSystemMessage;
import com.azure.ai.openai.models.ChatRequestUserMessage;
import com.metlife.team09.domain.openai.enums.ConversationType;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OpenAiUtil {
	private final OpenAiConfig openAiConfig;

	public String getMessages(ChatCompletions chatCompletions) {
		StringBuilder sb = new StringBuilder();

		for (ChatChoice choice : chatCompletions.getChoices()) {
			sb.append(choice.getMessage().getContent()).append("\n");
		}
		return sb.toString();
	}

	public ChatCompletions sendChat(List<ChatRequestMessage> chatMessages) {
		OpenAIClient client = openAiConfig.getOpenAiClient();
		return client.getChatCompletions(openAiConfig.getModelName(),
			new ChatCompletionsOptions(chatMessages));
	}

	public List<ChatRequestMessage> generateChatMessages(ConversationType type, String conversation) {
		String requestContent = type.getRequestContent();
		List<ChatRequestMessage> chatMessages = new ArrayList<>();
		chatMessages.add(new ChatRequestSystemMessage(requestContent));
		chatMessages.add(new ChatRequestUserMessage(conversation));
		return chatMessages;
	}
}
