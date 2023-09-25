package com.chatapp.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "conversations")
public class ConversationEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private UserEntity sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private UserEntity receiver;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "conversation_message", joinColumns = @JoinColumn(name = "conversation_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "message_id", nullable = false))
    private List<MessageEntity> messages;
}