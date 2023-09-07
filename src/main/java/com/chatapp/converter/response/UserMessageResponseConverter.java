package com.chatapp.converter.response;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.UserMessageResponseDTO;
import com.chatapp.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMessageResponseConverter extends BaseConverter<UserEntity, UserMessageResponseDTO> {
}
