package com.eapp.service;

import java.util.List;

import com.eapp.exception.DataNotFoundException;
import com.eapp.exception.DuplicateProductException;
import com.eapp.model.Product;

public interface ProductService {

	public Product addProduct(Product product , String categoryName) throws IllegalArgumentException,DuplicateProductException,DataNotFoundException;
	
	public List<Product> retriveAllProducts() throws DataNotFoundException;
	
	public Product retriveSpecificProductByProductId(Integer productId) throws DataNotFoundException,IllegalArgumentException;
	
	public List<Product> retriveAllProductsByCategoryId(Integer categoryId) throws IllegalArgumentException,DataNotFoundException;
}