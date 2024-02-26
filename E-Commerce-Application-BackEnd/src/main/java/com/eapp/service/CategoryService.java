package com.eapp.service;


import java.util.List;

import com.eapp.exception.DataNotFoundException;
import com.eapp.exception.DuplicateDataException;
import com.eapp.model.Category;

public interface CategoryService {

	public Category addCategory(Category category) throws DuplicateDataException,IllegalArgumentException;
	
	public List<Category> getAllCategoryWithProducts() throws DataNotFoundException;
	
	
}
