package com.metlife.team09.domain.chat.web;

import com.metlife.team09.domain.chat.application.ChatService;
import com.metlife.team09.domain.chat.application.dto.ChatRoomRequestDto;
import com.metlife.team09.domain.chat.application.dto.ChatRoomResponseDto;
import com.metlife.team09.domain.chat.application.dto.EndChatRoomRequestDto;
import com.metlife.team09.domain.chat.persistence.Chat;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class ChatRestController {
    private final ChatService chatService;

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
}
