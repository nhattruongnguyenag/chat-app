package com.chatapp.converter.request;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.request.MessageRequestDTO;
import com.chatapp.entity.MessageEntity;
import org.springframework.stereotype.Component;

@Component
public class MessageRequestConverter extends BaseConverter<MessageEntity, MessageRequestDTO> {
    @Override
    public MessageEntity toEntity(MessageRequestDTO messageRequestDTO) {
        return super.toEntity(messageRequestDTO);
    }
}
