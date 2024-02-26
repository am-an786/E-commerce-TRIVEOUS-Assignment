package com.eapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eapp.exception.DataNotFoundException;
import com.eapp.exception.DuplicateProductException;
import com.eapp.model.Category;
import com.eapp.model.Product;
import com.eapp.repository.CategoryRepository;
import com.eapp.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;

	@Override
	public Product addProduct(Product product , String categoryName) throws IllegalArgumentException,DuplicateProductException,DataNotFoundException{
		
		if(product==null) {
		     throw new IllegalArgumentException("Input is invalid");
		}
		
		Optional<Product> optProduct = productRepo.findByUniqueName(product.getUniqueName());
		if(optProduct.isPresent()) {
			throw new DuplicateProductException("Product already exits with " + product.getProductName() + " Product Name of same Band name "+ product.getBrand());
		}
	
		Optional<Category> optCategory = categoryRepo.findByCategoryName(categoryName);
		if(optCategory.isEmpty()) {
			throw new DataNotFoundException("Category with "+categoryName +" is not exist");
		}
		
		//product.setUniqueName(product.getBrand(),product.getProductName());
		product.setCategory(optCategory.get());
		productRepo.save(product);
	
		return product;
	}

	@Override
	public List<Product> retriveAllProducts() throws DataNotFoundException {
		
		List<Product> products = productRepo.findAll();
		
		if(products.isEmpty()) {
			 throw new DataNotFoundException("Oops! It seems there are no products available at the moment.\n"
			 		+ "Please check back later, we're working on adding some exciting new products!");
		}
		
		return products;
	}

	@Override
	public Product retriveSpecificProductByProductId(Integer productId) throws DataNotFoundException,IllegalArgumentException{
	
			if (productId <= 0) {
		         throw new IllegalArgumentException("Invalid productId: " + productId);
		    }
		
		 Optional<Product> opt = productRepo.findById(productId);
		 if(opt.isEmpty()) {
			 throw new DataNotFoundException("No Product found with productId "+ productId);
		 }
		 
		return opt.get();
	}


	@Override
	public List<Product> retriveAllProductsByCategoryId(Integer categoryId) throws IllegalArgumentException,DataNotFoundException {
		
		if(categoryId<=0) {
			throw new DataNotFoundException("Invalid categoryId " + categoryId);
		}
		List<Product> products = productRepo.findAllProductsByCategoryId(categoryId);
		if(products.isEmpty()) {
			throw new DataNotFoundException("No Product found with " + categoryId+ " Category Id");
		}
		return products;
 	}

}
