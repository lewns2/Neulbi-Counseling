package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Message;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
@Setter
@ApiModel("MessageResponse")
public class MessageRes extends BaseResponseBody {
    private List<Message> chatMessage;
    private Long chatRoomId;
    private Long userId;

    public static MessageRes of(Integer statusCode, String resMessage, List<Message> message) {

        MessageRes res = new MessageRes();
        res.setStatusCode(statusCode);
        res.setMessage(resMessage);
        res.setChatMessage(message);
//        for (Message msg: message) {
//            res.setMessage(msg.getMessage());
//            res.setChatRoomId(msg.getChatRoomId());
//            res.setUserId(msg.getUserId());
//        }

        return res;
    }
}
