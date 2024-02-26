package com.eapp.service;

import com.eapp.exception.DataNotFoundException;
import com.eapp.exception.DuplicateEmailException;
import com.eapp.model.Users;

public interface UsersService {

	public Users registerUsers(Users user) throws DuplicateEmailException,IllegalArgumentException;
	
	public Users getUserByEmail(String email) throws DataNotFoundException,IllegalArgumentException;
	
	public Users deleteUserByEmail(String email) throws DataNotFoundException,IllegalArgumentException;
	 
	public Users updateUserDetails(Users user) throws DataNotFoundException,IllegalArgumentException;
	 
}
