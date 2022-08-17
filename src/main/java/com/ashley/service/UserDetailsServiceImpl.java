package com.ashley.service;



import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ashley.repo.UserRepo;
import com.ashley.model.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepo repo;

	@Override
	public UserUserDetails loadUserByUsername(String username) {
		Supplier<UsernameNotFoundException> s = 
				() -> new UsernameNotFoundException(
						"Problen during authentication!");
		User u = repo.findByUsername(username).orElseThrow(s);
		
		return new UserUserDetails(u);
	}
	
	
}
