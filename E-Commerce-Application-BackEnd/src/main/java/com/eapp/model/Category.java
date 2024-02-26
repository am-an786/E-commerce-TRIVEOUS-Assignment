package com.eapp.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
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
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;
	
	@Column(unique = true,nullable = false)
	private String categoryName;
	
	private String categoryDescription;
	
	@Column(columnDefinition = "boolean default true")
	private Boolean active;
	
	@CreationTimestamp
	@Column(nullable = false , updatable = false)
	private LocalDateTime categoryAddedDateTime;
	
	@CreationTimestamp
	@Column(nullable = false)
	private LocalDateTime categoryUpdatedDateTime;
	
	@JsonProperty(access = Access.READ_ONLY)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "category")
	private List<Product> listOfProducts = new ArrayList<>();
	
}
