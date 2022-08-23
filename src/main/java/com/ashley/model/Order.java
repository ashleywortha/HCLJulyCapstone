package com.ashley.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="orders")
public class Order {
	@Id 
	@Column(name="ORDER_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private double amount;
	private Date date;
	private String status;
	private String shippingAddress;
	private String billingAddress;
	
	//add many to one relationship one user can have many orders
//	private int userId;
//	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="userId", nullable = false)
	private User user;
	
	
	@ManyToMany(cascade= {CascadeType.ALL})
	@JoinTable(name="ORDER_PRODUCT",
				joinColumns= {@JoinColumn(name="ORDER_ID")},
				inverseJoinColumns= {@JoinColumn(name="PRODUCT_ID")})
	private Set<Product> products = new HashSet<>();

						
	

}
