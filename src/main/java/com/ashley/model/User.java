package com.ashley.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user")
public class User{
	@Id 
	@Column(name="userId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private String password;
	private String contact;
	private String SSN;
	private String roles;
	
//	@ManyToMany
//	private Set<Role> roles;
	
//	@OneToMany(mappedBy="id", cascade = {CascadeType.ALL})
//	private Set<Address> userAddress;

}
