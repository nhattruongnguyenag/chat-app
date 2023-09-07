package com.chatapp.converter.response;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.MessageResponseDTO;
import com.chatapp.entity.MessageEntity;
import com.chatapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageResponseConverter extends BaseConverter<MessageEntity, MessageResponseDTO> {
    @Autowired
    private UserMessageResponseConverter userMessageResponseConverter;
    @Autowired
    private UserRepository userRepository;

    @Override
    public MessageResponseDTO toDTO(MessageEntity messageEntity) {
        MessageResponseDTO messageResponse = super.toDTO(messageEntity);
        messageResponse.setSender(userMessageResponseConverter.toDTO(messageEntity.getSender()));
        messageResponse.setReceiver(userMessageResponseConverter.toDTO(messageEntity.getReceiver()));
        return messageResponse;
    }
}
