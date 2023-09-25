package com.chatapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "short_answers")
public class ShortAnswerEntity extends BaseEntity {
    
    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private QuestionEntity question;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}