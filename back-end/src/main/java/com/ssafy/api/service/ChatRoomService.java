package com.ssafy.api.service;

import com.ssafy.api.request.ChatRoomJoinUserPostReq;
import com.ssafy.db.entity.ChatRoom;
import com.ssafy.db.entity.Message;
import com.ssafy.db.entity.User;

import java.util.List;

public interface ChatRoomService {
    Message chattingHandler(Message message);
    ChatRoom createChatRoom(ChatRoomJoinUserPostReq joinUserInfo);
    ChatRoom findChatRoomByUserIdAndConsultantId(Long userId, Long consultantId);
    ChatRoom getChatRoomByChatRoomId(Long id);
    List<ChatRoom> getChatRoomByUserId(Long userId);
    List<Long> getUsersByChatRoomId(Long chatRoomId);
}
