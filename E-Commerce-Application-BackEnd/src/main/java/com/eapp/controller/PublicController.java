package com.eapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eapp.model.Category;
import com.eapp.model.Product;
import com.eapp.service.CategoryService;
import com.eapp.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/triv/public")
public class PublicController {

	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	
	@GetMapping("/all-products") //http://localhost:8085/triv/public/all-products
	public ResponseEntity<List<Product>> retriveAllProductsHandler(){
		
		List<Product> products = productService.retriveAllProducts();
		
		return new  ResponseEntity<List<Product>>(products,HttpStatus.OK);
	}
	
	@GetMapping("/get-product/") //http://localhost:8085/triv/public/get-product?productId=1
	public ResponseEntity<Product>  retriveSpecificProductByProductIdHandler(@Valid @RequestParam Integer productId){
		
		Product product = productService.retriveSpecificProductByProductId(productId);
		
		return new ResponseEntity<Product>(product,HttpStatus.OK);
	}
	
	@GetMapping("/get-product-of-specific-category/") //http://localhost:8085/triv/public/get-product-of-specific-category?categoryId=1
	public ResponseEntity<List<Product>> retriveAllProductsByCategoryId(@Valid @RequestParam Integer categoryId){
		  
		List<Product> products = productService.retriveAllProductsByCategoryId(categoryId);
		
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
	} 
	
	@GetMapping("/all-categories") //http://localhost:8085/triv/public/all-categories
	public ResponseEntity<List<Category>> getAllCategoryWithProductsHandler(){
			List<Category> categories = categoryService.getAllCategoryWithProducts();
			
			return new ResponseEntity<List<Category>>(categories,HttpStatus.OK);
	}
	
	
}
