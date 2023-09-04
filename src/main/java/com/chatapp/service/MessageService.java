package com.chatapp.service;

import com.chatapp.dto.MessageDTO;

import java.util.List;

public interface MessageService {
    List<MessageDTO> findBySenderOrReceiver(Long senderId, Long receiverId);
    MessageDTO save(MessageDTO messageDTO);
    MessageDTO delete(Long messageId);
}
