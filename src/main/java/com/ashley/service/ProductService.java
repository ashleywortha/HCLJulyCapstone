package com.ashley.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashley.model.Product;
import com.ashley.repo.ProductRepo;

@Service
public class ProductService {
	@Autowired
	ProductRepo repo;
	
	public List<Product> getAllProducts(){
		return repo.findAll();
	}
	
	public Optional<Product> getProductById(int id){
		return repo.findById(id);
	}
	
	public void deleteProduct(Integer id) {
		repo.deleteById(id);
	}
	
	public void addProduct(Product product) {
		repo.save(product);
	}
	
	public void updateProduct(Integer id, Product product) {
		Product productInDB =
				repo.findById(id).get();
				repo.save(productInDB);
	}

}
