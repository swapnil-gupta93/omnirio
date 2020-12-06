package com.omnirio.account.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account")
public class Account {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "account_id")
	private String accountId;
	
	@Column(name = "account_type")
	private String accountType;
	
	@Column(name = "open_date")
	private LocalDateTime openDate;
	
	@Column(name = "user_id")
	private String customerId;
	
	@Column(name = "customer_name")
	private String customerName;
	
	@Column(name = "branch")
	private String branch;
	
	@Column(name = "minor_indicator")
	private String minorIndicator;
		
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