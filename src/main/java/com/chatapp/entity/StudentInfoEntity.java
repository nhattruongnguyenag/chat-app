package com.chatapp.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "student_infos")
@Data
public class StudentInfoEntity extends BaseEntity {

    @Column(name = "student_code", nullable = false)
    private String studentCode;

    @Column(name = "major", nullable = false)
    private String major;

    @Column(name = "faculty_name", nullable = false)
    private String faculty_name;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}
