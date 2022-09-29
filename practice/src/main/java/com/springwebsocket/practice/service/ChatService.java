package com.springwebsocket.practice.service;

import com.springwebsocket.practice.domain.ChatMessage;

public interface ChatService {
	
	public int sendChat( ChatMessage chatMessage );

	public int reportUser( String addr, String message );
	
}
