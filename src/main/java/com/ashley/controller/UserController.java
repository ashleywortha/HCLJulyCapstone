package com.ashley.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashley.common.UserConstraint;
import com.ashley.model.User;
import com.ashley.repo.UserRepo;
import com.ashley.service.UserService;
//import com.ashley.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService service;
	
	@Autowired 
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepo repo;
	
	@GetMapping("/view")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<User> listAllUser(){
		return service.getAllUsers();
	}
	
	@GetMapping("/view/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Optional<User> getUserId(@PathVariable Integer id){
		Optional<User> user = service.getUserById(id);
		return user;
//		return new ResponseEntity<User>(HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public String addUser(@RequestBody User user) {
		user.setRoles(UserConstraint.DEFAULT_ROLE);//user
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		service.addUser(user);
		return("Hi " + user.getUsername() + " you have been registered");
	}
	
	

//	@GetMapping("/login")
//    public String testUserAccess(@RequestBody User user) {
//		System.out.println("password is: " + user.getPassword());
//		System.out.println("encoded password is: " + repo.findByUsername(user.getUsername()).get().getPassword());
//		
//		if(!repo.findByUsername(user.getUsername()).isEmpty()) {
//			System.out.println(passwordEncoder.matches(user.getPassword(), repo.findByUsername(user.getUsername()).get().getPassword()));
//			if(passwordEncoder.matches(user.getPassword(), repo.findByUsername(user.getUsername()).get().getPassword())) {
//				return "you have logged in ";
//			}
//			
//		}
//		
//        return "Login Fail";
//    }
	
	private List<String> getRolesByLoggedInUser(Principal principal){
		String roles = getLoggedInUser(principal).getRoles();
		List<String> assignRoles = Arrays.stream(roles.split(",")).collect(Collectors.toList());
		if(assignRoles.contains("ROLE_ADMIN")) {
			return Arrays.stream(UserConstraint.ADMIN_ACCESS).collect(Collectors.toList());
		}
		if(assignRoles.contains("ROLE_MODERATOR")) {
			return Arrays.stream(UserConstraint.MODERATOR_ACCESS).collect(Collectors.toList());
		}
		return Collections.emptyList();
	}
	
	
	@GetMapping("/access/{userId}/{userRole}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String giveAccessToUser(@PathVariable int userId, @PathVariable String userRole, Principal principal) {
		User user = repo.findById(userId).get();
		List<String> activeRoles = getRolesByLoggedInUser(principal);
		String newRole = "";
		if(activeRoles.contains(userRole)) {
			newRole = user.getRoles() + "," + userRole;
			user.setRoles(newRole);
		}
		repo.save(user);
		return "Hi " + user.getUsername() + " New Role Assigned to you by " + principal.getName();
	}
	
	private User getLoggedInUser(Principal principal) {
		return repo.findByUsername(principal.getName()).get();
	}
	
	@DeleteMapping("/delete/{userId}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public void deleteUser(@PathVariable int userId) {
		service.deleteUser(userId);
	}
	
	@PutMapping("/update/{userId}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public void updateUser(@PathVariable int userId, @RequestBody User user) {
		service.updateUser(userId, user);
	}

}
