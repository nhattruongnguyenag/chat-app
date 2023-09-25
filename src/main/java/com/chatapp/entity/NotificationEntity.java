package com.chatapp.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "notifications")
@Data
public class NotificationEntity extends BaseEntity {

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}
