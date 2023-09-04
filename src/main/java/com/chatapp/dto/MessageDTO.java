package com.chatapp.dto;

import lombok.Data;

@Data
public class MessageDTO extends BaseDTO {
    private String content;
    private Long senderId;
    private Long receiverId;
}
