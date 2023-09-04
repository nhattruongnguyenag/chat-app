package com.chatapp.converter;

import com.chatapp.dto.UserDTO;
import com.chatapp.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter {
    @Autowired
    private ModelMapper modelMapper;

    public UserDTO toDTO(UserEntity userEntity) {
        UserDTO userDTO = userEntity != null ? modelMapper.map(userEntity, UserDTO.class) : null;
        return userDTO;
    }

    public List<UserDTO> toListDTO(List<UserEntity> userEntityList) {
        return userEntityList.stream().map(userEntity -> toDTO(userEntity)).collect(Collectors.toList());
    }

    public UserEntity toEntity(UserDTO userDTO) {
        UserEntity userEntity = userDTO != null ? modelMapper.map(userDTO, UserEntity.class) : null;
        return userEntity;
    }
}
