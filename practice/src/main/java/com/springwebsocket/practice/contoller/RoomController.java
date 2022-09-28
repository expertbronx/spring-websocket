package com.springwebsocket.practice.contoller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.springwebsocket.practice.repository.ChatRoomRepository;




@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/chat")
@Log4j2
public class RoomController {

    private final ChatRoomRepository repository;
    
    private int userCount = 0;

    //채팅방 목록 조회
    @GetMapping(value = "/rooms")
    public ModelAndView rooms(){

        log.info("# All Chat Rooms");
        ModelAndView mv = new ModelAndView("rooms");

        mv.addObject("list", repository.findAllRooms());

        return mv;
    }
    
    //메인페이지
    @GetMapping("/")
	public String main( ){
	
	    log.info("@ChatController, main GET()");
	      
	    return "main";
	  }

    //채팅방 개설
    @PostMapping(value = "/room")
    public void create(int roomNubmer){

        log.info("# Create Chat Room , name: " + roomNubmer);
        repository.createChatRoomDTO(roomNubmer);

    }

    //채팅방 조회
    @GetMapping("/room")
    public String getRoom(int roomNumber, Model model, HttpServletRequest req){
        log.info("# get Chat Room, roomID : " + roomNumber);
        model.addAttribute("addr", req.getRemoteAddr());
        model.addAttribute("room", repository.findRoomById(roomNumber));
        model.addAttribute("nickname", randomNick());
        
        return "room";
    }
    
    //매칭한 유저 수 카운트
    public void plusUserCount() {
    	userCount++;
    }
    
    //매칭한 유저 수 기준으로 방 만들기 or 방 입장하기 기능을 사용하는 매서드 
    @GetMapping(value = "/matching")
    public String chattingMatching( Model model, HttpServletRequest req ) {
    	
    	plusUserCount();
    	
    	if( userCount==1) {
    		create(userCount);
    		model.addAttribute("room", repository.findRoomById(userCount));
            model.addAttribute("nickname", randomNick());
    		getRoom(userCount, model, req);
    	}else if( userCount%2 == 0 ) {
    		model.addAttribute("room", repository.findRoomById(userCount/2));
            model.addAttribute("nickname", randomNick());
    		getRoom(userCount/2, model, req);
    	}else{
    		create((userCount+1)/2);
    		model.addAttribute("room", repository.findRoomById((userCount+1)/2));
            model.addAttribute("nickname", randomNick());
    		getRoom((userCount+1)/2, model, req);
    	}
    	
    	return "room";
    }
    
    //알파벳 10자리 랜덤 닉네임 생성 로직 
    public String randomNick() {
    	int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    	return generatedString;
    }
    
}
