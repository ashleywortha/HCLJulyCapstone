package com.ashley.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashley.model.User;
import com.ashley.service.UserService;

@RestController
public class HomeController {
	@Autowired
	private UserService service;
	
	@GetMapping({"/main"}) 
	public String main(final Model model) {
		return"you have logged in";

	}
	
	@GetMapping({"/"}) 
	public String home(final Model model) {
		return"home";

	}

}

