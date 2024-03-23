package com.metlife.team09.domain.chat.application;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.metlife.team09.domain.chat.application.dto.ChatSummaryResponseDto;
import com.metlife.team09.domain.chat.persistence.ChatLog;
import com.metlife.team09.domain.member.persistence.Member;
import com.metlife.team09.domain.member.persistence.MemberRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LogsConvertUtil {
	private final MemberRepository memberRepository;

	@Transactional(readOnly = true)
	public ChatSummaryResponseDto convertToAiRequest(List<ChatLog> chatLogs) {
		StringBuilder sb = new StringBuilder();

		Long plannerId = -1L;
		Long customerId = -1L;

		for (ChatLog chat : chatLogs) {
			sb.append(chat.getSenderId()).append(":").append(chat.getMessage()).append("\n");

			if(chat.getPlannerId() != null && plannerId.equals(-1L)) {
				plannerId = chat.getPlannerId();
			}
			if(chat.getCustomerId() != null && customerId.equals(-1L)) {
				customerId = chat.getCustomerId();
			}
		}

		String plannerEmail = "";
		String customerEmail = "";

		if(plannerId != -1L) {
			Member planner = memberRepository.findById(plannerId).orElseThrow(RuntimeException::new);
			plannerEmail = planner.getEmail();
		}
		if(customerId != -1L) {
			Member customer = memberRepository.findById(customerId).orElseThrow(RuntimeException::new);
			customerEmail = customer.getEmail();
		}

		String aiResult = sb.toString();
		return new ChatSummaryResponseDto(plannerEmail, customerEmail, aiResult);
	}
}
