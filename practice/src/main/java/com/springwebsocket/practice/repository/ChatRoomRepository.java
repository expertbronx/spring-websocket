package com.springwebsocket.practice.repository;

import org.springframework.stereotype.Repository;

import com.springwebsocket.practice.domain.ChatRoom;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class ChatRoomRepository {

    private Map<String, ChatRoom> chatRoomMap;

    @PostConstruct
    private void init(){
        chatRoomMap = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllRooms(){
        //채팅방 생성 순서 최근 순으로 반환
        List<ChatRoom> result = new ArrayList<>(chatRoomMap.values());
        Collections.reverse(result);

        return result;
    }

    public ChatRoom findRoomById(int roomNumber){
        return chatRoomMap.get(Integer.toString(roomNumber));
    }

    public ChatRoom createChatRoomDTO(int roomNumber){
        ChatRoom room = ChatRoom.create(roomNumber);
        chatRoomMap.put(room.getRoomId(), room);
        
        return room;
    }
}
