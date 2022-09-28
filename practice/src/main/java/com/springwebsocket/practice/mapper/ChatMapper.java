package com.springwebsocket.practice.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.springwebsocket.practice.domain.ChatMessage;

@Mapper
public interface ChatMapper {
	
	public int sendChat( ChatMessage chatMessage );
	
	public int reportUser( String addr );


}
