package com.metlife.team09.domain.chat.web;

import java.util.List;

import com.metlife.team09.domain.chat.application.ChatService;
import com.metlife.team09.domain.chat.application.LogsConvertUtil;
import com.metlife.team09.domain.chat.application.dto.ChatRoomRequestDto;
import com.metlife.team09.domain.chat.application.dto.ChatRoomResponseDto;
import com.metlife.team09.domain.chat.application.dto.ChatSummaryResponseDto;
import com.metlife.team09.domain.chat.application.dto.EndChatRoomRequestDto;
import com.metlife.team09.domain.chat.persistence.Chat;
import com.metlife.team09.domain.chat.persistence.ChatLog;
import com.metlife.team09.domain.chat.persistence.ChatRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class ChatRestController {
    private final ChatService chatService;
    private final ChatLogService chatLogService;
    private final LogsConvertUtil logsConvertUtil;


    @PostMapping("/chats")
    public ResponseEntity<ChatRoomResponseDto> createRoom(@RequestParam Long userId) {
        Chat room = chatService.createRoom(1L);
        ChatRoomResponseDto response = ChatRoomResponseDto.from(room);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/chats")
    public ResponseEntity<ChatRoomResponseDto> chatRoom(@RequestParam ChatRoomRequestDto requestDto){
        Chat chat = chatService.findRoomById(requestDto.roomId());
        chatService.joinChat(requestDto);
        ChatRoomResponseDto response = ChatRoomResponseDto.from(chat);

        return ResponseEntity.ok(response);
    }

    // ws 프로토콜로 세션 종료후 호출해야함
    @DeleteMapping("/chats")
    public void endChatRoom(@RequestParam final EndChatRoomRequestDto requestDto) {
        chatService.endChatRoom(requestDto);
    }

    @GetMapping("/api/chat/summary/{roomId}")
    public ResponseEntity<ChatSummaryResponseDto> getSummary(@PathVariable Long roomId) {

        List<ChatLog> chatLogs = chatLogService.getChatLogs(roomId);

        logsConvertUtil.convertToAiRequest(chatLogs);

        return null;

    }
}
