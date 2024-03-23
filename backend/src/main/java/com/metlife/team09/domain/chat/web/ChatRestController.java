package com.metlife.team09.domain.chat.web;

import com.metlife.team09.domain.chat.application.ChatLogService;
import com.metlife.team09.domain.chat.persistence.ChatLog;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.metlife.team09.domain.chat.application.ChatService;
import com.metlife.team09.domain.chat.application.LogsConvertUtil;
import com.metlife.team09.domain.chat.application.dto.ChatRoomRequestDto;
import com.metlife.team09.domain.chat.application.dto.ChatRoomResponseDto;
import com.metlife.team09.domain.chat.application.dto.ChatSummaryResponseDto;
import com.metlife.team09.domain.chat.application.dto.EndChatRoomRequestDto;
import com.metlife.team09.domain.chat.persistence.Chat;

import lombok.RequiredArgsConstructor;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ChatRestController {
    private final ChatService chatService;
    private final ChatLogService chatLogService;
    private final LogsConvertUtil logsConvertUtil;


    @PostMapping("/chats")
    public ResponseEntity<ChatRoomResponseDto> createRoom(@RequestParam Long userId) {
        Chat room = chatService.createRoom(userId);
        ChatRoomResponseDto response = ChatRoomResponseDto.from(room);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/chats")
    public ResponseEntity<ChatRoomResponseDto> chatRoom(@RequestParam final ChatRoomRequestDto requestDto){
        final Chat chat = chatService.findRoomById(requestDto.roomId());

        chatService.joinChat(requestDto);

        final ChatRoomResponseDto response = ChatRoomResponseDto.from(chat);

        return ResponseEntity.ok(response);
    }

    // ws 프로토콜로 세션 종료후 호출해야함
    @DeleteMapping("/chats")
    public void endChatRoom(@ModelAttribute final EndChatRoomRequestDto requestDto) {
        chatService.endChatRoom(requestDto);
    }

    @GetMapping("/api/chat/summary/{roomId}")
    public ResponseEntity<ChatSummaryResponseDto> getSummary(@PathVariable Long roomId) {

        List<ChatLog> chatLogs = chatLogService.getChatLogs(roomId);

        logsConvertUtil.convertToAiRequest(chatLogs);

        return null;

    }
}
