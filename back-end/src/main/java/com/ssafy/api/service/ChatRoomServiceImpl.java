package com.ssafy.api.service;

import com.ssafy.api.request.ChatRoomJoinUserPostReq;
import com.ssafy.db.entity.ChatRoom;
import com.ssafy.db.entity.Message;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("chatRoomService")
public class ChatRoomServiceImpl implements ChatRoomService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    ChatRoomRepository chatRoomRepository;

    public Message chattingHandler(Message message) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(message.toString());
        User user = userRepository.findById(message.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));
        message.setUserId(user.getId());
//        message.setMessage(message.getMessage());

        return messageRepository.save(message);
    }

    public ChatRoom createChatRoom(ChatRoomJoinUserPostReq joinUserInfo) {

        Long userId = joinUserInfo.getUserId();
        Long consultantId = joinUserInfo.getConsultantId();

        ChatRoom chatRoom = ChatRoom.builder().userId(userId).consultantId(consultantId).build();

        return chatRoomRepository.save(chatRoom);
    }

    public ChatRoom findChatRoomByUserIdAndConsultantId(Long userId, Long consultantId) {

        ChatRoom chatRoom = chatRoomRepository.findByUserIdAndConsultantId(userId,consultantId).orElse(null);

        return chatRoom;
    }

    @Override
    public ChatRoom getChatRoomByChatRoomId(Long id) {

        ChatRoom chatRoom = chatRoomRepository.findChatRoomById(id);

        return chatRoom;
    }

    public List<ChatRoom> getChatRoomByUserId(Long userId) {
        List<ChatRoom> chatRooms = chatRoomRepository.findChatRoomByUserId(userId);

        return chatRooms;
    }


}