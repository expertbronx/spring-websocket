package com.springwebsocket.practice.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.springwebsocket.practice.domain.ChatMessage;

@Mapper
public interface ChatMapper {
	
	public int sendChat( ChatMessage chatMessage );
	
	public int reportUser( @Param("ipAddress")String addr, @Param("reportedReason")String reportedReason );


}
