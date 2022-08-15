//package com.ashley.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.ashley.model.User;
//import com.ashley.service.UserService;
//
//@RestController
//public class HomeController {
//	@Autowired
//	private UserService service;
//	
//	@GetMapping({"/", "registration"}) 
//	public String register(final Model model) {
//		model.addAttribute("userForm", new User());
//		return"registration";
//
//	}
//	
//	@PostMapping("/")
//	public String userRegistration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
//		service.addUser(userForm);
//		return "redirect:/login";
//		//		if(bindingResult.hasErrors()) {
////			model.addAttribute("register", user);
////			return "account/register";
////		} 
////		service.addUser(user);
////		return "/login";
//	}
//
//}

