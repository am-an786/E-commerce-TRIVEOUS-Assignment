package com.eapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eapp.model.Users;
import com.eapp.service.UsersService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/triv/auth")
public class AuthenticationController {

	@Autowired
	public UsersService usersService;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
/*
 * for user---
		{
		  "firstName": "Sakshi",
		  "lastName": "Choudhary",
		  "contact": "9958090188",
		  "email": "sakshi@gmail.com",
		  "password": "12345",
		  "dateOfBirth": "2002-02-05",
          "address": {
		    "addressLine1": "D/O Kavita Choudhary",
		    "addressLine2": "",
		    "buildingName": "Surya Appartment",
		    "landMark": "Ramind Showroom",
		    "city": "Noida",
		    "state": "UP",
		    "country": "Indian",
		    "pincode": "201301"
		  }
    }
    
    
    for admin --
    	{
		  "firstName": "Kavita",
		  "lastName": "Choudhary",
		  "contact": "9818223632",
		  "email": "kavita@gmail.com",
		  "password": "1234567",
		  "dateOfBirth": "1994-04-11",
          "address": {
		    "addressLine1": "W/O Tasvir Singh Choudhary",
		    "addressLine2": "",
		    "buildingName": "Surya Appartment",
		    "landMark": "Ramind Showroom",
		    "city": "Noida",
		    "state": "UP",
		    "country": "Indian",
		    "pincode": "201301"
		  }
    }
	*/

	@PostMapping("/users/signup")  // http://localhost:8085/triv/auth/users/signup
	public ResponseEntity<Users> registerUserHandler(@Valid @RequestBody Users user) {

		System.out.println("inside the register User Handler");
		
		
		user.setRole("ROLE_USER");
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		
		Users registeredUser = usersService.registerUsers(user);
		return new ResponseEntity<Users>(registeredUser, HttpStatus.CREATED);
	}

	@PostMapping("/admins/signup") //http://localhost:8085/triv/auth/admins/signup
	public ResponseEntity<Users> registerAdminHandler(@Valid @RequestBody Users user) {

		System.out.println("inside the register Admin Handler");
		
		user.setRole("ROLE_ADMIN");
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		Users registeredAdmin = usersService.registerUsers(user);

		return new ResponseEntity<Users>(registeredAdmin, HttpStatus.CREATED);
	}
	
	
	
	
	
	
	
	@GetMapping("/users/signin") //http://localhost:8085/triv/auth/users/signin
	public ResponseEntity<Users> loginUserHandler(Authentication auth){
		
		Users user = usersService.getUserByEmail(auth.getName());
		
		return new ResponseEntity<Users>(user,HttpStatus.OK);			
	}
	
	@GetMapping("/admins/signin") //http://localhost:8085/triv/auth/admins/signin
	public ResponseEntity<Users> loginAdminHandler(Authentication auth){
		
		Users admin = usersService.getUserByEmail(auth.getName());
		
		return new ResponseEntity<Users>(admin,HttpStatus.OK);			
	}

}