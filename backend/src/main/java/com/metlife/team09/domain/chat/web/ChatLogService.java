package com.metlife.team09.domain.chat.web;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metlife.team09.domain.chat.persistence.Chat;
import com.metlife.team09.domain.chat.persistence.ChatLog;
import com.metlife.team09.domain.chat.persistence.ChatLogRepository;
import com.metlife.team09.domain.chat.persistence.ChatMessage;
import com.metlife.team09.domain.chat.persistence.ChatRepository;
import com.metlife.team09.domain.member.persistence.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ChatLogService {
	private final ChatLogRepository chatLogRepository;
	private final ChatRepository chatRepository;

	@Async
	public void saveChatLog(ChatMessage chatMessage) {
		Chat chat = chatRepository.findById(Long.valueOf(chatMessage.getRoomId())).orElseThrow(RuntimeException::new);

		Member planner = chat.getPlanner();
		Member customer = chat.getCustomer();

		Long plannerId = null;
		Long customerId = null;

		if(planner != null) {
			plannerId = planner.getId();
		}
		if(customer != null) {
			customerId = customer.getId();
		}

		ChatLog chatLog = ChatLog.builder()
			.message(chatMessage.getMessage())
			.customerId(customerId)
			.plannerId(plannerId)
			.senderId(Long.valueOf(chatMessage.getSenderId()))
			.roomId(Long.valueOf(chatMessage.getRoomId()))
			.build();

		log.info(">>>>>>>> async log saved : {}", chatLog);

		chatLogRepository.save(chatLog);
	}
}
