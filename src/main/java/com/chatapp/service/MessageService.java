package com.chatapp.service;

import com.chatapp.dto.request.MessageRequestDTO;
import com.chatapp.dto.response.MessageResponseDTO;

import java.util.List;

public interface MessageService {
    List<MessageResponseDTO> findBySenderOrReceiver(Long senderId, Long receiverId);
    MessageRequestDTO save(MessageRequestDTO messageDTO);
    MessageRequestDTO delete(Long messageId);
}
