package com.hmz.dao.repository;

import com.hmz.config.ConfigLoader;
import com.hmz.config.PropertiesConfigLoader;
import com.hmz.dao.annotations.FieldIgnore;
import com.hmz.dao.annotations.Id;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.List;
import java.util.Vector;

@SuppressWarnings("unchecked")
public class JDBCRepository<T> implements Repository<T> {

	private final ConfigLoader config;
	private final Class<?> cls;
	private String error_message;
	
	public JDBCRepository(Class<T> cls) {
		this(cls, new PropertiesConfigLoader());
	}

	public JDBCRepository(Class<T> cls, ConfigLoader config) {
		this.cls = cls;
		this.config = config;
	}
	
	protected Connection createConnection() {
		Connection ret = null;
		String url = "jdbc:" + config.getProperty("service") 
					 + "://" + config.getProperty("host") 
					 + ":" + config.getProperty("port")
					 + "/" + config.getProperty("db");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			ret = DriverManager.getConnection(url, config.getProperty("username"), config.getProperty("password"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	@Override
	public List<T> findAll() {
		List<T> ret = new Vector<>();
		Connection connection = createConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM " + cls.getSimpleName().toLowerCase());
			while(result.next()) {
				try {
					T instance = (T) cls.getDeclaredConstructor().newInstance();
					for(int i = 1; i <= cls.getDeclaredFields().length; i++) {
						Field field = cls.getDeclaredFields()[i - 1];
						if(!field.isAnnotationPresent(FieldIgnore.class)) {
							field.setAccessible(true);
							field.set(instance, result.getObject(i));
							field.setAccessible(false);
						}
					}
					ret.add(instance);
				} catch (Exception e) {
					error_message = e.getMessage();
					// e.printStackTrace();
				}
			}
			connection.close();
			statement.close();
			result.close();
		} catch (SQLException e) {
			error_message = e.getMessage();
			// e.printStackTrace();
		}
		return ret;
	}

	@Override
	public T findById(int id) {
		T ret = null;
		for(T data : findAll()) {
			try {
				for(Field field : cls.getDeclaredFields()) {
					if(field.isAnnotationPresent(Id.class)) {
						field.setAccessible(true);
						if(field.get(data).equals(id)) {
							ret = data;
						}
						field.setAccessible(false);
					}
				}
			} catch (Exception e) {
				error_message = e.getMessage();
				// e.printStackTrace();
			}
		}
		return ret;
	}

	@Override
	public T save(T data) {
		boolean update = false;
		Connection connection = createConnection();
		StringBuilder query = new StringBuilder();
		int id = 0;
		for(Field field : cls.getDeclaredFields()) {
			field.setAccessible(true);
			try {
				if(field.isAnnotationPresent(Id.class)) {
					id = field.getInt(data);
					if(findById(id) != null) {
						query = new StringBuilder("UPDATE " + cls.getSimpleName().toLowerCase() + " SET ");
						update = true;
					}
					else {
						query = new StringBuilder("INSERT INTO " + cls.getSimpleName().toLowerCase() + " VALUES(NULL, ");
					}
				} else if(!field.isAnnotationPresent(FieldIgnore.class)) {
					if(update) query.append(field.getName()).append("=?,");
					else query.append("?,");
				}
				field.setAccessible(false);
			} catch (Exception e) {
				error_message = e.getMessage();
			}
		}
		query = new StringBuilder(query.substring(0, query.length() - 1));
		if(update) {
			query.append(" WHERE id=").append(id);
		} else {
			query.append(")");
		}

		try  {
			PreparedStatement statement = connection.prepareStatement(query.toString());
			for(int i = 1 ; i< cls.getDeclaredFields().length; i++) {
				Field field = cls.getDeclaredFields()[i];
				field.setAccessible(true);
				if(!field.isAnnotationPresent(FieldIgnore.class)) {
					statement.setObject(i, field.get(data));
				}
				field.setAccessible(false);
			}
			statement.executeUpdate();
			connection.close();
			statement.close();
		} catch (Exception e) {
			// e.printStackTrace();
			error_message = e.getMessage();
		}
		return null;
	}

	@Override
	public void deleteAll() {
		try {
			Connection connection = createConnection();
			Statement statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM " + cls.getSimpleName().toLowerCase());
			statement.close();
			connection.close();
		} catch (Exception e) {
			error_message = e.getMessage();
		}
	}

	@Override
	public void deleteById(int id) {
		try {
			Connection connection = createConnection();
			Statement statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM " + cls.getSimpleName().toLowerCase() + " WHERE id=" + id);
			statement.close();
			connection.close();
		} catch (Exception e) {
			error_message = e.getMessage();
		}
	}
	
	public String getError_message() {
		return error_message;
	}

}
