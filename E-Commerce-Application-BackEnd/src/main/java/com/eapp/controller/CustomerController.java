package com.eapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eapp.model.Cart;
import com.eapp.model.Orders;
import com.eapp.model.Users;
import com.eapp.service.CartService;
import com.eapp.service.OrderService;
import com.eapp.service.UsersService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/triv/users")
public class CustomerController {

	@Autowired
	private UsersService usersService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private OrderService orderService;
	
	
	/* ------------ users related resources ----------*/
	
	@PutMapping("/update-user")  // by admin and user both  //http://localhost:8085/triv/auth/users/update-user
	public ResponseEntity<Users> updateUserDetailsHandler(@Valid @RequestBody Users user) {
		
		Users updatedAdmin = usersService.updateUserDetails(user);
		
		return new ResponseEntity<>(updatedAdmin,HttpStatus.ACCEPTED);
	}
	
	
	/* ------------ carts related resources ----------*/
	
	
	@PutMapping("/addProduct-to-cart/{productId}/{quantity}")  //http://localhost:8085/triv/users/addProduct-to-cart/{productId}/{quantity}
	public ResponseEntity<Cart> addProductToCartSpecificUserHandler(@PathVariable Integer productId,@PathVariable Integer quantity,Authentication auth) {
		
		Users user = usersService.getUserByEmail(auth.getName());
		
		Cart cart = cartService.addProductToCartSpecificUser(productId, quantity, user.getEmail());
		
		return new ResponseEntity<Cart>(cart,HttpStatus.OK);
	}
	
	
	@DeleteMapping("/deleteProduct-from-cart/{productId}")  //http://localhost:8085/triv/users/addProduct-to-cart/{productId}/{quantity}
	public ResponseEntity<Cart> deleteProductToCartOfSpecificUserHandler(@PathVariable Integer productId,Authentication auth) {
		
		Users user = usersService.getUserByEmail(auth.getName());
		
		Cart cart = cartService.deleteProductToCartOfSpecificUser(productId, user.getEmail());
		
		return new ResponseEntity<Cart>(cart,HttpStatus.OK);
	}
	
	
	@GetMapping("/getAllProducts-from-cart") //http://localhost:8085/triv/users/getAllProducts-from-cart
	public ResponseEntity<Cart> retriveAllProductsOfCartOfSpecificUserHandler(Authentication auth) {
		
		Users user = usersService.getUserByEmail(auth.getName());
		
		Cart cart = cartService.retriveAllProductsOfCartOfSpecificUser(user.getEmail());
		
		return new ResponseEntity<Cart>(cart,HttpStatus.OK);
	}
	
	@PutMapping("/update-quantity-of-product-in-cart/{quantity}") //http://localhost:8085/triv/users/update-quantity-of-product-in-cart/{quantity}
	public ResponseEntity<Cart>  updateQuantityFormCartSpecificUserHandler(@PathVariable Integer quantity,Authentication auth) {
		
		Users user = usersService.getUserByEmail(auth.getName());
		
		Cart cart = cartService.updateQuantityFormCartSpecificUser(user.getEmail(),quantity);
		
		return new ResponseEntity<Cart>(cart,HttpStatus.OK);
	}
	
	
	
	/* ------------ Orders related resources ----------*/
	@PutMapping("/placeOrder/{cartId}")
	public ResponseEntity<Orders> placeOrderFromSepcificUserHandler(@PathVariable Integer cartId,Authentication auth) {
		
		Users user = usersService.getUserByEmail(auth.getName());
		
		Orders savedOrder = orderService.placeOrderFromSepcificUser(cartId, user.getEmail());
		
		return new ResponseEntity<Orders>(savedOrder,HttpStatus.OK);
	}
	
	
	@GetMapping("/get-order/{orderId}")
	public ResponseEntity<Orders> getOrderByIdFromSepcificUserHandler(@PathVariable Integer orderId,Authentication auth) {
		
		Users user = usersService.getUserByEmail(auth.getName());
		
		Orders order = orderService.getOrderByIdFromSepcificUser(orderId, user.getEmail());
		
		return new ResponseEntity<Orders>(order,HttpStatus.OK);
	}
	
	
	@GetMapping("/get-all-order")
	public ResponseEntity<List<Orders>> getOrderHistoryOfSepcificUserHandler(Authentication auth) {
		
		Users user = usersService.getUserByEmail(auth.getName());
		
		List<Orders> orders = orderService.getOrderHistoryOfSepcificUser(user.getEmail());
		
		return new ResponseEntity<List<Orders>>(orders,HttpStatus.OK);
	}
	
}
