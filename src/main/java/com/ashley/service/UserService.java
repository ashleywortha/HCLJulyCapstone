package com.ashley.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ashley.repo.RoleRepo;
import com.ashley.repo.UserRepo;
import com.ashley.model.User;


@Service
public class UserService{
	@Autowired
	UserRepo repo;
	
	@Autowired
	RoleRepo roleRepo;
	
//	@Autowired
//	PasswordEncoder passwordEncoder;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	
	void sendEmail(String email, String fName) {
	SimpleMailMessage msg = new SimpleMailMessage();
	msg.setTo(email); //change to user input email
	msg.setSubject("You Have Successfully Created Your Account!");
	msg.setText("Thank you " + fName + " for signing up");
	javaMailSender.send(msg);
}
	
	public List<User> getAllUsers(){
		return repo.findAll();
	}
	
	
	public Optional<User> getUserById(int id){
		return repo.findById(id);
	}
	
	public void deleteUser(Integer id) {
		repo.deleteById(id);
	}
	
	public void addUser(User user) {
		repo.save(user);
		sendEmail(user.getEmail(), user.getFirstName());
	}
	
	
	public void updateUser(Integer id, User user) {
		User userInDB = repo.findById(id).get();
//		User userInDB = 
//				repo.findById(id).get();
//				userInDB.setFirstName(user.getFirstName());
//				userInDB.setLastName(user.getLastName());
//				userInDB.setEmail(user.getEmail());
//				userInDB.setContact(user.getContact());
//				userInDB.setUsername(user.getUsername());
//				userInDB.setPassword(user.getPassword());
//				userInDB.setSSN(user.getSSN());
//				repo.save(userInDB);
	}



}
