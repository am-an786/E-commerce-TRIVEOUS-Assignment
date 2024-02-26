package com.eapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eapp.exception.DataNotFoundException;
import com.eapp.exception.DuplicateDataException;
import com.eapp.model.Category;
import com.eapp.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepo;
	
	@Override
	public Category addCategory(Category category) throws DuplicateDataException, IllegalArgumentException {
		if(category==null) {
			throw new IllegalArgumentException("Invalid Category details "+ category);
		}
		
		Optional<Category> opt = categoryRepo.findByCategoryName(category.getCategoryName());
		if(opt.isPresent()) {
			throw new DuplicateDataException("Duplicate Data Exception means Category is already exits of categoryName");
		}
		
		categoryRepo.save(category);
		
		return category;
	}

	@Override
	public List<Category> getAllCategoryWithProducts() throws DataNotFoundException {
		
		List<Category> categories = categoryRepo.findAll();
		
		if(categories.isEmpty()) {
			throw new DataNotFoundException("No Category Found in Record");
		}
		
		return categories;
	}

}
