package com.eapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eapp.model.Cart;
import com.eapp.model.Product;

import jakarta.transaction.Transactional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

	 @Query("SELECT p FROM Cart c JOIN c.cartProducts p "
	 		+ "WHERE p.productId = :productId AND "
	 		+ "c.cartId = :cartId "
	 		+ "AND c.user.email = :email")
	    public Optional<Product> findProductByIdAndCartIdAndUserEmail(
	    		@Param("productId") Integer productId, 
	    		@Param("cartId") Integer cartId,
	    		@Param("email") String email
	    		);
	 
	 	@Transactional
	    @Modifying
	    @Query("UPDATE Cart c SET c.cartProducts = :updatedProducts WHERE c.cartId = :cartId")
	    public void deleteProductFromCart(@Param("cartId") Integer cartId, @Param("updatedProducts") List<Product> updatedProducts);
	 	
	 	
	 	@Query("SELECT c FROM Cart c JOIN FETCH c.cartProducts WHERE c.user.email = :email")
	    public Optional<Cart> findAllProductsOfCartByUserEmail(@Param("email") String email);
	 	
	 	
	 	 @Transactional
	     @Modifying
	     @Query("UPDATE Cart c SET c.cartQuantity = :quantity WHERE c.user.email = :email")
	     void updateQuantityForAllProductsInCart(@Param("email") String email, @Param("quantity") Integer quantity);
	 	
}
