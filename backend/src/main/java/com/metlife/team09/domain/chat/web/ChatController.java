package com.metlife.team09.domain.chat.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.metlife.team09.domain.chat.application.ChatService;
import com.metlife.team09.domain.chat.persistence.Chat;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @RequestMapping("/chat/chatList")
    public String chatList(Model model, @RequestParam Long userId){
        List<Chat> roomList = chatService.findAllRoom();
        model.addAttribute("roomList",roomList);
        model.addAttribute("userId", userId);
        return "chat/chatList";
    }

    @PostMapping("/chat/createRoom")  //방을 만들었으면 해당 방으로 가야지.
    public String createRoom(Model model) {
        Chat room = chatService.createRoom(1L);
        model.addAttribute("room", room);
        model.addAttribute("userId", 1L);
        return "chat/chatRoom";  //만든사람이 채팅방 1빠로 들어가게 됩니다
    }

    @GetMapping("/chat/chatRoom")
    public String chatRoom(Model model, @RequestParam Long roomId, @RequestParam Long userId){
        Chat room = chatService.findRoomById(roomId);
        model.addAttribute("room",room);
        model.addAttribute("userId", userId);
        return "chat/chatRoom";
    }
}
