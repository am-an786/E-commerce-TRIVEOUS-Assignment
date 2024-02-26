package com.eapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eapp.exception.DataNotFoundException;
import com.eapp.model.Cart;
import com.eapp.model.Orders;
import com.eapp.model.Users;
import com.eapp.repository.CartRepository;
import com.eapp.repository.OrderRepository;
import com.eapp.repository.UsersRepository;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private UsersRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Override
	public Orders placeOrderFromSepcificUser(Integer cartId, String email)
			throws DataNotFoundException, IllegalArgumentException {
		

        Optional<Users> userOpt = userRepository.findByEmail(email);
	    Optional<Cart> cartOpt = cartRepository.findById(cartId);

	    if (userOpt.isEmpty() || cartOpt.isEmpty()) {
	        throw new DataNotFoundException("User or Cart not found");
	    }

	    Cart cart = cartOpt.get();
	    Users user = userOpt.get();
	    
        Orders order = new Orders();
        order.setOrderQuantity(cart.getCartQuantity());
        order.setTotalOrderAmount(cart.getCartTotalAmount());
        order.setUser(user);
        order.setCart(cart);

        return orderRepository.save(order);

	}

	@Override
	public Orders getOrderByIdFromSepcificUser(Integer orderId, String email)
			throws DataNotFoundException, IllegalArgumentException {
		 

        Optional<Users> userOpt = userRepository.findByEmail(email);

        if (userOpt.isEmpty()) {
            throw new DataNotFoundException("User not found");
        }

 
        Optional<Orders> orderOpt = orderRepository.findByOrderIdAndUser(orderId,email);
        if(orderOpt.isEmpty()) {
        	throw new DataNotFoundException("Order not found for the user with orderId: " + orderId);
        }

        return orderOpt.get();
	}

	@Override
	public List<Orders> getOrderHistoryOfSepcificUser(String email)
			throws DataNotFoundException, IllegalArgumentException {
		
			Optional<Users> userOpt = userRepository.findByEmail(email);

	        if (userOpt.isEmpty()) {
	            throw new DataNotFoundException("User not found");
	        }
	        
       
        List<Orders> orderHistory = orderRepository.findOrdersByUserEmail(email);

        if (orderHistory.isEmpty()) {
            throw new DataNotFoundException("Order history not found for the user");
        }

        return orderHistory;
	}



}
