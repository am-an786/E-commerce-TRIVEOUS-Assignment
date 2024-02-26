package com.eapp.service;

import com.eapp.exception.DataNotFoundException;
import com.eapp.model.Cart;

public interface CartService {

	public Cart addProductToCartSpecificUser(Integer productId,Integer quantity,String email)  throws DataNotFoundException,IllegalArgumentException;;
	
	public Cart deleteProductToCartOfSpecificUser(Integer productId,String email) throws DataNotFoundException,IllegalArgumentException;;
	
	public Cart retriveAllProductsOfCartOfSpecificUser(String email) throws DataNotFoundException,IllegalArgumentException;;
	
	public Cart updateQuantityFormCartSpecificUser(String email,Integer quentity) throws DataNotFoundException,IllegalArgumentException;;
	
}
