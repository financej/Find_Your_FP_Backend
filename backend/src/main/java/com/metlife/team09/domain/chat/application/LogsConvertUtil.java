package com.metlife.team09.domain.chat.application;

import java.util.List;

import org.springframework.stereotype.Component;

import com.metlife.team09.domain.chat.persistence.ChatLog;

@Component
public class LogsConvertUtil {

	public String convertToAiRequest(List<ChatLog> chatLogs) {
		StringBuilder sb = new StringBuilder();

		for (ChatLog chat : chatLogs) {
			sb.append(chat.getSenderId()).append(":").append(chat.getMessage()).append("\n");
		}

		return sb.toString();
	}
}
