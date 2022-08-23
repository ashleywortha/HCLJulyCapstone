package com.ashley.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashley.model.Product;

@Repository 
public interface ProductRepo extends JpaRepository <Product, Integer>{
	Product getProductById(Integer id);

}
