package com.raghavx.auth.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.raghavx.auth.model.User;

import lombok.Data;

/**
 * Instantiates a new billing.
 */
@Data
@Entity
@Table(name = "BILLING" )
public class Billing {
	
	/** The billing id. */
	@Id
	@Column(name="BILLING_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long billingId;
	
	/** The user id. */
	@OneToOne(fetch=FetchType.LAZY,targetEntity=User.class,cascade=CascadeType.ALL)
    @JoinColumn(name="USER_ID")
	private User user;
	
	/** The available DND credit. */
	@Column(name="AVAILABLE_DND_CREDIT")
	private Long availableDNDCredit;	

}
