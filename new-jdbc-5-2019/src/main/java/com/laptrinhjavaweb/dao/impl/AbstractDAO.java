package com.laptrinhjavaweb.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.laptrinhjavaweb.dao.GenericDAO;
import com.laptrinhjavaweb.mapper.RowMapper;

public class AbstractDAO<T> implements GenericDAO<T> {

	public Connection getConnection() {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
		try {
			Class.forName(resourceBundle.getString("driverName")).newInstance();
			String url =resourceBundle.getString("url");
			String user =resourceBundle.getString("user");
			String password = resourceBundle.getString("password");
			return DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<T> lists = new ArrayList<T>();
		try {
		connection = getConnection();
		connection.setAutoCommit(false);
		statement = connection.prepareStatement(sql);
		setParameter(statement, parameters);
		resultSet = statement.executeQuery();
		while(resultSet.next()) {
			lists.add(rowMapper.mapRow(resultSet));
		}
		connection.commit();
		return lists;
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				if(connection !=null) {
					connection.close();
				}if(statement != null) {
					statement.close();
				}if(resultSet !=null) {
					resultSet.close();
				}
			} catch (SQLException e) {

			}
		}
		return null;
		
		
		
	}

	@Override
	public Long insert(String sql, Object... parameters) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
		Long id = null;
		connection = getConnection();
		connection.setAutoCommit(false);
		statement = connection.prepareStatement(sql, statement.RETURN_GENERATED_KEYS);
		setParameter(statement, parameters);
		statement.executeUpdate();
		resultSet = statement.getGeneratedKeys();
		if(resultSet.next()) {
			id= resultSet.getLong(1);
		}
		connection.commit();
		return id;
		} catch (SQLException e) {
			if(connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}finally {
			try {
			if(connection !=null) {
				connection.close();
			}if(statement != null) {
				statement.close();
			}if(resultSet !=null) {
				resultSet.close();
				}
			} catch (SQLException e) {

			}
		}
		return null;
	}

	public void setParameter(PreparedStatement statement, Object... parameters) {
	try {
		for(int i =0; i < parameters.length; i++) {
			Object parameter = parameters[i];
			int index = i + 1;
			if(parameter instanceof Long) {
				statement.setLong(index, (Long) parameter);
			}
			else if (parameter instanceof String) {
				statement.setString(index, (String) parameter);
			}else if (parameter instanceof Integer) {
				statement.setInt(index, (int) parameter);	
			}else if(parameter instanceof Timestamp) {
				statement.setTimestamp(index, (Timestamp)parameter);
			}
		}
		}
		catch(Exception e) {
		 
		}

}

	@Override
	public void update(String sql, Object... parameters) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
		connection = getConnection();
		connection.setAutoCommit(false);
		statement = connection.prepareStatement(sql);
		setParameter(statement, parameters);
		statement.executeUpdate();
		connection.commit();
		} catch (SQLException e) {
			if(connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e.printStackTrace();
				}
			e.printStackTrace();
		}	
	}finally {
		try {
		if(connection != null) {
			connection.close();
		}
		if(statement != null) {
			statement.close();
		}
	}catch (Exception e) {
		// TODO: handle exception
	}
	}
	}

	@Override
	public void delete(String sql, Object... parameters) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
		connection = getConnection();
		connection.setAutoCommit(false);
		statement = connection.prepareStatement(sql);
		setParameter(statement, parameters);
		statement.executeUpdate();
		connection.commit();
		} catch (SQLException e) {
			if(connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e.printStackTrace();
				}
			e.printStackTrace();
		}	
	}finally {
		try {
		if(connection != null) {
			connection.close();
		}
		if(statement != null) {
			statement.close();
		}
		if(resultSet != null) {
			resultSet.close();
		}
	}catch (Exception e) {
		// TODO: handle exception
	}
	}
		
	}

	@Override
	public int count(String sql) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
		int id = 0;
		connection = getConnection();
		connection.setAutoCommit(false);
		statement = connection.prepareStatement(sql);
		resultSet = statement.executeQuery();
		if(resultSet.next()) {
			id = resultSet.getInt(1);
		}
		return id;
		}catch (SQLException e) {
			return 0;
	}finally {
		try {
		if(connection != null) {
			connection.close();
		}
		if(statement != null) {
			statement.close();
		}
		if(resultSet != null) {
			resultSet.close();
		}
	}catch (Exception e) {
		// TODO: handle exception
	}
	}
	}
}
