package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.model.NewModel;

public class NewMapper implements RowMapper<NewModel> {

	@Override
	public NewModel mapRow(ResultSet rs) {
		try {
			NewModel model = new NewModel();
			model.setId(rs.getLong("id"));
			model.setTitle(rs.getString("title"));
			model.setContent(rs.getString("content"));
			model.setShortDescription(rs.getString("shortdescription"));
			model.setThumbnail(rs.getString("thumbnail"));
			model.setCategoryId(rs.getLong("categoryid"));
			model.setCreatedDate(rs.getTimestamp("createddate"));
			model.setCreatedBy(rs.getString("createdby"));
			if(rs.getTimestamp("modifieddate")!= null) {
			model.setModifiedDate(rs.getTimestamp("modifieddate"));
			}
			if(rs.getString("modifiedby")!= null) {
			model.setModifiedBy(rs.getString("modifiedby"));
			}
			return model;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	
}
