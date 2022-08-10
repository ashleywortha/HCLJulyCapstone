package com.ashley.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ashley.repo.OrderRepo;
import com.ashley.model.Order;


@Service
public class OrderService {
	@Autowired
	OrderRepo repo;
	
	public List<Order> getAllOrders(){
		return repo.findAll();
	}
	
	public Optional<Order> getOrderById(int id){
		return repo.findById(id);
	}
	
	public void deleteOrder(Integer id) {
		repo.deleteById(id);
	}
	
	public void addOrder(Order order) {
		repo.save(order);
	}
	
	public void updateOrder(Integer id, Order order) {
		Order orderInDB =
				repo.findById(id).get();
				repo.save(orderInDB);
	}

}

