package com.ashley.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
	//add reference to user table
	//need to add a way to store user payment information?
	@ManyToMany(cascade= {CascadeType.ALL})
	@JoinTable(name="ORDER_PRODUCT",
				joinColumns= {@JoinColumn(name="ORDER_ID")},
				inverseJoinColumns= {@JoinColumn(name="PRODUCT_ID")})
	private Set<Product> products = new HashSet<>();
						
	

}
