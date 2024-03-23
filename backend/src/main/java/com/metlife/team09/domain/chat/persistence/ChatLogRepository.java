package com.metlife.team09.domain.chat.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatLogRepository extends JpaRepository<ChatLog, Long> {

	List<ChatLog> findAllByRoomId(Long roomId);
}
