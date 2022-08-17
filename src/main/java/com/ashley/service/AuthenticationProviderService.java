package com.ashley.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationProviderService implements AuthenticationProvider{
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
//	@Autowired 
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		UserUserDetails user = userDetailsService.loadUserByUsername(username);
		return checkPassword(user, password, passwordEncoder);
	}
	
	
	private Authentication checkPassword(UserUserDetails user, String rawPassword, PasswordEncoder encoder) {
		if(encoder.matches(rawPassword, user.getPassword())) {
			return new UsernamePasswordAuthenticationToken(
						user.getUsername(),
						user.getPassword(),
						user.getAuthorities());
		} else {
			throw new BadCredentialsException("Bad Credentials");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}
	

}
