package com.ssafy.api.controller;

import com.ssafy.api.request.ChatRoomJoinUserPostReq;
import com.ssafy.api.response.ChatRoomRes;
import com.ssafy.api.response.MessageRes;
import com.ssafy.api.service.ChatRoomService;
import com.ssafy.api.service.MessageService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.ChatRoom;
import com.ssafy.db.entity.Message;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private MessageService messageService;

    // /receive를 메시지를 받을 endpoint로 설정합니다.
    @MessageMapping("/receive")
    // /send로 메시지를 반환합니다.
    @SendTo("/send")
    public Message chattingHandler(Message message) {
        return chatRoomService.chattingHandler(message);
    }


    @PostMapping("/chatroom/create")
    @ApiOperation(value = "채팅방 생성", notes = "<strong>유저아이디와 컨설턴트 아이디</strong>를 통해 채팅방을 생성 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> createChatRooom(@RequestBody ChatRoomJoinUserPostReq joiner) {

        Long userId = joiner.getUserId();
        Long consultantId = joiner.getConsultantId();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(userId);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>con");
        System.out.println(consultantId);
        ChatRoom chatRoom = chatRoomService.findChatRoomByUserIdAndConsultantId(userId,consultantId);

        if (chatRoom == null) {
            chatRoomService.createChatRoom(joiner);
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
        }

        else {
            // 해당 채팅방 정보 받아오기 위에서
            // 완료

            // 해당 채팅방의 메시지 정보 받아오기
            List<Message> message = messageService.getByChatRoomId(chatRoom.getId());

            return ResponseEntity.status(200).body(MessageRes.of(200,"Success",message));
            //return ResponseEntity.ok().body(MessageRes.of(message));
            //return (ResponseEntity<? extends BaseResponseBody>) ResponseEntity.ok(message);
        }
    }

    @GetMapping("/chatroom/{user_id}")
    @ApiOperation(value = "내 채팅방 정보", notes = "<strong>내 채팅방 정보</strong>불러오기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> getChatRoom(@PathVariable("user_id") Long userId) {

        System.out.println(userId);
        // 유저 아이디를 통해 해당 유저의 채팅방 정보를 얻어온다.
        List<ChatRoom> chatRooms = chatRoomService.getChatRoomByUserId(userId);

        return ResponseEntity.status(200).body(ChatRoomRes.of(200,"Success",chatRooms));
    }

    @GetMapping("/chatroom/{user_id}/{chatRoom_id}")
    @ApiOperation(value = "해당 컨설턴트와의 채팅방 정보", notes = "<strong>해당 컨설턴트와의 채팅방 정보</strong>불러오기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> getChatRoomDetail(@PathVariable("user_id") Long userId, @PathVariable("chatRoom_id") Long chatRoomId) {

        System.out.println(userId);
        // 메시지 테이블에서 채팅방 아이디에 해당하는 메시지들을 받아온다.
        List<Message> messages = messageService.getByChatRoomId(chatRoomId);

        return ResponseEntity.status(200).body(MessageRes.of(200,"Success",messages));
    }
}


//    // SocketHandler는 1) /receive에서 메시지를 받고, /send로 메시지를 보내줍니다.
//    // 정의한 SocketVO를 1) 인자값, 2) 반환값으로 사용합니다.
//    public ChatRoom SocketHandler(ChatRoom chatRoom) {
//        // vo에서 getter로 userName을 가져옵니다.
//        String userName = chatRoom.getUserName();
//        // vo에서 setter로 content를 가져옵니다.
//        String content = chatRoom.getContent();
//
//        // 생성자로 반환값을 생성합니다.
//        ChatRoom result = new ChatRoom(userName, content);
//        // 반환
//        return result;
//    }
//}
