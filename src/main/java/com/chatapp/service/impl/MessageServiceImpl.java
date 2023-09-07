package com.chatapp.service.impl;

import com.chatapp.converter.request.MessageRequestConverter;
import com.chatapp.converter.response.MessageResponseConverter;
import com.chatapp.dto.request.MessageRequestDTO;
import com.chatapp.dto.response.MessageResponseDTO;
import com.chatapp.entity.MessageEntity;
import com.chatapp.repository.CustomizedMessageRepository;
import com.chatapp.repository.MessageRepository;
import com.chatapp.repository.UserRepository;
import com.chatapp.service.MessageService;
import com.chatapp.util.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRequestConverter messageRequestConverter;
    @Autowired
    private MessageResponseConverter messageResponseConverter;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private CustomizedMessageRepository customizedMessageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenProvider tokenProvider;

    @Override
    public List<MessageResponseDTO> findBySenderOrReceiver(Long senderId, Long receiverId) {
        if (userRepository.findById(senderId).isPresent()
                && userRepository.findById(receiverId).isPresent()) {
            return messageResponseConverter.toDTOGroup(customizedMessageRepository.findBySenderOrReceiver(senderId, receiverId));
        }

        throw new RuntimeException("user_does_not_exists");
    }

    @Override
    public MessageRequestDTO save(MessageRequestDTO messageDTO) {
        if (userRepository.findById(messageDTO.getSenderId()).isPresent()
                && userRepository.findById(messageDTO.getReceiverId()).isPresent()) {
            return messageRequestConverter.toDTO(messageRepository.save(messageRequestConverter.toEntity(messageDTO)));
        }

        throw new RuntimeException("user_does_not_exists");
    }

    @Override
    public MessageRequestDTO delete(Long messageId) {
        Optional<MessageEntity> messageEntity = messageRepository.findById(messageId);

        if (messageEntity.isPresent()) {
            MessageRequestDTO messageDTO = messageRequestConverter.toDTO(messageEntity.get());
            messageRepository.deleteById(messageId);
            return messageDTO;
        }

        throw new RuntimeException("message_does_not_exists");
    }
}
