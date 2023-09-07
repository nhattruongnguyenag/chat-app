package com.chatapp.converter.response;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.UserInfoResponseDTO;
import com.chatapp.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserInfoResponseConverter extends BaseConverter<UserEntity, UserInfoResponseDTO> {
}
