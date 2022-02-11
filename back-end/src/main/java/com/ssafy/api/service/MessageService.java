package com.ssafy.api.service;

import com.ssafy.db.entity.Message;

import java.util.List;

public interface MessageService {
    List<Message> getByChatRoomId(Long chatRoomId);
    Message getLastMessageByChatRoomId(Long chatRoomId);
}
