package com.springwebsocket.practice.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springwebsocket.practice.domain.ChatMessage;
import com.springwebsocket.practice.service.ChatService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/sanction/")
public class ChatRestController {
	
	//Field
	@Autowired
	@Qualifier("chatServiceImpl")
	private ChatService chatService;
	
	@GetMapping("json/chat")
	public int sendChat( ChatMessage chatMessage ) {
		
		log.info("RestController = {} ", "/sanction/json/chat : GET start... ");
		chatService.sendChat(chatMessage);
		
		return 0;
	}
	
	@GetMapping("json/report")
	public int reportUser( String addr ) {
		
		log.info("RestController = {} ", "/sanction/json/report/reportUser : GET start... ");
		chatService.reportUser(addr);
		
		return 1;
	}
	
	

}
