package com.chatapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "vote_answers")
public class VoteAnswerEntity extends BaseEntity {

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "count_vote", nullable = false)
    private Integer countVote;

    @Column(name = "vote_user_ids", nullable = false)
    private String voteUserIds;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private QuestionEntity question;

}