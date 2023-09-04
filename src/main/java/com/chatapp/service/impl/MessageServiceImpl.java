package com.chatapp.service.impl;

import com.chatapp.converter.MessageConverter;
import com.chatapp.dto.MessageDTO;
import com.chatapp.dto.UserDTO;
import com.chatapp.entity.MessageEntity;
import com.chatapp.repository.CustomizedMessageRepository;
import com.chatapp.repository.MessageRepository;
import com.chatapp.repository.UserRepository;
import com.chatapp.service.MessageService;
import com.chatapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageConverter messageConverter;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private CustomizedMessageRepository customizedMessageRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<MessageDTO> findBySenderOrReceiver(Long senderId, Long receiverId) {
        if (userRepository.findById(senderId).isPresent()
                && userRepository.findById(receiverId).isPresent()) {
            return messageConverter.toListDTO(customizedMessageRepository.findBySenderOrReceiver(senderId, receiverId));
        }

        throw new RuntimeException("user_does_not_exists");
    }

    @Override
    public MessageDTO save(MessageDTO messageDTO) {
        if (userRepository.findById(messageDTO.getSenderId()).isPresent()
                && userRepository.findById(messageDTO.getReceiverId()).isPresent()) {
            return messageConverter.toDTO(messageRepository.save(messageConverter.toEntity(messageDTO)));
        }

        throw new RuntimeException("user_does_not_exists");
    }

    @Override
    public MessageDTO delete(Long messageId) {
        Optional<MessageEntity> messageEntity = messageRepository.findById(messageId);

        if (messageEntity.isPresent()) {
            MessageDTO messageDTO = messageConverter.toDTO(messageEntity.get());
            messageRepository.deleteById(messageId);
            return messageDTO;
        }

        throw new RuntimeException("message_does_not_exists");
    }
}
