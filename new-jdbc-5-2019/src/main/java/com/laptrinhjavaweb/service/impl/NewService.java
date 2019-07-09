package com.laptrinhjavaweb.service.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.ICategoryDAO;
import com.laptrinhjavaweb.dao.INewDAO;
import com.laptrinhjavaweb.model.CategoryModel;
import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.service.INewService;

public class NewService implements INewService {
	
	@Inject ICategoryDAO categoryDAO;
	
	@Inject INewDAO newDAO;
	
	@Override
	public List<NewModel> findAll(Pageble pageble){
		return newDAO.findAll(pageble);
	}

	@Override
	public NewModel save(NewModel newModel) {
		newModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		newModel.setModifiedDate(new Timestamp(System.currentTimeMillis()) );
		CategoryModel category = categoryDAO.findOneByCategoryCode(newModel.getCategoryCode());
		newModel.setCategoryId(category.getId());
		Long newId = newDAO.save(newModel);
		return newDAO.findOne(newId);
	}

	@Override
	public NewModel update(NewModel updateNew) {
			updateNew.setModifiedDate(new Timestamp(new Date().getTime()));
			CategoryModel category = categoryDAO.findOneByCategoryCode(updateNew.getCategoryCode());
			updateNew.setCategoryId(category.getId());
			newDAO.update(updateNew);
		return newDAO.findOne(updateNew.getId());
		
	}

	@Override
	public void delete(long[] ids) {
		for(int i =0 ; i < ids.length; i++) {
			newDAO.delete(ids[i]);
		}
		
	}

	@Override
	public int getTotalItem() {
		return newDAO.getTotalItem();
	}

	@Override
	public NewModel findOne(long id) {
		NewModel news = newDAO.findOne(id);
		CategoryModel category = categoryDAO.findOne(news.getCategoryId());
		news.setCategoryCode(category.getCode());
		return news;
	}

	

}
