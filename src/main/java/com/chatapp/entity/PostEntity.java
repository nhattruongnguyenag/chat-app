package com.chatapp.entity;



import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "posts")
public class PostEntity extends BaseEntity {
    @Column(name = "content", nullable = false, unique = true)
    private String content;

    @Column(name = "status", nullable = false, unique = true)
    private Byte status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}