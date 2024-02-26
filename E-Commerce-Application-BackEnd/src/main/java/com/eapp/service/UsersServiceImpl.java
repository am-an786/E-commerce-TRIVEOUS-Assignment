
package com.eapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eapp.exception.DataNotFoundException;
import com.eapp.exception.DuplicateEmailException;
import com.eapp.model.Users;
import com.eapp.repository.UsersRepository;



@Service
public class UsersServiceImpl implements UsersService{

	@Autowired
	private UsersRepository usersRepo;
	

	@Override
	public Users getUserByEmail(String email) throws DataNotFoundException,IllegalArgumentException {
		
		Optional<Users> opt = usersRepo.findByEmail(email);
		
		if(!opt.isPresent()) {
			throw new DataNotFoundException("User doesn't found with email "+ email);
		}
		
		return opt.get();
	}


	@Override
	public Users registerUsers(Users user) throws DuplicateEmailException,IllegalArgumentException {

		Optional<Users> opt = usersRepo.findByEmail(user.getEmail());
		
		if(opt.isPresent()) {
			throw new DuplicateEmailException("With this email "+user.getEmail()+" "+user.getRole()+" already exits");
		}
		
		usersRepo.save(user);
		
		return user;
	}


	@Override
	public Users deleteUserByEmail(String email) throws DataNotFoundException,IllegalArgumentException {
		
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Invalid input. Please provide a valid email address.");
        }

        Optional<Users> opt = usersRepo.findByEmail(email);

        if (opt.isEmpty()) {
            throw new DataNotFoundException("User with email " + email + " not found.");
        }
        
        Users deletedUser = opt.get();
        
        usersRepo.delete(deletedUser);
        
        return deletedUser;
	}


	@Override
	public Users updateUserDetails(Users user) throws DataNotFoundException,IllegalArgumentException{
		
		 if (user == null) {
	            throw new IllegalArgumentException("Invalid input. Please provide a valid details of User");
	        }
		 
		 usersRepo.save(user);
		
		return user;
	}
	
	
}
