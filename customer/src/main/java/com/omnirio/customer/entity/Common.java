package com.omnirio.customer.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@MappedSuperclass
public class Common {

	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "creation_dttm")
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	@Column(name = "modified_by")
	private String modifiedBy;
	
	@Column(name = "modified_dttm")
	@UpdateTimestamp
	private LocalDateTime modifiedDate;
	
	@Column(name = "version")
	@Version
	private int version;
}
