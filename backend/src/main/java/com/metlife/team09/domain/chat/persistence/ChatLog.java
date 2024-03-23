package com.metlife.team09.domain.chat.persistence;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatLog {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	public enum MessageType {
		ENTER, TALK, QUIT
	}
	@Enumerated
	private ChatMessage.MessageType type; // 메시지 타입
	private Long roomId; // 방번호
	private Long customerId;
	private Long plannerId;
	private Long senderId;
	private String message; // 메시지
}
