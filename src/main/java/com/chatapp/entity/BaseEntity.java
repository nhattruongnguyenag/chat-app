package com.chatapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Data
@MappedSuperclass
public abstract class BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "createddate", updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
	@CreatedDate
	private Date createdAt = new Date();
	
	@Column(name = "modifieddate", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
	@LastModifiedDate
	private Date updatedAt = new Date();
	
	// @Column(name = "createdby")
	// @CreatedBy
	// private String createdBy;
	
	// @Column(name = "modifiedby")
	// @LastModifiedBy
	// private String modifiedBy;
}
