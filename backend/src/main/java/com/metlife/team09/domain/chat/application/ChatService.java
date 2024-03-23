package com.metlife.team09.domain.chat.application;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.metlife.team09.domain.chat.application.dto.ChatRoomRequestDto;
import com.metlife.team09.domain.chat.application.dto.EndChatRoomRequestDto;
import com.metlife.team09.domain.chat.exception.ChatRoomNotFoundException;
import com.metlife.team09.domain.chat.persistence.Chat;
import com.metlife.team09.domain.chat.persistence.ChatRepository;
import com.metlife.team09.domain.chat.persistence.ChatSocketSessionHandler;
import com.metlife.team09.domain.member.exception.MemberNotFound;
import com.metlife.team09.domain.member.persistence.Member;
import com.metlife.team09.domain.member.persistence.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ChatService {

    private final ObjectMapper objectMapper;
    private final ChatRepository chatRepository;
    private final MemberRepository memberRepository;

    public List<Chat> findAllRoom() {
        return chatRepository.findAll();
    }

    public Chat findRoomById(final Long roomId) {
        return chatRepository.findById(roomId)
                .orElseThrow(ChatRoomNotFoundException::new);
    }

    public Chat createRoom(final Long plannerId) {
        Member member = memberRepository.findById(plannerId)
                .orElseThrow(MemberNotFound::new);

        Chat chat = Chat.builder()
                .build();

        if(member.getIsAdmin()) {
            chat.updateChatPlanner(member);
        } else {
            chat.updateChatCustomer(member);
        }

        Chat savedChat = chatRepository.save(chat);

        return savedChat;
    }

    public Chat joinChat(final ChatRoomRequestDto requestDto) {
        final Member member = memberRepository.findById(requestDto.roomId())
                .orElseThrow(MemberNotFound::new);
        final Chat chat = chatRepository.findById(requestDto.roomId())
                .orElseThrow(ChatRoomNotFoundException::new);

        if(member.isAdmin) {
            chat.updateChatPlanner(member);
        } else {
            chat.updateChatCustomer(member);
        }

        return chat;
    }

    public void endChatRoom(final EndChatRoomRequestDto requestDto) {
        final Set<WebSocketSession> sessions = ChatSocketSessionHandler.getSessions();

        if(sessions.size() != 0) { // session이 모두 끝
            return;
        }

        final Chat chat = chatRepository.findById(requestDto.roomId())
                .orElseThrow(RuntimeException::new);
        chatRepository.delete(chat);
    }
}
