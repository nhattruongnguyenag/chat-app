package com.chatapp.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class MessageResponseDTO {
    private String content;
    private String type;
    private UserMessageResponseDTO sender;
    private UserMessageResponseDTO receiver;
    private Date createdAt;
}
