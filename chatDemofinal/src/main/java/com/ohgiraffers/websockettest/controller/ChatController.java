package com.ohgiraffers.websockettest.controller;

import com.ohgiraffers.websockettest.entity.ChatMessageEntity;
import com.ohgiraffers.websockettest.entity.ChatRoomEntity;
import com.ohgiraffers.websockettest.service.ChatMessageService;
import com.ohgiraffers.websockettest.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;
    private final ChatMessageService chatMessageService;

    // REST API <- 규칙에 위배되는 이름 설계야! 그래서 안돼.
    // REST API의 핵심은? 자원의 이름(URL)과 자원에 대한 행위(HTTP Method)의 분리
    // 두 번째로 중요한게, "계층!"
    // GET /chat (목록 조회)
    // GET /chat/{id} (한 건 조회)
    // POST /chat (채팅방 생성)

    // 1. 경로 정리 <- RequestMapping
    // 2. 이름이 장황해졌어! 왤까?
    // 3. 맞아, 짧은 이름보단 길어도 괜찮으니까 차라리 긴게 나아
    // 4. 근거는 나중에 이거 RESTful API로 바꿀 수도 있을 것 같아서
    // 그래서 `render`를 붙였어

    // Yub
    // 현재 채팅하고 있는 채팅방 전체리스트를 보여주는곳
    @GetMapping("/chatList")
    public String renderChatRoomList(Model model) {
        List<ChatRoomEntity> roomList = chatService.findAllRoom();
        model.addAttribute("roomList", roomList);
        return "chat/chatList";
    }

    // 아까 계층 얘기했었잖아? 이렇게 API URL을 써주면 `/chat/3` 이런 식으로 요청할 수 있어
    // 보통 이렇게 많이 해
    // /kmu/departments/3/students/20181615
    // Department 3 : 소프트웨어학부
    // Student 20181615 : 학번이 "20181615"인 학생
    // 사실 복잡한 설계야 HATEOAS라고 있는데 이런 것까지,, 알면 좋고 모르면 좋고
    // 현업에서 이렇게 빡센수준으로 REST 규칙을 지키진 않아, 이건 "논문"
    // 로이 필딩이 고안한 API 설계 기법인데, 이 사람 말 그대로 다 지켜서 하지는 보통 않는다
    // 근데 이정도는,,,,,, 해주는 편이라서 알려주는 것!
    // Request Param? <-> HTTP Query Parameter
    // Query? "질의" <- Database 용어
    // 페이지네이션, 필터링 같은 정보를 보통 쓰는 것 같고, RESTful API 디자인에서는
    // 보통 path variable로 써준다!

    // Yub
//    @GetMapping("/chat/{id}")
//    public String renderChatRoomPage(Model model, @PathVariable long id){
//        ChatRoomEntity room = chatService.findById(id);
//        model.addAttribute("room", room);
//        return "chat/chatList";
//    }

    // 채팅방 만들기를 했을때 해당 채팅방으로 바로 접속하고
    // 채팅방에 내용을 보여주는 곳
    @PostMapping("/createRoom")
    private String createRoom(Model model, @RequestBody RoomCreateReq requestDto) {
        ChatRoomEntity newRoom = chatService.createRoom(requestDto.name);
        ChatMessageEntity newMessage = chatMessageService.createMessage(requestDto.name);
        model.addAttribute("room", newRoom);
        model.addAttribute("message", newMessage);
        return "chat/chatRoomMessage";
    }

    // 채팅방 전체리스트에서 특정 채팅방을 선택하고 삭제를 했을때
    // 특정 채팅방이 삭제된 후 채팅방 전체리스트를 다시 보여주는 곳


    // 비어 있으면 무슨 뜻일까? 어떤 주소로 요청을 줘야 할까?
    // 맞춰봐.
    // 1. /chat : @PostMapping("")
    // 2. /chat/ : @PostMapping("/")
    // 1번이야, 근데 이걸로 아마 코딩 인생에 한 세 번쯤은 골머리 썩어볼꺼라서 미리 알려줌 ㅋㅋㅋㅋ
    // Yub
//    @PostMapping("/chatRoom")
//    private String createRoomAndRedirectToChatRoomPage(Model model, @RequestBody RoomCreateReq requestDto) {
//        ChatRoomEntity newRoom = chatService.createRoom(requestDto.name);
//        model.addAttribute("room", newRoom);
//        return "chat/chatRoomMessage";
//    }

    // Domain은 요즘은 POJO로 쓰는게 정석,,? 느낌인뎅
    // Plain Old Java Object
    // Entity는 Spring Data JPA라는 Repository layer의 기술이야
    // 확신할 수 있어? 프로젝트 끝날 때까지 저것만 쓸 거라고?
    // MongoDB List<Entity> <- 중간 객체, Java로만 된
    // 그래야 나중에 호오오오오옥시 repository에 변경이 생겨도
    // 기술 변경 때문에 우리 회사의 소중한 도메인 로직이 변경되는 불상사를 피할 수 있어

    // 프로젝트 구조를 만들 때 크게 두 가지 스타일이 있는데
    // 1. Layer로 구성 <- Config, Controller, Service, Repository가 최상단 패키지 이름이 됨
    // 2. Domain으로 구성
    // ex. 카페 키오스크를 만든다?
    // /coffee
    //   / controller
    //   / service
    //   / repository
    // /employee
    //   / controller
    //   / service
    //   / repository
    // 왜냐 하면,, 가면 갈수록 많아지는데 감당이 안되니까
    // 같은 분야의 코드끼리 모아놓는거야
    // 나중에는 요즘에 MSA, Microservice Architecture
    // 지금은 서버 하나에다가 모든 로직을 다 박아넣는데 이걸 Monolothic
    // 기업 서비스 규모가 커지면 아예 인증 서버, 커피 관리 서버, 직원 관리 서버
    // 각각의 Microservice들이 서로 통신하면서 여러가지 기업 내의 서비스를 만들도록
    // 하는 아키텍처 스타일이 있어, 어려운 얘기고, 나중에 공부하면 돼
    // 이 MSA로 전환하기에 1번보단 2번이 당연히 쉽겠지?

    // 내 취향은 repository 안에다가 그냥 Entity 클래스들도 같이 넣어버림

    // DTO가 뭐죠?
    // Data Transfer Object
    // "계층" 간의 전송
    // 우리가 3-tier arch.를 쓰잖아? controller-service-repo.
    // 나누는 이유는 유지보수의 용이함, 계층간의 논리적인 결합 정도를 낮추는게 목표
    // 그래서 쓰는게 DTO야, 왜냐? 아까 얘기한 것처럼 Repository 레이어의 객체가
    // 컨트롤러까지 뚫고 올라오지 않게 하려고
    // + 도메인 객체가 직접 움직이지 않게 하기 위함이라서
    // 원래는 controller, service, repository 사이사이에 DTO를 다 따로 만드는게
    // 맞,, 는 말이긴 해
    // 생각만해도 귀찮다 그죠? 귀찮잖아
    // 그래서 보통은 DTO를 따로 떼어서 쓰긴 하는데, 뭐,,, entity를 직접
    // 응답으로 주는 정도가 아니라면 지금 레벨에선 크게 고려안해도 될 것 가탕
    // 근데 서비서 규모가 커지면,, 생각해봐야겠지? <- POJO

    // VO는 뭐죠?

    // 주로 데이터를 내보내는 경우(DTO), 설정 클래스, 특수한 데이터를 얻으려면 record메시지를 보내기
    record RoomCreateReq(
            String name
    ) {
    }
}