package com.ashley.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ashley.model.Product;
import com.ashley.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductService service;
	@GetMapping("/view")
	public List<Product> listAllProduct(){
		return service.getAllProducts();
	}
	
	@GetMapping("/view/{id}")
	public ResponseEntity<Product> getProductId(@PathVariable Integer id){
		Optional<Product> product = service.getProductById(id);
		return new ResponseEntity<Product>(HttpStatus.OK);
	}
	
//	@Secured("ROLE_ADMIN")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PostMapping("/add")
	public String addProduct(@RequestBody Product product) {
		service.addProduct(product);
		return "Product" + product.getName() + " added";
	}
	
//	@Secured("ROLE_ADMIN")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@DeleteMapping("/delete/{productId}")
	public void deleteProduct(@PathVariable int productId) {
		service.deleteProduct(productId);
	}
	
//	@Secured("ROLE_ADMIN")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PutMapping("/update/{productId}")
	public void updateProduct(@PathVariable int productId, @RequestBody Product product) {
		service.updateProduct(productId, product);
	}

}
