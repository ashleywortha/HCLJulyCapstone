package com.ashley.model;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.ashley.model.Product;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
	private List<Product> productList;
	

}
