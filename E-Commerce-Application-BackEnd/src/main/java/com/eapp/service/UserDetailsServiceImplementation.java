package com.eapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.eapp.model.Users;
import com.eapp.repository.UsersRepository;


@Service
public class UserDetailsServiceImplementation implements UserDetailsService{

	@Autowired
	private UsersRepository userRepo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
			
		Optional<Users> opt = userRepo.findByEmail(username);
		
		 if(opt.isPresent()) {
			 Users user = opt.get();
		
			// Empty Authorities
			 List<GrantedAuthority> authorities = new ArrayList<>();
				 authorities.add(new SimpleGrantedAuthority(user.getRole())); 
			 
		      return new User(user.getEmail(), user.getPassword(), authorities);
		      
		 }else {
			 throw new BadCredentialsException("User Details not found with this username : "+ username);
		 }

	}
	

}
