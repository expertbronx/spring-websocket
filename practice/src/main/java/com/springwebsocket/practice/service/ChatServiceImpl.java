package com.springwebsocket.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springwebsocket.practice.domain.ChatMessage;
import com.springwebsocket.practice.mapper.ChatMapper;

@Service("chatServiceImpl")
@Transactional
public class ChatServiceImpl implements ChatService {
	
	@Autowired
	private ChatMapper chatMapper;
	
	
	public int sendChat( ChatMessage chatMessage ) {
		return chatMapper.sendChat(chatMessage);
	}
	
	public int reportUser( String addr ) {
		return chatMapper.reportUser(addr);
	}
	
}
