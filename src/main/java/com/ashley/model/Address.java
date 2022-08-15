package com.ashley.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="address")
public class Address{
	@Id 
	@Column(name="addressId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String street;
	private String apartmentNumber;
	private String city;
	private String state;
	private String zipcode;
	private String country;
	
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name="userId")
//	private User user;
	

}
