package com.chatapp.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "messages")
public class MessageEntity extends BaseEntity {
    @Column(nullable = false, updatable = false)
    private String content;

    @Column(nullable = false, updatable = false)
    private String type;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private UserEntity sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private UserEntity receiver;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "conversation_message", joinColumns = @JoinColumn(name = "message_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "conversation_id", nullable = false))
    private List<ConversationEntity> conversations;
}
