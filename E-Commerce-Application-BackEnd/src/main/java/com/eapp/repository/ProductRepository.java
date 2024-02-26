package com.eapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eapp.model.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	public Optional<Product> findByUniqueName(String uniqueName);
	
	@Query("select p from Product p where p.category.id = :categoryId")
	public List<Product> findAllProductsByCategoryId(@Param("categoryId") Integer categoryId);
	
}