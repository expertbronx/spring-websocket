package com.springwebsocket.practice.contoller;



import javax.servlet.http.HttpServletRequest;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.springwebsocket.practice.domain.ChatMessage;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class StompChatController {
	
    private final SimpMessagingTemplate template; //특정 Broker로 메세지를 전달

    //Client가 SEND할 수 있는 경로
    //stompConfig에서 설정한 applicationDestinationPrefixes와 @MessageMapping 경로가 병합됨
    //"/pub/chat/enter"
    
    @MessageMapping("/chat/enter")
    public void enter(ChatMessage message){
        message.setMessage("!@#$%상대방이 채팅방에 참여하였습니다.");
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }

    @MessageMapping("/chat/message")
    public void message(ChatMessage message){
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
    
    @MessageMapping("/chat/report")
    public void report(ChatMessage message){
    	message.setMessage("!@#$%신고");
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
    
    //신고 기능을 위한 ip 정보를 메세지에 담아 connect된 주소로 전달
    @MessageMapping("/chat/addr")
    public void addr(ChatMessage message, HttpServletRequest req){
        message.setMessage(req.getRemoteAddr());
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
 }