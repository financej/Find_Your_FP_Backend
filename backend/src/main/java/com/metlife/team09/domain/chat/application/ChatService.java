package com.metlife.team09.domain.chat.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.metlife.team09.domain.chat.persistence.Chat;
import com.metlife.team09.domain.chat.persistence.ChatRepository;
import com.metlife.team09.domain.member.persistence.Member;
import com.metlife.team09.domain.member.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class ChatService {

    private final ObjectMapper objectMapper;
    private final ChatRepository chatRepository;
    private final MemberRepository memberRepository;

    public List<Chat> findAllRoom() {
        return chatRepository.findAll();
    }

    public Chat findRoomById(Long roomId) {
        return chatRepository.findById(roomId).orElseThrow(RuntimeException::new);
    }

    public Chat createRoom(Long plannerId) {
        Member member = memberRepository.findById(plannerId).orElseThrow(RuntimeException::new);

        Chat chat = Chat.builder()
                .planner(member)
                .build();

        Chat savedChat = chatRepository.save(chat);

        return savedChat;
    }
}
