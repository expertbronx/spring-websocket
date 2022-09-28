
            
            $(document).ready(function(){

                var roomName = $("#roomNumber").val();
                var roomId = $("#roomId").val();
                var username = $("#nickName").val();

                console.log(roomName + ", " + roomId + ", " + username);

                var sockJs = new SockJS("/stomp/chat");
                //1. SockJS를 내부에 들고있는 stomp를 내어줌
                var stomp = Stomp.over(sockJs);

                //2. connection이 맺어지면 실행
                stomp.connect({}, function (){
                   console.log("STOMP Connection")

                   //4. subscribe(path, callback)으로 메세지를 받을 수 있음
                   stomp.subscribe("/sub/chat/room/" + roomId, function (chat) {
                       var content = JSON.parse(chat.body);

                       var writer = content.writer;
                       var message = content.message;
                       var str = '';

                       if( writer === username ){
                           str = "<div class='me'>";
                           str += "<div class='chat-bubble mine'>";
                           str += "<span class='chat-message'>";
                           if( message === '상대방이 채팅방에 참여하였습니다.' ){
                           str += "<b>" + "채팅방에 입장하셨습니다." + "</b><br/>"
                           	   +  "<b>" + "대화 상대 입장까지 잠시만 기다려주세요." + "</b>";
                           }else{
                           str += "<b>" + "나" + " : " + message + "</b>";
                           }
                           str += "</div></div>";
                           $("#msgArea").append(str);
                       }else{
                           str = "<div class='other'>";              
                           str += "<div class='chat-bubble'>";
                           str += "<span class='chat-message'>";
                           if( message === '상대방이 채팅방에 참여하였습니다.'){
                           str = "<div class='enter'>";
                           str += "<div class='alert alert-success'>";
                           str += "<b>" + "대화 상대가 채팅방에 참여하였습니다." + "</b>";
                           }else{
                           str += "<b>" + "상대" + " : " + message + "</b>";
                           }
                           str += "</div></div>";
                           $("#msgArea").append(str);
                       }	
                       	   
                       	   var divdiv = document.getElementById("msgArea"); 
						   divdiv.scrollTop = divdiv.scrollHeight; 
                   });
                   
                   	console.log('stomp Subscribe Test [o][o][o][o][o]');

                   //3. send(path, header, message)로 메세지를 보낼 수 있음
                   stomp.send('/pub/chat/enter', {}, JSON.stringify({roomId: roomId, writer: username}))
                   console.log('입장 안내 메시지 출력');
                   
                });

               		document.addEventListener('keydown', (event) => {
               		if ( event.key == 'Enter'){
                    var msg = document.getElementById("msg");

                    console.log(username + ":" + msg.value);
                    stomp.send('/pub/chat/message', {}, JSON.stringify({roomId: roomId, message: msg.value, writer: username}));
                    msg.value = '';
                    }
                });
                
                	$('#other').click(function(){
                		stomp.unsubscribe(roomName);
                		stomp.disconnect();
                		location.href="/chat/matching";
                	});
                	
                	$('#exit').click(function(){
                		stomp.unsubscribe(roomName);
                		stomp.disconnect();
                		location.href="/chat/";
                	});
                
            });
