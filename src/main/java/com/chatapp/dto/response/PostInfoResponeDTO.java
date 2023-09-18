package com.chatapp.dto.response;

import com.chatapp.dto.BaseDTO;


import lombok.*;

@Data
public class PostInfoResponeDTO extends BaseDTO{
    private String content;
    private Byte status;
    private UserInfoResponseDTO user;
}
