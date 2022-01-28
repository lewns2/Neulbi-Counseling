package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.ChatRoom;
import com.ssafy.db.entity.QChatRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ChatRoomRepositorySupport {


    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QChatRoom qChatRoom = QChatRoom.chatRoom;

    public Optional<ChatRoom> findByUserIdAndConsultantId(Long userId, Long consultantId) {

        ChatRoom chatRoom = jpaQueryFactory.select(qChatRoom)
                .from(qChatRoom).where(qChatRoom.userId.eq(userId)
                        .and(qChatRoom.consultantId.eq(consultantId))).fetchOne();

        if(chatRoom == null) return Optional.empty();
        return Optional.ofNullable(chatRoom);
    }

    public List<ChatRoom> findChatRoomByUserId(Long userId) {

        List<ChatRoom> chatRooms = jpaQueryFactory.select(qChatRoom).from(qChatRoom).where(qChatRoom.userId.eq(userId).or(qChatRoom.consultantId.eq(userId))).fetch();

        if(chatRooms == null) return null;
        return chatRooms;
    }

    public ChatRoom findChatRoomById(Long id) {
        ChatRoom chatRoom = jpaQueryFactory.select(qChatRoom).from(qChatRoom).where(qChatRoom.id.eq(id)).fetchOne();

        return chatRoom;
    }
}
