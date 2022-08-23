package com.ashley.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.ashley.repo.OrderRepo;
import com.ashley.repo.UserRepo;
import com.ashley.model.Order;
import com.ashley.model.Product;
import com.ashley.model.User;


@Service
public class OrderService {
	@Autowired
	OrderRepo repo;
	
	@Autowired
	UserRepo uRepo;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	
	void sendEmail(User user, Order order) {
	SimpleMailMessage msg = new SimpleMailMessage();
	msg.setTo(user.getEmail()); 
	msg.setSubject("Your Order " + order.getId() +  " has been Shipped!");
	msg.setText("Hi " + user.getFirstName() + " your order has been shipped & is on the way!");
	javaMailSender.send(msg);
}
	
	public List<Order> getAllOrders(){
		return repo.findAll();
	}
	
	
	public Optional<Order> getOrderById(Integer id) {
		return repo.findById(id);
	}
	
	public void deleteOrder(Integer id) {
		repo.deleteById(id);
	}
	
	public void addOrder(User user, Order order) {
		order.setUser(user);
		repo.save(order);
		
		user.getUserOrders().add(order);
		uRepo.save(user);
		
		sendEmail(user, order);
		
	}
	
	public void updateOrder(Integer id, Order order) {
		Order orderInDB =
				repo.findById(id).get();
				repo.save(orderInDB);
	}

}

