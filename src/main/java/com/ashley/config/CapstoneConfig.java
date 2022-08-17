package com.ashley.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ashley.service.AuthenticationProviderService;
import com.ashley.service.UserDetailsServiceImpl;
import com.ashley.service.UserUserDetails;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class CapstoneConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private AuthenticationProviderService authenticationProvider;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.authenticationProvider(authenticationProvider);
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.formLogin().defaultSuccessUrl("/main", true);
//		http.authorizeRequests().anyRequest().authenticated();
		
		http.csrf().disable();
		http.authorizeRequests()
//		.antMatchers("/products/view").permitAll()
//		.antMatchers("/products/**").hasRole("ROLE_ADMIN")
		.antMatchers("/user/register").permitAll().and().authorizeRequests();
//		.antMatchers("/user/login").permitAll()
//		.antMatchers("/user/**").permitAll();
//		.authenticated().and().httpBasic();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
}
