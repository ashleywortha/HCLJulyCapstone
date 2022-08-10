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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashley.model.Order;
import com.ashley.service.OrderService;

@RestController
public class OrderController {
	@Autowired
	private OrderService service;
	@GetMapping("/orders")
	public List<Order> listAllOrders(){
		return service.getAllOrders();
	}
	
	@GetMapping("/orders/{id}")
	public ResponseEntity<Order> getOrderId(@PathVariable Integer id){
		Optional<Order> order = service.getOrderById(id);
		return new ResponseEntity<Order>(HttpStatus.OK);
	}
	
	@PostMapping("/orders")
	public void addOrder(@RequestBody Order order) {
		service.addOrder(order);
	}
	
	@DeleteMapping("/orders/{orderId}")
	public void deleteOrder(@PathVariable int orderId) {
		service.deleteOrder(orderId);
	}
}
