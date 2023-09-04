package com.template.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role")
@Data
public class RoleEntity extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String code;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<UserEntity> users = new ArrayList<>();
}
