package com.chatapp.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "business_infos")
@Data
public class BusinessesInfoEntity extends BaseEntity {

    @Column(name = "representor", nullable = false)
    private String representor;

    @Column(name = "tax_code", nullable = false)
    private String tax_code;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "active_time", nullable = false)
    private String active_time;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}
