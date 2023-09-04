package com.chatapp.converter;

import com.chatapp.dto.MessageDTO;
import com.chatapp.entity.MessageEntity;
import com.chatapp.entity.UserEntity;
import com.chatapp.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MessageConverter {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;

    public MessageDTO toDTO(MessageEntity messageEntity) {
        MessageDTO messageDTO = modelMapper.map(messageEntity, MessageDTO.class);
        messageDTO.setSenderId(messageEntity.getSender().getId());
        messageDTO.setReceiverId(messageEntity.getReceiver().getId());
        return messageDTO;
    }

    public List<MessageDTO> toListDTO(List<MessageEntity> messageEntityList) {
        return messageEntityList.stream().map(messageEntity -> toDTO(messageEntity)).collect(Collectors.toList());
    }

    public MessageEntity toEntity(MessageDTO messageDTO) {
        MessageEntity messageEntity = modelMapper.map(messageDTO, MessageEntity.class);
        UserEntity sender = userRepository.findOneById(messageDTO.getSenderId());
        UserEntity receiver = userRepository.findOneById(messageDTO.getReceiverId());

        if (sender == null) {
            throw new RuntimeException("sender_does_not_exists");
        }

        if (receiver == null) {
            throw new RuntimeException("receiver_doest_not_exists");
        }

        messageEntity.setSender(sender);
        messageEntity.setReceiver(receiver);

        return messageEntity;
    }
}
