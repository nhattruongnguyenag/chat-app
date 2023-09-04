package com.chatapp.repository;

import com.chatapp.entity.MessageEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomizedMessageRepository {
    List<MessageEntity> findBySenderOrReceiver(Long senderId, Long receiverId);
}
