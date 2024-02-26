package com.eapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eapp.exception.DataNotFoundException;
import com.eapp.model.Cart;
import com.eapp.model.Product;
import com.eapp.model.Users;
import com.eapp.repository.CartRepository;
import com.eapp.repository.ProductRepository;
import com.eapp.repository.UsersRepository;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private UsersRepository userRepository;
	
	@Autowired
	private ProductRepository productRepoistory;

	@Override
	public Cart addProductToCartSpecificUser(Integer productId, Integer quantity, String email)
			throws DataNotFoundException, IllegalArgumentException {
		
		if(email == null || email.isEmpty() || quantity<=0 || productId<=0){
			throw new IllegalArgumentException("Invalid Argument is provided");
		}
		
	    Optional<Users> userOpt = userRepository.findByEmail(email);
	    Optional<Product> productOpt = productRepoistory.findById(productId);

	    if (userOpt.isEmpty() || productOpt.isEmpty()) {
	        throw new DataNotFoundException("User or Product not found");
	    }

	    Users user = userOpt.get();
	    
	    
	    Cart cart = user.getCart();

	    if (cart == null) {
	        cart = new Cart();
	    }
	    cart.getCartProducts().add(productOpt.get());
        cart.setCartQuantity(quantity);
        cart.setCartTotalAmount(quantity * productOpt.get().getPrice());
        
        user.setCart(cart);
        
        cartRepository.save(cart);
        
        return cart;
	}

	@Override
	public Cart deleteProductToCartOfSpecificUser(Integer productId, String email)
			throws DataNotFoundException, IllegalArgumentException {
		
		if(email == null || email.isEmpty() || productId<=0){
			throw new IllegalArgumentException("Invalid Argument is provided");
		}
		
	    Optional<Users> userOpt = userRepository.findByEmail(email);

	    if (userOpt.isEmpty()) {
	        throw new DataNotFoundException("User not found");
	    }
	    
	    Cart cart = userOpt.get().getCart();
	    
		if(cart == null) {
			throw new DataNotFoundException("Cart is empty to delete !");
		}
	
	    Optional<Product> productOpt = cartRepository.findProductByIdAndCartIdAndUserEmail(productId, cart.getCartId(), email);

		if(productOpt.isEmpty()) {
			throw new DataNotFoundException("Such product is not even add in cart ");
		}
		
	
	     List<Product> updatedProducts = new ArrayList<>(cart.getCartProducts());
	       
	     updatedProducts.removeIf(product -> product.getProductId().equals(productId));

	     cartRepository.deleteProductFromCart(cart.getCartId(), updatedProducts);
	       
	     Optional<Cart> afterDeleteCart = cartRepository.findById(cart.getCartId());
	     
	     Cart c = afterDeleteCart.get();
	     
		return c;
	}

	@Override
	public Cart retriveAllProductsOfCartOfSpecificUser(String email)
			throws DataNotFoundException, IllegalArgumentException {
		
			Optional<Users> userOpt = userRepository.findByEmail(email);

		    if (userOpt.isEmpty()) {
		        throw new DataNotFoundException("User not Found with "+ email);
		    }

		    Optional<Cart> cartOpt = cartRepository.findAllProductsOfCartByUserEmail(email);

		    if (cartOpt.isEmpty()) {
		        throw new DataNotFoundException("Cart not found for the user");
		    }

		    return cartOpt.get();
	}

	@Override
	public Cart updateQuantityFormCartSpecificUser(String email, Integer quantity)
			throws DataNotFoundException, IllegalArgumentException {
		 	
			Optional<Users> userOpt = userRepository.findByEmail(email);
	
		    if (userOpt.isEmpty()) {
		        throw new DataNotFoundException("User not Found with "+ email);
		    }
		    
		    Optional<Cart> cartOpt = cartRepository.findAllProductsOfCartByUserEmail(email);

		    if (cartOpt.isEmpty()) {
		        throw new DataNotFoundException("Cart not found for the user");
		    }
		    
		   cartRepository.updateQuantityForAllProductsInCart(email, quantity);
		    
		    Optional<Cart> updatedCartOpt =  cartRepository.findAllProductsOfCartByUserEmail(email);
		    
		    return updatedCartOpt.get();

	}





}
