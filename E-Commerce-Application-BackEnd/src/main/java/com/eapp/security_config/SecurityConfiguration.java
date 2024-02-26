package com.eapp.security_config;


import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class SecurityConfiguration {
	
	//http://localhost:8085/triv/auth/users/signup
	//http://localhost:8085/triv/auth/admins/signup
	//http://localhost:8085/triv/public/all-categories
	//http://localhost:8085/triv/public/get-product-of-specific-category?categoryId=1
	//http://localhost:8085/triv/public/get-product?productId=1
	//http://localhost:8085/triv/public/all-products
	
	public static final String[] PUBLIC_URLS = {"/triv/auth/users/signup",
												"/triv/auth/admins/signup",
												"/triv/public/all-categories",
												"/triv/public/get-product-of-specific-category/**",
												"/triv/public/get-product/**",
												"/triv/public/all-products"					
						};
	
	
	//http://localhost:8085/triv/auth/users/update-user
	public static final String USER_ADMIN_URLS[] = { "/triv/auth/users/signin",
												   "/triv/auth/users/update-user"						   
						};
	
	//http://localhost:8085/triv/users/addProduct-to-cart/{productId}/{quantity}
	//http://localhost:8085/triv/users/getAllProducts-from-cart
	//http://localhost:8085/triv/users/update-quantity-of-product-in-cart/{quantity}
	public static final String USER_URLS[] = {"/triv/users/addProduct-to-cart/{productId}/{quantity}",
											"/triv/users/getAllProducts-from-cart",
											"/triv/users/update-quantity-of-product-in-cart/{quantity}"
	};
	
	//http://localhost:8085/triv/auth/admins/signin
	//http://localhost:8085/triv/admins/delete-user
	//http://localhost:8085/triv/admins/save-product
	//http://localhost:8085/triv/admins/save-category
	public static final String ADMIN_URLS[] = {"/triv/auth/admins/signin",
											"/triv/admins/delete-user",
											"/triv/admins/save-category",
											"/triv/admins/save-product"
											
				};
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.cors(cors -> cors.configurationSource(new CorsConfigurationSource() {
			
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				CorsConfiguration cfg = new CorsConfiguration();
				cfg.setAllowedOriginPatterns(Collections.singletonList("*"));
				cfg.setAllowedMethods(Collections.singletonList("*"));
				cfg.setAllowCredentials(true);
				cfg.setAllowedHeaders(Collections.singletonList("*"));
				cfg.setExposedHeaders(Arrays.asList("Authorization")); // this need to be configure in cors origin if 
														//any ui domain try to use resource of web service
				return cfg;
			}
		}))
		.authorizeHttpRequests(auth -> {
			auth
			  .requestMatchers("/swagger-ui*/**","/v3/api-docs/**").permitAll()
			  .requestMatchers(PUBLIC_URLS).permitAll()
			  .requestMatchers(ADMIN_URLS).hasRole("ADMIN")
			  .requestMatchers(USER_ADMIN_URLS).hasAnyRole("ADMIN","USER")
			  .requestMatchers(USER_URLS).hasRole("USER")// what is ** is showing PathVeriable -----
			  .anyRequest().authenticated();
		})
		.csrf(csrf -> csrf.disable())
		.addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
		.addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)
		.formLogin(Customizer.withDefaults())
		.httpBasic(Customizer.withDefaults());
		
		return http.build();	
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); 
		// this is one of the hashing technique and recommendated one
	}
	
}
