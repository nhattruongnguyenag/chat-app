package com.chatapp.dto;

import lombok.Data;

@Data
public class UserDTO extends BaseDTO {
    private String fullName;
    private String username;
    private String password;
    private Byte status;
}
