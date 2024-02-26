package com.eapp.security_config;


import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTokenGeneratorFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("inside doFilter....");
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (null != authentication) {
			
			System.out.println("auth.getAuthorities " + authentication.getAuthorities());
			
			SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes());
							// which we doesn't share for per user
			
			String jwt = Jwts.builder()
					.setIssuer("Masai_Student")
					.setSubject("JWT Token")
					.claim("username", authentication.getName())
					.claim("authorities", populateAuthorities(authentication.getAuthorities()))
					.setIssuedAt(new Date())
					.setExpiration(new Date(new Date().getTime() + 30000000)) // expiration time of 8 hours
					.signWith(key).compact();
					
			
			response.setHeader(SecurityConstants.JWT_HEADER, jwt);
			
		}
		
		filterChain.doFilter(request, response);
	}
	

	private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
		Set<String> authoritiesSet = new HashSet<>();
		for (GrantedAuthority authority : collection) {
			authoritiesSet.add(authority.getAuthority());
		}
		return String.join(",", authoritiesSet);
	}
	
	
	//this make sure that this filter will execute only for first time when client call the api /signIn at first time
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
			
		       boolean result = request.getServletPath().equals("/triv/auth/users/signin") 
		    		   || request.getServletPath().equals("/triv/auth/admins/signin");
		    		   
		       return !result;
		}

}
