package com.ashley.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashley.model.Order;
import com.ashley.model.Product;
@Repository 
public interface OrderRepo extends JpaRepository <Order, Integer>{
	Product getOrderById(Integer id);

}
