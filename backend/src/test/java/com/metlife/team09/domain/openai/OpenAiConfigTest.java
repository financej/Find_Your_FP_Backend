package com.metlife.team09.domain.openai;


import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.models.ChatChoice;
import com.azure.ai.openai.models.ChatCompletions;
import com.azure.ai.openai.models.ChatCompletionsOptions;
import com.azure.ai.openai.models.ChatRequestAssistantMessage;
import com.azure.ai.openai.models.ChatRequestMessage;
import com.azure.ai.openai.models.ChatRequestSystemMessage;
import com.azure.ai.openai.models.ChatRequestUserMessage;
import com.azure.ai.openai.models.ChatResponseMessage;
import com.metlife.team09.domain.openai.application.OpenAiRequestSender;
import com.metlife.team09.domain.openai.application.OpenAiUtil;
import com.metlife.team09.domain.openai.application.SummaryRequestSender;
import com.metlife.team09.domain.openai.enums.ConversationType;

@SpringBootTest
class OpenAiConfigTest {

	@Autowired
	OpenAiUtil openAiUtil;


	@Test
	public void getGptResultTest() {
		String conversation = "A: 안녕 나는 26살 오형석이야 너 소개좀 부탁해\n"
			+ "B: 안녕 나는 29살 배선영이야 반가워\n"
			+ "A: 너의 직업은 뭐니?\n"
			+ "B: 나는 개발자야";

		OpenAiRequestSender gptResult = new SummaryRequestSender(openAiUtil);
		String result = gptResult.getResult(ConversationType.SUMMARY, conversation);

		System.out.println("result : " + result);
		assertThat(result).isNotBlank();

	}
}