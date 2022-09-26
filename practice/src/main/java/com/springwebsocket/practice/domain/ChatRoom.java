package com.springwebsocket.practice.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ChatRoom {

    private String roomId;
    private int roomNumber;
    private Set<WebSocketSession> sessions = new HashSet<>();
    //WebSocketSession은 Spring에서 Websocket Connection이 맺어진 세션

    public static ChatRoom create(int roomNumber){
        ChatRoom room = new ChatRoom();

        room.roomId = Integer.toString(roomNumber);
        room.roomNumber = roomNumber;
        return room;
    }
}