package com.chatapp.dto;

import lombok.Data;

@Data
public class BaseDTO {
    private Long id;
    private String createdAt;
    private String updatedAt;
    private String createdBy;
    private String updatedBy;
}
