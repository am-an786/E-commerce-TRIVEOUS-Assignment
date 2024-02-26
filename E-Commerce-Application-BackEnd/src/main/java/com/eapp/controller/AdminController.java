package com.eapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eapp.model.Category;
import com.eapp.model.Product;
import com.eapp.model.Users;
import com.eapp.service.CategoryService;
import com.eapp.service.ProductService;
import com.eapp.service.UsersService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/triv/admins")
public class AdminController {

	@Autowired
	private UsersService usersService;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	
	/*       All users related resources      */
	
	@DeleteMapping("delete-user")  //http://localhost:8085/triv/admins/delete-user
	public ResponseEntity<Users> deleteUserByEmailHandler(@Valid @RequestBody String email) {
		
		 Users deletedAdmin = usersService.deleteUserByEmail(email);
		
		 return new ResponseEntity<Users>(deletedAdmin,HttpStatus.OK);
		 
	}
	
	
	
	
	/*       All products related resources      */ 
	
	@PostMapping("/save-product/{categoryName}") //http://localhost:8085/triv/admins/save-product
	public ResponseEntity<Product> addProductHandler(@Valid @RequestBody Product product, @PathVariable String categoryName){
		
		Product saveproduct = productService.addProduct(product, categoryName);
		
		return new ResponseEntity<Product>(saveproduct,HttpStatus.OK);
	}
	
	
	
	
	/*       All categories related resources      */
	
	/*
	 * {
    	"categoryName":"Books",
    	"categoryDescription" : "Books related to motivation",
    	"active" : true
		}
	 * 
	 * */
	@PostMapping("/save-category") //http://localhost:8085/triv/admins/save-category
	public ResponseEntity<Category> addCategoryHandler(@Valid @RequestBody Category category){
		
		Category saveCategory = categoryService.addCategory(category);
		
		return new ResponseEntity<Category>(saveCategory,HttpStatus.OK);
	}
	
	
}
