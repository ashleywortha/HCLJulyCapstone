package com.ashley.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashley.model.Order;
import com.ashley.model.User;
import com.ashley.repo.OrderRepo;
import com.ashley.repo.UserRepo;
import com.ashley.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	@Autowired
	private OrderService service;
	
	@Autowired
	private UserRepo uRepo;
	
	@Autowired
	private OrderRepo repo;
	
	@GetMapping("/view")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<Order> listAllOrders(){
		return service.getAllOrders();
	}

	@GetMapping("/view/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Optional<Order> getOrderId(@PathVariable Integer id){
		Optional<Order> order = service.getOrderById(id);
		return order;
	}
	
	public void addOrder(User user, @RequestBody Order order) {
		service.addOrder(user, order);
	}
	
	@DeleteMapping("/delete/{orderId}")
	public String deleteOrder(@PathVariable int orderId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUsername = authentication.getName();
			int userid = uRepo.findByUsername(currentUsername).get().getId();
			
			//change to find the user id associated with the order
			int orderUserId = repo.findById(orderId).get().getId();
			
			if(userid == orderUserId) {
				service.deleteOrder(orderId);
				return "order cancelled";
			}
			
			return "order cannot be cancelled as it does not belong to you";
			
		}
		return "User not logged in";
	}
}
