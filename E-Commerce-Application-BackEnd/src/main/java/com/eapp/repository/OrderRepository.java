package com.eapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eapp.model.Orders;


@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {

	@Query("SELECT o FROM Orders o WHERE o.orderId = :orderId AND o.user.email = :userEmail")
    Optional<Orders> findByOrderIdAndUser(
    			@Param("orderId") Integer orderId, 
    			@Param("userEmail") String userEmail
    			);
	
	@Query("SELECT o FROM Orders o WHERE o.user.email = :email")
	List<Orders> findOrdersByUserEmail(@Param("email") String email);
}
