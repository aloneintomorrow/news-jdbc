package com.laptrinhjavaweb.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.ICategoryDAO;
import com.laptrinhjavaweb.model.CategoryModel;
import com.laptrinhjavaweb.service.ICategoryService;

public class CategoryService implements ICategoryService {

	@Inject ICategoryDAO categoryDAO;
	
	@Override
	public List<CategoryModel> findAll() {
		return categoryDAO.findAll();
	}

	@Override
	public CategoryModel findOne(long id) {
		return categoryDAO.findOne(id);
	}

}
