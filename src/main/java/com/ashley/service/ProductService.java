package com.ashley.service;

import java.beans.FeatureDescriptor;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashley.model.Product;
import com.ashley.model.User;
import com.ashley.repo.ProductRepo;

@Service
public class ProductService {
	@Autowired
	ProductRepo repo;
	
	public List<Product> getAllProducts(){
		return repo.findAll();
	}
	
	public Optional<Product> getProductsById(Integer id) {
		return repo.findById(id);
	}
//	public Product getProductById(int id){
//		return repo.findById(id);
//	}
	
	public void deleteProduct(Integer id) {
		repo.deleteById(id);
	}
	
	public void addProduct(Product product) {
		repo.save(product);
	}
	
	public void updateProduct(Integer id, Product product) {
		Product productInDB = repo.findById(id).get();
		String[] ignoredProperties = getNullPropertyNames(product);
		
		BeanUtils.copyProperties(product, productInDB, ignoredProperties);
		productInDB.setId(id);
		repo.save(productInDB);
	}
	
	public static String[] getNullPropertyNames(Object object) {
		final BeanWrapper wrappedSource = new BeanWrapperImpl(object);
		return Stream.of(wrappedSource.getPropertyDescriptors())
				.map(FeatureDescriptor::getName)
				.filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null 
				|| wrappedSource.getPropertyValue(propertyName).equals(0.0)
				 || wrappedSource.getPropertyValue(propertyName).equals(0))
				.toArray(String[]::new);
	}
}
