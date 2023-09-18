package com.chatapp.dto;

import com.chatapp.util.DateTimeUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

import java.util.Date;

@Data
public class BaseDTO {
    private Long id;
    private Date createdAt = new Date();
    private Date updatedAt = new Date();
    private String createdBy;
    private String updatedBy;
}
