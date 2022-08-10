package com.ashley.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private double amount;
	private Date date;
	private String status;
	private String shippingAddress;
	private String billingAddress;
	//add reference to user table
	//need to add a way to store user payment information?

}
