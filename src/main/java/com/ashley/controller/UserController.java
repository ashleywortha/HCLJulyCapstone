package com.ashley.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ashley.model.User;
import com.ashley.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService service;
	@GetMapping("/users")
	public List<User> listAllUser(){
		return service.getAllUsers();
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserId(@PathVariable Integer id){
		Optional<User> user = service.getUserById(id);
		return new ResponseEntity<User>(HttpStatus.OK);
	}
	
	@PostMapping("/users")
	public void addUser(@RequestBody User user) {
		service.addUser(user);
	}
	
	@DeleteMapping("/users/{userId}")
	public void deleteUser(@PathVariable int userId) {
		service.deleteUser(userId);
	}
	
	@PutMapping("/users/{userId}")
	public void updateUser(@PathVariable int userId, @RequestBody User user) {
		service.updateUser(userId, user);
	}

}
