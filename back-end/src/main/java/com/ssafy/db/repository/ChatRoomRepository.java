package com.ssafy.db.repository;

import com.ssafy.db.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    Optional<ChatRoom> findByUserIdAndConsultantId(Long userId, Long consultantId);
    List<ChatRoom> findChatRoomByUserId(Long userId);
    ChatRoom findChatRoomById(Long id);
}
