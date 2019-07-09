package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.paging.Pageble;

public interface INewService {
	List<NewModel> findAll(Pageble pageble);
	NewModel save(NewModel newModel);
	NewModel update(NewModel updateNew);
	void delete(long[] ids);
	int getTotalItem();
	NewModel findOne(long id);
	
}
