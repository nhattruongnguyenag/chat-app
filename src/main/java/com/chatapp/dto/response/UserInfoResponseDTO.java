package com.chatapp.dto.response;

import com.chatapp.dto.BaseDTO;
import lombok.Data;

@Data
public class UserInfoResponseDTO extends BaseDTO {
    private String username;
    private String fullName;
    private Byte status;
}
