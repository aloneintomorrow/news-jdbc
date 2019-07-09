package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.paging.Pageble;

public interface INewDAO {
	  List<NewModel> findAll(Pageble pageble);
	  Long save(NewModel newModel);
	  NewModel findOne(Long id);
	  void update(NewModel updateNew);
	  void delete(long id);
	  int getTotalItem();
	 
}
