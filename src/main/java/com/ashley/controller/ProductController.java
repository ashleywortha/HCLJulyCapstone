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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ashley.model.Product;
import com.ashley.service.ProductService;

@RestController
public class ProductController {
	@Autowired
	private ProductService service;
	@GetMapping("/productss")
	public List<Product> listAllProduct(){
		return service.getAllProducts();
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProductId(@PathVariable Integer id){
		Optional<Product> product = service.getProductById(id);
		return new ResponseEntity<Product>(HttpStatus.OK);
	}
	
	@PostMapping("/products")
	public void addProduct(@RequestBody Product product) {
		service.addProduct(product);
	}
	
	@DeleteMapping("/products/{productId}")
	public void deleteProduct(@PathVariable int productId) {
		service.deleteProduct(productId);
	}
	
	@PutMapping("/products")
	public void updateProduct(@PathVariable int productId, @RequestBody Product product) {
		service.updateProduct(productId, product);
	}

}
