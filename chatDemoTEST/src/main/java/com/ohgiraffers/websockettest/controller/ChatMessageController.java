package com.ohgiraffers.websockettest.controller;

import com.ohgiraffers.websockettest.dto.ChatMessageDTO;
import com.ohgiraffers.websockettest.entity.ChatMessageEntity;
import com.ohgiraffers.websockettest.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// "계층" 채팅방과 채팅 메시지의 관계는? ChattingRoom : ChattingMessage = 1 : N
// 힌트는 @PathVariable
@Controller
@RequiredArgsConstructor
@RequestMapping("/chatMessage")
public class ChatMessageController {

    private final ChatMessageService chatMessageService;

    @GetMapping("/chatRoom")
    public String chatRoom(Model model) {
        List<ChatMessageEntity> messageList = chatMessageService.findAllMessage();
        model.addAttribute("messageList", messageList);
        return "/chat/chatRoomMessage";
    }

    @GetMapping("/{id}")
    public String renderChatRoomPage(Model model, @PathVariable long id) {
        ChatMessageEntity message = chatMessageService.findMessageById(id);
        model.addAttribute("message", message);
        return "/chat/chatRoomMessage";
    }

    @PostMapping("/createMessage")
    private String createMessage(Model model, @RequestParam ChatMessageDTO chatMessageDTO){
        ChatMessageEntity newMessage = chatMessageService.createMessage(chatMessageDTO.getMessage());
        model.addAttribute("message", newMessage);
        return "/chat/chatRoomMessage";
    }


    @PostMapping
    private String createMessageAndRedirectToChatMessagePage(Model model, @RequestParam ChatMessageDTO chatMessageDTO){
        ChatMessageEntity newMessage = chatMessageService.createMessage(chatMessageDTO.getMessage());
        model.addAttribute("message", newMessage);
        return "/chat/chatRoomMessage";
    }
}