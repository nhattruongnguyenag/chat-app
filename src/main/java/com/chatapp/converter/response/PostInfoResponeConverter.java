package com.chatapp.converter.response;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.PostInfoResponeDTO;
import com.chatapp.entity.PostEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostInfoResponeConverter extends BaseConverter<PostEntity,PostInfoResponeDTO>{

    @Autowired
    private UserInfoResponseConverter userInfoResponseConverter;

    @Override
    public PostInfoResponeDTO toDTO(PostEntity entity) {
        PostInfoResponeDTO postInfoResponeDTO = super.toDTO(entity);
        postInfoResponeDTO.setUser(userInfoResponseConverter.toDTO(entity.getUser()));
        return postInfoResponeDTO;
    }
    
}
