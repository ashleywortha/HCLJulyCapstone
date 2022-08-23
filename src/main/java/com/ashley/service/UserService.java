package com.ashley.service;

import java.beans.FeatureDescriptor;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ashley.repo.AddressRepo;
import com.ashley.repo.RoleRepo;
import com.ashley.repo.UserRepo;
import com.ashley.model.Address;
import com.ashley.model.User;


@Service
public class UserService{
	@Autowired
	UserRepo repo;
	
	@Autowired
	RoleRepo roleRepo;
	
	@Autowired
	private AddressRepo aRepo;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	
	void sendEmail(String email, String fName) {
	SimpleMailMessage msg = new SimpleMailMessage();
	msg.setTo(email); 
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
		String[] ignoredProperties = getNullPropertyNames(user);
		BeanUtils.copyProperties(user, userInDB, ignoredProperties);
		userInDB.setId(id);
		repo.save(userInDB);
	}
	
	public static String[] getNullPropertyNames(Object object) {
		final BeanWrapper wrappedSource = new BeanWrapperImpl(object);
		return Stream.of(wrappedSource.getPropertyDescriptors())
				.map(FeatureDescriptor::getName)
				.filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
				.toArray(String[]::new);
	}



}
