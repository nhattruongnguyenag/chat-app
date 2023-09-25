package com.chatapp.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "falcuty_infos")
@Data
public class FalcutyInfoEntity extends BaseEntity {

    @Column(name = "faculty_name", nullable = false)
    private String facultyName;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}
