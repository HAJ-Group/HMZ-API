package com.hmz.dao;

import com.hmz.collections.Vecrray;
import com.hmz.dao.annotations.FieldIgnore;
import com.hmz.dao.annotations.TableName;
import com.hmz.jdbc.DBManager;
import com.hmz.operations.ListConverter;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

/**
 * 
 * @author hamza
 *
 * @param <M>
 */
@SuppressWarnings("unchecked")
public class MySQLLSDAO<M> implements LSDAO<M> {
	
	private final Class<M> model;
	private final DBManager manager;
	private final String tableName;
	
	public MySQLLSDAO(Class<M> model, String dbname) {
		this(model, dbname, "localhost", "root", "");
	}
	
	public MySQLLSDAO(Class<M> model, String dbname, String host, String user, String pass) {
		this.model = model;
		manager = new DBManager(host, dbname, user, pass);
		if(this.model.isAnnotationPresent(TableName.class)) {
			tableName = this.model.getDeclaredAnnotation(TableName.class).value();
		}
		else tableName = model.getSimpleName().toLowerCase();
	}

	@Override
	public List<M> load() {
		List<M> ret = null;
		try {
			manager.connect();
			ResultSet resultSet = manager.selectAllFrom(tableName);
			ret = transform(resultSet);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			manager.closeConnection();
		}
		return ret;
	}

	@Override
	public void save(List<M> data) {
		try {
			manager.connect();
			try {
				manager.deleteAllFrom(tableName);
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
			for(M d : data) {
				if(model.isAnnotationPresent(TableName.class))
				manager.s_insertInto(model.getSimpleName().toLowerCase(), getProperties(d));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			manager.closeConnection();
		}
	}
	
	private List<M> transform(ResultSet resultSet) {
		List<M> ret = new Vecrray<M>();
		try {
			while(resultSet.next()) {
				M instance = model.getDeclaredConstructor().newInstance();
				int i = 1;
				for(Field field : model.getDeclaredFields()) {
					if(field.isAnnotationPresent(FieldIgnore.class)) {
						if(resultSet.getObject(i) != null) {
							field.setAccessible(true);
							field.set(instance, resultSet.getObject(i));
							field.setAccessible(false);
						}
						i++;
					}
					
				}
				ret.add(instance);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	private String [] getProperties(M data) {
		List<String> ret = new Vector<String>();
		for(Field field : model.getDeclaredFields()) {
			if(!field.isAnnotationPresent(FieldIgnore.class)) {
				try {
					field.setAccessible(true);
					ret.add("" + field.get(data));
					field.setAccessible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return ListConverter.toStringArray(ret);
	}
	
	public DBManager getManager() {
		return manager;
	}
	
	public String getTableName() {
		return tableName;
	}
	
	public Class<?> getModel() {
		return model;
	}

}
