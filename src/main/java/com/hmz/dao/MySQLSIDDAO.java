package com.hmz.dao;

import com.hmz.collections.Vecrray;
import com.hmz.dao.annotations.FieldIgnore;
import com.hmz.dao.annotations.TableName;
import com.hmz.jdbc.DBManager;

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
public class MySQLSIDDAO<M> implements SIDDAO<M> {
	
	private final Class<?> model;
	private final DBManager manager;
	private final String tableName;
	
	public MySQLSIDDAO(Class<?> model, String dbname) {
		this(model, dbname, "localhost", "root", "");
	}
	
	public MySQLSIDDAO(Class<?> model, String dbname, String host, String user, String pass) {
		this.model = model;
		manager = new DBManager(host, dbname, user, pass);
		if(this.model.isAnnotationPresent(TableName.class)) {
			tableName = this.model.getDeclaredAnnotation(TableName.class).value();
		}
		else tableName = model.getSimpleName().toLowerCase();
	}

	@Override
	public List<M> select() {
		List<M> ret = null;
		try {
			manager.connect();
			ret = transform(manager.selectAllFrom(tableName));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			manager.closeConnection();
		}
		return ret;
	}

	@Override
	public M select(int id) {
		List<M> ret = null;
		try {
			manager.connect();
			ret = transform(manager.selectAllFrom(tableName, "id", "" + id));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			manager.closeConnection();
		}
		assert ret != null;
		return ret.get(0);
	}

	@Override
	public int insert(M object) {
		return insert(object, false);
	}
	
	@Override
	public int insert(M object, boolean update) {
		try {
			manager.connect();
			if(update) {
				StringBuilder query = new StringBuilder("UPDATE " + tableName + " SET ");
				for(int i = 0; i<model.getDeclaredFields().length; i++) {
					Field field = model.getDeclaredFields()[i];
					field.setAccessible(true);
					query.append(field.getName()).append("=?,");
					field.setAccessible(false);
				}
				query = new StringBuilder(query.substring(0, query.length() - 2));
				manager.set(query.toString(), getProperties(object));
			}
			else manager.s_insertInto(tableName, (String []) getProperties(object));
			manager.closeConnection();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			manager.closeConnection();
		}
		return 0;
	}

	@Override
	public int delete() {
		try {
			manager.connect();
			manager.deleteAllFrom(tableName);
			manager.closeConnection();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			manager.closeConnection();
		}
		return 0;
	}

	@Override
	public int delete(int id) {
		try {
			manager.connect();
			manager.deleteFrom(tableName, "id", "" + id);
			manager.closeConnection();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			manager.closeConnection();
		}
		return 0;
	}
	
	private List<M> transform(ResultSet resultSet) {
		List<M> ret = new Vecrray<M>();
		try {
			while(resultSet.next()) {
				M instance = (M) model.getDeclaredConstructor().newInstance();
				int i = 1;
				for(Field field : model.getDeclaredFields()) {
					if(!field.isAnnotationPresent(FieldIgnore.class)) {
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
	
	private Object [] getProperties(M data) {
		List<Object> ret = new Vector<>();
		for(Field field : model.getDeclaredFields()) {
			if(!field.isAnnotationPresent(FieldIgnore.class)) {
				try {
					field.setAccessible(true);
					ret.add(field.get(data));
					field.setAccessible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return ret.toArray();
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
