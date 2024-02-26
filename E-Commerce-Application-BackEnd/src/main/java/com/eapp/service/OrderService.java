package com.eapp.service;

import java.util.List;

import com.eapp.exception.DataNotFoundException;
import com.eapp.model.Orders;

public interface OrderService {

	public Orders placeOrderFromSepcificUser(Integer cartId, String email) throws DataNotFoundException,IllegalArgumentException;
	
	public Orders getOrderByIdFromSepcificUser(Integer orderId, String email) throws DataNotFoundException,IllegalArgumentException;
	
	public List<Orders> getOrderHistoryOfSepcificUser(String email)  throws DataNotFoundException,IllegalArgumentException;
}
