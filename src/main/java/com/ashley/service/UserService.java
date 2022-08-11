package com.ashley.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ashley.repo.UserRepo;
import com.ashley.model.User;


@Service
public class UserService {
	@Autowired
	UserRepo repo;
	
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
	}
	
	public void updateUser(Integer id, User user) {
		User userInDB = 
				repo.findById(id).get();
				userInDB.setFirstName(user.getFirstName());
				userInDB.setLastName(user.getLastName());
				userInDB.setEmail(user.getEmail());
				userInDB.setContact(user.getContact());
				userInDB.setUsername(user.getUsername());
				userInDB.setPassword(user.getPassword());
				userInDB.setSSN(user.getSSN());
				repo.save(userInDB);
	}

}
