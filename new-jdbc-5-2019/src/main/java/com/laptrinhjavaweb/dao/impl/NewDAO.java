package com.laptrinhjavaweb.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.dao.INewDAO;
import com.laptrinhjavaweb.mapper.NewMapper;
import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.paging.Pageble;

public class NewDAO extends AbstractDAO<NewModel> implements INewDAO {
	
	Connection conn = null;
	PreparedStatement statement = null;
	ResultSet resultSet = null;
	/*public Connection getConnection() {
		String url ="jdbc:mysql://localhost:3306/newservlet5_2019";
		String user ="root";
		String password = "123456";
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, user, password);
			return conn;
		} catch (InstantiationException  | IllegalAccessException | ClassNotFoundException |SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}*/
	@Override
	public List<NewModel> findAll(Pageble pageble) {
		/*String url ="jdbc:mysql://localhost:3306/newservlet5_2019";
		String user ="root";
		String password = "123456";
		List<NewModel> lists = new ArrayList<NewModel>();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, user, password);
			String sql = "SELECT * FROM news";
			statement = conn.prepareStatement(sql);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				NewModel newModel = new NewModel();
				newModel.setId(resultSet.getLong("id"));
				newModel.setTitle(resultSet.getString("title"));
				newModel.setShortDescription(resultSet.getString("shortdescription"));
				lists.add(newModel);
			}
			return lists;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;*/
		StringBuilder sql = new StringBuilder("SELECT * FROM news");
		if(pageble.getSorter()!=null) {
			sql.append(" ORDER BY "+pageble.getSorter().getSortName()+" "+pageble.getSorter().getSortBy()+"");
		}if(pageble.getOffSet() != null && pageble.getLimit() != null) {
			sql.append(" LIMIT "+pageble.getOffSet()+","+pageble.getLimit()+"");
		}
		return query(sql.toString(), new NewMapper());
	
	}

	public Long save(NewModel newModel) {
		/*String url ="jdbc:mysql://localhost:3306/newservlet5_2019";
		String user ="root";
		String password = "123456";
		ResultSet resultSet = null;
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conn = DriverManager.getConnection(url, user, password);
				String sql = "INSERT INTO news(title,shortdescription) VALUES(?,?)";
				statement = conn.prepareStatement(sql);
				conn.setAutoCommit(false);
				statement.setString(1,newModel.getTitle());
				statement.setString(2,newModel.getShortDescription());
				statement.executeUpdate();
				conn.commit();
				resultSet = statement.getGeneratedKeys();
				if (resultSet.next()){
					return  resultSet.getLong(1);
				}
				return null;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;*/
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO news(title,shortdescription,content,thumbnail,categoryid,createddate,createdby)");
		sql.append(" VALUES(?,?,?,?,?,?,?)");
		return insert(sql.toString(), newModel.getTitle(),newModel.getShortDescription(),newModel.getContent()
				,newModel.getThumbnail(),newModel.getCategoryId(),newModel.getCreatedDate(),newModel.getCreatedBy());
		
	}

	@Override
	public NewModel findOne(Long id) {
		String sql = "SELECT * FROM news WHERE id = ?";
		List<NewModel> news = query(sql, new NewMapper(), id);
		return news.isEmpty() ? null : news.get(0);
	}

	@Override
	public void update(NewModel updateNew) {
		StringBuilder sql = new StringBuilder("UPDATE news");
		sql.append(" SET title = ?, shortdescription = ?, content = ?, thumbnail = ?, categoryid =?");
		sql.append(" WHERE id = ?");
		update(sql.toString(), updateNew.getTitle(),updateNew.getShortDescription(),updateNew.getContent(),updateNew.getThumbnail(),updateNew.getCategoryId(),updateNew.getId());
		
	}

	@Override
	public void delete(long id) {
		String sql = "DELETE FROM news WHERE id = ?";
		delete(sql, id);	
	}

	@Override
	public int getTotalItem() {
		String sql ="SELECT count(*) FROM news";
		return count(sql);
	}

	


	

	

}
