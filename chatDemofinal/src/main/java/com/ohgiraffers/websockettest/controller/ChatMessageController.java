package com.ohgiraffers.websockettest.controller;

import com.ohgiraffers.websockettest.entity.ChatMessageEntity;
import com.ohgiraffers.websockettest.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// 코딩은 직접 따라 해야 늘어용 생각하면서 따라하구
// 학교 다시 다니는 것 같당 ㅎㅎ
// 따단,, 해봐해봐 막 손떨린다 그죠? 뭐가 맞는지 모르겠다 그죠???
// 때애ㅐ애ㅐ애애ㅐ애ㅐ애ㅐㅐ애애애애ㅐ애애ㅐㅐ애애ㅐㅐ애ㅐㅐㅇㅐ애애앵
// "계층" 채팅방과 채팅 메시지의 관계는? ChattingRoom : ChattingMessage = 1 : N
// 채팅 메시지가 채팅룸의 "하위" 개념이야 글치?
// 채팅 메시지 목록을 받아온다, URL은? ->
// ^^^^^ 막 떼굴떼굴 머리 굴러가는 소리가 세종대까지 들리는데 ^^^^^
// 나는~~ 읽기쉬운 마음이야~~~
// 힌트는 @PathVariable
@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatMessageController {

    private final ChatMessageService chatMessageService;

    // 지금 구현으로는 내채팅방이든 남채팅방이든 메시지를 싹 긁어오고 있다 그치?
    // 그럼 된다 안된다????
    // 탕!
    // Foreign Key가 없는데? 안배웠어?~
    // 바보였슴 ㅋㅋ
    // 바보 하나 추가 ㅋㅋ
    // 그래!!!! 외래 키!!!!! 외국인 키 ㅋㅋㅋㅋㅋㅋㅋㅋㅋ
    // 면접가서 꼭 외국인 키라고 해
    // 그리고 나한테 후기좀
    // 그건 Literal이라고 해용
    @GetMapping("/chatRoom")
    public String chatRoom(Model model) {
//        List<ChatMessageEntity> chatMessage = chatMessageService.findMessageByMe(me);
//        model.addAttribute("chatMessageList", chatMessageList);
        return "chat/chatRoom";
    }


    @PostMapping("/chat/createMessage")
    public String createMessage(Model model, @RequestParam String message) {
        List<ChatMessageEntity> messages = null;
//        if (chatMessageService.createMessage(message) == 1) {
//            messages = chatMessageService.findAllMessage();
//        }
        model.addAttribute("messages", messages);
        return "chat/chatRoom";
    }
}