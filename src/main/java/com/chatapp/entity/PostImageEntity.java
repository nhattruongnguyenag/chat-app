package com.chatapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "posts_images")
public class PostImageEntity extends BaseEntity {

    @Column(name = "uri", nullable = false)
    private String uri;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post;
}