package com.chatapp.dto.request;

import com.chatapp.dto.BaseDTO;
import lombok.Data;

@Data
public class MessageRequestDTO extends BaseDTO {
    private String content;
    private String type;
    private Long senderId;
    private Long receiverId;
}
