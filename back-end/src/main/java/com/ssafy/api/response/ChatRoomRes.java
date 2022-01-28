package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.ChatRoom;
import com.ssafy.db.repository.ChatRoomRepository;
import io.swagger.annotations.ApiModel;
import io.swagger.models.auth.In;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ApiModel("ChatRoomResponse")
public class ChatRoomRes extends BaseResponseBody {

    Long id;
    // 유저 아이디 따로 받아옴
    Long userId;

    // 해당 컨설턴트 아이디 따로 받아옴
    Long consultantId;

    LocalDateTime createdDate;
    List<ChatRoom> chatRooms;

    public static ChatRoomRes of(Integer stausCode, String message, List<ChatRoom> chatRoomList) {

        ChatRoomRes res = new ChatRoomRes();
        res.setStatusCode(stausCode);
        res.setMessage(message);
        res.setChatRooms(chatRoomList);
        return res;
    }


}
