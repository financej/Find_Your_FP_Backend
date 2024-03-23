package com.metlife.team09.domain.chat.application.dto;

import com.metlife.team09.domain.chat.persistence.Chat;
import com.metlife.team09.domain.member.persistence.Member;

public record ChatSummaryResponseDto(
	Member planner,
	Member customer,
	String result
) { }
