package com.ssafy.api.service;

import com.ssafy.db.entity.Message;
import com.ssafy.db.repository.MessageRepository;
import com.ssafy.db.repository.MessageRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("messageService")
public class MessageServiceImpl implements MessageService{

    @Autowired
    MessageRepositorySupport messageRepositorySupport;

    @Autowired
    MessageRepository messageRepository;

    @Override
    public List<Message> getByChatRoomId(Long chatRoomId) {

        List<Message> message = messageRepositorySupport.findByChatRoomId(chatRoomId);

        return message;
    }

    @Override
    public Message getLastMessageByChatRoomId(Long chatRoomId) {
        Message message = messageRepositorySupport.findLastMessageByChatRoomId(chatRoomId);


        return message;
    }
}
