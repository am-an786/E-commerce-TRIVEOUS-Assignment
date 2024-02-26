package com.eapp.model;

import org.hibernate.validator.constraints.UniqueElements;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "Products")
public class Product{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productId;
	
	@JsonIgnore
	@Column(unique = true,nullable = false)
	private String uniqueName;
	        {
		      this.uniqueName = this.productName+"_"+this.brand;
	         }
	
	private String productName;
	
	private String brand;
	
	private String productDescription;
	
	private Double price;
	
	private Integer stockQuantity;
	
	private String image;
	
	@Column(nullable = false)
	private Boolean avaiable;
	
	private String rating;
	
	private Integer manufacturingYear;
	
	private Integer manufacturingMonth;
	
	@JsonProperty(access = Access.READ_ONLY)
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Category category;
	
	 
}
