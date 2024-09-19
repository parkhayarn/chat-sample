package com.ohgiraffers.websockettest.controller;

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
    private String createMessage(Model model, @RequestBody MessageCreateReq requestDto){
        ChatMessageEntity newMessage = chatMessageService.createMessage(requestDto .name);
        model.addAttribute("message", newMessage);
        return "/chat/chatRoomMessage";
    }


    @PostMapping
    private String createMessageAndRedirectToChatMessagePage(Model model, @RequestBody MessageCreateReq requestDto){
        ChatMessageEntity newMessage = chatMessageService.createMessage(requestDto.name);
        model.addAttribute("message", newMessage);
        return "/chat/chatRoomMessage";
    }

    // 주로 데이터를 내보내는 경우(DTO), 설정 클래스, 특수한 데이터를 얻으려면 record메시지를 보내기
    record MessageCreateReq(
            String name
    ){

    }
}